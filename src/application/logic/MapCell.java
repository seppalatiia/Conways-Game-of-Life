package application.logic;

public class MapCell {

//	Rules: 
//	1: Any live cell with fewer than two live neighbours dies, as if caused by under-population.
//	2: Any live cell with two or three live neighbours lives on to the next generation.
//	3: Any live cell with more than three live neighbours dies, as if by over-population.
//	4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	
//	https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
	
	/**
	 * Calculate if cell survives
	 * @param x			x position
	 * @param y			y position
	 * @param map		map where cell lives
	 * @param allowWarp	if warping is allowed
	 * @return
	 */
	public static int doesItSurvive(int x, int y, int[][]map, boolean allowWarp) {
//		Get live cell count from neighbours
		int value = checkNeighbors(x, y, map, allowWarp);
		
//		If current cell is alive
		if (map[x][y] == 1){
//			If cell count is not 2 or 3
			if (value < 2 | value > 3){
//				Current cell will be dead in next generation
				return 0;
			}
//			Otherwise
			else{
//				Current cell will be alive in next generation
				return 1;
			}
		}
//		In other case, where current cell is dead and three cells in neighbourhood are alive 
		else if(map[x][y] == 0 & value == 3){
//			Current cell will be alive in next generation
			return 1;
		}
//		In any other case current cell will be dead in next generation
		return 0;
	}

	/**
	 * Checks if the neighboring cells are alive and counts the amount
	 * @param x			x position
	 * @param y			y position
	 * @param map		map where cell lives
	 * @param allowWarp	if warping is allowed
	 * @return			live cell count
	 */
	public static int checkNeighbors(int x, int y, int[][]map, boolean allowWarp){
		int value = isAlive(x-1,y-1, map, allowWarp)	// top left corner
				+ isAlive(x,y-1, map, allowWarp)		// top
				+ isAlive(x+1,y-1, map, allowWarp)		// top right corner
				+ isAlive(x+1,y, map, allowWarp)		// right
				+ isAlive(x+1,y+1, map, allowWarp)		// bottom right corner
				+ isAlive(x,y+1, map, allowWarp)		// bottom
				+ isAlive(x-1,y+1, map, allowWarp)		// bottom left corner
				+ isAlive(x-1,y, map, allowWarp);		// left
		
		return value;
	}
	
	/**
	 * Checks if the cell is alive</br>
	 * @param x1
	 * @param y1
	 * @param map
	 * @param allowWarp
	 * @return
	 */
	private static int isAlive(int x1, int y1, int[][]map, boolean allowWarp){
		int x = x1;
		int y = y1;
		
//		Checks if the neighbor is out of the array bounds
		if (x < 0 | y < 0 | x >= map.length | y>= map[0].length) {
			
//			if warp is not allowed, out of bounds cells are considered dead
			if (!allowWarp) {
				return 0;
			}
			
//			if warp is allowed, change the x other end of the map
			if (x < 0){
				x = map.length-1;
			}
			else if (x >= map.length) {
				x = 0;
			}
//			if warp is allowed, change the y other end of the map
			if (y < 0) {
				y = map[x].length-1;
			}
			else if (y >= map[x].length) {
				y = 0;
			}
		}
//		If cell is alive
		if (map[x][y] == 1) {
			return 1;
		}
		
		return 0;
	}
}
