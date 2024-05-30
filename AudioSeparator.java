import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AudioSeparator {
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java AudioSeparator <input_file> <output_dir>");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputDir = args[1];

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "separate_audio.py", inputFile, outputDir);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
