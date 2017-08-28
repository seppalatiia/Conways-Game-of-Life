package application.components;

import application.Constants;
import application.LifeAnimation;
import application.logic.PaintMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class MainMap extends Canvas {
	
	private GraphicsContext graphics;
	
	/**
	 * Create MainMap
	 * @param animation
	 */
	public MainMap(LifeAnimation animation) {
		super(Constants.PREF_CANVAS_SIZE, Constants.PREF_CANVAS_SIZE);
		
		animation.setMap(this);
		graphics = this.getGraphicsContext2D();
	}
	
	/**
	 * Called when map is changed. 
	 * Paint the map
	 * @param map
	 */
	public void changeMap(int[][] map) {
		PaintMap.map(graphics, map, new int[51][51], false);
	}
	
	/**
	 * Called when animation is playing or "Next Generation" -button is pressed.
	 * Paint the map
	 * @param map
	 * @param shadowMap
	 * @param showShadow
	 */
	public void repaintMap(int[][] map, int[][] shadowMap, boolean showShadow){
		PaintMap.map(graphics, map, shadowMap, showShadow);
	}
}
