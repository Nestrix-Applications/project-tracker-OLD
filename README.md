# Assignment Tracker

A simple and easy way to keep track of due dates and make a list.
Made for MacOs

## Prerequisites

- Java Development Kit (JDK) 14 or higher

## Project Structure

```
lightweight-java-app
├── src
│   └── Main.java        # Entry point of the application
├── build.gradle         # Gradle build configuration
├── settings.gradle      # Gradle project settings
└── README.md            # Project documentation
```

Note to self


cd "/Volumes/Toshiba HDD/Code/assignment logger/src"
javac AssignmentLogger.java


jar cfm AssignmentLogger.jar manifest.txt AssignmentLogger.class AssignmentLogger\$1.class AssignmentLogger\$2.class

jpackage \
  --type app-image \
  --input . \
  --name AssignmentLogger \
  --main-jar AssignmentLogger.jar \
  --main-class AssignmentLogger

  chmod +w "/Volumes/Toshiba HDD/Code/assignment logger/src"