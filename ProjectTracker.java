import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProjectTracker {
    private static final String SAVE_FILE = System.getProperty("user.home") + "/tasks.txt";

    public static void main(String[] args) {
        // Create the main application window
        JFrame frame = new JFrame("Assignment Logger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create panels for task names and due dates
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(taskPanel);

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
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Load tasks from the save file
        loadTasks(taskPanel);

        // Make the window visible
        frame.setVisible(true);

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

            // Add the task to the panel
            addTaskToPanel(taskPanel, taskText, dueDateText);

            // Save the task to the file
            saveTask(taskText, dueDateText);

            // Clear input fields
            taskField.setText("");
            dueDateField.setText("");
        });
    }

    private static void loadTasks(JPanel taskPanel) {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            return; // No tasks to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length == 2) {
                    addTaskToPanel(taskPanel, parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading tasks: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void saveTask(String taskName, String dueDate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE, true))) {
            writer.write(taskName + " | " + dueDate);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving task: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void addTaskToPanel(JPanel taskPanel, String taskName, String dueDate) {
        JPanel taskRow = new JPanel(new BorderLayout());
        JCheckBox checkBox = new JCheckBox("• " + taskName + " (" + dueDate + ")");
        checkBox.addActionListener(_ -> {
            if (checkBox.isSelected()) {
                taskPanel.remove(taskRow);
                taskPanel.revalidate();
                taskPanel.repaint();
                deleteTaskFromFile(taskName, dueDate);
            }
        });

        taskRow.add(checkBox, BorderLayout.CENTER);
        taskPanel.add(taskRow);
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    private static void deleteTaskFromFile(String taskName, String dueDate) {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            return;
        }

        File tempFile = new File(SAVE_FILE + ".tmp");
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals(taskName + " | " + dueDate)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error deleting task: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Replace the original file with the updated file
        if (!file.delete() || !tempFile.renameTo(file)) {
            JOptionPane.showMessageDialog(null, "Error updating task file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
