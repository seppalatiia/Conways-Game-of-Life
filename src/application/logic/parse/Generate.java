package application.logic.parse;

import application.texts.T;

public class Generate {
	/**
	 * Generate a new filename by using the map's name
	 * @param mapName
	 * @return
	 */
	public static String fileName(String mapName) {
		String fileName = mapName
				.replaceAll("Title:", "")
				.toLowerCase()
				.replaceAll("[ä]", "a")
				.replaceAll("[å]", "a")
				.replaceAll("[ö]", "o")
				.replaceAll("[^A-Za-z0-9 ]", "")	//	Remove rest of special characters 
				.replaceAll(" ", "-");				//	Replace spaces with -
		
//		Add prefix and file type
		fileName = T.get("FILE__MAP_PREFIX") + fileName + ".tkp";
		
		return fileName;
	}
}
