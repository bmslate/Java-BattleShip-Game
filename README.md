# Java BattleShip Game

A Java Swing Battleship desktop application built with an MVC architecture. The project includes a graphical user interface, 10x10 game-board logic, preset and custom ship layouts, player-vs-computer gameplay, turn timer feedback, basic socket-based networking, chat messaging, and English/Chinese localization resources.

## Project Overview

This project implements a desktop Battleship-style game in Java. The application is organized using the **Model-View-Controller (MVC)** pattern:

* **Model**: stores game state, board data, player/computer maps, win conditions, damage status, networking state, and message handling.
* **View**: builds the Swing user interface, including menus, game panels, buttons, images, player information, chat area, timer/progress feedback, map switching, and language-specific text.
* **Controller**: connects the model and view, handles button events, layout selection, game start/restart actions, player-vs-computer mode, turn-based gameplay flow, and server/client setup.
* **Main**: starts the application and initializes the MVC components.

## My Contributions

Although this project was originally developed as an academic Java application project, I completed the core application design and implementation work, including:

* Designed and implemented the MVC-based project structure.
* Implemented the main Java Swing user interface, including menus, gameplay panels, buttons, image-based hit/miss feedback, player/opponent views, chat area, timer/progress feedback, and map switching.
* Implemented the Battleship game-board logic, including 10x10 board management, ship placement, hit/miss handling, damage tracking, turn control, and winner detection.
* Implemented player-vs-computer gameplay and computer board generation.
* Added preset ship layout options and custom ship placement support.
* Implemented turn-based gameplay flow with UI state updates, damage tracking, winner detection, and timer/progress-bar feedback.
* Implemented basic socket-based server/client setup and chat message handling.
* Integrated localization resource files into the application flow.
* Organized the Eclipse project structure, image assets, run instructions, and GitHub documentation.

Localization resource content was contributed by Kexin Huang. The original teammate name is kept in the repository for academic record transparency.

## Technical Highlights

* Applied MVC architecture to separate game state, UI rendering, and event-control logic.
* Used Java Swing and AWT event handling to build an interactive desktop GUI.
* Managed game state through 10x10 board arrays, ship placement rules, hit/miss status, damage tracking, turn control, timer feedback, and win-condition checking.
* Implemented player-vs-computer gameplay with generated opponent board logic.
* Added basic socket communication using server/client classes, background processing, and message handling.
* Used Java `ResourceBundle` files to support English and Chinese UI localization.

## Features

* Java Swing graphical user interface
* MVC-based project structure
* 10x10 Battleship game board
* Player-vs-computer gameplay
* Preset ship layout options
* Custom ship placement support
* Hit and miss image feedback
* Player and opponent damage progress bars
* Turn timer with progress-bar feedback
* Swap button to switch between player and opponent map views
* Restart game support
* Basic socket-based network mode
* Server and client connection setup
* Chat message area and input field
* English and Chinese localization through resource bundle files
* Eclipse project configuration included

## Project Structure

```text
Java-BattleShip-Game/
├── src/
│   ├── Main/
│   │   └── MVCBattleShip.java
│   ├── Controller/
│   │   └── BattleShipController.java
│   ├── Model/
│   │   ├── BattleShipModel.java
│   │   ├── ClientManager.java
│   │   ├── Computer.java
│   │   ├── ServerManager.java
│   │   └── Setting.java
│   ├── View/
│   │   ├── BattleShipView.java
│   │   ├── GameInterFace.java
│   │   ├── ProductionTeam.java
│   │   ├── SettingUI.java
│   │   └── Strategy.java
│   ├── MessagesBundle_en.properties
│   └── MessagesBundle_zh.properties
├── .classpath
├── .project
├── .settings/
├── JAP_CompileScript.bat
├── battleShip.png
├── battleShip1.png
├── battleShip2.png
├── battleShip4.png
├── battleShip5.png
├── battleApplogo.png
├── hit.png
├── miss.png
├── player.png
├── player1.png
└── README.md
```

## Architecture

### Main

`MVCBattleShip.java` is the entry point of the application. It loads the saved language setting, initializes the locale, creates the model, view, strategy layout view, and controller, then starts the game flow.

### Controller

`BattleShipController.java` manages the interaction between the UI and the game model. It handles:

* Main menu buttons
* Preset layout selection
* Custom ship layout selection
* Start game logic
* Player-vs-computer mode
* Turn-based gameplay flow
* Timer/progress feedback control
* Server/client connection setup
* Restart actions
* Swap-map actions
* Winner detection display

### Model

The model package contains the core game state and behavior.

* `BattleShipModel.java` manages board status, ship placement, game rules, win detection, damage tracking, and communication with the UI.
* `Computer.java` creates the computer player's 10x10 map.
* `ServerManager.java` manages server-side socket communication.
* `ClientManager.java` manages client-side socket communication.
* `Setting.java` stores and loads application settings such as language preference.

### View

The view package contains Swing-based UI classes.

* `BattleShipView.java` builds the main menu and top-level game options.
* `GameInterFace.java` builds the main gameplay window, including the game board, player panels, health bars, timer/progress feedback, chat area, and control buttons.
* `Strategy.java` provides preset and custom ship layout options.
* `SettingUI.java` provides language/settings UI.
* `ProductionTeam.java` displays project/team information.

## Game Modes

### Player vs Computer

The game can be started in a local mode where the player plays against a computer-generated opponent board.

### Network Mode

The project includes basic socket-based server/client support.

* One player can start as a server and listen on a selected port.
* Another player can connect as a client using the server address and port.
* Server and client communication is handled through sockets, input/output streams, background processing, and message handling.
* The networking feature demonstrates basic Java socket communication and chat/message exchange.

## Localization

The project includes resource bundle files for multi-language UI text:

```text
MessagesBundle_en.properties
MessagesBundle_zh.properties
```

The application loads the selected language setting at startup and applies the corresponding locale to the view components.

## How to Run in Eclipse

1. Open Eclipse.
2. Choose **File > Import**.
3. Select **Existing Projects into Workspace**.
4. Choose the project folder `Java-BattleShip-Game`.
5. Make sure the project uses a compatible Java version, such as Java 17.
6. Open:

```text
src/Main/MVCBattleShip.java
```

7. Run the file as a Java application.

## How to Compile Manually

From the project root, you can compile the Java source files into a `bin` folder:

```bash
mkdir -p bin
javac -encoding UTF-8 -d bin src/Main/*.java src/Controller/*.java src/Model/*.java src/View/*.java
```

Then copy the resource bundle files and image assets into the runtime location as needed, or run the project directly through Eclipse where the project structure and resources are already configured.

## Assets

The repository includes image assets used by the Swing interface, such as:

* Battleship images
* Player images
* Hit and miss indicators
* Menu images
* Application logo

These files are used by the UI classes through `ImageIcon`.

## Technologies Used

* Java
* Java Swing
* AWT event handling
* MVC architecture
* Java socket programming
* Background processing / multithreading concepts
* ResourceBundle localization
* Eclipse IDE

## Notes

This project was developed as an academic Java application project. It demonstrates practical use of object-oriented programming, GUI design, MVC structure, event-driven programming, game-state management, basic network communication, turn-based UI feedback, and bilingual UI support.

## Project Context

This project was originally developed as an academic Java application project.

Primary application design, core implementation, and portfolio documentation: Huijun Bu
Localization resource contribution: Kexin Huang
Original academic project record: Huijun Bu and Kexin Huang

## License

This project is intended for educational and portfolio demonstration purposes.
