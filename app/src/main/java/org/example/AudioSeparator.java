package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class AudioSeparator {
    
    public static void main(String[] args) {
       /* if (args.length != 2) {
            System.out.println("Usage: java AudioSeparator <input_file> <output_dir>");
            System.exit(1);
        }*/

        String inputFile = "C:/Users/rusen/Downloads/test.mp3";
        String outputDir = "C:/Users/rusen/Desktop/PY_TEST";

         URL scriptUrl = AudioSeparator.class.getResource("/separate_audio.py");
        if (scriptUrl == null) {
            System.err.println("Could not find Python script.");
            System.exit(1);
        }

        // Python betiğinin dosya yolu
        String pythonScriptPath = new File(scriptUrl.getFile()).getAbsolutePath();

        // Python betiğini çalıştırma
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, inputFile, outputDir);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Python betiğinden gelen çıktıyı okuma
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // İşlem tamamlanmasını bekleme
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
