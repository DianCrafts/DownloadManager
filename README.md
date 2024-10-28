# Java Download Manager
This is a simple download manager implemented in Java using Swing for the graphical user interface (GUI). The download manager supports multiple file downloads, tracks download progress, and provides options for different UI themes.
(Advanced programing course project- 2020)
## Features
- GUI for managing file downloads
- Track download progress with visual indicators
- Download limit settings
- Different look-and-feel themes for the interface
## Requirements
Java Development Kit (JDK) 8 or higher

## Project Structure
- Main.java: Entry point of the application, initializes the GUI and applies saved settings (like download limits and look-and-feel theme).
- DownloadBanner.java: Displays individual download progress bars and information in the GUI.
- DownloadTask.java: Handles the background download process, managing network requests and file saving.
- FileOutputStream.java: Manages file output and logging.
- Mouse.java: implements the MouseListener interface, allowing it to handle mouse events in a graphical user interface (GUI) application
- JDM.java:  implements an event-driven GUI in Java that manages download tasks, allowing users to add new downloads, configure settings, and manipulate download queues through various buttons and action listeners.
## Installation
Clone the Repository:
```
git clone https://github.com/your-username/JavaDownloadManager.git
cd JavaDownloadManager
```
Compile the Java Files: 
```
javac src/com/company/*.java
```
run:
```
java com.company.Main
```
