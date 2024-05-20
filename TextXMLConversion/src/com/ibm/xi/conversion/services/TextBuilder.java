package com.ibm.xi.conversion.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ibm.xi.conversion.utility.ArrayElementIndex;
import com.ibm.xi.conversion.utility.ConversionException;
import com.ibm.xi.conversion.utility.ParameterHelper;
import com.ibm.xi.conversion.utility.Utility;

public class TextBuilder {

	private Document doc;
	private String documentNodeName;
	private String csvString;
	private ParameterHelper param;
	private String lastHeader = "";
	private static final String nl = "\r\n";
	
	public TextBuilder(InputStream input, ParameterHelper param) throws ConversionException {
		try {
			this.setParam(param);
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(input);
			setDoc(documentBuilder.newDocument());
			document.getDocumentElement().normalize();

			setDocumentNodeName(document.getDocumentElement().getNodeName());

			if (document.hasChildNodes())
				subNodeRdWt(document.getChildNodes());

			if (this.getParam().getBoolParameter("convertCSV", "true"))
				setCsvString(csvConversion());

		} catch (NullPointerException | ParserConfigurationException | SAXException | IOException e) {
			new ConversionException(e.getMessage(), e);
		}

	}
	
	/**
	 * @return the documentNodeName
	 */
	public String getDocumentNodeName() {
		return documentNodeName;
	}

	/**
	 * @param documentNodeName the documentNodeName to set
	 */
	public void setDocumentNodeName(String documentNodeName) {
		this.documentNodeName = documentNodeName;
	}

	/**
	 * @return the doc
	 */
	public Document getDoc() {
		return doc;
	}

	/**
	 * @param doc the doc to set
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}

	/**
	 * @return the csvString
	 */
	public String getCsvString() {
		return csvString;
	}

	/**
	 * @return the csvStringByte
	 */
	public byte[] getCsvByte() {
		return csvString.getBytes();
	}
	
	/**
	 * @return the csvStringByte
	 * @throws UnsupportedEncodingException 
	 */
	public byte[] getCsvByte(String charsetName) throws UnsupportedEncodingException {
		return csvString.getBytes(charsetName);
	}

	/**
	 * @param csvString the csvString to set
	 */
	public void setCsvString(String csvString) {
		this.csvString = csvString;
	}

	/**
	 * @return the param
	 */
	public ParameterHelper getParam() {
		return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(ParameterHelper param) {
		this.param = param;
	}

	/**
	 * @return the lastHeader
	 */
	public String getLastHeader() {
		return lastHeader;
	}

	/**
	 * @param lastHeader the lastHeader to set
	 */
	public void setLastHeader(String lastHeader) {
		this.lastHeader = lastHeader;
	}

	private String csvConversion() throws ConversionException {
		String[] recordsetStructure = this.getParam().getMandatoryParameter("recordsetStructure").split(",");
		StringBuilder returnString = new StringBuilder("");
		ArrayElementIndex arrayElementIndex = new ArrayElementIndex();
		if (doc.hasChildNodes()) {
			Node rootNode = doc.getElementsByTagName(documentNodeName).item(0);
			NodeList nodeList = rootNode.getChildNodes();
			int totalNode = nodeList.getLength() - 1;
			for (int count = 0; count < nodeList.getLength(); count++) {
				Node node = nodeList.item(count);
				if (node.getNodeType() == Node.ELEMENT_NODE
						&& arrayElementIndex.findIndex(recordsetStructure, node.getNodeName()) > -1) {
					String nodeName = node.getNodeName();
					String beginSeparator = getParam().getGParameter(nodeName, "beginSeparator", "");
					String endSeparator = getParam().getGParameter(nodeName, "endSeparator", "'nl'");
					beginSeparator = getSeparator(beginSeparator);
					endSeparator = getSeparator(endSeparator);
					String fieldFixedLengths = getParam().getParameter(nodeName + ".fieldFixedLengths");
					returnString.append(beginSeparator);
					if (fieldFixedLengths == null)
						returnString.append(getNodeTextCSV(node.getChildNodes(), nodeName));
					else
						returnString.append(getNodeTextFixedLength(node.getChildNodes(), nodeName, fieldFixedLengths));
					if (count != totalNode)
						returnString.append(endSeparator);
				}
			}
		}
		return returnString.toString();
	}

	private String getNodeTextCSV(NodeList nodeList, String parentNode) throws ConversionException {
		String dataString = "";
		String headerString = "";
		Utility ut = new Utility();
		String fieldSepator = getParam().getParameter(parentNode + ".fieldSeparator");
		if (fieldSepator == null)
			fieldSepator = getParam().getParameter("fieldSeparator", ",");
		String encloser = getParam().getParameter(parentNode + ".enclosureSign");
		if (encloser == null)
			encloser = getParam().getParameter("enclosureSign", "");
		if (encloser.equals(""))
			getParam().checkParamValidValues("enclosureSign", "\",,'");

		boolean headerRequired = this.getParam().getBoolParameter("headerRequired", "false");
		int totalNode = nodeList.getLength() - 1;
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node node = nodeList.item(count);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (headerRequired)
					headerString = headerString + node.getNodeName();
				dataString = dataString + ut.appendEncloser(node.getTextContent(), fieldSepator, encloser);
				String attributeData = "";
				if (node.hasAttributes()) {
					String attributeHeader = "";
					String attributeSeparator = getParam().getParameter("attributeSeparator");
					if (attributeSeparator == null)
						attributeSeparator = getParam().getParameter(parentNode + ".attributeSeparator", fieldSepator);
					NamedNodeMap nodeMap = node.getAttributes();
					int totalNodeMap = nodeMap.getLength() - 1;
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node atNode = nodeMap.item(i);
						if (headerRequired)
							attributeHeader = attributeHeader + node.getNodeName() + "." + atNode.getNodeName();
						attributeData = attributeData
								+ ut.appendEncloser(atNode.getNodeValue(), attributeSeparator, encloser);
						if (i < totalNodeMap) {
							if (headerRequired)
								attributeHeader = attributeHeader + attributeSeparator;
							attributeData = attributeData + attributeSeparator;
						}
					}
					if (headerRequired)
						headerString = headerString + fieldSepator + attributeHeader;
				}
				if (count < totalNode) {
					if (headerRequired)
						headerString = headerString + fieldSepator;
					if (attributeData.equals(""))
						dataString = dataString + fieldSepator;
					else
						dataString = dataString + fieldSepator + attributeData + fieldSepator;
				}
			}
		}
		if (headerRequired) {
			if (getLastHeader().equals(headerString))
				return dataString;
			else {
				String beginSeparator = getParam().getGParameter(parentNode, "beginSeparator", "");
				String endSeparator = getParam().getGParameter(parentNode, "endSeparator", "'nl'");
				beginSeparator = getSeparator(beginSeparator);
				endSeparator = getSeparator(endSeparator);
				setLastHeader(headerString);
				return headerString + endSeparator + beginSeparator + dataString;
			}
		} else
			return dataString;
	}

	private String getNodeTextFixedLength(NodeList nodeList, String parentNode, String fieldFixedLengths)
			throws ConversionException {
		String dataString = "";
		String headerString = "";

		String[] fieldLengths = fieldFixedLengths.split(",");
		String fixedLengthTooShortHandling = getParam().getParameter("fixedLengthTooShortHandling");
		if (fixedLengthTooShortHandling == null)
			fixedLengthTooShortHandling = getParam().getParameter(parentNode + ".fixedLengthTooShortHandling", "Error");

		if (fieldLengths.length > nodeList.getLength())
			throw new ConversionException(
					"For " + parentNode + " fieldFixedLengths specified is less than in payload.");

		boolean headerRequired = this.getParam().getBoolParameter("headerRequired", "false");
		Utility ut = new Utility();
		for (int count = 0; count < fieldLengths.length; count++) {
			Node node = nodeList.item(count);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (headerRequired)
					headerString = headerString
							+ ut.formateText(node.getNodeName(), fieldLengths[count], fixedLengthTooShortHandling);
				dataString = dataString
						+ ut.formateText(node.getTextContent(), fieldLengths[count], fixedLengthTooShortHandling);
			}
		}
		if (headerRequired) {
			if (getLastHeader().equals(headerString))
				return dataString;
			else {
				String beginSeparator = getParam().getGParameter(parentNode, "beginSeparator", "");
				String endSeparator = getParam().getGParameter(parentNode, "endSeparator", "'nl'");
				beginSeparator = getSeparator(beginSeparator);
				endSeparator = getSeparator(endSeparator);
				setLastHeader(headerString);
				return headerString + endSeparator + beginSeparator + dataString;
			}
		} else
			return dataString;
	}

	private void subNodeRdWt(NodeList nodeList) {
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node node = nodeList.item(count);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = doc.createElement(node.getNodeName());
				if (node.getChildNodes().getLength() == 1 && !node.getNodeName().equals(documentNodeName))
					element.setTextContent(node.getTextContent());
				if (node.hasAttributes()) {
					NamedNodeMap nodeMap = node.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node atNode = nodeMap.item(i);
						element.setAttribute(atNode.getNodeName(), atNode.getNodeValue());
					}
				}
				if (node.hasChildNodes()
						&& (node.getChildNodes().getLength() > 1 || node.getNodeName().equals(documentNodeName))) {
					if (node.getNodeName().equals(documentNodeName))
						doc.appendChild(element);
					else
						doc.getDocumentElement().appendChild(element);
					subNodeRdWt(node.getChildNodes());
				} else {
					if (node.getParentNode().getNodeName().equals(documentNodeName))
						doc.getDocumentElement().appendChild(element);
					else
						doc.getDocumentElement().getLastChild().appendChild(element);
				}

			}
		}
	}

	/*private String getBegainSeparator(String nodeName) {
		String endSeparator = getParam().getParameter(nodeName + ".endSeparator");
		if (endSeparator == null)
			endSeparator = getParam().getParameter("endSeparator", nl);
		if (endSeparator.equals("'nl'"))
			endSeparator = nl;
		return endSeparator;
	}

	private String getEndSeparator(String nodeName) {
		String endSeparator = getParam().getParameter(nodeName + ".endSeparator");
		if (endSeparator == null)
			endSeparator = getParam().getParameter("endSeparator", nl);
		if (endSeparator.equals("'nl'"))
			endSeparator = nl;
		return endSeparator;
	}*/
	
	private String getSeparator(String value) {
		return (value.equals("'nl'"))?nl:value;
	}

}
