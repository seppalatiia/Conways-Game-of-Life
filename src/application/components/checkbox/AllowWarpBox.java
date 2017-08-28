package application.components.checkbox;

import application.LifeAnimation;
import application.components.EditorStage;
import application.texts.T;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;

public class AllowWarpBox extends CheckBox{
	
	/**
	 * This is in MainRightSideBar
	 * @param animation
	 */
	public AllowWarpBox(LifeAnimation animation) {
		super(T.get("CHECKBOX__ALLOW_WARP"));
		
//		Add a tooltip
		this.setTooltip(new Tooltip(T.get("TOOLTIP__CHECKBOX__ALLOW_WARP")));
		
//		set box selected based on map's value
		setSelected(animation.getWarp());

//		Add EventHandler
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				Pass selection to animation
				animation.setAnimationWarp(isSelected());
			}
		});
	}
	
	/**
	 * This is in EditorSideBar
	 * @param editor
	 */
	public AllowWarpBox(EditorStage editor) {
		super(T.get("CHECKBOX__ALLOW_WARP"));
		
//		Add a tooltip
		this.setTooltip(new Tooltip(T.get("TOOLTIP__CHECKBOX__ALLOW_WARP")));
		
//		set box selected based on map's value
		setSelected(editor.getWarp());
		
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				Pass selection to editor
				editor.setWarp(isSelected());
			}
		});
	}
}