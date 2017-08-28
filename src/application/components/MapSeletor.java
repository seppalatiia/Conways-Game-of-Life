package application.components;

import application.LifeAnimation;
import application.logic.files.LoadMaps;
import application.type.LifeMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.ComboBox;

public class MapSeletor extends ComboBox<LifeMap>{
	
	static LoadMaps load = new LoadMaps();

	LifeAnimation animation;
	
	/**
	 * This allows user to select map by comboBox
	 * @param animation	Pass selection to this
	 */
	public MapSeletor(LifeAnimation animation) {
		super(load.getAllMaps());

		this.animation = animation;
		this.animation.setMapSelector(this);
		
//		make the width full sized
		this.setMaxWidth(Double.MAX_VALUE);
		
//		Put the button on the left side of the comBobox
//		This makes the text to be aligned right, so css is used to align text back to left side
		this.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		
//		Select default map
		setMap(null);
		
		addSelectionListener();
	}

	public void setMap(String path){
		
//		If there is no path
		if (path == null) {
//			Select the first item in the combobox
			this.getSelectionModel().selectFirst();
//			Pass the selection to the map
			setSelectedMap();
//			Don't go further in the code
			return;
		}

//		Get all items from this combobox
		ObservableList<LifeMap> temp = this.getItems();
//		Go through all items in the combobox
		for (LifeMap lifeMap : temp) {
//			If given path is same as the lifemap's path
			if (lifeMap.getPath().equals(path)) {
//				Set selected
				this.setValue(lifeMap);
//				Pass the selection to the map
				setSelectedMap();
//				break the loop
				return;
			}
			
		}
	}
	/**
	 * Pass selection to animation
	 */
	private void setSelectedMap(){
//		Pass the selection to the map
		animation.changeMap(this.getValue());
	}
	private void addSelectionListener() {
		this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LifeMap>() {
			
			@Override
			public void changed(ObservableValue <? extends LifeMap>ov, LifeMap arg1, LifeMap selected) {
	
				if (selected != null) {
//					Stop the timeline in case the animation is playing
					animation.setAnimation("stop");
					
//					Set new selected map
					setSelectedMap();
				}
			}
		});
	}

	/**
	 * Reset mapSelector
	 * @param path
	 */
	public void reset(String path) {
//		Remove all elements from selectMap
		this.getItems().clear();
//		Reload all elements for selectMap
		this.getItems().addAll(load.getAllMaps());
		
//		Set selected map based on given path
		setMap(path);
	}
}
