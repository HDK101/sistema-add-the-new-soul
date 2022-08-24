package com.example.sistemabarbeirodoos4;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class HelloController {
    @FXML
    private GridPane gridTest;

    public void start() {
        final int size = 100;

        ObservableList<RowConstraints> rows = gridTest.getRowConstraints();
        ObservableList<ColumnConstraints> cols = gridTest.getColumnConstraints();

        rows.clear();
        cols.clear();

        for (int i = 0; i < size; i++) {
            RowConstraints row = new RowConstraints(50, 50, 50);
            ColumnConstraints col = new ColumnConstraints(50, 50, 50);

            rows.add(row);
            cols.add(col);

        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: red; -fx-border-color: black");
                gridTest.add(pane, j, i);
            }
        }
    }
}