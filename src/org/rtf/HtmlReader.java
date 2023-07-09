package org.rtf;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JEditorPane;
import javax.swing.text.StyledEditorKit;

public class HtmlReader {

	/**
	 * This method read input as rtf 
	 * return html string
	 * @param rtf
	 * @return
	 */
	
	static  String testRtfToHtml(String rtf)
	 {
	     JEditorPane pane = new JEditorPane();
	     pane.setContentType("text/rtf");

	     StyledEditorKit kitRtf = (StyledEditorKit) pane.getEditorKitForContentType("text/rtf");

	     try
	     {
	         kitRtf.read(
	             new StringReader(
	                 
	            		 rtf),
	             pane.getDocument(), 0);
	         kitRtf = null;

	         StyledEditorKit kitHtml =
	             (StyledEditorKit) pane.getEditorKitForContentType("text/html");

	         Writer writer = new StringWriter();
	         kitHtml.write(writer, pane.getDocument(), 0, pane.getDocument().getLength());
	         return writer.toString();
	     }
	     catch (Exception e)
	     {
	         e.printStackTrace();
	     }
		return null;
	 }
}
