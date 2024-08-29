package controller;

import java.io.IOException;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.StudentLoggedIn;
import model.StudentLoginQueue;
import utilities.DataCenter;
import model.Student;

public class QueueController {
	
    @FXML private AnchorPane queuePane;

    @FXML private Button adminBtn;
    @FXML private Button signInBtn;
    @FXML private Button signOutBtn;

    @FXML private Label currentTutorLbl;
    @FXML private Label queueLengthLbl;
    @FXML private Label queueTitle;
	
    @FXML private TableView<StudentLoggedIn> queueTableView; //tableview
    @FXML private TableColumn<StudentLoggedIn, LocalTime> timeInColumn;
    @FXML private TableColumn<StudentLoggedIn, Student> studentNameColumn;
    @FXML private TableColumn<StudentLoggedIn, String> courseColumn;
    @FXML private TableColumn<StudentLoggedIn, String> instructorColumn;
    @FXML private TableColumn<StudentLoggedIn, String> topicsColumn;
    
    private StudentLoginQueue queue;
    private DataCenter dc;
	private ObservableList<StudentLoggedIn> tvData = FXCollections.observableArrayList(); //source
    
    public void initialize(){
    	try {
			dc = DataCenter.getInstance();
	    	queue = dc.getStudentLoginQueue();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    	
    	queueTableView.itemsProperty().addListener((obs, oldValue, newValue) -> {
    	    queueLengthLbl.setText("Number of students ahead of you: " + tvData.size());
    	});
    	
    	if (dc.getTutor()!= null) {
        	currentTutorLbl.setText("Current tutor: " + dc.getTutor());
    	}
    	    
    	tvData.addAll(queue.getStudentLoggedInList());
		timeInColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, LocalTime>("timeBegin"));
		studentNameColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, Student>("name"));
		courseColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, String>("course"));
		instructorColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, String>("instructor"));
		topicsColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, String>("topics"));
		queueTableView.setItems(tvData);			
    }
    

    @FXML
    void adminBtnClicked(ActionEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/adminLoginScene.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void signInBtnClicked(ActionEvent event) {
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
    void signOutBtnClicked(ActionEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/signOutScene.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}
