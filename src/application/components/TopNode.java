package application.components;

import application.LifeAnimation;
import application.Main;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TopNode extends VBox{
	
	MyStyledTitleBar titleBar;
	
	/**
	 * This is a container for elements shown at the top of UI
	 * @param title			Text shown at top of the ui and taskbar
	 * @param stage			Parent Stage
	 * @param isMainStage	
	 * @param animation
	 */
	public TopNode(String title, Stage stage, boolean isMainStage, LifeAnimation animation) {
		titleBar = new MyStyledTitleBar(title, stage, isMainStage);
		
		MapNameField mapSelectorField = new MapNameField(isMainStage, stage, animation, this);
		
//		Add style sheets
		addStyleSheets(stage.getScene());
		
		this.getChildren().addAll(titleBar, mapSelectorField);
	}
	
	/**
	 * When map is saved pass value thourh this
	 * @param title
	 */
	public void changeTitle(String title){
		titleBar.setTitle(title);
	}
	
	/**
	 * Add css files to given scene
	 * @param scene
	 */
	private void addStyleSheets(Scene scene){
		scene.getStylesheets().add(Main.class.getResource("css/application.css").toExternalForm());
		scene.getStylesheets().add(Main.class.getResource("css/title-bar.css").toExternalForm());
		scene.getStylesheets().add(Main.class.getResource("css/button.css").toExternalForm());
		scene.getStylesheets().add(Main.class.getResource("css/checkbox.css").toExternalForm());
		scene.getStylesheets().add(Main.class.getResource("css/combobox.css").toExternalForm());
		scene.getStylesheets().add(Main.class.getResource("css/textfield.css").toExternalForm());
	}
}