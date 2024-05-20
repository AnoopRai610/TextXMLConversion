package com.ibm.xi.conversion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.ibm.xi.conversion.services.TextBuilder;
import com.ibm.xi.conversion.utility.AuditLogHelper;
import com.ibm.xi.conversion.utility.AuditLogStatus;
import com.ibm.xi.conversion.utility.ConversionException;
import com.ibm.xi.conversion.utility.ParameterContext;
import com.ibm.xi.conversion.utility.ParameterHelper;
import com.sap.it.api.msglog.MessageLog;

/**
 * Use to convert data from plain XML to text(Fixed Line)/csv
 * 
 * <pre>
 * Define parameters like below mentioned for CSV:</br>
 * {@code
 * Map<String, String> parameterValues = new HashMap<>();
 * parameterValues.put("recordsetStructure", "Header,Customer,TransactionDetails,Trailer");
 * parameterValues.put("fieldSeparator", ",");	//Default separator
 * parameterValues.put("Header.fieldSeparator", "|"); //Overlook default field separator for Header Record set
 * parameterValues.put("Trailer.fieldSeparator", "|");
 * parameterValues.put("headerRequired", "true");
 * parameterValues.put("encoding", "UTF-8"); //Default value is 16-bits char
 * }
 * Define parameters like below mentioned for CSV:</br>
 * {@code
 * Map<String, String> parameterValues = new HashMap<>();
 * parameterValues.put("recordsetStructure", "Header,Customer,TransactionDetails,Trailer");
 * parameterValues.put("fieldSeparator", ",");	//Default separator
 * parameterValues.put("Header.fieldSeparator", "|"); //Overlook default field separator for Header Record set
 * parameterValues.put("Trailer.fieldSeparator", "|");
 * parameterValues.put("headerRequired", "true");
 * }
 * 
 * define parameter like below for fixed length:
 * {@code
 * Map<String, String> parameterValues = new HashMap<>();
 * parameterValues.put("recordsetStructure", "Header,Customer,TransactionDetails,Trailer");
 * parameterValues.put("fieldSeparator", ",");
 * parameterValues.put("Header.fieldFixedLengths", "20,20");
 * parameterValues.put("Trailer.fieldFixedLengths", "3,20,20");
 * parameterValues.put("headerRequired", "true");
 * }
 * 
 * For more information on parameters {@link com.ibm.xi.conversion.utility.ParameterContext}
 * </pre>
 * 
 * @author IBM GBS, IN (AnoopRai)
 * @version 1.0
 */
public class ConversionXML2Text implements ConversionInterface {
	
	private AuditLogHelper auditLog;

	@Override
	public void execute(InputStream input, OutputStream output, Map<String, String> parameterValues)
			throws ConversionException {

		ParameterContext mContext = new ParameterContext(parameterValues);
		ParameterHelper param = new ParameterHelper(mContext);

		TextBuilder textBuilder = new TextBuilder(input, param);
		try {
			if (param.getBoolParameter("convertCSV", "true"))
				if (param.hasParameter("encoding"))
					output.write(textBuilder.getCsvByte(param.getParameter("encoding")));
				else
					output.write(textBuilder.getCsvByte()); // Default as 16-bits char
			else {
				TransformerFactory transformerFactory = TransformerFactory.newInstance();

				Transformer transformer = transformerFactory.newTransformer();
				if (param.getParameter("indentFactor") != null)
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
							param.getParameter("indentFactor"));
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.ENCODING, param.getParameter("encoding", "UTF-8"));
				DOMSource source = new DOMSource(textBuilder.getDoc());
				StreamResult result = new StreamResult(output);

				transformer.transform(source, result);
			}
		} catch (TransformerException e) {
			throw new ConversionException("Unable to build output XML Document : " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			throw new ConversionException("The Character Encoding " + param.getParameter("encoding")
					+ " is not supported : " + e.getMessage());
		} catch (IOException e) {
			throw new ConversionException("Unable to build output XML Document : " + e.getMessage());
		}

	}

	@Override
	public void execute(InputStream input, OutputStream output, Map<String, String> parameterValues, MessageLog log)
			throws ConversionException {
		auditLog = new AuditLogHelper(log);
		long st = new Date().getTime();
		auditLog.addLog(AuditLogStatus.SUCCESS, "MM ConJM : Transformation starting.");
		execute(input, output, parameterValues);
		long sec = (new Date().getTime() - st)/1000;
		if(sec>5)
			auditLog.addLog(AuditLogStatus.WARNING, "MM ConJM : Transformation completed but taking more than 10sec. Its occure in case of big files."
					+ " Please execute such files in idle time.");
		else
			auditLog.addLog(AuditLogStatus.SUCCESS, "MM ConJM : Transformation completed.");

	}

	public AuditLogHelper getAuditLog() {
		return auditLog;
	}

	public void setAuditLog(AuditLogHelper auditLog) {
		this.auditLog = auditLog;
	}

}
