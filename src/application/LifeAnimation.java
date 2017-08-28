package application;

import application.components.MainMap;
import application.components.MapSeletor;
import application.components.sidebar.MainRightSideBar;
import application.logic.MapCell;
import application.logic.files.DeleteMap;
import application.type.LifeMap;
import javafx.animation.AnimationTimer;

public class LifeAnimation extends AnimationTimer {

	private long lastUpdate = 0 ;
	
	private MapSeletor mapSeletor;
	private MainRightSideBar sideBar;
	private MainMap mainMap;
	
	private boolean animationWarp;
	private boolean animationShowShadow;
	private boolean isEditorOpen = false;
	
	private LifeMap selectedMap;
	
	private int map[][];
	private int shadowMap[][];
	
	/**
	 * Do this when animation is playing
	 */
	@Override
	public void handle(long now) {

//		Add a delay to the animation
		if (now - lastUpdate >= 100_000_000) {
			
			updateNextGeneration();
			lastUpdate = now ;
		}
	}
	/**
	 * Update next generation for lifeMap and repaint map
	 * NOTE: Use settings from sideBar not from lifeMap
	 */
	public void updateNextGeneration() {
		shadowMap = map;
		int tempMap[][] = new int[map.length][map[0].length];
		
//		go through every cell and calculate if they survive
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				tempMap[x][y] = MapCell.doesItSurvive(x, y, map, animationWarp);
			}
		}
//		set the updated map
		map = tempMap;
		mainMap.repaintMap(map, shadowMap, animationShowShadow);
	}
	
	/**
	 * Start, pause, or stop animation based on command
	 * @param animationStatus	command
	 */
	public void setAnimation(String animationStatus) {
		switch (animationStatus) {
		case "start":
			this.start();
			break;
		case "pause":
			this.stop();
			break;
		case "stop":
			this.stop();
			break;
		default:
			break;
		}
	}

	/**
	 * Change the map shown in interface
	 * @param lifeMap
	 */
	public void changeMap(LifeMap lifeMap) {
		selectedMap = lifeMap;
		
		map = selectedMap.getMap();
		shadowMap = new int[map.length][map[0].length];
		animationWarp = selectedMap.getWarp();

		mainMap.changeMap(map);
		sideBar.reset();
	}
	/**
	 * Reset UI for update
	 * @param path	Map that is to be selected
	 */
	public void resetUI(String path) {
//		By resetting mapSelector, desired result is achieved (since it calls changeMap() in this class)
		mapSeletor.reset(path);
	}
	/**
	 * Delete currently selected map
	 */
	public void deleteMap() {
		boolean success = DeleteMap.delete(selectedMap);
		
//		If delete is successful
		if (success) {
			resetUI(null);
		}
	}
	/**
	 * When animation is stopped map is reseted to original
	 * NOTE: sideBar settings are not reseted
	 */
	public void resetMap() {

//		Set the map to the original
		map = selectedMap.getMap();
		
//		Essentially same as resetting to original map
		mainMap.changeMap(map);
	}
	
/*	-----GETTERS_AND-SETTERS--------------------------------------------------------------------------	*/
	
	/**
	 * Determine if currently selected map is broken
	 * @return
	 */
	public boolean getBroken() {
//		This method will be called before the selected map is set, therefore try catch clause
		try {
			return selectedMap.isBroken();
		} catch (NullPointerException e){
			return false;
		}
	}
	
	/**
	 * Set showShadow status. This value determines if shadow is shown during animation
	 * @param selected
	 */
	public void setShowShadow(boolean selected) {
		animationShowShadow = selected;
	}
	
	/**
	 * Set animation warping status. This does not affect selectedMap.
	 * @param selected
	 */
	public void setAnimationWarp(boolean selected) {
		this.animationWarp = selected;
	}
	
	/**
	 * Determine if warping is allowed in animation. User may change this if he pleases. This does not affect selectedMap.
	 * @return	Is warping allowed in animation
	 */
	public boolean getWarp() {
		return animationWarp;
	}
	
	/**
	 * Determine if current map is no-map
	 * @return	Is editing allowed
	 */
	public boolean isNoMap() {
//		This method will be called before the selected map is set, therefore try catch clause
		try {
			return selectedMap.isNoMap();
		} catch (NullPointerException e){
			return false;
		}
	}
	/**
	 * Get currently selected lifeMap
	 * @return	Currently selected lifeMap
	 */
	public LifeMap getMap() {
		return selectedMap;
	}
	/**
	 * Pass command to disable/enable editButtons to sideBar
	 * @param disabled
	 */
	public void disableEditButtons(boolean disabled) {
		sideBar.disableEditButtons(disabled);
	}
	
	public void setMap(MainMap mainMap) {
		this.mainMap = mainMap;
	}
	public void setSideBar(MainRightSideBar mainRightSideBar) {
		this.sideBar = mainRightSideBar;
	}
	public void setMapSelector(MapSeletor mapSeletor) {
		this.mapSeletor = mapSeletor;
	}
	public boolean isEditorOpen() {
		return isEditorOpen;
	}
	public void setEditorOpen(boolean isOpen){
		isEditorOpen = isOpen;
	}
}