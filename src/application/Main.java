package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.StudentLoginQueue;
import utilities.DataCenter;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/startingScene.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		primaryStage.setOnCloseRequest(e-> {
			DataCenter dc = null;
			StudentLoginQueue queue = null;
			try {
				dc = DataCenter.getInstance();	
				queue = dc.getStudentLoginQueue();
				if (queue != null) {
					for (int i = 0; i<queue.getStudentLoggedInList().size(); i++) {
						dc.getStudentHistory().addAll(queue.removeAll());
						dc.backup();
					}
				}
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
