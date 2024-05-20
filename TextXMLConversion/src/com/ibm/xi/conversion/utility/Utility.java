package com.ibm.xi.conversion.utility;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * Utility class for some common functions
 * 
 * @author IBM GBS, IN (AnoopRai)
 * @version 1.0
 */
public class Utility {

	public String parseInputstreamtoString(InputStream input) throws IOException {
		return parseInputstreamtoString(input, "", false);
	}

	public String parseInputstreamtoString(InputStream input, String contentType) throws IOException {
		return parseInputstreamtoString(input, contentType, false);
	}

	public String parseInputstreamtoString(InputStream input, boolean removeInvalidSpecialChars) throws IOException {
		return parseInputstreamtoString(input, "", removeInvalidSpecialChars);
	}

	public String parseInputstreamtoString(InputStream input, String contentType, boolean removeInvalidSpecialChars)
			throws IOException {
		String rtn;
		try {
			if (contentType.isEmpty())
				rtn = new String(inputStreamToByte(input, true));
			else
				rtn = new String(inputStreamToByte(input, true), contentType);
			if (removeInvalidSpecialChars) // use only for UTF-8 contents.........
				rtn = rtn.replaceAll("[\uFFFA-\uFFFF]|\u0000|\uFFF9", " ");
		} finally {
			if (input != null)
				input.close();
		}
		return rtn;
	}

	public List<String> parseStringtoList(String sbData, String endSeparator) {
		List<String> inputLines = new ArrayList<String>(Arrays.asList(sbData.split(endSeparator)));
		sbData = new String();
		return inputLines;
	}

	public List<String> parseInputStreamtoList(InputStream input, String contentType, boolean removeInvalidSpecialChars)
			throws IOException {
		// Useful only when endSeparator is System.lineSeparator()
		try {
			ArrayList<String> contents = new ArrayList<String>();
			BufferedReader bufferedReader;
			if (contentType.isEmpty())
				bufferedReader = new BufferedReader(new InputStreamReader(input));
			else
				bufferedReader = new BufferedReader(new InputStreamReader(input, contentType));

			String lineContent;
			while ((lineContent = bufferedReader.readLine()) != null) {
				if (removeInvalidSpecialChars) // use only for UTF-8 contents.........
					lineContent = lineContent.replaceAll("[\uFFFA-\uFFFF]|\u0000|\uFFF9", " ");
				contents.add(lineContent);
			}
			bufferedReader.close();
			System.out.println(contents.size());
			return contents;
		} finally {
			if (input != null)
				input.close();
		}
	}

	/**
	 * Use to parse the Inputstream data into List.
	 * 
	 * @param input        Inputstream
	 * @param endSeparator End separator if any
	 * 
	 */
	public List<String> parseInputstreamtoList(InputStream input, String endSeparator) throws IOException {
		if (endSeparator.equalsIgnoreCase("'nl'"))
			return parseStringtoList(parseInputstreamtoString(input), "\\r?\\n");
		if (endSeparator.equalsIgnoreCase("'tab'"))
			return parseStringtoList(parseInputstreamtoString(input), "\\t");
		return parseStringtoList(parseInputstreamtoString(input), endSeparator);
	}

	/**
	 * Use to parse the Inputstream data into List.
	 * 
	 * @param input                     Inputstream
	 * @param endSeparator              End separator if any
	 * @param contentType               Content type of Inputstream
	 * @param removeInvalidSpecialChars Remove invalid characters (in case of UTF-8)
	 * 
	 */
	public List<String> parseInputstreamtoList(InputStream input, String endSeparator, String contentType,
			boolean removeInvalidSpecialChars) throws IOException {
		if (endSeparator.equalsIgnoreCase("'nl'"))
			return parseStringtoList(parseInputstreamtoString(input, contentType, removeInvalidSpecialChars),
					"\\r?\\n");
		if (endSeparator.equalsIgnoreCase("'tab'"))
			return parseStringtoList(parseInputstreamtoString(input, contentType, removeInvalidSpecialChars), "\\t");
		return parseStringtoList(parseInputstreamtoString(input, contentType, removeInvalidSpecialChars), endSeparator);
	}

	/**
	 * Use to check duplicate value in an array of string
	 * 
	 * @param data String array
	 * @return boolean true if no duplicate entry found and false if any duplicate
	 *         value found
	 *
	 */
	public boolean checkDuplicateFromArray(String... data) {
		ArrayList<String> temp = new ArrayList<String>();
		for (String d : data) {
			if (temp.contains(d))
				return false;
			else
				temp.add(d);
		}
		temp.clear();
		return true;
	}

	/**
	 * Use to get duplicate value in an array of string
	 * 
	 * @param data String array
	 * @return String value of duplicate of first duplicate value or null for no
	 *         duplicate
	 *
	 */
	public String getFirstDuplicateFromArray(String... data) {
		ArrayList<String> temp = new ArrayList<String>();
		for (String d : data) {
			if (temp.contains(d))
				return d;
			else
				temp.add(d);
		}
		temp.clear();
		return null;
	}

	public Object lastEntityOfList(List<Object> list) {
		if (!list.isEmpty()) {
			return list.get(list.size() - 1);
		}
		return null;

	}

	/**
	 * Use to transform the {@link java.io.InputStream} into byte[]
	 * 
	 * @param inputStream Inputstream
	 * @param closeStream Stream need to close after transformation to byte[]
	 * @return byte array of {@link java.io.InputStream}
	 * @throws IOException
	 */
	public byte[] inputStreamToByte(InputStream inputStream, boolean closeStream) throws IOException {
		try {
			int len = inputStream.available();
			byte[] bytes = new byte[len];
			inputStream.read(bytes, 0, len);
			return bytes;
		} finally {
			if (closeStream && inputStream != null) // To release resources
				inputStream.close();
		}
	}

	/**
	 * Use to transform the org.w3c.dom.Document into byte array
	 * 
	 * @return byte array of org.w3c.dom.Document
	 */
	public byte[] documentToByte(Document doc, String encoding, String indentFactor) throws ConversionException {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer();
			if (indentFactor != null)
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", indentFactor);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(bos);

			transformer.transform(source, result);
			return bos.toByteArray();
		} catch (TransformerException e) {
			throw new ConversionException("Unable to transform XML Document to byte array : " + e.getMessage());
		}
	}

	/** Use to transform the org.w3c.dom.Document into file */
	public void documentToFile(Document doc, String encoding, File file, String indentFactor)
			throws ConversionException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			if (indentFactor != null)
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", indentFactor);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw new ConversionException("Unable to transform XML Document to byte array : " + e.getMessage());
		}
	}

	/**
	 * Use to parse CSV content.
	 * 
	 * @param line          Data string
	 * @param separator     Separator as string
	 * @param enclosureSign enclosure Sign
	 * @return List of parsed CSV data
	 */
	public List<String> parseCSVLine(String line, String separator, String enclosureSign) throws ConversionException {
		List<String> result = new ArrayList<>();
		if (line == null || line.trim().isEmpty())
			return result;
		int lenLine = line.length();
		int lenSep = separator.length();
		int lenEnc = enclosureSign.length();
		StringBuilder sb = new StringBuilder();
		boolean startEnclosure = false;
		for (int i = 0; i <= (lenLine - lenEnc); i++) {
			if (startEnclosure) {
				if (line.substring(i, i + lenEnc).equals(enclosureSign)) {
					if ((lenLine - lenEnc) != i)
						if (line.substring(i + lenEnc, i + lenEnc + lenSep).equals(separator)) {
							result.add(sb.toString());
							sb = new StringBuilder();
							i += lenSep;
							startEnclosure = false;
						} else
							sb.append(line.substring(i, i + 1));
				} else {
					sb.append(line.substring(i, i + 1));
				}
				if ((lenLine - lenEnc) == i) {
					result.add(sb.toString());
					sb = new StringBuilder();
					startEnclosure = false;
				}
			} else {
				if (line.substring(i, i + lenEnc).equals(enclosureSign) && startEnclosure == false) {
					startEnclosure = true;
				} else {
					if (line.substring(i, i + lenSep).equals(separator)) {
						result.add(sb.toString());
						sb = new StringBuilder();
					} else {
						sb.append(line.substring(i, i + 1));
					}
					if ((lenLine - lenEnc) == i) {
						sb.append(line.substring(i + 1));
						result.add(sb.toString());
						sb = new StringBuilder();
					}
				}
			}
		}
		if (startEnclosure)
			throw new ConversionException("Enclosure Sign '" + enclosureSign + "' started but not ended.");

		return result;
	}

	public int listElementSum(List<Integer> list) {
		if (list == null || list.size() < 1)
			return 0;
		int sum = 0;
		for (Integer i : list)
			sum = sum + i;

		return sum;
	}

	public String appendSpaceAtEnd(String value, String maxLength) {
		if (maxLength.trim().equals(""))
			maxLength = Integer.toString(value.length());
		while (value.length() < Integer.parseInt(maxLength))
			value = value + " ";
		return value;
	}

	public String formateText(String value, String maxLength, String type) throws ConversionException {
		value = appendSpaceAtEnd(value, maxLength);
		int maxlen = Integer.parseInt(maxLength);
		if (value.length() > maxlen) {
			if (type.equalsIgnoreCase("Error"))
				throw new ConversionException("Field length " + maxLength + " is too short to handle " + value.length()
						+ " character, " + value + ".");
			if (type.equalsIgnoreCase("Cut") || type.equalsIgnoreCase("Truncate"))
				return value.substring(0, maxlen);
			if (type.equalsIgnoreCase("Ignore"))
				return value;
		}
		return value;
	}

	public String appendEncloser(String value, String separator, String encloser) {
		String glEnc = "\"";
		if (encloser.equals("")) {
			if (value.contains(separator) || value.contains(glEnc))
				return glEnc + encloserFind(value, '"') + glEnc;
			return value;
		}
		return encloser + encloserFind(value, encloser.charAt(0)) + encloser;
	}

	public String encloserFind(String value, char encloser) {
		String finalString = "";
		for (char cr : value.toCharArray()) {
			if (cr == encloser)
				finalString += encloser;
			finalString += cr;
		}
		return finalString;

	}

	/**
	 * This we can use to get value from a String using a regular expression.
	 * 
	 * @param pattern      Regular expression pattern
	 * @param valueString  String value
	 * @param from         value at index
	 * @param notMatchCond What happen if not matched - error is default value i.e.
	 *                     throw exception - value is return whole string in valueString - blank is
	 *                     to return blank value 
	 */
	public String getValueFromRegex(String pattern, String valueString, int from, String notMatchCond)
			throws Exception {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(valueString);
		if (m.matches())
			return m.group(from);
		else {
			notMatchCond = (notMatchCond.isEmpty()) ? "error" : notMatchCond;
			if (notMatchCond.equalsIgnoreCase("error"))
				throw new Exception(pattern + " not matched in " + valueString + ".");
			else if (notMatchCond.equalsIgnoreCase("value"))
				return valueString;
			else if (notMatchCond.equalsIgnoreCase("blank"))
				return "";
			else
				return notMatchCond;
		}

	}
}
