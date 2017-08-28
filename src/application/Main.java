package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		Container root = new Container();
		
//		Configure scene and stage
		try {
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
//		Call the method that builds the stage
		root.buildMainUI(primaryStage);
		
//		Set the stage visible
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
