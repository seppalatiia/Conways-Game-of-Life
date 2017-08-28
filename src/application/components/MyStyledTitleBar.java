package application.components;

import application.Constants;
import application.components.button.MyStyledButton;
import application.texts.T;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyStyledTitleBar extends ToolBar{
	
	private static final int OFF_SCREEN_MARGIN = 5;
	
	private static final double SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
	private static final double SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

	private static double xOffset = 0;
	private static double yOffset = 0;
	
//	Components used in this class
	private Label header;
	
//	The variables received from the constructor
	private String title;
	private Stage stage;
	private boolean isMainStage;
	
	public MyStyledTitleBar(String title, Stage stage, boolean isMainStage){
		this.title = title;
		this.stage = stage;
		this.isMainStage = isMainStage;
		
		buildTheBar();
		addMouseActions();
	}
	
	private void buildTheBar() {
		
//		Removes the window decorations
		this.stage.initStyle(StageStyle.UNDECORATED);
		
//		Set the title for the window by using this label
		header = new Label(title);
		
//		Add css classes for the title bar elements
		this.getStyleClass().add(T.get("CSS_CLASS__TITLE_BAR"));
		header.getStyleClass().add(T.get("CSS_CLASS__TITLE_BAR"));
		
		
//		Set the height of the title bar
		this.setPrefHeight(Constants.TITLE_BAR_HEIGHT);
		this.setMinHeight(Constants.TITLE_BAR_HEIGHT);
		this.setMaxHeight(Constants.TITLE_BAR_HEIGHT);
		
//		Also set the title for the stage so the title is displayed in the taskbar
		this.stage.setTitle(title);
		
//		The purpose of this pane is to keep the header label and window buttons on the opposite sides
		Pane emptySpace = new Pane();
		HBox.setHgrow(emptySpace, Priority.ALWAYS);
		
//		Add components to the titleBar
		this.getItems().addAll(header, emptySpace, new WindowButtons());
	}
	
	/**
	 * Define the actions for the mouse events
	 */
	private void addMouseActions() {
		
//		When mouse button is pressed save position of the window
		this.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = stage.getX() - event.getScreenX();
				yOffset = stage.getY() - event.getScreenY();
			}
		});
//		When the mouse is dragged move the windows position and change cursor style
		this.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() + xOffset);
				stage.setY(event.getScreenY() + yOffset);
				
//				Change the cursor style to "move"
				stage.getScene().setCursor(Cursor.MOVE);
			}
		});
//		When the mouse is released verify that the window is not too much outside the screen
//		If it is move the window completely to the screen
		this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if (stage.getX() > SCREEN_WIDTH - OFF_SCREEN_MARGIN) {
					stage.setX(SCREEN_WIDTH-stage.getWidth());
				}
				
				if (stage.getX()+stage.getWidth() - OFF_SCREEN_MARGIN < 0) {
					stage.setX(0);
				}
				
				
				if (stage.getY() > SCREEN_HEIGHT - OFF_SCREEN_MARGIN) {
					stage.setY(SCREEN_HEIGHT-stage.getHeight());
				}
				
				if (stage.getY() < 0) {
					stage.setY(0);
				}
				
//				Change the cursor to default
				stage.getScene().setCursor(Cursor.DEFAULT);
			}
		});
	}
	
//	This class creates the buttons for minimizing and closing the stage
	class WindowButtons extends HBox {
		Button minimizeButton;
		Button closeButton;
		
		public WindowButtons() {

//			Create buttons with FontAwesome icons
			minimizeButton = new MyStyledButton(FontAwesomeIcon.MINUS);
			closeButton = new MyStyledButton(FontAwesomeIcon.CLOSE);

			minimizeButton.getStyleClass().add(T.get("CSS_CLASS__TITLE_BAR"));
			closeButton.getStyleClass().add(T.get("CSS_CLASS__TITLE_BAR"));
			
			addButtonActions();
			
//			Add buttons to the container
			this.getChildren().addAll(minimizeButton, closeButton);
		}
		
		/**
		 * Add actions for the buttons
		 */
		private void addButtonActions() {
			minimizeButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
//					Minimize the stage
					stage.setIconified(true);
				}
			});
			
			closeButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent actionEvent) {
//					If the clicked button is in main stage, stop entire program
//					even if an editor is open.
					if (isMainStage) {
						Platform.exit();
					}
//					If the stage is not the main stage, close only this stage
					else {
						stage.close();
					}
				}
			});
		}
	}
	
	/**
	 * When map is saved the title needs to be changed in editor stage
	 * @param newTitle
	 */
	public void setTitle(String newTitle) {
		stage.setTitle(newTitle);
		header.setText(newTitle);
	}
}
