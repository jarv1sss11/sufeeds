package com.segera.sufeeds;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

    public class switchpages {
        public switchpages(VBox currentVBox, String fxml) throws Exception {
            VBox nextVBox = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxml)));
            currentVBox.getChildren().removeAll();
            currentVBox.getChildren().setAll(nextVBox);
        }
    }

// Rooney Segera Mogaka 189735 icsb