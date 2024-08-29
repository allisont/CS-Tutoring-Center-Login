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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Student;
import model.StudentList;
import model.StudentLoginQueue;
import utilities.DataCenter;

public class SignInController {
	
    @FXML private AnchorPane signInPane;

    @FXML private Button backBtn;
    @FXML private Button enterBtn;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField studentIDField;

    @FXML private Label newStudentLbl;
    @FXML private Label orLbl;
    @FXML private Label signInTitle;
    @FXML private Label warningLbl;
    
    private StudentList studentList;
    private Student studentLoggedIn;
    private DataCenter dc;
    private StudentLoginQueue queue;

    public void initialize() {

        try {
			studentList = DataCenter.getInstance().getStudentList();
        	dc = DataCenter.getInstance();
	       	queue = dc.getStudentLoginQueue();
	       	studentLoggedIn = dc.getStudentLoggedIn();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void backBtnClicked(ActionEvent event) {
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
    		studentLoggedIn = studentList.find(i -> i.getId().equals(studentIDField.getText().trim()));
    	} else {
    		studentLoggedIn = studentList.find(i -> i.getFirstName().equals(firstNameField.getText().trim()) &&
        	i.getLastName().equals(lastNameField.getText().trim()));
    	}
    	
    	if (studentLoggedIn==null) {
    		warningLbl.setOpacity(1);
    		studentIDField.clear();
    		firstNameField.clear();
    		lastNameField.clear();
    		return;
    	}
    	
    	if (queue.find(i -> i.getStudent().equals(studentLoggedIn))!= null) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Sign Out");
    		alert.setHeaderText("You are already signed in.");
    		alert.setContentText("Please sign out.");
    		alert.showAndWait();
    		studentIDField.clear();
    		firstNameField.clear();
    		lastNameField.clear();
    		return;
    	}
    	
		dc.setStudentLoggedIn(studentLoggedIn);		
		
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/studentHub.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void newStudentClicked(MouseEvent event){
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/newStudentSignInScene.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
