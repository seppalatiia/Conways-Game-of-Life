package application.logic;

import application.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintMap {

	/**
	 * Paint map
	 * @param graphics
	 * @param map			current generation map
	 * @param shadowMap		previous generation map
	 * @param showShadow	Is shadow map shown
	 */
	public static void map(GraphicsContext graphics, int[][] map, int[][] shadowMap, boolean showShadow) {
//		Paint background
		background(graphics, 0);
		
//		In order to put the map to the center it is necessary to calculate the starting point for the map
		int pointZeroY = (int)(Constants.BLOCK_AMOUNT - map.length) / 2;
		int pointZeroX = (int)(Constants.BLOCK_AMOUNT - map[0].length) / 2;
		
//		Go through every cell in map
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				
//				Pick color
				Color color;
				if (map[y][x] == 1) {
					color = Constants.LIVE_CELL_COLOR;
				}
				else if (showShadow & (shadowMap[y][x] == 1)) {
					color = Constants.LIVE_CELL_COLOR.darker().darker().darker();
				}
				else{
					color = Constants.DEAD_CELL_COLOR;
				}
				
//				Paint cell
				cells(graphics, color, x + pointZeroX, y + pointZeroY, 0);
				
			}
		}
	}
	
	/**
	 * Paint cell in given (x,y) coordinate
	 * @param graphics
	 * @param color		Color cell is to be painted
	 * @param x			x position in map
	 * @param y			y position in map
	 * @param offset	Offset for point (0,0)
	 */
	private static void cells(GraphicsContext graphics, Color color, int x, int y, int offset) {
		int xCoord = x * (Constants.BLOCK_SIZE + Constants.BORDER_WIDTH) + offset;
		int yCoord = y * (Constants.BLOCK_SIZE + Constants.BORDER_WIDTH) + offset;
		
		graphics.setFill(color);
		graphics.fillRect(xCoord, yCoord, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
	}
	
	/**
	 * Paint entire background with inactive cells
	 * @param graphics
	 * @param offset	Offset for point (0,0)
	 */
	private static void background(GraphicsContext graphics, int offset) {
		for (int y = 0; y < Constants.BLOCK_AMOUNT; y++) {
			for (int x = 0; x < Constants.BLOCK_AMOUNT; x++) {
				PaintMap.cells(graphics, Constants.INACTIVE_CELL_COLOR, x, y, offset);
			}
		}
	}

	/**
	 * Paint mapEditor
	 * @param graphics
	 * @param inactiveX
	 * @param inactiveY
	 * @param map
	 */
	public static void mapEditor(GraphicsContext graphics, int[] inactiveX, int[] inactiveY, int[][] map) {

//		paint column handles
		for (int x = 0; x < inactiveX.length; x++) {
			handles(x, 0, inactiveX[x], graphics);
		}
//		paint row handles
		for (int y = 0; y < inactiveY.length; y++) {
			handles(y, 1, inactiveY[y], graphics);
		}
//		Paint the map
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[0].length; x++) {
				
//				Pick color
				Color color;
				if (inactiveX[x] != 0 | inactiveY[y] != 0) {
					color = Constants.INACTIVE_CELL_COLOR;
				}
				else if (map[y][x] != 0) {
					color = Constants.LIVE_CELL_COLOR;
				}
				else{
					color = Constants.DEAD_CELL_COLOR;
				}
				
//				Paint cell
				cells(graphics, color,  x, y, Constants.HANDLE_LENGTH);
			}
		}
	}
	
	/**
	 * Paint handles in given direction and position
	 * @param pos			Position of handle
	 * @param direction		Direction of handle (0 for xHandle, 1 for yHandle)
	 * @param handleValue	Is handle active or inactive
	 * @param gc
	 */
	private static void handles(int pos, int direction, int handleValue,GraphicsContext gc){
		int coord = pos * (Constants.BLOCK_SIZE + Constants.BORDER_WIDTH) + Constants.HANDLE_LENGTH;
		
//		Select color based on handle activity
		if (handleValue == 0) {
			gc.setFill(Constants.ACTIVE_HANDLE_COLOR);
		}
		else{
			gc.setFill(Constants.INACTIVE_HANDLE_COLOR);
		}
//		Based on direction the handle will be painted either on top of the map or on left side 
		if (direction == 0) {
			gc.fillRect(coord, 0, Constants.BLOCK_SIZE, Constants.HANDLE_LENGTH);
		}
		else{
			gc.fillRect(0, coord, Constants.HANDLE_LENGTH, Constants.BLOCK_SIZE);
		}
	}
}
