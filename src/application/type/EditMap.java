package application.type;

public class EditMap extends LifeMap {
	
	private int[] xHandles;
	private int[] yHandles;
	
	/**
	 * Store data for editor
	 * @param name
	 */
	public EditMap(String name) {
		super(name);
	}
	
	/**
	 * Get map editor's horizontal handles
	 * @return	Handles
	 */
	public int[] getXHandles() {
		return xHandles;
	}
	/**
	 * Set editor's horizontal handles
	 * @param xHandles
	 */
	public void setXHandles(int[] xHandles) {
		this.xHandles = xHandles;
	}
	
	/**
	 * Get map editor's vertical handles
	 * @return	Handles
	 */
	public int[] getYHandles() {
		return yHandles;
	}
	
	/**
	 * Set editor's vertical handles
	 * @param yHandles
	 */
	public void setYHandles(int[] yHandles) {
		this.yHandles = yHandles;
	}
}
