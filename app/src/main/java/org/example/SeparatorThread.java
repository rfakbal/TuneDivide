package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;

public class SeparatorThread extends Thread {
    private String inputFile;
    private String outputDir;
    private Button button;
    private ImageView loadingImageView;
    private String model;
    public SeparatorThread(String inputFile, String outputDir,Button button, ImageView loadingImageView,String model){
        this.inputFile = inputFile;
        this.outputDir = outputDir;
        this.button= button;
        this.loadingImageView = loadingImageView;
        this.model = model;
    }

    @Override
    public void run(){
        button.setDisable(true);
         URL scriptUrl = AudioSeparator.class.getResource("/"+model+".py");
            if (scriptUrl == null) {
                System.err.println("Could not find Python script.");
                button.setDisable(false);
                loadingImageView.setVisible(false);
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
            button.setDisable(false);
            loadingImageView.setVisible(false);
    }
}
