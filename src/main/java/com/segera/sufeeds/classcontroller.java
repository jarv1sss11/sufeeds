package com.segera.sufeeds;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class classcontroller implements Initializable {

        @FXML
        private Button alter_classes;

        @FXML
        private Button classes_classes;

        @FXML
        private Button clear_classes;

        @FXML
        private TextField code_classes;

        @FXML
        private TableColumn<classes, String> col_cod_classes;

        @FXML
        private TableColumn<classes, String> col_name_id;

        @FXML
        private Button create_classes;

        @FXML
        private Button logout_classes;

        @FXML
        private TextField name_classes;

        @FXML
        private Button remove_classes;

        @FXML
        private TableView<classes> tbl_classes;

        @FXML
        private Button topic_classes;

        @FXML
        private VBox vbox_class;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        int class_id;
        Alert alert;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showClasses();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        @FXML
        void Logout(ActionEvent event) throws Exception {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" Message");
            alert.setHeaderText(null);
            alert.setContentText("Log Out process has been well executed.");
            alert.showAndWait();
            new switchpages(vbox_class,"Log-in.fxml");

        }
    void clear(){
        code_classes.setText(null);
        name_classes.setText(null);
    }
        @FXML
        void clearfield(ActionEvent event) {
        clear();

        }

        @FXML
        void createclasses(ActionEvent event) throws SQLException, ClassNotFoundException {
            if (code_classes.getText().isEmpty() || name_classes.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the input fields");
                alert.showAndWait();
            } else {


                String insert = "INSERT INTO `tbl_classes`(`Class_digits`,`class_Name`) VALUES (?,?)";
                con = databaseconnection.getCon();
                pst = con.prepareStatement(insert);
                pst.setString(1, code_classes.getText());
                pst.setString(2, name_classes.getText());
                pst.executeUpdate();
                clear();
                showClasses();
            }

        }

        @FXML
        void deleteclasses(ActionEvent event) throws SQLException, ClassNotFoundException {
            String delete = "DELETE FROM `tbl_classes` WHERE class_id = ?";
            con = databaseconnection.getCon();
            pst = con.prepareStatement(delete);
            pst.setInt(1,class_id);
            pst.executeUpdate();
            showClasses();
            clear();

        }

        @FXML
        void onswitchToclasses(ActionEvent event) throws Exception {
            new switchpages(vbox_class, "classes.fxml");
        }

        @FXML
        void onswitchTopics(ActionEvent event) throws Exception {
            new switchpages(vbox_class, "topics.fxml");

        }

        @FXML
        void updateclasses(ActionEvent event) throws SQLException, ClassNotFoundException {

            if (code_classes.getText().isEmpty() || name_classes.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the input fields");
                alert.showAndWait();
            } else {


                String update = "UPDATE `tbl_classes` SET `Class_digits`=? , `class_Name`= ? WHERE `class_id`= ?";
                con = databaseconnection.getCon();
                pst = con.prepareStatement(update);
                pst.setString(1, code_classes.getText());
                pst.setString(2, name_classes.getText());
                pst.setInt(3, class_id);
                pst.executeUpdate();
                showClasses();
                clear();

            }
        }

    public ObservableList<classes> getClasses() throws SQLException, ClassNotFoundException {
        ObservableList<classes> classeslist = FXCollections.observableArrayList();

        String query = "SELECT * FROM `tbl_classes`";
        con = databaseconnection.getCon();
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();
        while(rs.next()){
            classes cl = new classes();
            cl.setClass_id(rs.getInt("class_id"));
            cl.setClass_digits(rs.getString("Class_digits"));
            cl.setClass_Name(rs.getString("class_Name"));
            classeslist.add(cl);
        }
        return classeslist;
    }
    public void showClasses() throws SQLException, ClassNotFoundException {
        ObservableList<classes> list = getClasses();
        tbl_classes.setItems(list);

        col_cod_classes.setCellValueFactory(new PropertyValueFactory<classes,String>("Class_digits"));
        col_name_id.setCellValueFactory(new PropertyValueFactory<classes,String>("class_Name"));

    }
    @FXML
    void getData(MouseEvent event) {
        classes classes = tbl_classes.getSelectionModel().getSelectedItem();

        class_id = classes.getClass_id();
        code_classes.setText(classes.getClass_digits());
        name_classes.setText(classes.getClass_Name());


    }}
// Rooney Segera Mogaka 189735 icsb