package application.logic.parse;

import application.logic.MyArray;

public class MapTrimmer {

//	Trim inactive cells from map
	/**
	 * Trim inactive cells away from map
	 * @param map
	 * @param inactiveX
	 * @param inactiveY
	 * @return
	 */
	public static int[][] trim(int[][] map, int[] inactiveX, int[] inactiveY) {
		
//		Count the amount of active handles in the arrays
		int activeX = MyArray.countActiveHandles(inactiveX);
		int activeY = MyArray.countActiveHandles(inactiveY);
		
//		Create a map using active handle counts as size
		int[][] mapToSave = new int[activeY][activeX];
		
		int newX = 0;
		boolean countX = false;
		
//		Go though every cell in the map
		for (int y = 0; y < map.length; y++) {
//			This is used to count position when putting data in mapToSave
			int newY = 0;
			for (int x = 0; x < map[y].length; x++) {
//				If both x and y are in range of active cells
				if (inactiveX[x] == 0 & inactiveY[y] == 0) {
//					This indicates that it is safe to start counting x (this is so the inactive cells won't be written in the file)
					countX = true;
					
//					Place cell into mapToSave
					mapToSave[newX][newY] = map[y][x];
					newY++;
				}
			}
//			If it is OK to count x
			if (countX) {
//				count x
				newX++;
			}
		}
		
		return mapToSave;
	}
}
