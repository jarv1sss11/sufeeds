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

public class topiccontroller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showTopics();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

        @FXML
        private Button Clear_topics;

        @FXML
        private TableView<Topics> Table_Topics;

        @FXML
        private TextField admn_topic;

        @FXML
        private Button alter_topics;

        @FXML
        private Button btn_classTopics;

        @FXML
        private TextField class_topic;

        @FXML
        private TableColumn<Topics, String> col_com_topic;

        @FXML
        private TableColumn<Topics, String> col_digits_topic;

        @FXML
        private TableColumn<Topics, String> col_name_topic;

        @FXML
        private TableColumn<Topics, String> col_stud_topic;

        @FXML
        private TextField comment_topic;

        @FXML
        private Button create_topic;

        @FXML
        private Button logout_topics;

        @FXML
        private Button remove_topics;

        @FXML
        private TextField topic_topic;

        @FXML
        private VBox vbox_topics;

        int topic_id;
        Alert alert;

    @FXML
    void AlterTopics(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (admn_topic.getText().isEmpty() || class_topic.getText().isEmpty() || topic_topic.getText().isEmpty() || comment_topic.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the input fields");
            alert.showAndWait();
        }
        else {

            String update = "UPDATE `tbl_topics` SET `Topic_Name`=?,`Comment`=?,`Class_digits`=?,`Student_Admn`=? WHERE `topic_id=?`";
            con = databaseconnection.getCon();
            pst = con.prepareStatement(update);
            pst.setString(1, topic_topic.getText());
            pst.setString(2, comment_topic.getText());
            pst.setString(3, class_topic.getText());
            pst.setString(4, col_stud_topic.getText());
            pst.setInt(5, topic_id);
            pst.executeUpdate();
            showTopics();
            clear();

        }
    }

    void clear() {
        admn_topic.setText(null);
        class_topic.setText(null);
        comment_topic.setText(null);
        topic_topic.setText(null);
    }

        @FXML
        void clearTopics(ActionEvent event) {
            clear();

        }



        @FXML
        void createTopics(ActionEvent event) throws SQLException, ClassNotFoundException {

            if (admn_topic.getText().isEmpty() || class_topic.getText().isEmpty() || topic_topic.getText().isEmpty() || comment_topic.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the input fields");
                alert.showAndWait();
            } else {
                String insert = "INSERT INTO `tbl_topics`( `Topic_Name`, `Comment`, `Class_digits`, `Student_Admn`) VALUES (?,?,?,?)";
                con = databaseconnection.getCon();
                pst = con.prepareStatement(insert);
                pst.setString(1, topic_topic.getText());
                pst.setString(2, comment_topic.getText());
                pst.setString(3, class_topic.getText());
                pst.setString(4, admn_topic.getText());
                pst.executeUpdate();
                clear();
                showTopics();

            }
        }

        @FXML
        void logout(ActionEvent event) throws Exception {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" Message");
            alert.setHeaderText(null);
            alert.setContentText("Log Out process has been well executed.");
            alert.showAndWait();
            new switchpages(vbox_topics,"Log-in.fxml");

        }

        @FXML
        void onSwitchclassTo(ActionEvent event) throws Exception {

                new switchpages(vbox_topics, "classes.fxml");

        }

        @FXML
        void removeTopics(ActionEvent event) throws SQLException, ClassNotFoundException {
            String delete = "DELETE FROM `tbl_topics` WHERE topic_id = ?";
            con = databaseconnection.getCon();
            pst = con.prepareStatement(delete);
            pst.setInt(1,topic_id);
            pst.executeUpdate();
            showTopics();
            clear();

        }

    public ObservableList<Topics> getTopics() throws SQLException, ClassNotFoundException {
        ObservableList<Topics> topicslist = FXCollections.observableArrayList();

        String query = "SELECT * FROM `tbl_topics`";
        con = databaseconnection.getCon();
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();
        while(rs.next()){
            Topics tpcs = new Topics();
            tpcs.setTopic_id(rs.getInt("topic_id"));
            tpcs.setComment(rs.getString("Comment"));
            tpcs.setTopic_Name(rs.getString("Topic_Name"));
            tpcs.setStudent_Admn(rs.getString("Student_Admn"));
            tpcs.setClass_digits(rs.getString("Class_digits"));
            topicslist.add(tpcs);
        }
        return topicslist;
    }
    public void showTopics() throws SQLException, ClassNotFoundException {
        ObservableList<Topics> list = getTopics();
        Table_Topics.setItems(list);

        col_digits_topic.setCellValueFactory(new PropertyValueFactory<Topics,String>("Class_digits"));
        col_stud_topic.setCellValueFactory(new PropertyValueFactory<Topics,String>("Student_Admn"));
        col_name_topic.setCellValueFactory(new PropertyValueFactory<Topics,String>("Topic_Name"));
        col_com_topic.setCellValueFactory(new PropertyValueFactory<Topics,String>("Comment"));

    }
    @FXML
    void getData(MouseEvent event) {
        Topics Topics = Table_Topics.getSelectionModel().getSelectedItem();

        topic_id = Topics.getTopic_id();
        admn_topic.setText(Topics.getStudent_Admn());
        class_topic.setText(Topics.getClass_digits());
        topic_topic.setText(Topics.getTopic_Name());
        comment_topic.setText(Topics.getComment());


    }}

// Rooney Segera Mogaka 189735 icsb
