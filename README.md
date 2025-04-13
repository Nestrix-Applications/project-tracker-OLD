# Lightweight Java Application

This is a lightweight Java application designed to run on macOS. Below are the instructions on how to build and run the application.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Gradle 6.0 or higher

## Building the Application

1. Open a terminal and navigate to the project directory:
   ```
   cd /path/to/lightweight-java-app
   ```

2. Build the application using Gradle:
   ```
   ./gradlew build
   ```

## Running the Application

After building the application, you can run it with the following command:

```
java -cp build/libs/lightweight-java-app.jar src.Main
```

## Project Structure

```
lightweight-java-app
├── src
│   └── Main.java        # Entry point of the application
├── build.gradle         # Gradle build configuration
├── settings.gradle      # Gradle project settings
└── README.md            # Project documentation
```

## License

This project is licensed under the MIT License. See the LICENSE file for more details.