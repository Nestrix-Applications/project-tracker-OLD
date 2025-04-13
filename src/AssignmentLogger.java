import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        JTextField dueDateField = new JTextField("Due Date (MM/DD/YYYY)");
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

            // Validate due date format or allow it to be blank
            if (!dueDateText.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    dateFormat.setLenient(false);
                    dateFormat.parse(dueDateText); // Validate date format
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid date format. Use MM/DD/YYYY.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (!taskText.isEmpty()) {
                // Create a new panel for the task row
                JPanel taskRow = new JPanel();
                taskRow.setLayout(new BorderLayout());
                taskRow.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding

                // Create labels for the task name and due date
                JLabel taskLabel = new JLabel("• " + taskText);
                JLabel dueDateLabel = new JLabel(dueDateText, SwingConstants.RIGHT);

                // Add the labels to the task row with a vertical separator
                taskRow.add(taskLabel, BorderLayout.WEST);
                taskRow.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER); // Vertical line
                taskRow.add(dueDateLabel, BorderLayout.EAST);

                // Add the task row to the task panel
                taskPanel.add(taskRow);

                // Refresh the task panel
                taskPanel.revalidate();
                taskPanel.repaint();

                // Clear input fields
                taskField.setText("Task Name");
                dueDateField.setText("Due Date (MM/DD/YYYY)");
            }
        });

        // Make the window visible
        frame.setVisible(true);
    }
}
