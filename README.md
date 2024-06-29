[![java](https://img.shields.io/badge/https%3A%2F%2Fimg.shields.io%2Fbadge%2FGradle-21-orange?label=JDK
)]()
[![python](https://camo.githubusercontent.com/17114d65f88908002aa940f4788f9b70fa981688bec65c1e209229e37a6fe204/68747470733a2f2f696d672e736869656c64732e696f2f707970692f707976657273696f6e732f73706c6565746572)]() [![gradle](https://img.shields.io/badge/https%3A%2F%2Fimg.shields.io%2Fbadge%2FGradle-8.7-green?label=Gradle
)]() 

# TuneDivide

Tune Divide is a desktop application designed to separate an audio file into its constituent components using the Spleeter library. This project is aimed at enhancing my skills in GUI design and desktop application development as a computer engineering student.

## Features

- Select an audio file.
- Choose an output directory.
- Select the separation model (2 stems, 4 stems, or 5 stems).
- Click the `Run` button to start the separation process.

## Requirements

- Java 8 or higher
- JavaFX library
- Any Python version compatible with spleeter
- Spleeter library (`spleeter`)

## Setup

1. Clone the project:

    ```bash
    git clone https://github.com/username/audio-separator.git
    cd audio-separator
    ```

2. Install the required Python packages:

    ```bash
    pip install spleeter
    pip install ffmpeg-python
    pip install httpx
    pip install norbert
    pip install pandas
    pip install tensorflow
    pip install typer
    ```

3. Ensure you have JavaFX set up. If not, download it from the [official website](https://openjfx.io/) and follow the installation instructions.

## Running the Application With Using IDE


1. Open the project in your preferred IDE (such as IntelliJ IDEA or Eclipse).

2. Run the `AudioSeparator` class.

3. Use the interface to select an audio file, choose an output directory, and select the desired separation model.

4. Click `Run` to start the separation process. The output will be saved in the specified directory.

## Running the Application With Using Gradle

1. Open a terminal in the project directory and execute the following command: 
   ```bash
    gradle run

    (This command will compile the project, set up the necessary dependencies, and run the `AudioSeparator` application.)
    (Don't forget to edit the gradle.build file according to your own system specifications)

2. Use the interface to select an audio file, choose an output directory, and select the desired separation model.

3. Click `Run` to start the separation process. The output will be saved in the specified directory.
## Learning Objectives

As a computer engineering student, I created this project to achieve and practice the following learning objectives :

- **GUI Design**: To design an intuitive and user-friendly graphical interface using JavaFX.
- **Multithreading**: To implement multithreading in a desktop application to maintain responsiveness during long-running tasks.
- **Integration of Java and Python**: To integrate Java with Python scripts and learn how to execute Python code from a Java application.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
