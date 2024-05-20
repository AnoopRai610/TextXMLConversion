## Text-XML conversion

# Convert Text to Deep/Structured XML and Deep XML to Text #
Easy to use as Adapter Module, Java Mapping or Directly in Message Mapping for SAP PO and use in Groovy message process in SAP CPI.

This is a Jar package that is useful to transform a simple/complex plain or csv text into simple/deep 
XML and vice-versa. It can transform a text file content to up to n-level deep XML with different 
recordset structures. Also, when it’s come to transform from very deep XML to text, it can do this 
with different field separators for each record set as well we can ignore record set that we don’t 
want to be part of the text.
As it is developed on the Java platform, we can directly use this in custom Adapter Module, Java 
Mapping or Message Mapping by @Override its transform method.
Its matter of some minutes to create a custom Adapter module that does this transformation by 
importing this jar as an external jar in the EJB Project (No need to write it as EAR available for it).
We can see it in the later section. Also, similarly it can be used in a Java Mapping also with no 
time, but as java mapping is very fixed to one requirement, we are unable to use it in different 
integration scenarios.
To make it reusable in ESR directly, this jar needs to import directly in Import Archive and after 
that, we can use it in any Message Mapping with overriding the transform method of 
AbstractTransformation abstract class. This will reduce the time spent on creating custom AM or 
JM.
This Jar needs to deploy as one-time activity in the form of custom Adapter Module or Imported 
Archive over SAP PO Java server after we can reuse it. It will reduce the effort in such 
transformation by 60-70% and 4-5 Hrs. in terms of time.
Also, as it’s a simple jar, we can also use it in any other Java-based application where such content 
transformation required