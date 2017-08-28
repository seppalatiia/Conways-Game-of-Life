package application.logic.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import application.logic.MyArray;
import application.logic.parse.Generate;
import application.logic.parse.MapTrimmer;
import application.logic.verify.VerifyAlert;
import application.texts.T;
import application.type.EditMap;

public class SaveMap {

	/**
	 * Convert editMap into lines and save lines to file
	 * @param edit		map to save
	 * @param newFile	is map to be saved in new file
	 * @return
	 */
	public static String save(EditMap edit, boolean newFile) {
//		Count active handles in map
		int activeX = MyArray.countActiveHandles(edit.getXHandles());
		int activeY = MyArray.countActiveHandles(edit.getYHandles());
		
//		Verify that there is cells to be saved
		boolean error = VerifyAlert.noActiveCells(activeX, activeY);
		
//		If there is no cells
		if (error) {
//			By returning null program knows save failed 
			return null;
		}
		
//		Trim inactive rows and columns from map
		int[][] mapToSave = MapTrimmer.trim(edit.getMap(), edit.getXHandles(), edit.getYHandles());
		
//		set file path (this will be null map is saved as new)
		String fileName = edit.getPath();
//		Put everything on array so it can be saved row by row
		String[] fileText = new String[activeY+2];
		
//		Add title and warp to fileText
		fileText[0] = "Title:" + edit.toString();
		fileText[1] = "Warp:" + edit.getWarp();
		
//		Go through every row in map
		for (int x = 0; x < mapToSave.length; x++) {
			
//			Convert map row into text
			String row = Arrays.toString(mapToSave[x]).replace("[", "").replace("]", "").replaceAll(" ", "");
			
//			input the row into the array
			fileText[2+x] = row;
		}
		
//		Attempt to write the file
		try {
			fileName = writeTheMap(fileText, fileName, newFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return fileName;
	}
	
	/**
	 * Perform actual save 
	 * @param text			Lines for the file
	 * @param fileName		file where map is saved
	 * @param newFile		is map to be saved in new file
	 * @return				path to saved file
	 * @throws IOException
	 */
	private static String writeTheMap(String[] text, String fileName, boolean newFile) throws IOException {
		File file;
		
		if (newFile) {
//			Create file using filename generated from map's name
			fileName = T.get("FILE__MAP_PATH") + Generate.fileName(text[0]);
			file = new File(fileName);
			
//			If the file already exists add a count
			int count = 1;
			while (file.exists()) {
//				NOTE: fileName does not change so this is ok
				file = new File(fileName.replace(".tkp", "-" + count + ".tkp"));
				count++;
			}
//			Since this value is used in mapSelector, this is updated so it can actually be used
			fileName = file.getPath();
		} else {
			file = new File(fileName);
			
//			Delete the existing file so the map can be written in new file with same name
			File temp = file;
			temp.delete();
		}
		
//		Create writer
		FileOutputStream stream = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));

//		Use the writer to write the map
		for (int i = 0; i < text.length; i++) {
			writer.write(text[i]);
			writer.newLine();
		}
		
//		Close the writer
		writer.close();
		
//		Return the path for the file
		return fileName;
	}
}
