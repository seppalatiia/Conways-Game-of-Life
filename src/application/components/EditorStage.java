package application.components;

import application.LifeAnimation;
import application.logic.files.SaveMap;
import application.type.EditMap;
import javafx.stage.Stage;

public class EditorStage extends Stage{
	
	private LifeAnimation animation;
	
	private boolean newMap;
	
	private MapNameField nameField;
	private EditorMap map;
	
	/**
	 * Build editorStage
	 * @param animation
	 */
	public EditorStage(LifeAnimation animation) {
		this.animation = animation;
	}
	
	/**
	 * When this stage is closed map editing buttons are disabled, so those are set to enabled
	 * NOTE: This is probably bad practice but it works since hide() and close() are practically same
	 * NOTE: This doesn't work other way around
	 */
	@Override
	public void close(){
		animation.disableEditButtons(false);
		animation.setEditorOpen(false);
		this.hide();
//		TODO When closing from close button: check if user has made changes and ask if user is sure he want's discard changes
	}
	
	/**
	 * Prepare map for save. If there is no problems call SaveMap class
	 * @param saveAsNew		If user clicked save as new -button
	 * @return				File path
	 */
	public String saveMap(boolean saveAsNew) {
		EditMap edit = map.getEditMap();
		
		boolean newFile = false;
		String name = getMapName();
		
//		Decide if the map will be saved in new file
		if (saveAsNew | newMap) {
			newFile = true;
		}
		
//		If there is no name for map, do not save
		if (name == null) {
			return null;
		}
//		Set name for editMap
		edit.setName(name);
		
//		Perform save and receive file path as result
		String path = SaveMap.save(edit, newFile);
		
		return path;
	}
	
	/**
	 * Update mainUI so this newly saved map is shown
	 * @param path	Path to saved map
	 */
	public void updateMainUI(String path) {
		animation.resetUI(path);
	}
	
	/**
	 * Reset map after rotating or mirroring
	 */
	public void repaint() {
		map.resetEditMap();
	}
	
/*	-----GETTERS_AND-SETTERS--------------------------------------------------------------------------	*/
	
	/**
	 * Determine if currently shown map is new
	 * @param isNewMap
	 */
	public void setIsNew(boolean isNewMap) {
		newMap = isNewMap;
	}
	public boolean getIsNew() {
		return newMap;
	}
	
	/**
	 * Set map name in textField
	 * @param name	Map name
	 */
	public void setMapName(String name) {
		nameField.setMapName(name);
	}
	/**
	 * Get map name from textField
	 * @return	Name
	 */
	public String getMapName() {
		return nameField.getMapName();
	}
	
	/**
	 * Set user selection to editMap 
	 * @param selected
	 */
	public void setWarp(boolean selected) {
		getEditMap().setWarp(selected);
	}
	/**
	 * When building sideBar this method is called
	 * @return
	 */
	public boolean getWarp() {
		return getEditMap().getWarp();
	}
	
	public EditMap getEditMap() {
		return map.getEditMap();
	}
	
	public void setNameField(MapNameField mapNameField) {
		this.nameField = mapNameField;
	}
	public void setMap(EditorMap editorMap) {
		this.map = editorMap;
	}
}