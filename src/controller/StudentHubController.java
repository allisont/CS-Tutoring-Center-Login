package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Student;
import model.StudentLoggedIn;
import model.StudentLoginQueue;
import utilities.DataCenter;

public class StudentHubController {
	
    @FXML private AnchorPane studentHubPane;
    
    @FXML private Button doneBtn;

    @FXML private Label chooseCourseLbl;
    @FXML private Label missingWarningLbl;
    @FXML private Label topicsLbl;
    @FXML private Label welcomeLbl;

    @FXML private ListView<String> courseListView;
    @FXML private ComboBox<String> instructorComboBox;
    
    @FXML private TextField topicsField;

    private Student student;
    private StudentLoginQueue queue;
    
    public void initialize(){
    	try {
			queue = DataCenter.getInstance().getStudentLoginQueue();
	    	student = DataCenter.getInstance().getStudentLoggedIn();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

    	missingWarningLbl.setOpacity(0);
        instructorComboBox.getItems().setAll("Chen", "Li");
    	
    	welcomeLbl.setText("Welcome " + student.getFirstName() + "!");
    	courseListView.getItems().addAll(student.getCourses());
    }

    @FXML
    void doneBtnClicked(ActionEvent event){
    	String course = courseListView.getSelectionModel().getSelectedItem();
    	String topics = topicsField.getText();
    	String instructor = (String)instructorComboBox.getValue();
    	
    	if (course == null|| topics.equals("") || instructor==null) {
    		missingWarningLbl.setOpacity(1);
        	System.out.println(missingWarningLbl.getOpacity());
    		return;
    	}
    	
    	StudentLoggedIn studentLoggedIn = new StudentLoggedIn(student, instructor, course, topics);
    	queue.getStudentLoggedInList().add(studentLoggedIn);
    	
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

}

    


