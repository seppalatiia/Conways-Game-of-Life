package application.logic;

import application.Constants;
import application.components.EditorStage;
import application.type.EditMap;

public class MirrorMap {
	
	/**
	 * Mirror map vertically
	 * @param editor
	 */
	public static void vertical(EditorStage editor){
		
		EditMap editMap = editor.getEditMap();
		
		int[] tempXHandles = editMap.getXHandles();
//		Reverse yHandles
		int[] tempYHandles = MyArray.reverse(editMap.getYHandles());
		
		int[][] map = editMap.getMap();
		int[][] map2 = new int[Constants.BLOCK_AMOUNT][Constants.BLOCK_AMOUNT];
		
//		Switch rows' position (Top is at bottom etc.)
		for (int y = 0; y < Constants.BLOCK_AMOUNT; y++) {
			map2[y] = map[Constants.BLOCK_AMOUNT-1-y];
		}
		
//		Pass changes forward
		setValuesAndRepaintMap(editor, editMap, map2, tempXHandles, tempYHandles);
	}
	
	/**
	 * Mirror map horizontally
	 * @param editor
	 */
	public static void horizontal(EditorStage editor){
		
		EditMap editMap = editor.getEditMap();

//		Reverse xHandles
		int[] tempXHandles = MyArray.reverse(editMap.getXHandles());
		int[] tempYHandles = editMap.getYHandles();
		
		int[][] map = editMap.getMap();
		int[][] map2 = new int[Constants.BLOCK_AMOUNT][Constants.BLOCK_AMOUNT];
		
//		reverse each row in map
		for (int y = 0; y < Constants.BLOCK_AMOUNT; y++) {
			map2[y] = MyArray.reverse(map[y]);
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
