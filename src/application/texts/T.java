package application.texts;

import java.util.ResourceBundle;

public class T {
	/**
	 * This method takes a key and returns a text value assigned to the key.</br>
	 * The text value can be :
	 * <ul>
	 * 	<li>readable text for the UI</li>
	 * 	<li>css class names</li>
	 * 	<li>path names for images, files etc.</li>
	 * </ul>
	 * 
	 * @param	key	The text key used to retrieve assigned text value 
	 * @return	text	The text value
	 */
	public static String get(String key) {
		
		try {
			ResourceBundle texts = ResourceBundle.getBundle("application.texts.texts");
			
			return texts.getString(key);
		} catch (Exception e) {
			return key;
		}
	}
}
