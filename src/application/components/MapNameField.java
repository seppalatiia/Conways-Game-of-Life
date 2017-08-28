package application.components;

import application.LifeAnimation;
import application.logic.verify.VerifyAlert;
import application.texts.T;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class MapNameField extends HBox{
	
	Label label;
	TextField mapNameField;
	
	TopNode topNode;
	
	/**
	 * Create name Field</br>
	 * This is called when mainUI and editorUI are created. There are some differences, but here are main ones:</br></br>
	 * When mainUI is created
	 * <ul>
	 * 	<li>MapNameField contains MapSeletor</li>
	 * </ul>
	 * When editUI is created
	 * <ul>
	 * 	<li>MapNameField contains TextField</li>
	 * </ul>
	 * @param isMainStage
	 * @param stage
	 * @param animation
	 * @param topNode
	 */
	public MapNameField(boolean isMainStage, Stage stage, LifeAnimation animation, TopNode topNode){
		this.topNode = topNode;
		
//		If the stage is the primary stage
		if (isMainStage) {
			label = new Label(T.get("LABEL__MAP"));
			
//			Configure mapSelector
			MapSeletor mapSelector = new MapSeletor(animation);
//			Make mapSelector to fill out empty space
			HBox.setHgrow(mapSelector, Priority.ALWAYS);
			
//			Add items to this
			this.getChildren().addAll(label, mapSelector);
		}
//		Otherwise
		else {
			label = new Label(T.get("LABEL__MAP_NAME"));

//			Configure textField
			mapNameField = new TextField();
//			Make textBox to fill out empty space
			HBox.setHgrow(mapNameField, Priority.ALWAYS);

//			Add items to this
			this.getChildren().addAll(label, mapNameField);
			
			((EditorStage)stage).setNameField(this);
		}
	}
	
	/**
	 * Set name in nameField
	 * @param name
	 */
	public void setMapName(String name) {
		mapNameField.setText(name);
//		Since this is called when save is performed it is safe to assume that there is no need for new map
//		And that it is ok to reset the title
		topNode.changeTitle(T.get("TITLE__EDIT_MAP") + name);
	}
	
	/**
	 * Get name from nameField
	 * @return
	 */
	public String getMapName() {
//		Verify name
		String name = VerifyAlert.nameExists(mapNameField.getText());
		
//		If there is value
		if (name != null) {
//			set map name
			mapNameField.setText(name);
		}
		return name;
	}
}
