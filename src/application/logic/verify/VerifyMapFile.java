package application.logic.verify;

import application.Constants;
import application.texts.T;

public class VerifyMapFile {
//	This class exists only to make the try block in Convert.arrayListToLifeMap a little prettier
	
//	TODO Don't throw error, return error message and pass this line to next method (In case that it is just one line missing)
	/**
	 * Verify that map name is found in the line assigned to it
	 * @param line			Line where value should be found
	 * @return				Value found in line
	 * @throws Exception	If line does not have value presented correctly
	 */
	public static String mapTitle(String line) throws Exception{
		if (!line.contains("Title:")) {
			throw new Exception(T.get("MESSAGE__MAP_NAME_MISSING"));
		}
		return line.replace("Title:", "");
	}
	
//	TODO Don't throw error, assume value as false and pass this line to next method (In case that it is just one line missing)
	/**
	 * Verify that warp is found in the line assigned to it
	 * @param line			Line where value should be found
	 * @return				Value found in line
	 * @throws Exception	If line does not have value presented correctly
	 */
	public static boolean allowWarp(String line) throws Exception{
		if (!line.contains("Warp:")) {
			throw new Exception(T.get("MESSAGE__MAP_WARP_MISSING"));
		}
//		If value is true
		if (line.replace("Warp:", "").equals("true")) {
			return true;
		}
//		Otherwise if value is false
		return false;
	}
	
	/**
	 * Verify that map is not irregular by comparing current line's length to first line's length
	 * @param length		current line's length
	 * @param mapLength		first line's length
	 * @throws Exception
	 */
	public static void mapNotIrregular(int length, int mapLength) throws Exception {
//		If the map row lengths are irregular
		if (length != mapLength) {
			throw new Exception(T.get("MESSAGE__IRREGULAR_MAP"));
		}
	}
	
	/**
	 * Verify that map's size is not bigger than maximum amount of blocks
	 * @param mapLength		Length of map
	 * @param mapHeight		Height of map
	 * @throws Exception
	 */
	public static void mapNotOutOfBounds(int mapLength, int mapHeight) throws Exception {
//		If the map is bigger than the maximum allowed blocks
		if (mapLength > Constants.BLOCK_AMOUNT | mapHeight > Constants.BLOCK_AMOUNT) {
			throw new Exception( T.get("MESSAGE__TOO_BIG_MAP"));
		}
	}
	
	/**
	 * Decides if the map exists, based on the mapLenght. If not, an error is thrown
	 * @param mapLength		Length of map
	 * @throws Exception
	 */
	public static void mapNotZero(int mapLength) throws Exception {
//		If there is no map
		if (mapLength == 0) {
			throw new Exception(T.get("MESSAGE__NO_MAP"));
		}
	}
}
