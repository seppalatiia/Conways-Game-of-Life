package application.components.button;

import application.Constants;
import application.texts.T;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class MyStyledButton extends Button {
	
	/**
	 * Create a button with only an icon
	 * @param icon
	 */
	public MyStyledButton(FontAwesomeIcon icon) {
		
//		Set button icon
		this.setGraphic(GlyphsDude.createIcon(icon, Constants.BUTTON_ICON_SIZE));
	}
	
	/**
	 * Create a button with text and tooltip
	 * @param textKey
	 * @param isDisabled
	 */
	public MyStyledButton(String textKey, boolean isDisabled) {
		super(T.get(textKey));
		
		this.setDisable(isDisabled);
		
		this.setTooltip(new Tooltip(T.get("TOOLTIP__" + textKey)));
	}
	
	/**
	 * Create a button with icon and tooltip
	 * @param icon
	 * @param tooltipKey
	 */
	public MyStyledButton(FontAwesomeIcon icon, String tooltipKey) {
		
//		Set button icon
		this.setGraphic(GlyphsDude.createIcon(icon, Constants.BUTTON_ICON_SIZE));
		
//		Add tooltip
		this.setTooltip(new Tooltip(T.get(tooltipKey)));

//		Add css class
		this.getStyleClass().add(T.get("CSS_CLASS__NO_BACKGROUND"));
	}
	
	/**
	 * Create a button with icon and tooltip
	 * @param icon
	 * @param tooltipKey
	 */
	public MyStyledButton(MaterialIcon icon, String tooltipKey) {
		
//		Set button icon
		this.setGraphic(GlyphsDude.createIcon(icon, Constants.BUTTON_ICON_SIZE));
		
//		Add tooltip
		this.setTooltip(new Tooltip(T.get(tooltipKey)));
		
//		Add css class
		this.getStyleClass().add(T.get("CSS_CLASS__NO_BACKGROUND"));
	}
}