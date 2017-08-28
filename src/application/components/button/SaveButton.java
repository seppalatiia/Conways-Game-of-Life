package application.components.button;

import application.components.sidebar.EditorSideBar;
import application.texts.T;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class SaveButton extends Button{
	
	/**
	 * Create a Button with save action
	 * @param textKey		text that is shown on button
	 * @param closeAfter	close editor after save
	 * @param saveNew		save map as new
	 * @param editorSideBar	parent
	 */
	public SaveButton(String textKey, boolean closeAfter, boolean saveNew, EditorSideBar editorSideBar) {
		super(T.get(textKey));
		
//		Set the button to fit sidebars width
		this.setMaxWidth(Double.MAX_VALUE);
//		Align buttons text to left
		this.setAlignment(Pos.BASELINE_LEFT);
		
//		Set tooltip for button
		this.setTooltip(new Tooltip(T.get("TOOLTIP__" + textKey)));
		
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				Trigger save command
				editorSideBar.performSaveActions(closeAfter, saveNew);
			}
		});
	}
}