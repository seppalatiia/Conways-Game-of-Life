package application.type;

public class LifeMap {
	
	
	private String name;
	private String path;
	private boolean allowWarp;

	private int[][] map;
	
	private boolean isBroken = false;
	
	/**
	 * This object is used to store map data
	 * @param name	Name of the map
	 */
	public LifeMap(String name) {
		this.name = name;
	}
	
	/**
	 * Return mapName
	 */
	@Override
	public String toString() {
		return name;
	}
	/**
	 * Set new mapName
	 * @param mapName
	 */
	public void setName(String mapName) {
		this.name = mapName;
	}
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	public boolean getWarp() {
		return allowWarp;
	}
	public void setWarp(boolean allowWarp) {
		this.allowWarp = allowWarp;
	}
	
	
	public boolean isBroken(){
		return isBroken;
	}
	public void setBroken() {
		isBroken = true;
	}
	
	
	public int[][] getMap() {
		return map;
	}
	public void setMap(int[][] map) {
		this.map = map;
	}
	
	/**
	 * This method is used to determine if current map is no-maps map
	 * If it is then the map cannot be edited or deleted
	 * @return
	 */
	public boolean isNoMap(){
		if (path == null) {
			return true;
		}
		return false;
	}
}
