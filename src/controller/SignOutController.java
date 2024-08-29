package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.StudentHistory;
import model.StudentLoggedIn;
import model.StudentLoginQueue;
import utilities.DataCenter;

public class SignOutController {

    @FXML private AnchorPane signOutPane;
    
    @FXML private Button backBtn;
    @FXML private Button enterBtn;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField studentIDField;

    @FXML private Label orLbl;
    @FXML private Label studentSignOutTitle;
    
    private StudentLoggedIn studentLoggedIn;
    private StudentHistory studentHistory;
    private StudentLoginQueue queue;
    private DataCenter dc;
        
    public void initialize(){
    	try {
			dc = DataCenter.getInstance();
	    	queue = dc.getStudentLoginQueue();
	    	studentHistory = dc.getStudentHistory();   
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void backBtnClicked(ActionEvent event){
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/startingScene.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void enterBtnClicked(ActionEvent event) { 
    	if (!(firstNameField.getText().isEmpty()) && !(lastNameField.getText().isEmpty()) && !(studentIDField.getText().isEmpty())) {
      		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Incorrect Login");
    		alert.setHeaderText("Enter in one field. ");
    		alert.setContentText("Please type either your ID or name.");
    		alert.showAndWait();
    		studentIDField.clear();
    		firstNameField.clear();
    		lastNameField.clear();
    		return;
    	}
    	if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
    		studentLoggedIn = queue.find(i -> i.getId().equals(studentIDField.getText().trim()));
    	} else {
    		studentLoggedIn = queue.find(i -> i.getStudent().getFirstName().equals(firstNameField.getText().trim()) &&
        	i.getStudent().getLastName().equals(lastNameField.getText().trim()));
    	}
    	
    	if (studentLoggedIn == null) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Student Not Signed In");
    		alert.setHeaderText("You are not in the queue.");
    		alert.setContentText("Please sign in.");
    		alert.showAndWait();
    		studentIDField.clear();
    		firstNameField.clear();
    		lastNameField.clear();
    		return;
    	}
    	queue.remove(i -> i.equals(studentLoggedIn));
    	studentHistory.add(studentLoggedIn);
    	dc.backup();

    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Goodbye!");
		alert.setHeaderText("You are signed out.");
		alert.setContentText("Successfully signed out.");
		alert.showAndWait();
    	
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/startingScene.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}
