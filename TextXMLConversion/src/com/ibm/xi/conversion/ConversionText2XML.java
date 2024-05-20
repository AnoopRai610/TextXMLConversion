package com.ibm.xi.conversion;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import com.ibm.xi.conversion.services.XMLBuilder;
import com.ibm.xi.conversion.services.XMLBuilderInterface;
import com.ibm.xi.conversion.utility.AuditLogHelper;
import com.ibm.xi.conversion.utility.AuditLogStatus;
import com.ibm.xi.conversion.utility.ConversionException;
import com.ibm.xi.conversion.utility.ParameterContext;
import com.ibm.xi.conversion.utility.ParameterHelper;
import com.sap.it.api.msglog.MessageLog;

/**
 * Use to convert data from plain text/csv to XML
 * 
 * <pre>
 * Define parameters like below mentioned:</br>
 * {@code
 * Map<String, String> parameterValues = new HashMap<>();
 * parameterValues.put("documentName", "MT_GLAccountdetails");
 * parameterValues.put("documentNamespace", "urn:BA:pi:HOST:DOC_I_007:GLAccountPosting");
 * parameterValues.put("recordSetName", "RootElement");
 * parameterValues.put("recordSetNamespace", "urn:testRootNamespace.com");
 * parameterValues.put("recordsetStructure", "Header,GroupHeader,Items,GroupTrailer,Trailer");
 * parameterValues.put("Header.fieldNames",
 * 		"IL_FILE_TRANS_ID,IL_FILE_SYS_CODE,IL_FILE_YEAR,IL_FILE_MONTH,IL_FILE_SEQ_NO,IL_BAL_POST_FLAG,FILLER");
 * parameterValues.put("GroupHeader.fieldNames",
 * 		"IL_Journ_trans_ID,IL_JOURN_NO_IL_JOURN_DEPT,IL_JOURN_NO_IL_JOURN_SEQ_NO,IL_JOURN_NO_IL_JOURN_TYPE,IL_JOURN_DATE,IL_ACCOUNT_CODE_KEY_IL_COMPANY,IL_JOURN_DESC,IL_Currency_Code,IL_Filler");
 * parameterValues.put("GroupTrailer.fieldNames",
 * 		"IL_TRLR_TRANS_ID,IL_TRLR_GROUP_NO_IL_TRLR_DEPT,IL_TRLR_GROUP_NO_IL_TRLR_SEQ_NO,IL_TRLR_GROUP_NO_IL_TRLR_TYPE,IL_TRLR_DATE,IL_TRLR_CMPY,IL_TOTAL_VALUE,IL_TOTAL_RECS,IL_GTR_FILLER");
 * parameterValues.put("Items.fieldNames",
 * 		"IL_Journ_trans_ID,IL_JOURN_NO_IL_JOURN_DEPT,IL_JOURN_NO_IL_JOURN_SEQ_NO,IL_JOURN_NO_IL_JOURN_TYPE,IL_JOURN_DATE,IL_ACCOUNT_CODE_KEY_IL_COMPANY,IL_ACCOUNT_CODE_KEY_IL_CENTRE,IL_ACCOUNT_CODE_KEY_IL_ACCOUNT,IL_ACCOUNT_CODE_KEY_IL_CURRENCY,IL_ACCOUNT_CODE_KEY_IL_ACTIVITY,IL_ACCOUNT_CODE_KEY_IL_RECHARGE_CODE,IL_JOURN_SIGN,IL_JOURN_AMT,IL_LINE_REF,IL_LINE_DESC,IL_EXCH_RATE,IL_EXCH_RATE_TYPE,IL_EXCH_RATE_EFF_DATE,IL_CONV_JOURN_AMT,IL_PROJECT_CODE,IL_AUDIT_REF,IL_TARGET_ACK_IL_TARGET_CENTRE,IL_TARGET_ACK_IL_TARGET_ACCOUNT,IL_TARGET_ACK_IL_TARGET_ACTIVITY,IL_TARGET_ACK_IL_TARGET_RECH_CODE,IL_EFFECTIVE_DATE,IL_SL_CUST_IL_CUST_LAST_5,IL_SL_CUST_IL_CUST_CMPY,FILLER");
 * parameterValues.put("Trailer.fieldNames",
 * 		"IL_FTRLR,IL_TRLR_SYS_CODE,IL_GROUP_TOTAL,IL_JOURN_TOTAL,IL_FTR_FILLER"); //
 * parameterValues.put("keyFieldAtFront", "true");
 * parameterValues.put("Header.keyFieldName", "IL_FILE_TRANS_ID");
 * parameterValues.put("GroupHeader.keyFieldName", "IL_Journ_trans_ID"); //
 * parameterValues.put("Items.keyFieldName", "IL_Journ_trans_ID");
 * parameterValues.put("GroupTrailer.keyFieldName", "IL_TRLR_TRANS_ID");
 * parameterValues.put("Trailer.keyFieldName", "IL_FTRLR");
 * parameterValues.put("Header.keyFieldValue", "000");
 * parameterValues.put("GroupHeader.keyFieldValue", "120");
 * parameterValues.put("GroupTrailer.keyFieldValue", "128");
 * parameterValues.put("Items.keyFieldValue", "$DefaultValue$");
 * parameterValues.put("Trailer.keyFieldValue", "999");
 * parameterValues.put("Root.startWith", "Header");
 * parameterValues.put("Root.parentNode", "recordSet");
 * parameterValues.put("Header.parentNode", "Root");
 * parameterValues.put("Group.parentNode", "Root");
 * parameterValues.put("Group.startWith", "GroupHeader");
 * parameterValues.put("GroupHeader.parentNode", "Group");
 * parameterValues.put("GroupTrailer.parentNode", "Group");
 * parameterValues.put("Items.parentNode", "Group");
 * parameterValues.put("Trailer.parentNode", "Root");
 * parameterValues.put("Header.fieldFixedLengths", "3,2,4,2,2,1,186");
 * parameterValues.put("GroupHeader.fieldFixedLengths", "3,3,4,1,6,3,20,3,157");
 * parameterValues.put("Items.fieldFixedLengths",
 * 		"3,3,4,1,6,3,5,4,3,6,3,1,15,10,36,15,4,6,15,9,10,5,4,6,3,6,5,2,7");
 * parameterValues.put("GroupTrailer.fieldFixedLengths", "3,3,4,1,6,3,15,7,158");
 * parameterValues.put("Trailer.fieldFixedLengths", "3,2,4,7,184");
 * parameterValues.put("trimContents", "true");
 * parameterValues.put("endSeparator", "'nl'");
 * parameterValues.put("encoding", "UTF-8");
 * }
 * 
 * For more information on parameters {@link com.ibm.xi.conversion.utility.ParameterContext}
 * </pre>
 * 
 * @author IBM GBS, IN (AnoopRai)
 * @version 1.0
 */
public class ConversionText2XML implements ConversionInterface {

	private AuditLogHelper auditLog;

	@Override
	public void execute(InputStream input, OutputStream output, Map<String, String> parameterValues)
			throws ConversionException {
		ParameterContext mContext = new ParameterContext(parameterValues);
		ParameterHelper param = new ParameterHelper(mContext);
		
		boolean debug  = param.getBoolParameter("debug", false);
		
		if(debug && auditLog!=null)
			auditLog.addLog(AuditLogStatus.SUCCESS, "MM ConJM : Starting data parsing.");
		XMLBuilderInterface xmlBuilder = new XMLBuilder(input, param);
		if(debug && auditLog!=null)
			auditLog.addLog(AuditLogStatus.SUCCESS, "MM ConJM : Data parsing completed. Starting XML document build.");
		Document doc = xmlBuilder.creatXML();
		
		if(debug && auditLog!=null)
			auditLog.addLog(AuditLogStatus.SUCCESS, "MM ConJM : XML document build completed. Transforming document to OutputStream.");
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			if (param.getParameter("indentFactor") != null)
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
						param.getParameter("indentFactor"));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, param.getParameter("encoding", "UTF-8"));
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(output);

			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw new ConversionException("Unable to build output XML Document : " + e.getMessage());
		}
		if(debug && auditLog!=null)
			auditLog.addLog(AuditLogStatus.SUCCESS, "MM ConJM : Message transformed into XML and convert to OutputStream.");
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
			auditLog.addLog(AuditLogStatus.WARNING, "MM ConJM : Transformation completed but taking more than 5 sec. Its occure in case of big files."
					+ " Please execute such files in idle time.");
		else
			auditLog.addLog(AuditLogStatus.SUCCESS, "MM ConJM : Transformation completed.");
	}

	/**
	 * @return the auditLog
	 */
	public AuditLogHelper getAuditLog() {
		return auditLog;
	}

	/**
	 * @param auditLog the auditLog to set
	 */
	public void setAuditLog(AuditLogHelper auditLog) {
		this.auditLog = auditLog;
	}
}
