package application.components;

import application.Constants;
import application.logic.Convert;
import application.logic.PaintMap;
import application.type.EditMap;
import application.type.LifeMap;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class EditorMap extends Canvas {
	
	private GraphicsContext graphics;
	
	private EditMap editMap;
	
	private int[][] map;
	private int[] inactiveX;
	private int[] inactiveY;
	
	/**
	 * Generate EditorMap
	 * @param selectedMap
	 * @param editor
	 */
	public EditorMap(LifeMap selectedMap, EditorStage editor) {
		super(Constants.PREF_EDITOR_CANVAS_SIZE, Constants.PREF_EDITOR_CANVAS_SIZE);
		
		editor.setMap(this);
		graphics = this.getGraphicsContext2D();
		
//		If there is no selected map (In other words: new map is to be created)
		if (selectedMap == null) {
			editMap = Convert.lifeMapToEditMap(new LifeMap(null));
		}
		else {
			editMap = Convert.lifeMapToEditMap(selectedMap);
//			Set map's name as value in textField
			editor.setMapName(editMap.toString());
		}
		map = editMap.getMap();
		inactiveX = editMap.getXHandles();
		inactiveY = editMap.getYHandles();
		
		repaint();
		setMouseClickActions();
	}
	
	/**
	 * Add mouse click actions to this
	 */
	private void setMouseClickActions() {
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double x = event.getX();
				double y = event.getY();
				
//				NOTE: if clicked between 10 and 20, functions below return 0 instead -1
				int x_coord = (int)(x-Constants.HANDLE_LENGTH)/(Constants.BLOCK_SIZE+1);
				int y_coord = (int)(y-Constants.HANDLE_LENGTH)/(Constants.BLOCK_SIZE+1);
				
				int handleArea = Constants.HANDLE_LENGTH;
				
//				if the area is -1, -1 do nothing
				if (x < handleArea & y < handleArea) {}
				
//				activate/deactivate rows
				else if (x < handleArea & y_coord >= 0) {
					inactiveY[y_coord] = activeHandle(inactiveY, y_coord);
				}
//				activate/deactivate columns
				else if (x_coord >= 0 & y < handleArea) {
					inactiveX[x_coord] = activeHandle(inactiveX, x_coord);
				}
//				set cells dead or alive
				else {
					if (inactiveX[x_coord] == 0 & inactiveY[y_coord] == 0) {
						if (map[y_coord][x_coord] == 0) {
							map[y_coord][x_coord] = 1;
						}
						else {
							map[y_coord][x_coord] = 0;
						}
					}
				}
//				Every time editor is clicked map is repainted
				repaint();
			}
		});
	}
	
	/**
	 * This method checks whether given arrays value in position can be changed
	 * i.e. can a row or a column be activated/deactivated
	 * @param handles
	 * @param pos
	 * @return
	 */
	private int activeHandle(int[] handles, int pos){
		int inactive = 0;
		
//		if all handles are inactive, activate any handle clicked
		boolean isAllInactive = true;
		for (int i = 0; i < handles.length; i++) {
			if (handles[i] == 0) { isAllInactive = false;}
		}
		if (isAllInactive) {
			inactive = 0;
			return inactive;
		}
		
		if (handles[pos] == 0) {
//			if the cell is on either side of the map, do it at all situations
//			if not check the cells next to current one
//			This is to prevent random inactive columns in the middle
			if (pos == 0 | pos == handles.length-1) {
				inactive = 1;
			}
			else if (handles[pos-1] == 1 | handles[pos+1] == 1) {
				inactive = 1;
			}
			else {
				inactive = 0;
			}
		} else {
//			if the cell is on either side of the map and the cell next to it is inactive, don't do it
//			This is to prevent random inactive columns in the middle
			if ((pos == 0 & handles[1] == 0) | (pos == handles.length-1 & handles[handles.length-2] == 0)) {
				inactive = 0;
			}
			else if (pos == 0| pos == handles.length-1){
				inactive = 1;
			}
			else if (handles[pos-1] == 0 | handles[pos+1] == 0) {
				inactive = 0;
			}
			else {
				inactive = 1;
			}
		}
		return inactive;
	}
	
	/**
	 * Repaint map
	 */
	public void repaint() {
		PaintMap.mapEditor(graphics, inactiveX, inactiveY, map);
	}
	
	/**
	 * When map is rotated or mirrored map needs to be reseted and repainted
	 */
	public void resetEditMap() {
		map = editMap.getMap();
		inactiveX = editMap.getXHandles();
		inactiveY = editMap.getYHandles();
		
		repaint();
	}
	
	/**
	 * Get editMap
	 * @return
	 */
	public EditMap getEditMap() {
		return editMap;
	}
}
