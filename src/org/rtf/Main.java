package org.rtf;

public class Main {

	/**
	 * Main File
	 * @param args
	 */
	
	public static void main(String args[]) {
		String inputHtml="<p><b>i am a bold paragraph</b></p>world<br>i am break;<br>Numbers List<ul><li>1</li><li>2</li><li>3</li></ul>";
		String rtfOutput = RTFReader.convertHtmlToRtf(inputHtml);
		System.out.println(rtfOutput);
		String returnHtml = HtmlReader.testRtfToHtml(rtfOutput);
		System.out.println(returnHtml);
	}
}
