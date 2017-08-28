package application.components.button;

import application.Constants;
import application.LifeAnimation;
import application.logic.verify.VerifyAlert;
import application.texts.T;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class DeleteMapButton extends Button {
	
	/**
	 * Create button with delete action
	 * @param isDisabled	is the button disabled
	 * @param animation		
	 */
	public DeleteMapButton(boolean isDisabled, LifeAnimation animation) {
		super(T.get("BUTTON__DELETE_MAP"));
		
//		Set the icon for this button
		this.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.CLOSE, Constants.BUTTON_ICON_SIZE));
		
//		Set disabled status
		this.setDisable(isDisabled);
		
//		Set the button to fit sidebars width
		this.setMaxWidth(Double.MAX_VALUE);
//		Align buttons text to left
		this.setAlignment(Pos.BASELINE_LEFT);
		
//		Add a tooltip
		this.setTooltip(new Tooltip(T.get("TOOLTIP__" + "BUTTON__DELETE_MAP")));
		
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
//				Trigger verification dialog and wait for result
				boolean delete = VerifyAlert.deleteMap();
//				If result indicates OK
				if (delete) {
//					Trigger delete command
					animation.deleteMap();
				}
			}
		});
	}
}