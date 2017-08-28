package application.components.button;


import application.Constants;
import application.components.sidebar.MainRightSideBar;
import application.texts.T;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class AnimationButton extends Button{
	
	/**
	 * Build animation Button
	 * @param textKey		Text for button
	 * @param icon			Icon for button
	 * @param isDisabled	Is button disabled
	 * @param actionCommand	ActionCommand for animation
	 * @param sideBar		Parent
	 */
	public AnimationButton(String textKey, FontAwesomeIcon icon, boolean isDisabled, String actionCommand,
			MainRightSideBar sideBar) {
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
		
//		Add EventHandler
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				Set buttons enabled or disabled based on the animation status
				sideBar.setAnimationButtonsStatus(actionCommand);
//				Change the animation status
				sideBar.setAnimationStatus(actionCommand);
			}
		});
	}
}