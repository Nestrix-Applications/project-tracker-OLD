import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AssignmentLogger {
    public static void main(String[] args) {
        // Create the main application window
        JFrame frame = new JFrame("Assignment Logger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create panels for task names and due dates
        JPanel taskNamePanel = new JPanel();
        taskNamePanel.setLayout(new BoxLayout(taskNamePanel, BoxLayout.Y_AXIS));

        JPanel dueDatePanel = new JPanel();
        dueDatePanel.setLayout(new BoxLayout(dueDatePanel, BoxLayout.Y_AXIS));

        // Create a split pane to separate task names and due dates
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, taskNamePanel, dueDatePanel);
        splitPane.setEnabled(false); // Disable user resizing

        // Create a panel for adding new tasks
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField taskField = new JTextField("Task Name");
        JTextField dueDateField = new JTextField("Due Date (MM/DD/YYYY)");
        JButton addButton = new JButton("Add Task");

        // Add focus listeners to clear placeholder text
        taskField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (taskField.getText().equals("Task Name")) {
                    taskField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (taskField.getText().isEmpty()) {
                    taskField.setText("Task Name");
                }
            }
        });

        dueDateField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dueDateField.getText().equals("Due Date (MM/DD/YYYY)")) {
                    dueDateField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dueDateField.getText().isEmpty()) {
                    dueDateField.setText("Due Date (MM/DD/YYYY)");
                }
            }
        });

        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(dueDateField, BorderLayout.EAST);
        inputPanel.add(addButton, BorderLayout.SOUTH);

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(splitPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Make the window visible
        frame.setVisible(true);

        // Set the divider location after the frame is visible
        splitPane.setDividerLocation(frame.getWidth() / 2);

        // Add action listener to the "Add Task" button
        addButton.addActionListener(_ -> {
            String taskText = taskField.getText();
            String dueDateText = dueDateField.getText();

            // Validate task name
            if (taskText.isEmpty() || taskText.equals("Task Name")) {
                JOptionPane.showMessageDialog(frame, "Task name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate due date format or allow it to be blank
            if (!dueDateText.isEmpty() && !dueDateText.equals("Due Date (MM/DD/YYYY)")) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    dateFormat.setLenient(false);
                    dateFormat.parse(dueDateText); // Validate date format
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid date format. Use MM/DD/YYYY.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Create labels for the task name and due date
            JLabel taskLabel = new JLabel("• " + taskText);
            JLabel dueDateLabel = new JLabel(dueDateText);

            // Add the labels to their respective panels
            taskNamePanel.add(taskLabel);
            dueDatePanel.add(dueDateLabel);

            // Refresh the panels
            taskNamePanel.revalidate();
            taskNamePanel.repaint();
            dueDatePanel.revalidate();
            dueDatePanel.repaint();

            // Clear input fields
            taskField.setText("");
            dueDateField.setText("");
        });
    }
}
