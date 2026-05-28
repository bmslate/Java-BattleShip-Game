/**
 * CET - CS Academic Level 4
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Huijun Bu Kexin Huang
 * Student Number: 0411121881   041096457   
 * Section #: 301  
 * Course: CST8221 - Java application
 * Assignment: 3.2
 * @author Huijun Bu. Kexin Huang
 *
 */
package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;

import Controller.BattleShipController;

/**
 * The ServerManager class manages the server-side operations for the Battleship game.
 * It handles the server socket, client connections, and message exchanges between the server and client.
 */
public class ServerManager {

    private BattleShipModel theModel;
    private ServerSocket server;
    private Socket connection;
    private BufferedReader input;
    private BufferedWriter output;
    private boolean remotePlayerIsSet = false;
    private boolean serverIsWaiting;
    private BlockingQueue<String> messageQueue;
    private BattleShipController theController;

    /**
     * Gets the server waiting status.
     * @return true if the server is waiting for a connection, false otherwise.
     */
    public boolean isServerIsWaiting() {
        return serverIsWaiting;
    }

    /**
     * Sets the server waiting status.
     * @param serverIsWaiting the new waiting status of the server.
     */
    public void setServerIsWaiting(boolean serverIsWaiting) {
        this.serverIsWaiting = serverIsWaiting;
    }

    /**
     * Gets the status of the remote player connection.
     * @return true if the remote player is connected, false otherwise.
     */
    public boolean getRemotePlayerIsSet() {
        return remotePlayerIsSet;
    }

    /**
     * Sets the status of the remote player connection.
     * @param remotePlayerIsSet the new status of the remote player connection.
     */
    public void setRemotePlayerIsSet(boolean remotePlayerIsSet) {
        this.remotePlayerIsSet = remotePlayerIsSet;
    }

    /**
     * Constructs a ServerManager instance and initializes the server socket.
     * Starts a new thread to wait for client connections.
     * @param port The port number the server will listen on.
     * @param theModel The Battleship game model.
     * @param theController The Battleship game controller.
     */
    public ServerManager(int port, BattleShipModel theModel, BattleShipController theController) {
        this.theModel = theModel;
        this.theController = theController;
        try {
            server = new ServerSocket(port);
            server.setSoTimeout(30000); // Set timeout to 30 seconds
            serverIsWaiting = true;
            messageQueue = new LinkedBlockingQueue<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server is listening on port: " + port);

        // Start a thread to wait for client connection
        new Thread(this::waitForClientConnection).start();

        JOptionPane.showMessageDialog(null, "Server is listening on port: " + port + ". Waiting for client connection...", "Server Status", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Waits for a client to connect to the server.
     * Once a client connects, it initializes the input and output streams for communication.
     */
    private void waitForClientConnection() {
        try {
            System.out.println("Server is waiting ");
            connection = server.accept();
            remotePlayerIsSet = true;
            serverIsWaiting = false;
            // Notify that the opponent has connected
            System.out.println("Your opponent has connected!");
            theModel.setServerTurn(true);

            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

            // Notify the client of a successful connection
            sendMessageToClient("Connected to server.");

            Thread inputThread = new Thread(new InputBufferedReader(input));
            Thread outputThread = new Thread(new OutputBufferedWriter(output, messageQueue));

            inputThread.start();
            outputThread.start();
        } catch (SocketTimeoutException e) {
            System.out.println("Connection timed out! No client connected within 30 seconds.");
            theModel.setServerTurn(false);
            serverIsWaiting = false;
        } catch (IOException e) {
            e.printStackTrace();
            remotePlayerIsSet = false;
            System.out.println("Your opponent has not connected!");
            theModel.setServerTurn(false);
            serverIsWaiting = false;
        }
    }

    /**
     * Inner class that handles incoming messages from the client.
     * Reads messages from the input stream and passes them to the game model for processing.
     */
    class InputBufferedReader implements Runnable {
        private BufferedReader input;

        /**
         * Constructs an InputBufferedReader with the given input stream.
         * @param input The BufferedReader for reading client messages.
         */
        public InputBufferedReader(BufferedReader input) {
            this.input = input;
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = input.readLine()) != null) {
                    theModel.handleReceivedMessage(message);
                    System.out.println("received client message: " + message);
                }
            } catch (IOException e) {
                System.err.println("Connection closed or error: " + e.getMessage());
                remotePlayerIsSet = false;
                theModel.initiateNewComputerMap();
            }
        }
    }

    /**
     * Inner class that handles outgoing messages to the client.
     * Takes messages from the message queue and writes them to the output stream.
     */
    class OutputBufferedWriter implements Runnable {
        private BufferedWriter output;
        private BlockingQueue<String> messageQueue;

        /**
         * Constructs an OutputBufferedWriter with the given output stream and message queue.
         * @param output The BufferedWriter for writing messages to the client.
         * @param messageQueue The queue holding messages to be sent to the client.
         */
        public OutputBufferedWriter(BufferedWriter output, BlockingQueue<String> messageQueue) {
            this.output = output;
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            try {
                while (remotePlayerIsSet) {
                    String message = messageQueue.take();
                    output.write(message + "\n");
                    System.out.println("server sent to client message: " + message);
                    output.flush();
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error sending message: " + e.getMessage());
                remotePlayerIsSet = false;
            }
        }
    }

    /**
     * Sends a message to the client by adding it to the message queue.
     * @param message The message to be sent to the client.
     */
    public void sendMessageToClient(String message) {
        try {
            messageQueue.put(message);
            System.out.println(message);
        } catch (InterruptedException e) {
            System.err.println("Error adding message to queue: " + e.getMessage());
        }
    }
}
