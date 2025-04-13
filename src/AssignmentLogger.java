import javax.swing.*;
import java.awt.*;

public class AssignmentLogger {
    public static void main(String[] args) {
        // Create the main application window
        JFrame frame = new JFrame("Assignment Logger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create a panel to hold the task list
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));

        // Create a scroll pane for the task panel
        JScrollPane scrollPane = new JScrollPane(taskPanel);

        // Create a panel for adding new tasks
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField taskField = new JTextField("Task Name");
        JTextField dueDateField = new JTextField("Due Date (e.g., 2025-04-15)");
        JButton addButton = new JButton("Add Task");

        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(dueDateField, BorderLayout.EAST);
        inputPanel.add(addButton, BorderLayout.SOUTH);

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Add action listener to the "Add Task" button
        addButton.addActionListener(_ -> {
            String taskText = taskField.getText();
            String dueDateText = dueDateField.getText();

            if (!taskText.isEmpty() && !dueDateText.isEmpty()) {
                // Create a new panel for the task row
                JPanel taskRow = new JPanel();
                taskRow.setLayout(new BorderLayout());
                taskRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY)); // Add a dividing line

                // Create labels for the task name and due date
                JLabel taskLabel = new JLabel(taskText);
                JLabel dueDateLabel = new JLabel(dueDateText, SwingConstants.RIGHT);

                // Add the labels to the task row
                taskRow.add(taskLabel, BorderLayout.WEST);
                taskRow.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER); // Add a vertical line
                taskRow.add(dueDateLabel, BorderLayout.EAST);

                // Add the task row to the task panel
                taskPanel.add(taskRow);

                // Refresh the task panel
                taskPanel.revalidate();
                taskPanel.repaint();

                // Clear input fields
                taskField.setText("Task Name");
                dueDateField.setText("Due Date (e.g., 2025-04-15)");
            }
        });

        // Make the window visible
        frame.setVisible(true);
    }
}
