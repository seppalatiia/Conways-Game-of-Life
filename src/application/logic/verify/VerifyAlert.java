package application.logic.verify;

import java.util.Optional;

import application.Main;
import application.texts.T;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;

public class VerifyAlert {
	
	/**
	 * Verify that map has a name. if there is no name user is asked to input one
	 * @param nameFieldText	Value found in textfield
	 * @return				User input
	 */
	public static String nameExists(String nameFieldText) {
//		If there is no name
		if (nameFieldText.equals("")) {
//			Ask user to input map name
			String result = showInputError("MESSAGE__CONFIRM_NO_NAME");
//			Return result
			return result;
		}
//		Otherwise return name back
		return nameFieldText;
	}

	/**
	 * Verify that there is active rows and columns in the map by using given arrays
	 * @param activeX
	 * @param activeY
	 * @return
	 */
	public static boolean noActiveCells(int activeX, int activeY) {
//		If there is no active handles in either one
		if (activeX == 0 | activeY == 0) {
//			Can't save an size zero map
//			Show message window
			showAlert("MESSAGE__SAVE_FAILED__NO_MAP");
			return true;
		}
		return false;
	}
	
	/**
	 * Ask user if he truly desires to delete the map
	 * @return	the value received from dialog
	 */
	public static boolean deleteMap(){
		return showConfirmationDialog("MESSAGE__CONFIRM_MAP_DELETE");
	}
	
	/**
	 * Display input error with given message
	 * @param message	message key
	 * @return			user input
	 */
	private static String showInputError(String message) {
		
//		Create dialog
		TextInputDialog alert = new TextInputDialog();

//		Add texts to dialog
		alert.setTitle(T.get(message + "__TITLE"));
		alert.setHeaderText(T.get(message + "__HEADER"));
		alert.setContentText(T.get(message + "__CONTEXT"));

//		Set dialog's style undecorated
		alert.initStyle(StageStyle.UNDECORATED);
		
//		Add css
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(Main.class.getResource("css/dialog.css").toExternalForm());
		dialogPane.getStyleClass().add(T.get("CSS_CLASS__DIALOG"));
		
//		wait for result
		Optional<String> result = alert.showAndWait();
//		If there is input
		if (result.isPresent()){
//			Return text
			return result.get();
		}
//		Otherwise return null
		return null;
	}
	
	/**
	 * Show simple information dialog
	 * @param message	message key
	 */
	public static void showAlert(String message) {
//		Create dialog
		Alert alert = new Alert(AlertType.INFORMATION);
		
//		Add texts to dialog
		setAlertTexts(alert, message);
		
//		Set dialog's style undecorated
		alert.initStyle(StageStyle.UNDECORATED);
		
//		Add css
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(Main.class.getResource("css/dialog.css").toExternalForm());
		dialogPane.getStyleClass().add(T.get("CSS_CLASS__DIALOG"));
		
//		Show dialog and wait for user to acknowledge it
		alert.showAndWait();
		return;
	}
	
	/**
	 * show simple confirmation dialog
	 * @param message	message key
	 * @return			If it is OK to proceed
	 */
	private static boolean showConfirmationDialog(String message) {
//		Create dialog
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
//		Add texts to dialog
		setAlertTexts(alert, message);
		
//		Set dialog's style undecorated
		alert.initStyle(StageStyle.UNDECORATED);
		
//		Add css
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(Main.class.getResource("css/dialog.css").toExternalForm());
		dialogPane.getStyleClass().add(T.get("CSS_CLASS__DIALOG"));
		
//		wait for result
		Optional<ButtonType> result = alert.showAndWait();
//		If result indicates OK
		if (result.get() == ButtonType.OK){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Set texts to given alert
	 * @param alert		Alert where text  are to be added
	 * @param message	message key
	 */
	private static void setAlertTexts(Alert alert, String message){

		alert.setTitle(T.get(message + "__TITLE"));
		alert.setHeaderText(T.get(message + "__HEADER"));
		alert.setContentText(T.get(message + "__CONTEXT"));
	}
}
