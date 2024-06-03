package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class SepratorThread extends Thread {
    private String inputFile;
    private String outputDir;
    public SepratorThread(String inputFile, String outputDir){
        this.inputFile = inputFile;
        this.outputDir = outputDir;
    }
    @Override
    public void run(){
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
    }
}
