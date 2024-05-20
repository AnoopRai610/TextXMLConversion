package com.ibm.xi.conversion.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ibm.xi.conversion.utility.ConversionException;
import com.ibm.xi.conversion.utility.ParameterFieldMap;
import com.ibm.xi.conversion.utility.ParameterHelper;
import com.ibm.xi.conversion.utility.Utility;

/**
 * Use to convert data in List to required XML format Its very messy code,
 * please try to understand it before making any change.
 * 
 * @author IBM GBS, IN (AnoopRai)
 * @version 1.0
 */
public class XMLBuilder implements XMLBuilderInterface {
	private List<String> payload;
	private ParameterHelper param;
	private ParameterFieldMap paramFieldMap;
	private Document doc;
	private Element docRootElement;
	private List<String> hierarchy = new ArrayList<>();
	private String root;
	private long indexProcessing = 1;
	private boolean trimContents;
	private boolean ignoreNonKeyFieldRecords;

	@SuppressWarnings("unused")
	private XMLBuilder() {
		super();
	}

	/**
	 * Constructor use to set XML Builder default value
	 * 
	 * @param payload
	 *            Input payload as InputStream
	 * @param param
	 *            Class instance that contains parameters set by user
	 * 
	 */
	public XMLBuilder(InputStream payload, ParameterHelper param) throws ConversionException {
		try {
			this.param = param;

			this.trimContents = param.getBoolParameter("trimContents", "false");
			this.ignoreNonKeyFieldRecords = param.getBoolParameter("ignoreNonKeyFieldRecords", "false");

			this.paramFieldMap = new ParameterFieldMap(param);

			String contentType = this.param.getParameter("Transfer.ContentType", "");
			Utility utility = new Utility();
			if (this.param.hasParameter("Transfer.ContentType"))
				contentType = utility.getValueFromRegex("(.*;*)(charset\\s*=\\s*)(.*?[^;]*)(.*)",
						this.param.getParameter("Transfer.ContentType"), 3, "error").trim();

			this.payload = utility.parseInputstreamtoList(payload, this.param.getParameter("endSeparator", "'nl'"),
					contentType, this.param.getBoolParameter("removeInvalidSpecialChars", "false"));

			this.root = this.param.getParameter("recordSetName", "recordSet");
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = domFactory.newDocumentBuilder();
			this.doc = docBuilder.newDocument();
			doc.setXmlStandalone(true);

		} catch (Exception e) {
			throw new ConversionException(e.getMessage(), e);
		}
	}

	@Override
	public Document creatXML() throws ConversionException {
		if (this.param.getParameter("recordSetName") != null) {
			this.root = this.param.getParameter("recordSetName");
			Element rootElement = this.doc.createElementNS(this.param.getParameter("documentNamespace", ""), this.param.getMandatoryParameter("documentName"));
			this.docRootElement = this.doc.createElementNS(this.param.getParameter("recordSetNamespace",""),this.root);
			creatXMLFromList();
			rootElement.appendChild(this.docRootElement);
			this.doc.appendChild(rootElement);
		} else {
			this.docRootElement = this.doc.createElementNS(this.param.getParameter("documentNamespace", ""), this.param.getMandatoryParameter("documentName"));
			creatXMLFromList();
			this.doc.appendChild(this.docRootElement);
		}
		return this.doc;
	}

	/**
	 * It use to iterate over payload that stored in List
	 * 
	 * @throws ConversionException
	 *             Many reason for this exception. Reasons will be depicted in
	 *             message.
	 */
	private void creatXMLFromList() throws ConversionException {
		if (this.param.getParameter("offsetTop") != null)
			for (int i = 0; i < Integer.parseInt(this.param.getParameter("offsetTop")); i++)
				this.payload.remove(0);
		if (this.param.getParameter("offsetBottom") != null)
			for (int i = 0; i < Integer.parseInt(this.param.getParameter("offsetBottom")); i++)
				this.payload.remove(this.payload.size() - 1);
		for (String data : this.payload) {
			if (!data.isEmpty())
				elementBuilder(data);
			this.indexProcessing += 1;
		}
		this.payload.clear();
	}

	/**
	 * This is most important method that exactly doing the transformation of string
	 * data into XML elements and use to append it into root node.
	 * 
	 * @param data
	 *            Data string
	 */
	private void elementBuilder(String data) throws ConversionException {
		String keyName = getKeyValueName(data);
		// System.out.println(data);
		if (hierarchy.size() == 0)
			hierarchy.add(this.root);
		if (keyName != null) {
			String parent = this.param.getMandatoryParameter(keyName + ".parentNode");
			if (parent.equals(this.root)) {
				hierarchy.clear();
				docRootElement.appendChild(elementSetBuilder(data, keyName));
				hierarchy.add(this.root);
				hierarchy.add(keyName);
			} else {
				if (this.paramFieldMap.getParamFieldMap().containsKey(parent)) {
					if (hierarchy.get(hierarchy.size() - 1).equals(parent)) {
						insertNode(data, parent, keyName);
						hierarchy.add(keyName);
					} else {
						if (hierarchy.get(hierarchy.size() - 1).equals(keyName)) {
							insertNode(data, hierarchy.get(hierarchy.size() - 2), keyName);
						} else {
							int index = hierarchy.lastIndexOf(parent);
							hierarchy.subList(index + 1, hierarchy.size()).clear();
							insertNode(data, parent, keyName);
							if (!hierarchy.get(hierarchy.size() - 1).equals(keyName))
								hierarchy.add(keyName);
						}
					}
				} else {
					if (this.param.getMandatoryParameter(parent + ".startWith").equals(keyName)) {
						insertBlankNode(this.param.getMandatoryParameter(parent + ".parentNode"), parent);
						insertNode(data, parent, keyName);
						if (this.param.getMandatoryParameter(parent + ".parentNode").equals(this.root)) {
							hierarchy.clear();
							hierarchy.add(this.root);
						} else {
							int index = hierarchy.lastIndexOf(parent);
							if (index >= 0) {
								hierarchy.subList(index + 1, hierarchy.size()).clear();
							}
						}
						if (!hierarchy.get(hierarchy.size() - 1).equals(parent))
							hierarchy.add(parent);
						if (!hierarchy.get(hierarchy.size() - 1).equals(keyName))
							hierarchy.add(keyName);
					} else {
						if (hierarchy.get(hierarchy.size() - 1).equals(keyName)) {
							insertNode(data, hierarchy.get(hierarchy.size() - 2), keyName);
						} else {
							int index = hierarchy.lastIndexOf(parent);
							hierarchy.subList(index + 1, hierarchy.size()).clear();
							// System.out.println(data+parent+ keyName);
							insertNode(data, parent, keyName);
							if (!hierarchy.get(hierarchy.size() - 1).equals(keyName))
								hierarchy.add(keyName);
						}
					}
				}
			}

		} else {
			if (ignoreNonKeyFieldRecords)
				return;
			throw new ConversionException(ignoreNonKeyFieldRecords + "No keyFieldValue maintained for data " + data);
		}

	}

	/**
	 * This use to insert node based on data to specific parent node with specific
	 * node name
	 * 
	 * @param data
	 *            Data string
	 * @param parent
	 *            Parent node name
	 * @param keyName
	 *            Node name
	 */
	private void insertNode(String data, String parent, String keyName) throws ConversionException {
		try {
			Node node = this.docRootElement.getLastChild();
			if (node.getNodeName().equals(parent))
				node.appendChild(elementSetBuilder(data, keyName));
			else {
				boolean notFound = true;
				while (node.getLastChild().getNodeType() == Node.ELEMENT_NODE && notFound) {
					if (node.getLastChild().getNodeName().equals(parent)) {
						node.getLastChild().appendChild(elementSetBuilder(data, keyName));
						notFound = false;
					} else
						node = node.getLastChild();
				}
				if (notFound)
					throw new ConversionException(
							"There is some problem with data : " + data + ". The error may be parent : " + parent
									+ " not in the correct root for entity : " + keyName);
			}
		} catch (Exception e) {
			throw new ConversionException(
					"There is some problem with data : " + data + ". \n This may cause due to parentNode " + parent
							+ " is not configured correctly. Exception has been raised by " + e.getClass()
							+ " class. Please find this message for more information: " + e.getMessage(),
					e.getCause());
		}
	}

	/**
	 * This use to insert blank node inside specific parent node with specific node
	 * name
	 * 
	 * @param parent
	 *            Parent node name
	 * @param keyName
	 *            Node name
	 */
	private void insertBlankNode(String parent, String keyName) {
		Node node = this.docRootElement.getLastChild();
		boolean notFound = true;
		if (node != null) {
			if (node.getNodeName().equals(parent)) {
				node.appendChild(this.doc.createElement(keyName));
				notFound = false;
			} else {
				while (node.getLastChild().getNodeType() == Node.ELEMENT_NODE && notFound) {
					if (node.getLastChild().getNodeName().equals(parent)) {
						node.getLastChild().appendChild(this.doc.createElement(keyName));
						notFound = false;
					} else {
						node = node.getLastChild();
						if (node.getChildNodes().getLength() < 1)
							break;
					}
				}
			}
		}
		if (notFound) {
			Element elementRoot = this.doc.createElement(keyName);
			docRootElement.appendChild(elementRoot);
		}
	}

	/**
	 * Its one of the important method that use to get recordSet name based on data.
	 * 
	 * @param data
	 *            Data string
	 * @return recordSet name for data
	 */
	private String getKeyValueName(String data) throws ConversionException {
		if (!this.paramFieldMap.isKeyFieldValueRequire())
			return this.param.getMandatoryParameter("recordsetStructure");
		Map<String, String> keyFieldValueMap = this.paramFieldMap.getKeyFieldValueMap();

		if (this.param.getBoolParameter("keyFieldAtFront", "false")) {
			for (String a : keyFieldValueMap.keySet())
				if (data.startsWith(a))
					return keyFieldValueMap.get(a);
		}
		if (this.param.getParameter("keyFieldAtFront") == null)
			for (String a : keyFieldValueMap.keySet()) {
				if (!a.equals("$DefaultValue$"))
					if (keyNameBasedOnValue(data, keyFieldValueMap.get(a), a,
							(this.paramFieldMap.isGlobalKeyFieldName())
									? this.param.getMandatoryParameter("keyFieldName")
									: this.param.getMandatoryParameter(keyFieldValueMap.get(a) + ".keyFieldName")))
						return keyFieldValueMap.get(a);
			}
		if (keyFieldValueMap.containsKey("$DefaultValue$"))
			return keyFieldValueMap.get("$DefaultValue$");
		return null;
	}

	/**
	 * Return true when data belongs to that specific recordSet. This method
	 * actually segregate the data based on recordSet fieldLength or separator and
	 * find based on its keyfieldValue and keyFieldName
	 * 
	 * @param data
	 * @param recordName
	 *            recordSet Name
	 */
	private boolean keyNameBasedOnValue(String data, String recordName, String keyFieldValue, String keyFieldName) {
		try {
			@SuppressWarnings("unchecked")
			List<String> fields = (List<String>) this.paramFieldMap.getSpecificParamFieldValue(recordName);

			List<String> formatData = this.formatData(data, recordName, false);
			if (formatData.get(fields.indexOf(keyFieldName)).equals(keyFieldValue))
				return true;
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Its one of the important method that use to create element based on recordSet
	 * name and data.
	 * 
	 * @param data
	 *            Data string
	 * @param keyName
	 *            recordName for which field map required with data
	 * @return xml element of recordSet
	 */
	private Element elementSetBuilder(String data, String keyName) throws ConversionException {
		try {
			Element elementRoot = this.doc.createElement(keyName);
			List<String> dataList = formatData(data, keyName, true);
			@SuppressWarnings("unchecked")
			List<String> fieldList = (List<String>) this.paramFieldMap.getParamFieldMap().get(keyName);
			for (int i = 0; i < dataList.size(); i++) {
				if (this.param.getBoolParameter("removeBlankNodes", false) && dataList.get(i).length() == 0)
					continue;
				Element subElement = doc.createElement(fieldList.get(i));
				subElement.setTextContent(dataList.get(i));
				elementRoot.appendChild(subElement);
			}
			return elementRoot;
		} catch (Exception e) {
			throw new ConversionException("Error while processing line :" + this.indexProcessing + ". Please"
					+ " check row with data : " + data + ".\nError description :- " + e.getMessage(), e);
		}
	}

	/**
	 * Its use to format data based on recordSet name and data, and return field to
	 * value list
	 * 
	 * @param data
	 *            Data string
	 * @param keyName
	 *            recordName for which field map required with data
	 * @return field to value list
	 */
	@SuppressWarnings("unchecked")
	private List<String> formatData(String data, String keyName, boolean checkLength) throws ConversionException {
		try {
			List<String> dataFormat = new ArrayList<>();

			if (this.paramFieldMap.isSeparator() || this.paramFieldMap.getKeyFieldSeparatorMap().containsKey(keyName)) { // For
				// System.out.println(this.paramFieldMap.isSeparator()); // fieldSeparators
				String[] dataSplit = csvDataSplitter(data, keyName);
				int i = 0;
				boolean missingLF = this.param.getParameter(keyName + ".missingLastFields", "ignore").equals("ignore")
						? true
						: false;
				List<String> fieldNames = (List<String>) this.paramFieldMap.getParamFieldMap().get(keyName);
				if (!this.param.getBoolParameter(keyName + ".additionalLastFields", "false")
						&& dataSplit.length > fieldNames.size()) {
					throw new ConversionException("File contains more data than fields specified in config for "
							+ keyName + ". Add " + keyName + ".additionalLastFields as true to ignore such cases.");
				}
				for (@SuppressWarnings("unused")
				String field : fieldNames) {
					if (missingLF || !checkLength) {
						if (dataSplit.length <= i)
							dataFormat.add("");
						else
							dataFormat.add((this.trimContents) ? dataSplit[i].trim() : dataSplit[i]);
					} else
						dataFormat.add((this.trimContents) ? dataSplit[i].trim() : dataSplit[i]);

					i += 1;
				}

			} else { // For fixed length flat files
				List<Integer> lengths = (List<Integer>) this.paramFieldMap.getParamLengthMap().get(keyName);
				if (checkLength && !param.getBoolParameter(keyName + ".ignoreExtraLength", "NO")
						&& data.length() != new Utility().listElementSum(lengths)) {
					throw new ConversionException("Length of data record (" + data.length()
							+ ") mismatch with the fixed length (" + new Utility().listElementSum(lengths)
							+ ") maintained in param " + "Exception raised by data record" + data);
				}
				if (param.getBoolParameter(keyName + ".ignoreIncompleteLastFields", "false")) {
					Utility ut = new Utility();
					data = ut.appendSpaceAtEnd(data, Integer.toString(ut.listElementSum(lengths)));
				}
				int j = 0, k = 0;
				for (int i : lengths) {
					k = j + i;
					dataFormat.add((this.trimContents) ? data.substring(j, k).trim() : data.substring(j, k));
					j += i;
				}
			}
			return dataFormat;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ConversionException(
					"Number of fields mentioned in fieldLength/fieldNames is not same as much data come in file. Error at line number "
							+ this.indexProcessing + ". Record :" + data.length() + ":" + data + ". Error is :"
							+ e.getMessage(),
					e);
		} catch (Exception e) {
			throw new ConversionException("Error at line number " + this.indexProcessing + ". Record :" + data.length()
					+ ":" + data + ". Error is :" + e.getMessage(), e);
		}
	}

	private String[] csvDataSplitter(String data, String keyName) throws ConversionException {
		String separator = (this.paramFieldMap.isSeparator()) ? this.param.getMandatoryParameter("fieldSeparator")
				: this.param.getMandatoryParameter(keyName + ".fieldSeparator");
		if (this.param.getParameter("enclosureSign") != null
				|| this.param.getParameter(keyName + ".enclosureSign") != null) {
			String enclosureSign = (this.param.getParameter("enclosureSign") != null)
					? this.param.getMandatoryParameter("enclosureSign")
					: this.param.getMandatoryParameter(keyName + ".enclosureSign");
			List<String> list = new Utility().parseCSVLine(data, separator, enclosureSign);
			String[] result = list.toArray(new String[list.size()]);
			return result;
		} else
			return data.split(separator, -1);
	}

	@Override
	public byte[] getDoctoByte(Document docment) throws ConversionException {
		return new Utility().documentToByte(docment, this.param.getParameter("encoding", "UTF-8"),
				this.param.getParameter("indentFactor"));
	}

}
