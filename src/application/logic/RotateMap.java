package application.logic;

import application.Constants;
import application.components.EditorStage;
import application.type.EditMap;

public class RotateMap {

	/**
	 * Rotate map clockwise
	 * @param editor
	 */
	public static void clockwise(EditorStage editor) {
		EditMap editMap = editor.getEditMap();

//		Reverse yHandles and save them in xHandles
		int[] tempXHandles = MyArray.reverse(editMap.getYHandles());
//		save xHandles as yHandles
		int[] tempYHandles = editMap.getXHandles();
		
		int[][] map = editMap.getMap();
		int[][] map2 = new int[Constants.BLOCK_AMOUNT][Constants.BLOCK_AMOUNT];
		
//		Rotate each map cell
		for (int y = 0; y < Constants.BLOCK_AMOUNT; y++) {
			for (int x = 0; x < Constants.BLOCK_AMOUNT; x++) {
				map2[y][x] = map[Constants.BLOCK_AMOUNT-1-x][y];
			}
		}
		
//		Pass changes forward
		setValuesAndRepaintMap(editor, editMap, map2, tempXHandles, tempYHandles);
	}
	
	/**
	 * Rotate map counter-clockwise
	 * @param editor
	 */
	public static void counterClockwise(EditorStage editor) {
		EditMap editMap = editor.getEditMap();

//		save yHandles as xHandles
		int[] tempXHandles = editMap.getYHandles();
//		Reverse xHandles and save them in yHandles
		int[] tempYHandles = MyArray.reverse(editMap.getXHandles());
		
		int[][] map = editMap.getMap();
		int[][] map2 = new int[Constants.BLOCK_AMOUNT][Constants.BLOCK_AMOUNT];
		
//		Rotate each map cell
		for (int y = 0; y < Constants.BLOCK_AMOUNT; y++) {
			for (int x = 0; x < Constants.BLOCK_AMOUNT; x++) {
				map2[y][x] = map[x][Constants.BLOCK_AMOUNT-1-y];
			}
		}
		
//		Pass changes forward
		setValuesAndRepaintMap(editor, editMap, map2, tempXHandles, tempYHandles);
	}

	/**
	 * Set new values to editMap and repaint map so changes can be seen
	 * @param editor
	 * @param editMap
	 * @param map2
	 * @param tempXHandles
	 * @param tempYHandles
	 */
	private static void setValuesAndRepaintMap(EditorStage editor, EditMap editMap, int[][] map2, int[] tempXHandles, int[] tempYHandles){
		editMap.setMap(map2);
		editMap.setXHandles(tempXHandles);
		editMap.setYHandles(tempYHandles);
		
		editor.repaint();
	}
}
