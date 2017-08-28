package application.components.button;

import application.Constants;
import application.Container;
import application.LifeAnimation;
import application.components.EditorStage;
import application.texts.T;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class EditMapButton extends Button{
	
	/**
	 * Create a Button with text, icon and edit action
	 * @param textKey		Text for button
	 * @param icon			Icon for button
	 * @param isDisabled	Is button disabled
	 * @param animation
	 * @param newMap		Is editor to be opened with or without currently selected map
	 */
	public EditMapButton(String textKey, FontAwesomeIcon icon, boolean isDisabled, LifeAnimation animation, boolean newMap) {
		super(T.get(textKey));
		
//		Set the icon for this button
		this.setGraphic(GlyphsDude.createIcon(icon, Constants.BUTTON_ICON_SIZE));
		
//		Set disabled status
		this.setDisable(isDisabled);
		
//		Set the button to fit sidebars width
		this.setMaxWidth(Double.MAX_VALUE);
//		Align buttons text to left
		this.setAlignment(Pos.BASELINE_LEFT);
		
//		Add a tooltip
		this.setTooltip(new Tooltip(T.get("TOOLTIP__" + textKey)));
		
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
//				When editor is open edit Buttons will be disabled
				animation.disableEditButtons(true);
//				Build editor
				buildEditor(newMap, animation);
			}
		});
	}
	
	/**
	 * Build editor
	 * @param newMap	Is editor to be opened with or without currently selected map
	 * @param animation
	 */
	private void buildEditor(boolean newMap, LifeAnimation animation) {
		EditorStage editor = new EditorStage(animation);
		Container mapEditor = new Container();
		Scene scene = new Scene(mapEditor);
		editor.setScene(scene);
		
//		If the map is new
		if (newMap) {
//			Build editor
			mapEditor.buildEditorUI(editor, newMap, null);
		}
		else {
//			Build editor otherwise same as in new map, but this time also pass map's path
			mapEditor.buildEditorUI(editor, newMap, animation.getMap());
		}
		animation.setEditorOpen(true);
//		Show editor
		editor.show();
	}
}