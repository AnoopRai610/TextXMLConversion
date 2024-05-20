package com.ibm.xi.conversion.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use to store parameters set by users. Note that only specific parameter
 * allowed as specified in below table:-
 * 
 * <meta http-equiv=Content-Type content="text/html; charset=windows-1252"><meta
 * name=ProgId content=Excel.Sheet><meta name=Generator content="Microsoft Excel
 * 15"><link rel=File-List href=
 * "CustomModule_Plain2XML_files/filelist.xml"><style id=
 * "CustomModule_Plain2XML_30238_Styles"><!--table{mso-displayed-decimal-separator:"\.";mso-displayed-thousand-separator:"\,";}.xl6330238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:"\@";text-align:general;vertical-align:middle;border:.5pt
 * solid
 * windowtext;mso-background-source:auto;mso-pattern:auto;white-space:normal;}.xl6430238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:General;text-align:general;vertical-align:middle;border:.5pt
 * solid
 * windowtext;mso-background-source:auto;mso-pattern:auto;white-space:normal;}.xl6530238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:General;text-align:general;vertical-align:middle;mso-background-source:auto;mso-pattern:auto;white-space:nowrap;}.xl6630238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:"\@";text-align:general;vertical-align:middle;mso-background-source:auto;mso-pattern:auto;white-space:normal;}.xl6730238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:General;text-align:general;vertical-align:middle;mso-background-source:auto;mso-pattern:auto;white-space:normal;}.xl6830238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:"\@";text-align:general;vertical-align:middle;border-top:.5pt
 * solid windowtext;border-right:.5pt solid windowtext;border-bottom:1.0pt solid
 * windowtext;border-left:.5pt solid
 * windowtext;mso-background-source:auto;mso-pattern:auto;white-space:normal;}.xl6930238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:General;text-align:general;vertical-align:middle;border-top:.5pt
 * solid windowtext;border-right:.5pt solid windowtext;border-bottom:1.0pt solid
 * windowtext;border-left:.5pt solid
 * windowtext;mso-background-source:auto;mso-pattern:auto;white-space:normal;}.xl7030238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:General;text-align:general;vertical-align:middle;border-top:.5pt
 * solid windowtext;border-right:.5pt solid windowtext;border-bottom:.5pt solid
 * windowtext;border-left:1.0pt solid
 * windowtext;mso-background-source:auto;mso-pattern:auto;white-space:normal;}.xl7130238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:General;text-align:general;vertical-align:middle;border-top:.5pt
 * solid windowtext;border-right:.5pt solid windowtext;border-bottom:1.0pt solid
 * windowtext;border-left:1.0pt solid
 * windowtext;mso-background-source:auto;mso-pattern:auto;white-space:normal;}.xl7230238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:General;text-align:center;vertical-align:middle;border-top:1.0pt
 * solid windowtext;border-right:.5pt solid windowtext;border-bottom:.5pt solid
 * windowtext;border-left:1.0pt solid
 * windowtext;background:#EF8B69;mso-pattern:black
 * none;white-space:normal;}.xl7330238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:"\@";text-align:center;vertical-align:middle;border-top:1.0pt
 * solid windowtext;border-right:.5pt solid windowtext;border-bottom:.5pt solid
 * windowtext;border-left:.5pt solid
 * windowtext;background:#EF8B69;mso-pattern:black
 * none;white-space:normal;}.xl7430238{padding-top:1px;padding-right:1px;padding-left:1px;mso-ignore:padding;color:black;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;font-family:Rockwell,
 * sans-serif;mso-font-charset:0;mso-number-format:General;text-align:center;vertical-align:middle;border-top:1.0pt
 * solid windowtext;border-right:.5pt solid windowtext;border-bottom:.5pt solid
 * windowtext;border-left:.5pt solid
 * windowtext;background:#EF8B69;mso-pattern:black
 * none;white-space:normal;}--></style></head><body><!--[if
 * !excel]>&nbsp;&nbsp;<![endif]--><!--The following information was generated
 * by Microsoft Excel's Publish as WebPage wizard.--><!--If the same item is
 * republished from Excel, all information between the DIVtags will be
 * replaced.--><!-----------------------------><!--START OF OUTPUT FROM EXCEL
 * PUBLISH AS WEB PAGE WIZARD
 * --><!-----------------------------><div id="CustomModule_Plain2XML_30238"
 * align=center x:publishsource="Excel">
 * <table border=0 cellpadding=0 cellspacing=0 width=1525 class=xl6530238 style=
 * 'border-collapse:collapse;table-layout:fixed;width:1144pt'>
 * <col class=xl6730238 width=232 style='mso-width-source:userset;mso-width-alt:
 * 7424;width:174pt'>
 * <col class=xl6630238 width=481 style='mso-width-source:userset;mso-width-alt:
 * 15402;width:361pt'>
 * <col class=xl6630238 width=160 style='mso-width-source:userset;mso-width-alt:
 * 5120;width:120pt'>
 * <col class=xl6730238 width=323 style='mso-width-source:userset;mso-width-alt:
 * 10325;width:242pt'>
 * <col class=xl6730238 width=329 style='mso-width-source:userset;mso-width-alt:
 * 10517;width:247pt'>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7230238 width=232 style=
 * 'height:14.0pt;width:174pt'>Parameter Name</td>
 * <td class=xl7330238 width=481 style='border-left:none;width:361pt'>Sample
 * Parameter Value</td>
 * <td class=xl7330238 width=160 style=
 * 'border-left:none;width:120pt'>Status</td>
 * <td class=xl7430238 width=323 style=
 * 'border-left:none;width:242pt'>Mandatory/Optional</td>
 * <td class=xl7430238 width=329 style=
 * 'border-left:none;width:247pt'>Remarks</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>documentName</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>MT_TESTMessage</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>documentNamespace</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>urn:testNamespace.com.BA</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>recordSetName</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Recordset</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Partially completed as non-repeatable</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Please note that recordSet will not repeat. Its still pending.
 * Better to avoide it.</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>recordSetNamespace</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>urn:testRecordNamesace</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Partially completed as non-repeatable</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>recordsetStructure</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Header,GroupHeader,Items,GroupTrailer,Trailer,Undeclear</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>keyFieldAtFront</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>true/false</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Default false</td>
 * </tr>
 * <tr height=56 style='height:42.0pt'>
 * <td height=56 class=xl7030238 width=232 style='height:42.0pt;border-top:none;
 * width:174pt'>keyFieldName</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>&nbsp;</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory if keyFieldAtFront is false and recordSet based
 * keyFieldName is not maintained</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Use only when only one keyFieldName is fixed for all
 * recordSets</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Header.keyFieldName</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory if keyFieldAtFront is false</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>GroupHeader.keyFieldName</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory if keyFieldAtFront is false</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>GroupTrailer.keyFieldName</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory if keyFieldAtFront is false</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Items.keyFieldName</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory if keyFieldAtFront is false</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Trailer.keyFieldName</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory if keyFieldAtFront is false</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>Header.fieldNames</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID,System_code,Posting_year,Posting_Month,Run_sequence_number,Balance_and_post_flag,Filler</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>GroupHeader.fieldNames</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID,Department,Journal_Sequence_No,Journal_Type,Group_Date,Company,Journal_Description,Currency_code,Filler</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=56 style='height:42.0pt'>
 * <td height=56 class=xl7030238 width=232 style='height:42.0pt;border-top:none;
 * width:174pt'>GroupTrailer.fieldNames</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID,Department,Journal_Sequence_No,Journal_Type,Group_Date,Company,Positive_Journals_Total,Journal_Line_Recs._Total,Filler</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=149 style='height:112.0pt'>
 * <td height=149 class=xl7030238 width=232 style='height:112.0pt;border-top:
 * none;width:174pt'>Items.fieldNames</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID,Department,Journal_Sequence_No,Journal_Type,Group_Date,Company,Center,Account,Currency,Activity,Recharge_Code,Journal_Amount_Sign,Journal_Amount,Line_Reference,Line_Description,Exchange_Rate,Exchange_Rate_Type,Exchange_Rate_Effective_Date,Converted_Journal_Amount,Project_Code,Audit_Reference,Target_Centre_Code,Target_Account_Code,Target_Activity_Code,Target_Recharge_Code,Journal_effective_date,Station_code,Type_Code,Sequence_Number,Filler</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>Trailer.fieldNames</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Transaction_ID,System_code,Number_of_groups,Number_of_journals,Filler</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Undeclear.fieldNames</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Details</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>&nbsp;</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>&nbsp;</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>Header.keyFieldValue</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>000</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when recordsetStructure has more than one element</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>GroupHeader.keyFieldValue</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>120</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when recordsetStructure has more than one element</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>GroupTrailer.keyFieldValue</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>128</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when recordsetStructure has more than one element</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>Items.keyFieldValue</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>122</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when recordsetStructure has more than one element</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>Trailer.keyFieldValue</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>999</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when recordsetStructure has more than one element</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=56 style='height:42.0pt'>
 * <td height=56 class=xl7030238 width=232 style='height:42.0pt;border-top:none;
 * width:174pt'>Undeclear.keyFieldValue</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>$DefaultValue$</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when recordsetStructure has more than one element and
 * we are unclear about its keyFieldValue</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Maintain $DefultValue$ if we want to include some undecleared
 * records. Don’t maintain more than one such value.</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>Header.parentNode</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Recordset</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Set recordSet if recordSetName/parent node is not available</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>Group.parentNode</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Recordset</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Set recordSet if recordSetName/parent node is not available</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Group.startWith</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>GroupHeader</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>GroupHeader.parentNode</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Group</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>GroupTrailer.parentNode</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Group</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Items.parentNode</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Group</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>Trailer.parentNode</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Recordset</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Set recordSet if recordSetName/parent node is not available</td>
 * </tr>
 * <tr height=37 style='height:28.0pt'>
 * <td height=37 class=xl7030238 width=232 style='height:28.0pt;border-top:none;
 * width:174pt'>Undeclear.parentNode</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>Recordset</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Set recordSet if recordSetName/parent node is not available</td>
 * </tr>
 * <tr height=56 style='height:42.0pt'>
 * <td height=56 class=xl7030238 width=232 style='height:42.0pt;border-top:none;
 * width:174pt'>fieldSeparator</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>,</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional but Override all values of
 * &lt;recordSet&gt;.fieldSeparator &amp;
 * &lt;recordSet&gt;.fieldFixedLengths</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Use only when only one separator is fixed for all recordSets and
 * there is no fieldSeparator<span style='mso-spacerun:yes'> </span></td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Header.fieldSeparator</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>,</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>&nbsp;</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Header.fieldFixedLengths</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>3,2,4,2,2,1,185</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when fieldSeparator not specified</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Not read this value if fieldSeparator is available</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>GroupHeader.fieldFixedLengths</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>3,3,4,1,6,3,20,3,157</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when fieldSeparator not specified</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Not read this value if fieldSeparator is available</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Items.fieldFixedLengths</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>3,3,4,1,6,3,5,4,3,6,3,1,15,10,36,15,4,6,15,9,10,5,4,6,3,6,3,1,1,9</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when fieldSeparator not specified</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Not read this value if fieldSeparator is available</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>GroupTrailer.fieldFixedLengths</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>3,3,4,1,6,3,15,7,158</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when fieldSeparator not specified</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Not read this value if fieldSeparator is available</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Trailer.fieldFixedLengths</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>3,2,4,7,184</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when fieldSeparator not specified</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Not read this value if fieldSeparator is available</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>Undeclear.fieldFixedLengths</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>4</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Mandatory when fieldSeparator not specified</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Not read this value if fieldSeparator is available</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>trimContents</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>true</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Default false</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>indentFactor</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>2</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Default o</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>encoding</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>UTF-8</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>offsetTop</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>1</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>offsetBottom</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>1</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>&nbsp;</td>
 * </tr>
 * <tr height=19 style='height:14.0pt'>
 * <td height=19 class=xl7030238 width=232 style='height:14.0pt;border-top:none;
 * width:174pt'>ignoreNonKeyFieldRecords</td>
 * <td class=xl6330238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>true</td>
 * <td class=xl6330238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6430238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional</td>
 * <td class=xl6430238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Default false</td>
 * </tr>
 * <tr height=57 style='height:42.5pt'>
 * <td height=57 class=xl7130238 width=232 style='height:42.5pt;border-top:none;
 * width:174pt'>endSeparator</td>
 * <td class=xl6830238 width=481 style='border-top:none;border-left:none;
 * width:361pt'>'nl'</td>
 * <td class=xl6830238 width=160 style='border-top:none;border-left:none;
 * width:120pt'>Completed</td>
 * <td class=xl6930238 width=323 style='border-top:none;border-left:none;
 * width:242pt'>Optional</td>
 * <td class=xl6930238 width=329 style='border-top:none;border-left:none;
 * width:247pt'>Default 'nl'. Use 'tab' for tab. For any other separator just
 * mention here. Field wise endSeparator not available.</td>
 * </tr>
 * </table>
 * 
 * @author IBM GBS, IN (AnoopRai)
 * @version 1.0
 */
public class ParameterContext {

	private Map<String, String> parameterValues;

	public ParameterContext() {
		this.parameterValues = new HashMap<String, String>();
	}

	public ParameterContext(Map<String, String> parameterValues) {
		this.parameterValues = parameterValues;
	}

	/**
	 * @return the parameterValues
	 */
	public Map<String, String> getParameterValues() {
		return parameterValues;
	}

	/**
	 * @param parameterValues the parameterValues to set
	 */
	public void setParameterValues(Map<String, String> parameterValues) {
		this.parameterValues = parameterValues;
	}

	public String getContextData(String key) {
		return this.parameterValues.get(key);
	}

	public List<String> getContextDataKeys() {
		return new ArrayList<String>(this.parameterValues.keySet());
	}
	
	public boolean containsKey(String key) {
		return this.parameterValues.containsKey(key);
	}
}
