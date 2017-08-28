package application.logic;

import java.util.ArrayList;
import java.util.Arrays;

import application.Constants;
import application.logic.verify.VerifyMapFile;
import application.texts.T;
import application.type.EditMap;
import application.type.LifeMap;

public class Convert {
	
	/**
	 * Convert given ArrayList into LifeMap
	 * @param lines		Lines from the file
	 * @param path		Path to the file
	 * @return
	 */
	public static LifeMap arrayListToLifeMap(ArrayList<String> lines, String path) {
		
//		NOTE: position 0 is title, 1 is allow warp, rest is the map
		
//		Prepare variables
		String title = "Title";
		boolean allowWarp = false;
		int[][] map;
		
//		First two lines are reserved to title and warp and rest is for map, thus map size is lineCount-2
//		NOTE: If mapTitle() and allowWarp methods in VerifyMapFile are edited so those won't throw error this has to be changed
		int mapHeight  = lines.size()-2;
		int mapLength = 0;
		
//		Broken status is false by default; it will be changed only if something goes wrong
		boolean isBroken = false;
		
//		Start converting
		try{
//			Validate components
			title = VerifyMapFile.mapTitle(lines.get(0));
			allowWarp = VerifyMapFile.allowWarp(lines.get(1));
			
			map = new int[mapHeight][];
			
//			Go through every line remaining in file
			for (int i = 0; i < map.length; i++) {
//				Convert lines into arrays
				map[i] = stringToIntArray(lines.get(i+2));
				if (mapLength == 0) {
					mapLength = map[i].length;
				}
				
//				Compare current line length to first one
				VerifyMapFile.mapNotIrregular(map[i].length, mapLength);
				
			}
			
//			Verify map is not too big
			VerifyMapFile.mapNotOutOfBounds(mapLength, map.length);
//			Verify map exists
			VerifyMapFile.mapNotZero(mapLength);
			
		} catch (Exception e) {
//			Add error message to title so user can see it 
			if (e.getMessage().contains("For input string: ")) {
//				This is when there is a non numeric character in the map
				title = title + " " + T.get("MESSAGE__NON_NUMERIC_CHARACTER");
			}
			else if (e.getMessage().contains("Index: 0, Size: 0")) {
				title = T.get("MESSAGE__EMPTY_FILE");
			}
			else {
				title = title + " " + e.getMessage();
			}
			
//			Create an empty map so there is a map to use
			map = new int[51][51];
			for (int i = 0; i < map.length; i++) {
				Arrays.fill(map[i], 0);
			}
			isBroken = true;
		}
		
//		Create LifeMap and set values
		LifeMap lifeMap = new LifeMap(title);
		lifeMap.setPath(path);
		lifeMap.setWarp(allowWarp);
		lifeMap.setMap(map);
		
//		If there has been an error during convert, the map is marked as broken
		if (isBroken) {
			lifeMap.setBroken();
		}
		
		return lifeMap;
	}
	
	/**
	 * Convert a String into an int array
	 * This method is only used in map loading so it has a customization in case there is other numbers than 1 or 0
	 * @param line
	 * @return
	 */
	private static int[] stringToIntArray(String line) {
		String[] array = line.split(",");
		
		int[] numbers = new int[array.length];
		
		for (int i = 0; i < array.length; i++) {
			int number = Integer.parseInt(array[i]);
			
//			If the number is not 1 it is 0
//			This is in case there are other numbers in the map
			if (number != 1) {
				number = 0;
			}
			
			numbers[i] = number;
		}
		
		return numbers;
	}
	
	/**
	 * Convert given LifeMap into EditMap
	 * @param selectedMap
	 * @return
	 */
	public static EditMap lifeMapToEditMap(LifeMap selectedMap) {
		
//		Create editMap
		EditMap editMap = new EditMap(selectedMap.toString());
		
//		Set path and warp
		editMap.setWarp(selectedMap.getWarp());
		editMap.setPath(selectedMap.getPath());
		
//		get map from lifeMap
		int[][] map = selectedMap.getMap();
		
//		Create map and handles used in editing
		int[][] editorMap = new int[Constants.BLOCK_AMOUNT][Constants.BLOCK_AMOUNT];
		int[] xHandles = new int[Constants.BLOCK_AMOUNT];
		int[] yHandles = new int[Constants.BLOCK_AMOUNT];
		
//		populate map with only dead cells. If there is a map, required cells will be set as live
		for (int i = 0; i < editorMap.length; i++) {
			Arrays.fill(editorMap[i], 0);
		}
		
		
//		if there is a map to edit, it will be placed to center of the map
		if (map != null) {
			int y_count = map.length;
			int x_count = map[0].length;
			
			int pointZeroX = ((int)(Constants.BLOCK_AMOUNT - x_count) / 2);
			int pointZeroY = ((int)(Constants.BLOCK_AMOUNT - y_count) / 2);
			
//			Set inactive columns
			for (int i = 0; i < editorMap.length; i++) {
				if (pointZeroX > i) { xHandles[i] = 1; }
				else if (pointZeroX + x_count <= i) { xHandles[i] = 1; }
			}
			
//			Set inactive rows
			for (int i = 0; i < editorMap.length; i++) {
				if (pointZeroY > i) { yHandles[i] = 1; }
				else if (pointZeroY + y_count <= i) { yHandles[i] = 1; }
			}
			
//			Fill the active portion
			for (int y = 0; y < map.length; y++) {
				for (int x = 0; x < map[y].length; x++) {
					editorMap[pointZeroY+y][pointZeroX+x] = map[y][x];
				}
			}
		}
		else {
//			sets all rows and columns active
			Arrays.fill(xHandles, 0);
			Arrays.fill(yHandles, 0);
			
		}
		editMap.setMap(editorMap);
		editMap.setXHandles(xHandles);
		editMap.setYHandles(yHandles);
		
		return editMap;
	}
}