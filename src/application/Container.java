package application;

import application.components.EditorMap;
import application.components.EditorStage;
import application.components.MainMap;
import application.components.TopNode;
import application.components.sidebar.EditorSideBar;
import application.components.sidebar.MainRightSideBar;
import application.texts.T;
import application.type.LifeMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Container extends BorderPane{
	
	TopNode topBar;
	VBox sideBar;
	Canvas map;
	
	/**
	 * Build the main stage for the program
	 * @param primaryStage
	 */
	public void buildMainUI(Stage primaryStage) {
		
		LifeAnimation animation = new LifeAnimation();

		map = new MainMap(animation);
		sideBar = new MainRightSideBar(animation);
		topBar = new TopNode(T.get("TITLE__PROGRAM"), primaryStage, true, animation);
		
		addComponents();
	}
	
	/**
	 * Build editor stage
	 * @param editor
	 * @param isNewMap		Is user creating a new map or editing existing one
	 * @param selectedMap	Selected map in case above is edit existing one
	 */
	public void buildEditorUI(EditorStage editor, boolean isNewMap, LifeMap selectedMap) {
//		Determine title for the window
		String title;
		if (isNewMap) {
			title = T.get("TITLE__NEW_MAP");
		} else {
			title = T.get("TITLE__EDIT_MAP") + selectedMap.toString();
		}
		editor.setIsNew(isNewMap);
		
		topBar = new TopNode(title, editor, false, null);
		map = new EditorMap(selectedMap, editor);
		sideBar = new EditorSideBar(editor);

		addComponents();
	}
	
	/**
	 * Add components to container
	 */
	private void addComponents() {
		
//		Add css class for this (This way this class needs to be added only one time)
		this.getStyleClass().add(T.get("CSS_CLASS__PANE"));
		
//		This css class is used to add a little padding so there's space between buttons and map
		sideBar.getStyleClass().add(T.get("CSS_CLASS__SIDEBAR"));
		
//		Add components to this
		this.setTop(topBar);
		this.setRight(sideBar);
		this.setCenter(map);
	}
}
