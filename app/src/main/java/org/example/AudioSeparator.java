package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;

public class AudioSeparator extends Application {

    private String inputFile;
    private String outputDir;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Audio Separator");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label inputLabel = new Label("Select Audio File:");
        inputLabel.getStyleClass().add("label");
        grid.add(inputLabel, 0, 1);

        TextField fileNameTextField = new TextField();
        fileNameTextField.setEditable(false);
        fileNameTextField.setText("No file selected");
        fileNameTextField.setPrefWidth(200);
        fileNameTextField.getStyleClass().add("text-field");
        grid.add(fileNameTextField, 1, 1);

        Button inputButton = new Button("Search");
        inputButton.getStyleClass().add("button");
        grid.add(inputButton, 2, 1);

        Label outputLabel = new Label("Select Output Directory:");
        outputLabel.getStyleClass().add("label");
        grid.add(outputLabel, 0, 2);

        TextField outputDirTextField = new TextField();
        outputDirTextField.setEditable(false);
        outputDirTextField.setText("No output directory selected");
        outputDirTextField.setPrefWidth(200);
        outputDirTextField.getStyleClass().add("text-field");
        grid.add(outputDirTextField, 1, 2);

        Button outputButton = new Button("Browse");
        outputButton.getStyleClass().add("button");
        grid.add(outputButton, 2, 2);

        HBox thirdLine = new HBox(15);
        thirdLine.setAlignment(Pos.CENTER);
        Button runButton = new Button("Run");
        runButton.getStyleClass().add("button");
        thirdLine.getChildren().add(runButton);
        
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(grid, thirdLine);

        outputButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(stage);
            if (selectedDirectory != null) {
                outputDir = selectedDirectory.getAbsolutePath();
                outputDirTextField.setText(outputDir);
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
            SeparatorThread sT = new SeparatorThread(inputFile, outputDir, runButton);
            runButton.setDisable(true);
            sT.start();
        });

        Scene scene = new Scene(mainLayout, 750, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
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
