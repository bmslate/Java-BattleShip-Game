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
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Controller.BattleShipController;

/**
 * ClientManager class is responsible for managing the client-side socket connection to the server.
 * It handles communication with the server by sending and receiving messages.
 */
public class ClientManager {

    private BattleShipModel theModel;
    private BattleShipController theController;
    private Socket connection;
    private BufferedReader input;
    private BufferedWriter output;
    private boolean running;
    private BlockingQueue<String> messageQueue;
    
    /**
     * Constructor for the ClientManager class.
     * Establishes a connection to the server and initializes the necessary I/O streams.
     * 
     * @param serverAddress The IP address or hostname of the server to connect to.
     * @param port The port number on which the server is listening.
     * @param theModel The model instance of the game.
     * @param theController The controller instance of the game.
     * @throws IOException If an I/O error occurs when creating the socket or getting the streams.
     */
    public ClientManager(String serverAddress, int port, BattleShipModel theModel,BattleShipController theController) throws IOException {
        this.theModel = theModel;
        this.theController = theController;
        connection = new Socket(serverAddress, port);
        input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        System.out.println("Connected to server at: " + serverAddress + ":" + port);
        theModel.setServerTurn(true);

        messageQueue = new LinkedBlockingQueue<>();
        running = true;

        // Start threads for handling input and output
        Thread inputThread = new Thread(new InputBufferedReader(input));
        Thread outputThread = new Thread(new OutputBufferedWriter(output, messageQueue));

        inputThread.start();
        outputThread.start();
    }

    /**
     * Runnable class for handling incoming messages from the server.
     * Reads messages from the server and processes them in the game model.
     */
    class InputBufferedReader implements Runnable {
        private BufferedReader input;

        /**
         * Constructor for InputBufferedReader.
         * @param input The BufferedReader for reading input from the server.
         */
        public InputBufferedReader(BufferedReader input) {
            this.input = input;
        }

        @Override
        public void run() {
            String message;
            try {
                // Continuously read messages from the server
                while ((message = input.readLine()) != null) {
                    theModel.handleReceivedMessage(message);
                    System.out.println("Received from server: " + message);
                }
            } catch (IOException e) {
                System.err.println("Connection closed or error: " + e.getMessage());
                running = false;
                theModel.setComputerWin(true);
                theModel.initiateNewComputerMap();
            }
        }
    }

    /**
     * Runnable class for handling outgoing messages to the server.
     * Takes messages from the message queue and sends them to the server.
     */
    class OutputBufferedWriter implements Runnable {
        private BufferedWriter output;
        private BlockingQueue<String> messageQueue;

        /**
         * Constructor for OutputBufferedWriter.
         * @param output The BufferedWriter for sending output to the server.
         * @param messageQueue The queue containing messages to be sent to the server.
         */
        public OutputBufferedWriter(BufferedWriter output, BlockingQueue<String> messageQueue) {
            this.output = output;
            this.messageQueue = messageQueue;
        }

        @Override
        public void run() {
            try {
                // Continuously send messages to the server from the message queue
                while (running) {
                    String message = messageQueue.take();
                    output.write(message + "\n");
                    System.out.println("Message sent to server: " + message);
                    output.flush();
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error sending message: " + e.getMessage());
                running = false;
            }
        }
    }

    /**
     * Sends a message to the server by adding it to the message queue.
     * 
     * @param message The message to be sent to the server.
     */
    public void sendMessageToServer(String message) {
        try {
            messageQueue.put(message);
            System.out.println("Added to message queue: " + message);
        } catch (InterruptedException e) {
            System.err.println("Error adding message to queue: " + e.getMessage());
        }
    }
}
