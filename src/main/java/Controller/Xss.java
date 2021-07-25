package Controller;

public class Xss {

	//XSS対策メソッド
	public static String sanitizing(String input) {

		String outputData = input;

		outputData = outputData.replace("&", "&amp;");
		outputData = outputData.replace("\"", "&quot;");
		outputData = outputData.replace("<", "&lt;");
		outputData = outputData.replace(">", "&gt;");
		outputData = outputData.replace("'", "&#39;");

		return outputData;
	}
}
