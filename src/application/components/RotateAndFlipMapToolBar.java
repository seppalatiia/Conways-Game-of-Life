package application.components;

import application.Constants;
import application.components.button.MyStyledButton;
import application.logic.MirrorMap;
import application.logic.RotateMap;
import application.texts.T;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RotateAndFlipMapToolBar extends VBox{

	HBox rotate = new HBox();
	HBox mirror = new HBox();
	
	Label rotateLabel = new Label(T.get("LABEL__ROTATE_MAP"));
	Label mirrorLabel = new Label(T.get("LABEL__MIRROR_MAP"));
	
	Pane emptyRotateSpace = new Pane();
	Pane emptyMirrorSpace = new Pane();
	
	
	MyStyledButton flipClockwise;
	MyStyledButton flipCounterClockwise;
	MyStyledButton mirrorVertical;
	MyStyledButton mirrorHorizontal;
	
	EditorStage editor;
	
	public RotateAndFlipMapToolBar(EditorStage editor){
		super(Constants.SPACING);
		
		this.editor = editor;
		
		HBox.setHgrow(emptyRotateSpace, Priority.ALWAYS);
		HBox.setHgrow(emptyMirrorSpace, Priority.ALWAYS);
		
		flipClockwise = new MyStyledButton(FontAwesomeIcon.ROTATE_RIGHT, "TOOLTIP__BUTTON__ROTATE_CLOCKWISE");
		flipCounterClockwise = new MyStyledButton(FontAwesomeIcon.ROTATE_LEFT, "TOOLTIP__BUTTON__ROTATE_COUNTER_CLOCKWISE");
		mirrorVertical = new MyStyledButton(MaterialIcon.BORDER_HORIZONTAL, "TOOLTIP__BUTTON__MIRROR_VERTICAL");
		mirrorHorizontal = new MyStyledButton(MaterialIcon.BORDER_VERTICAL, "TOOLTIP__BUTTON__MIRROR_HORIZONTAL");

		rotate.getChildren().addAll(rotateLabel, emptyRotateSpace, flipClockwise, flipCounterClockwise);
		mirror.getChildren().addAll(mirrorLabel, emptyMirrorSpace, mirrorVertical, mirrorHorizontal);
		this.getChildren().addAll(rotate, mirror);
		
		addActions();
	}

	private void addActions() {

		flipClockwise.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				RotateMap.clockwise(editor);
			}
		});
		
		flipCounterClockwise.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				RotateMap.counterClockwise(editor);
			}
		});
		mirrorVertical.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MirrorMap.vertical(editor);
			}
		});
		mirrorHorizontal.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MirrorMap.horizontal(editor);
			}
		});
	}
}
