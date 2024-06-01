package org.example;

import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Audio Separator");

        Label inputLabel = new Label("No file selected");
        Button inputButton = new Button("Select MP3 File");
        inputButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                inputFile = selectedFile.getAbsolutePath();
                inputLabel.setText("Selected file: " + inputFile);
            }
        });

        Label outputLabel = new Label("No output directory selected");
        Button outputButton = new Button("Select Output Directory");
        outputButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if (selectedDirectory != null) {
                outputDir = selectedDirectory.getAbsolutePath();
                outputLabel.setText("Selected output directory: " + outputDir);
            }
        });

        Button runButton = new Button("Run");
        runButton.setOnAction(e -> {
            if (inputFile == null || outputDir == null) {
                System.err.println("Input file or output directory not selected");
                return;
            }

            URL scriptUrl = AudioSeparator.class.getResource("/separate_audio.py");
            if (scriptUrl == null) {
                System.err.println("Could not find Python script.");
                return;
            }

            String pythonScriptPath = new File(scriptUrl.getFile()).getAbsolutePath();

            try {
                ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, inputFile, outputDir);
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();

                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                int exitCode = process.waitFor();
                System.out.println("Exited with code: " + exitCode);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox vbox = new VBox(10, inputButton, inputLabel, outputButton, outputLabel, runButton);
        Scene scene = new Scene(vbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
