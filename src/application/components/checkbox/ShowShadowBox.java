package application.components.checkbox;

import application.LifeAnimation;
import application.texts.T;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;

public class ShowShadowBox extends CheckBox{
	
	/**
	 * Create a checkbox that indicates if shadow is shown in map during animation
	 * @param animation
	 */
	public ShowShadowBox(LifeAnimation animation) {
		super(T.get("CHECKBOX__SHOW_SHADOW"));

//		Add a tooltip
		this.setTooltip(new Tooltip(T.get("TOOLTIP__CHECKBOX__SHOW_SHADOW")));
		
//		Add EventHandler
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				Pass selection to animation
				animation.setShowShadow(isSelected());
			}
		});
	}
}
