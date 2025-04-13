import javax.swing.*;
import java.awt.*;

public class CalTestJavaApp {
    public static void main(String[] args) {
        // Create the main application window
        JFrame frame = new JFrame("Cal's Test Java Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create a text area for chat history
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Create a text field for user input
        JTextField inputField = new JTextField();

        // Create a button to send messages
        JButton sendButton = new JButton("Send");

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputField, BorderLayout.SOUTH);
        frame.add(sendButton, BorderLayout.EAST);

        // Add action listener to the send button
        sendButton.addActionListener(e -> {
            String userInput = inputField.getText();
            if (!userInput.isEmpty()) {
                chatArea.append("You: " + userInput + "\n");
                inputField.setText("");
            }
        });

        // Make the window visible
        frame.setVisible(true);
    }
}