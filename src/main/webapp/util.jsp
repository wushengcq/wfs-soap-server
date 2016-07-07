<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="header.jsp"%>
<%!
public String formatXml(String xml) throws DocumentException, IOException {
    Document doc = (new SAXReader()).read(new ByteArrayInputStream(xml.getBytes()));
    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setIndent(true);
    format.setNewlines(true);
    format.setEncoding("UTF-8");
    format.setIndentSize(2);
    format.setExpandEmptyElements(true);
    format.setNewLineAfterDeclaration(true);
    format.setNewLineAfterNTags(1);

    StringWriter writer = null;
    XMLWriter xmlwriter = null;
    try{
        writer = new StringWriter();
        xmlwriter = new XMLWriter(writer, format);
        xmlwriter.write(doc);
        return writer.toString();
    }finally{
        xmlwriter.close();
        writer.close();
    }
}
%>
