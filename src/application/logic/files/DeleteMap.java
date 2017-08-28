package application.logic.files;

import java.io.File;

import application.logic.verify.VerifyAlert;
import application.type.LifeMap;

public class DeleteMap {
	
	/**
	 * Delete given map
	 * @param selectedMap
	 * @return
	 */
	public static boolean delete(LifeMap selectedMap) {
		File file = new File(selectedMap.getPath());
		
//		Attempt file deletion
		try {
			file.delete();
		} catch (Exception e) {
			VerifyAlert.showAlert("MESSAGE__MAP_DELETE_FAILED");
			return false;
		}
		return true;
	}
}