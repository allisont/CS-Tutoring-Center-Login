package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminLoginController {

    @FXML private AnchorPane adminLoginPane;

    @FXML private Label adminLoginTitle;
    @FXML private Label notAdminLbl;
    @FXML private Label warningLbl;

    @FXML private Button loginBtn;

    @FXML private PasswordField passwordField;
    @FXML private TextField passwordTextField;

    @FXML private CheckBox showPasswordCheckBox;

    @FXML private TextField usernameField;

    public void initialize() {
    	passwordTextField.setManaged(false);
    	passwordTextField.setVisible(false);
    	passwordTextField.managedProperty().bind(showPasswordCheckBox.selectedProperty());
    	passwordTextField.visibleProperty().bind(showPasswordCheckBox.selectedProperty());
    	passwordField.managedProperty().bind(showPasswordCheckBox.selectedProperty().not());
    	passwordField.visibleProperty().bind(showPasswordCheckBox.selectedProperty().not());
    	passwordTextField.textProperty().bindBidirectional(passwordField.textProperty());
    	
    }
    
    @FXML
    void loginBtnClicked(ActionEvent event) {
    	if (usernameField.getText().trim().equals("Admin") && passwordField.getText().trim().equals("SCCC")) {
    		Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/view/adminHubScene.fxml"));
	    		Scene scene = new Scene(root);
	    		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		window.setScene(scene);
	    		window.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		warningLbl.setOpacity(1);
    		usernameField.clear();
    		passwordField.clear();
    	}
    	
    }

    @FXML
    void notAdminClicked(MouseEvent event) {
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

}
