package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Student;
import model.StudentList;
import utilities.DataCenter;

public class NewStudentSignInController {

    @FXML private AnchorPane newStudentSignInPane;
    
    @FXML private Button addBtn;
    @FXML private Button backBtn;
    @FXML private Button enterBtn;
    @FXML private Button removeBtn;

    @FXML private ComboBox<String> courseComboBox;
    @FXML private ListView<String> coursesListView;

    @FXML private Label coursesLbl;
    @FXML private Label newStudentSignInTitle;
    @FXML private Label missingWarningLbl;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField studentIDField;
    
    private DataCenter dc;
    private StudentList studentList;
    
    private ArrayList<String> courses;


    public void initialize(){
    	try {
			dc = DataCenter.getInstance();
	    	studentList = dc.getStudentList();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    	courses = new ArrayList<>();
    	courseComboBox.getItems().setAll("CSE 110", "CSE 118", "CSE 148", "CSE 218", "CSE 248");
    }
    
    
    @FXML
    void addBtnClicked(ActionEvent event) {
    	if (courseComboBox.getValue() != null && !(courses.contains(courseComboBox.getValue()))) {
    		courses.add(courseComboBox.getValue());
    		coursesListView.getItems().add(courseComboBox.getValue());
    	}
    }

    @FXML
    void backBtnClicked(ActionEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/signInScene.fxml"));
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
    	String firstName = firstNameField.getText().trim();
    	String lastName = lastNameField.getText().trim();
    	String studentID = studentIDField.getText().trim();
    	if (firstName.equals("") || lastName.equals("") || studentID.equals("") || courses == null) {
    		missingWarningLbl.setOpacity(1);
    		return;
    	}
    	if (studentID.length() != 8) { //invalid id
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Invalid ID");
    		alert.setHeaderText("This is not a real ID.");
    		alert.setContentText("Please input your ID with 8 numbers.");
    		alert.showAndWait();
    		return;
    	}
    	    	
    	for (int i = 0; i<studentList.getStudentList().size(); i++) { //check if id already exists
    		if (studentList.getStudentList().get(i).getId().equals(studentID)) {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
        		alert.setTitle("Invalid ID");
        		alert.setHeaderText("This ID is already in use.");
        		alert.setContentText("Please input your ID with 8 numbers.");
        		alert.showAndWait();
    		}
    	}
    	String[] temp = new String[courses.size()];
    	courses.toArray(temp);
    	Student student = new Student(firstName, lastName, studentID, temp);
    	studentList.add(student); 
    	dc.backup();
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION); //officially logged out
		alert.setTitle("New Student Added");
		alert.setHeaderText("Welcome " + firstNameField.getText());
		alert.setContentText("You have been added. Please log in.");
		alert.showAndWait();
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/signInScene.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    } 

    @FXML
    void removeBtnClicked(ActionEvent event) {
    	if (coursesListView.getSelectionModel().getSelectedItem() != null) {
    		courses.remove(coursesListView.getSelectionModel().getSelectedIndex());
    		coursesListView.getItems().remove(coursesListView.getSelectionModel().getSelectedIndex());
    	}
    }

}
