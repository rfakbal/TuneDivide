package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class AudioSeparator extends Application {

    private String inputFile;
    private String outputDir;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Audio Separator");

        VBox mainLayout = new VBox(10);

        HBox firstLine = new HBox(15);

        firstLine.setAlignment(Pos.CENTER);

        Label inputLabel = new Label("Select Audio File : ");
        Button inputButton = new Button("Search");

        TextField fileNameTextField = new TextField();
        fileNameTextField.setEditable(false);
        fileNameTextField.setText("No file selected");

        firstLine.getChildren().addAll(inputLabel, fileNameTextField, inputButton);


        HBox secondLine = new HBox(15);
        secondLine.setAlignment(Pos.CENTER);
        Label outputLabel = new Label("No output directory selected");

        Button outputButton = new Button("Select Output Directory");

        secondLine.getChildren().addAll(outputLabel, outputButton);

        HBox thirdLine = new HBox(15);
        Button runButton = new Button("Run");
        thirdLine.setAlignment(Pos.CENTER);
        thirdLine.getChildren().add(runButton);
        

        outputButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(stage);
            if (selectedDirectory != null) {
                outputDir = selectedDirectory.getAbsolutePath();
                outputLabel.setText("Selected output directory: " + outputDir);
            }
        });

        inputButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                inputFile = selectedFile.getAbsolutePath();
                fileNameTextField.setText(selectedFile.getName());
            }
        });

        runButton.setOnAction(e -> {
            if (inputFile == null || outputDir == null) {
                System.err.println("Input file or output directory not selected");
                return;
            }
            SeparatorThread sT = new SeparatorThread(inputFile, outputDir,runButton);
            runButton.setDisable(true);
            sT.start();
        });

        
        mainLayout.getChildren().addAll(firstLine,secondLine,thirdLine);
        Scene scene = new Scene(mainLayout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
