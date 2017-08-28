package application.components.sidebar;

import application.Constants;
import application.components.EditorStage;
import application.components.RotateAndFlipMapToolBar;
import application.components.button.SaveButton;
import application.components.checkbox.AllowWarpBox;
import javafx.scene.layout.VBox;

public class EditorSideBar extends VBox {

	private EditorStage editor;

	private SaveButton save, saveAsNew, saveAndClose;
	
	private AllowWarpBox allowWarpBox;
	
	private RotateAndFlipMapToolBar rotateFlipToolbar;
	
	/**
	 * This is the sideBar displayed in editorStage
	 * @param editor
	 */
	public EditorSideBar(EditorStage editor) {
		super(Constants.SPACING);
		
		this.editor = editor;

		validateComponents();
		addComponents();
	}

	private void validateComponents() {
		save = new SaveButton("BUTTON__SAVE_MAP", false, false, this);
		saveAsNew = new SaveButton("BUTTON__SAVE_AS_NEW", false, true, this);
		saveAndClose = new SaveButton("BUTTON__SAVE_CLOSE", true, false, this);
		
		allowWarpBox = new AllowWarpBox(editor);
		
		rotateFlipToolbar = new RotateAndFlipMapToolBar(editor);
	}
	private void addComponents() {
		this.getChildren().addAll(save, saveAsNew, saveAndClose);
		
		this.getChildren().add(allowWarpBox);
		
		this.getChildren().add(rotateFlipToolbar);
	}
	
	/**
	 * Trigger save command and handle what happens after save
	 * @param closeAfter	is the stage to be closed after
	 * @param saveNew		is the map to be saved in new file
	 */
	public void performSaveActions(boolean closeAfter, boolean saveNew) {
		String path = editor.saveMap(saveNew);
		
//		If save was successful
		if (path != null) {
//			If map was saved as new map
			if (editor.getIsNew()) {
//				Set path for map
				editor.getEditMap().setPath(path);
			}
//			Set newStatus false
			editor.setIsNew(false);
			
//			Update mainUI to show new map
			editor.updateMainUI(path);
			
//			While this seems useless, this is actually the way to change the title in titleBar
			editor.setMapName(editor.getMapName());
			
//			If there is command to close stage after save
			if (closeAfter) {
//				Close stage
				editor.close();
			}
		}
	}
}