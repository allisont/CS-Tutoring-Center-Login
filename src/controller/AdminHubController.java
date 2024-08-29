package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.StudentHistory;
import model.StudentLoggedIn;
import model.StudentLoginQueue;
import utilities.DataCenter;

public class AdminHubController {

    @FXML private BorderPane adminHubPane;
    @FXML private AnchorPane exportReportAnchorPane;
    @FXML private AnchorPane reportsAnchorPane;
    @FXML private AnchorPane tutorsAnchorPane;

    @FXML private Button closeProgramBtn;
    @FXML private Button enterBtn;
    @FXML private Button exportBtn;
    @FXML private Button generateBtn;
    @FXML private Button logoutBtn;

    @FXML private Label closeProgramLbl1;
    @FXML private Label closeProgramLbl2;
    @FXML private Label currentTutorLbl;
    @FXML private Label dailyActivityTitle;
    @FXML private Label toLbl;
    @FXML private Label totalHoursLbl;
    
    @FXML private TableView<StudentLoggedIn> exportReportTableView;
    @FXML private TableColumn<StudentLoggedIn, String> courseColumn;
    @FXML private TableColumn<StudentLoggedIn, LocalDate> dateColumn;
    @FXML private TableColumn<StudentLoggedIn, String> idColumn;
    @FXML private TableColumn<StudentLoggedIn, String> instructorColumn;
    @FXML private TableColumn<StudentLoggedIn, String> studentColumn;
    @FXML private TableColumn<StudentLoggedIn, String> topicsColumn;
    @FXML private TableColumn<StudentLoggedIn, LocalTime> timeInColumn;
    @FXML private TableColumn<StudentLoggedIn, LocalTime> timeOutColumn;

    @FXML private TabPane tabPane;
    @FXML private TabPane reportsTabPane;

    @FXML private Tab exportReportTab;
    @FXML private Tab reportsTab;
    @FXML private Tab trafficActivityTab;
    @FXML private Tab tutorsTab;

    @FXML private ComboBox<String> firstDayComboBox;
    @FXML private ComboBox<String> firstMonthComboBox;
    @FXML private ComboBox<String> secondDayComboBox;
    @FXML private ComboBox<String> secondMonthComboBox;

    @FXML private TextField firstNameField;
    @FXML private TextField firstYearField;
    @FXML private TextField secondYearField;

    @FXML private Line line;
    
    private DataCenter dc;
    private StudentHistory studentHistory;
    private StudentLoginQueue queue;
	private ObservableList<StudentLoggedIn> tvData = FXCollections.observableArrayList(); //source
	private FileChooser fc;
	
	private LocalDate firstDate;
	private LocalDate secondDate;
	private String totalHours;
    
    public void initialize() {
    	try {
			dc = DataCenter.getInstance();
			studentHistory = dc.getStudentHistory();
			queue = dc.getStudentLoginQueue();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	firstDate = null;
    	secondDate = null;
    	
    	firstMonthComboBox.getItems().setAll("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
    	secondMonthComboBox.getItems().setAll("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
    	firstDayComboBox.getItems().setAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
    		"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
    		"29", "30", "31");
    	secondDayComboBox.getItems().setAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
        		"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
        		"29", "30", "31");
    	
		courseColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, String>("course"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, LocalDate>("date"));
		idColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, String>("id"));
		instructorColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, String>("instructor"));
		studentColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, String>("name"));
		topicsColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, String>("topics"));
		timeInColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, LocalTime>("timeBegin"));
		timeOutColumn.setCellValueFactory(new PropertyValueFactory<StudentLoggedIn, LocalTime>("timeEnd"));
		exportReportTableView.setItems(tvData);

		
		FileChooser.ExtensionFilter fcFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		fc = new FileChooser();
		fc.getExtensionFilters().add(fcFilter);
		fc.setInitialDirectory(new File("src/export"));
				
    }

    @FXML
    void closeProgramBtnClicked(ActionEvent event) {
    	studentHistory.getStudentHistoryList().addAll(queue.removeAll());
    	dc.backup();
    	
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Goodbye");
		alert.setHeaderText("You have closed the program");
		alert.setContentText("All students will be logged out.");
		alert.showAndWait();
		
		Stage stage = (Stage) closeProgramBtn.getScene().getWindow();
	    stage.close();
    }

    @FXML
    void enterBtnClicked(ActionEvent event) {
    	if (firstNameField.getText().isEmpty()) {
    		return;
    	}
    	dc.setTutor(firstNameField.getText());
    }

    @FXML
    void exportBtnClicked(ActionEvent event) {
		if (firstDate == null || secondDate == null) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Export Unsuccessful");
    		alert.setHeaderText("Cannot export an empty report");
    		alert.setContentText("Please choose dates to create a report.");
    		alert.showAndWait();
    		return;
		}
    	File file = null;
    	try {
        	file = fc.showOpenDialog((Stage) exportBtn.getScene().getWindow());
    	} catch (Exception e) {
    		;
    	}
    	if (file != null) { //if press cancel
    		exportFile(file);
    	}
    	
    }
    
	private void exportFile(File file) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			if (firstDate.equals(secondDate)) {
				bw.write("Generated Report: " + firstDate);
			} else {
				bw.write("Generated Report: " + firstDate + " to " + secondDate);
			}
			bw.newLine();
			bw.write("Total Hours: " + totalHours);
			bw.newLine();
			for (int i = 0; i<tvData.size(); i++) {
				bw.write(tvData.get(i).toString());
				bw.newLine();
			}
		} catch (IOException e) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
    		alert.setTitle("Export Unsuccessful");
    		alert.setHeaderText("Export was unsuccessful");
    		alert.setContentText("Please choose another file to export to.");
    		alert.showAndWait();
		} 
	}

	@FXML
    void generateBtnClicked(ActionEvent event) {
    	try {
    		//first date
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d uuuu");
    		String tempFirstDate = firstMonthComboBox.getValue() + " " + firstDayComboBox.getValue() + " " + firstYearField.getText().trim();
    		firstDate = LocalDate.parse(tempFirstDate, formatter);
    		
    		//second Date
    		String tempSecondDate = secondMonthComboBox.getValue() + " " + secondDayComboBox.getValue() + " " + secondYearField.getText().trim();
    		secondDate = LocalDate.parse(tempSecondDate, formatter);
    		
    		Predicate<StudentLoggedIn> predicate = i -> !(i.getDate().isBefore(firstDate)) && !(i.getDate().isAfter(secondDate));
    		totalHours = studentHistory.generateTimeStats(predicate);
    		totalHoursLbl.setText("Total hours: " + totalHours);
    		tvData.setAll(studentHistory.generateStudentStats(predicate));
    		
    	} catch (Exception e) {
    		;
    	}
    }

    @FXML
    void logoutBtnClicked(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/startingScene.fxml"));
		Scene scene = new Scene(root);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
    }

}
