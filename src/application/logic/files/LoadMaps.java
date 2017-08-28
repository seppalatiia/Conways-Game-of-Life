package application.logic.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import application.logic.Convert;
import application.type.LifeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoadMaps {

	/**
	 * Load all maps found in specific external directory
	 * @return	All found maps
	 */
	public ObservableList<LifeMap> getAllMaps() {
		
		ObservableList<LifeMap> maps = FXCollections.observableArrayList();
		
//		Fetch all maps in the external folder
		getAllExternalMaps(maps);
		
//		If getAllExternalMaps did not yield any maps
		if (maps.size() == 0) {
//			Get the no-maps map
			getNoMaps(maps);
		}
		
		return maps;
	}
	
	/**
	 * Fetch all maps in the external folder and add them to given ArrayList
	 * @param maps
	 */
	private void getAllExternalMaps(ObservableList<LifeMap> maps) {
		
//		The directory where the files are located
		File directory = new File("maps");
		

//		If the directory doesn't exist
		if (!directory.exists()) {
			try{
//				Directory is created
				directory.mkdir();
			}
			catch(SecurityException se){
//				If creating directory failed, return without doing anything
//				TODO show error Window
				return;
			}
		}
		

//		Fetch all map (.tkp) files from the directory
		File[] files = directory.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".tkp");
			}
		});
//		Convert data from files into maps
		for (File file : files) {
			
			Scanner scanner;
			try {
//				Create a scanner to read the file
				scanner = new Scanner(file);
//				Read the scanner
				LifeMap map = readMap(scanner, file.getPath());
//				Add map to the ArrayList
				maps.add(map);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get the no-maps map in case there is no maps
	 * @param maps	ArrayList where the map is added
	 */
	private void getNoMaps(ObservableList<LifeMap> maps) {
		String path = "maps/no-maps.tkp";
		
//		Create a scanner to read the file
		InputStream stream = this.getClass().getResourceAsStream(path);
		Scanner scanner = new Scanner(stream);
		
//		Read the scanner
		LifeMap map = readMap(scanner, null);
//		Add map to the ArrayList
		maps.add(map);
	}
	
	/**
	 * Read the map using given scanner
	 * @param scanner	Scanner that is to be read 
	 * @param path		Path to the file
	 * @return
	 */
	private LifeMap readMap(Scanner scanner, String path) {
		
//		All the lines found in the file are placed in this ArrayList
		ArrayList<String> lines = new ArrayList<String>();
		
//		Scan the file line by line
		while (scanner.hasNextLine()) {
//			Place the line into the ArrayList
			lines.add(scanner.nextLine());
		}
//		Close the scanner
		scanner.close();
		
//		Convert the ArrayList into a map
		LifeMap map = Convert.arrayListToLifeMap(lines, path);
		
		return map;
	}

}
