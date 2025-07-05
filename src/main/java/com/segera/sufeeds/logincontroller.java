package com.segera.sufeeds;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logincontroller {

        @FXML
        private TextField admn_login;

        @FXML
        private Button login_login;

        @FXML
        private TextField pass_login;

        @FXML
        private Button signup_login;

    @FXML
    private VBox vbox_login;

        Alert alert;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        @FXML
        void Login(ActionEvent event) throws Exception {
            String query = "SELECT `Student_Admn`, `password` FROM `tbl_students` WHERE `Student_Admn` = ? and `password` = ?";
            con = databaseconnection.getCon();

            if(admn_login.getText().isEmpty() || pass_login.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the input fields");
                alert.showAndWait();
            }else{
                pst = con.prepareStatement(query);
                pst.setString(1,admn_login.getText());
                pst.setString(2,pass_login.getText());
                rs = pst.executeQuery();

                if(rs.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Log In Successful.");
                    alert.showAndWait();

                    new switchpages(vbox_login,"classes.fxml");

                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Welcome to SU Feeds.");
                    alert.showAndWait();
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect student registration number or password.");
                    alert.showAndWait();
                }
            }


        }

    @FXML
    void switchToSignUp(ActionEvent event) throws Exception {
        new switchpages(vbox_login,"sign-up.fxml");
    }


}

// Rooney Segera Mogaka 189735 icsb
