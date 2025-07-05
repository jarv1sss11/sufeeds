package com.segera.sufeeds;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signupcontroller {
    Alert alert;

    @FXML
    private TextField adm_signup;

    @FXML
    private TextField fname_signup;

    @FXML
    private TextField lname_signup;

    @FXML
    private Button login_signup;

    @FXML
    private PasswordField pass_signup;

    @FXML
    private Button signup_signup;

    @FXML
    private VBox vbox_signup;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    void signUP(ActionEvent event) throws Exception {
        String insert = "INSERT INTO `tbl_students`(`Student_Admn`, `fname`, `lname`, `password`) VALUES (?,?,?,?)";
        con = databaseconnection.getCon();

        if (adm_signup.getText().isEmpty() || fname_signup.getText().isEmpty() || lname_signup.getText().isEmpty() || pass_signup.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the input fields");
            alert.showAndWait();
        } else {
            if (pass_signup.getText().length() < 8) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password should be at least 8 characters.");
                alert.showAndWait();
            } else {
                String checkData = "SELECT  `Student_Admn`, `fname`,`lname`, `password` FROM `tbl_students` WHERE `Student_Admn` = ? ";
                con = databaseconnection.getCon();
                pst = con.prepareStatement(checkData);
                pst.setString(1, adm_signup.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("A user with registration number " + adm_signup.getText() + " already exists.");
                    alert.showAndWait();
                } else {
                    pst = con.prepareStatement(insert);
                    pst.setString(1, adm_signup.getText());
                    pst.setString(2, fname_signup.getText());
                    pst.setString(3, lname_signup.getText());
                    pst.setString(4, pass_signup.getText());
                    pst.executeUpdate();
                    new switchpages(vbox_signup, "Log-in.fxml");
                }
            }
        }
    }

    @FXML
    void switchToLogin (ActionEvent event) throws Exception {

        new switchpages(vbox_signup, "Log-in.fxml");
                }

            }

// Rooney Segera Mogaka 189735 icsb