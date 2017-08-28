package application.components.sidebar;

import application.Constants;
import application.LifeAnimation;
import application.components.button.AnimationButton;
import application.components.button.DeleteMapButton;
import application.components.button.EditMapButton;
import application.components.button.MyStyledButton;
import application.components.checkbox.AllowWarpBox;
import application.components.checkbox.ShowShadowBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainRightSideBar extends VBox {
	
	LifeAnimation animation;
	
	private MyStyledButton nextGeneration;
	private AnimationButton start, pause, stop;
	private EditMapButton newMap, editMap;
	private DeleteMapButton deleteMap;
	
	private AllowWarpBox allowWarpBox;
	private ShowShadowBox showShadowBox;

	public MainRightSideBar(LifeAnimation animation) {
		super(Constants.SPACING);
		
		this.animation = animation;
		this.animation.setSideBar(this);
		
		validateComponents();
		addComponents();
		addActions();
		mapSettings();
	}

	private void addActions() {
		nextGeneration.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				animation.updateNextGeneration();
			}
		});
	}

	private void validateComponents() {
		nextGeneration = new MyStyledButton("BUTTON__NEXT_GENERATION", false);
		
		start = new AnimationButton("BUTTON__START", FontAwesomeIcon.PLAY, false, "start", this);
		pause = new AnimationButton("BUTTON__PAUSE", FontAwesomeIcon.PAUSE, true, "pause", this);
		stop = new AnimationButton("BUTTON__STOP", FontAwesomeIcon.STOP, true, "stop", this);
		
		allowWarpBox = new AllowWarpBox(animation);
		showShadowBox = new ShowShadowBox(animation);

		newMap = new EditMapButton("BUTTON__NEW_MAP", FontAwesomeIcon.PLUS, false, animation, true);
		editMap = new EditMapButton("BUTTON__EDIT_MAP", FontAwesomeIcon.EDIT, false, animation, false);
		deleteMap = new DeleteMapButton(false, animation);
	}
	
	/**
	 * Add components to sidebar
	 */
	private void addComponents() {
		this.getChildren().add(nextGeneration);
		this.getChildren().addAll(start, pause, stop);
		this.getChildren().add(allowWarpBox);
		this.getChildren().add(showShadowBox);
		
//		Add an empty space so map editing buttons move to bottom
		Pane emptySpace = new Pane();
		VBox.setVgrow(emptySpace, Priority.ALWAYS);
		this.getChildren().add(emptySpace);
		
		this.getChildren().addAll(newMap,editMap);
		this.getChildren().add(deleteMap);
	}
	
	/**
	 * Set animation's status into what is passed to this class
	 * @param animationStatus
	 */
	public void setAnimationStatus(String animationStatus) {
		animation.setAnimation(animationStatus);
//		If animationStatus is to be set stop
		if (animationStatus.equals("stop")) {
//			repaint map
			animation.resetMap();
		}
	}
	
	/**
	 * Set animation buttons disabled or enabled based on animationStatus
	 * @param animationStatus
	 */
	public void setAnimationButtonsStatus(String animationStatus) {
		switch (animationStatus) {
		case "start":
			start.setDisable(true);
			pause.setDisable(false);
			stop.setDisable(false);
//			Can't use next generation when animation is active
			nextGeneration.setDisable(true);
			break;
		case "pause":
			start.setDisable(false);
			pause.setDisable(true);
			stop.setDisable(false);
			nextGeneration.setDisable(false);
			break;
		case "stop":
			start.setDisable(false);
			pause.setDisable(true);
			stop.setDisable(true);
			nextGeneration.setDisable(false);
			break;
//			this is an exception, this disables all animation buttons, as well as the "edit map" -button
		case "all":
			start.setDisable(true);
			pause.setDisable(true);
			stop.setDisable(true);
			nextGeneration.setDisable(true);
			editMap.setDisable(true);
			break;
		default:
			start.setDisable(false);
			pause.setDisable(true);
			stop.setDisable(true);
			nextGeneration.setDisable(false);
			break;
		}
	}
	public void disableEditButtons(boolean status){
//		If there is no external maps and editButtons are to be disabled
		if (animation.isNoMap() & status == false) {
			newMap.setDisable(status);
			return;
		}
		if (animation.getBroken() & status == false) {
			newMap.setDisable(status);
			deleteMap.setDisable(status);
			return;
		}
		newMap.setDisable(status);
		editMap.setDisable(status);
		deleteMap.setDisable(status);
	}
	
	public void reset() {
		setAnimationButtonsStatus("stop");
		mapSettings();
	}
	
	private void mapSettings() {
		allowWarpBox.setSelected(animation.getWarp());
		
		boolean isEditorOpen = animation.isEditorOpen();
		
//		Set buttons disabled or enabled depending if editor is open
		disableEditButtons(isEditorOpen);
//		If editor is not open
		if (!isEditorOpen) {
//			Set disabled/enabled based on get edit status
			editMap.setDisable(animation.isNoMap());
			deleteMap.setDisable(animation.isNoMap());
		}
		
//		If currently selected map is broken
		if (animation.getBroken()) {
			setAnimationButtonsStatus("all");
		}
		
//		Set the checkboxes disabled based on the broken status
		allowWarpBox.setDisable(animation.getBroken());
		showShadowBox.setDisable(animation.getBroken());
	}
}