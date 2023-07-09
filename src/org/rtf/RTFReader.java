package org.rtf;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JEditorPane;
import javax.swing.text.StyledEditorKit;
public class RTFReader {

	public static void main(String args[]) {
		String inputHtml="<p><b>i am a bold paragraph</b></p>world<br>i am break;<br>Numbers List<ul><li>1</li><li>2</li><li>3</li></ul>";
		String rtfOutput = convertHtmlToRtf(inputHtml);
		System.out.println(rtfOutput);
		String returnHtml = testRtfToHtml(rtfOutput);
		System.out.println(returnHtml);
	}
	
	public static String convertHtmlToRtf(String html) {
	    String tmp = html.replaceAll("\\R", " ")
	            .replaceAll("\\\\", "\\\\\\\\")
	            .replaceAll("\\{", "\\\\{")
	            .replaceAll("}", "\\\\}");
	    tmp = tmp.replaceAll("<a\\s+target=\"_blank\"\\s+href=[\"']([^\"']+?)[\"']\\s*>([^<]+?)</a>",
	            "{\\\\field{\\\\*\\\\fldinst HYPERLINK \"$1\"}{\\\\fldrslt \\\\plain \\\\f2\\\\b\\\\fs20\\\\cf2 $2}}");
	    tmp = tmp.replaceAll("<a\\s+href=[\"']([^\"']+?)[\"']\\s*>([^<]+?)</a>",
	            "{\\\\field{\\\\*\\\\fldinst HYPERLINK \"$1\"}{\\\\fldrslt \\\\plain \\\\f2\\\\b\\\\fs20\\\\cf2 $2}}");

	    tmp = tmp.replaceAll("<h3>", "\\\\line{\\\\b\\\\fs30{");
	    tmp = tmp.replaceAll("</h3>", "}}\\\\line\\\\line ");
	    tmp = tmp.replaceAll("<b>", "{\\\\b{");
	    tmp = tmp.replaceAll("</b>", "}}");
	    tmp = tmp.replaceAll("<ul[^>]*?>", "\\\\ul{");
	    tmp = tmp.replaceAll("</ul>", "\\}");
	    //li
	    tmp = tmp.replaceAll("<li[^>]*?>", "\\\\par{\\\\u8226?");
	    tmp = tmp.replaceAll("</li>", "\\}");
	    
	    tmp = tmp.replaceAll("<strong>", "{\\\\b{");
	    tmp = tmp.replaceAll("</strong>", "}}");
	    tmp = tmp.replaceAll("<i>", "{\\\\i{");
	    tmp = tmp.replaceAll("</i>", "}}");
	    tmp = tmp.replaceAll("&amp;", "&");
	    tmp = tmp.replaceAll("&quot;", "\"");
	    tmp = tmp.replaceAll("&copy;", "{\\\\'a9}");
	    tmp = tmp.replaceAll("&lt;", "<");
	    tmp = tmp.replaceAll("&gt;", ">");
	    tmp = tmp.replaceAll("<br/?><br/?>", "{\\\\pard \\\\par}\\\\line ");
	    tmp = tmp.replaceAll("<br/?>", "\\\\line ");
	    tmp = tmp.replaceAll("<BR>", "\\\\line ");
	    tmp = tmp.replaceAll("<p[^>]*?>", "{\\\\pard ");
	    tmp = tmp.replaceAll("</p>", " \\\\par}\\\\line ");
	    tmp = convertSpecialCharsToRtfCodes(tmp);
	    return "{\\rtf1\\ansi\\ansicpg1252\\deff0\\deflang1030{\\fonttbl{\\f0\\fswiss\\fcharset0 Arial;}}\\viewkind4\\uc1\\pard\\f0\\fs20 " + tmp + "}";
	}

	 private static String convertSpecialCharsToRtfCodes(String input) {
	    char[] chars = input.toCharArray();
	    StringBuffer sb = new StringBuffer();
	    int length = chars.length;
	    for (int i = 0; i < length; i++) {
	        switch (chars[i]) {
	            case '’':
	                sb.append("{\\'92}");
	                break;
	            case '`':
	                sb.append("{\\'60}");
	                break;
	            case '€':
	                sb.append("{\\'80}");
	                break;
	            case '…':
	                sb.append("{\\'85}");
	                break;
	            case '‘':
	                sb.append("{\\'91}");
	                break;
	            case '̕':
	                sb.append("{\\'92}");
	                break;
	            case '“':
	                sb.append("{\\'93}");
	                break;
	            case '”':
	                sb.append("{\\'94}");
	                break;
	            case '•':
	                sb.append("{\\'95}");
	                break;
	            case '–':
	            case '‒':
	                sb.append("{\\'96}");
	                break;
	            case '—':
	                sb.append("{\\'97}");
	                break;
	            case '©':
	                sb.append("{\\'a9}");
	                break;
	            case '«':
	                sb.append("{\\'ab}");
	                break;
	            case '±':
	                sb.append("{\\'b1}");
	                break;
	            case '„':
	                sb.append("\"");
	                break;
	            case '´':
	                sb.append("{\\'b4}");
	                break;
	            case '¸':
	                sb.append("{\\'b8}");
	                break;
	            case '»':
	                sb.append("{\\'bb}");
	                break;
	            case '½':
	                sb.append("{\\'bd}");
	                break;
	            case 'Ä':
	                sb.append("{\\'c4}");
	                break;
	            case 'È':
	                sb.append("{\\'c8}");
	                break;
	            case 'É':
	                sb.append("{\\'c9}");
	                break;
	            case 'Ë':
	                sb.append("{\\'cb}");
	                break;
	            case 'Ï':
	                sb.append("{\\'cf}");
	                break;
	            case 'Í':
	                sb.append("{\\'cd}");
	                break;
	            case 'Ó':
	                sb.append("{\\'d3}");
	                break;
	            case 'Ö':
	                sb.append("{\\'d6}");
	                break;
	            case 'Ü':
	                sb.append("{\\'dc}");
	                break;
	            case 'Ú':
	                sb.append("{\\'da}");
	                break;
	            case 'ß':
	            case 'β':
	                sb.append("{\\'df}");
	                break;
	            case 'à':
	                sb.append("{\\'e0}");
	                break;
	            case 'á':
	                sb.append("{\\'e1}");
	                break;
	            case 'ä':
	                sb.append("{\\'e4}");
	                break;
	            case 'è':
	                sb.append("{\\'e8}");
	                break;
	            case 'é':
	                sb.append("{\\'e9}");
	                break;
	            case 'ê':
	                sb.append("{\\'ea}");
	                break;
	            case 'ë':
	                sb.append("{\\'eb}");
	                break;
	            case 'ï':
	                sb.append("{\\'ef}");
	                break;
	            case 'í':
	                sb.append("{\\'ed}");
	                break;
	            case 'ò':
	                sb.append("{\\'f2}");
	                break;
	            case 'ó':
	                sb.append("{\\'f3}");
	                break;
	            case 'ö':
	                sb.append("{\\'f6}");
	                break;
	            case 'ú':
	                sb.append("{\\'fa}");
	                break;
	            case 'ü':
	                sb.append("{\\'fc}");
	                break;
	            default:
	                 if( chars[i] == 8218) {
	                    System.out.println("Strange comma ... ");
	                    sb.append(",");
	                } else if( chars[i] > 132) {
	                    System.err.println( "Special code that is not translated in RTF: '" + chars[i] + "', nummer=" + (int) chars[i]);
	                    sb.append(chars[i]);
	                } else {
	                    sb.append(chars[i]);
	                }
	        }
	    }
	    return sb.toString();
	}
	 
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
