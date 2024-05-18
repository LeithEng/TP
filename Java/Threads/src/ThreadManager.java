import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;

class ThreadGUI extends JFrame {
    private JPanel panel;
    private MyThread[] threads;
    private JLabel[][] threadLabels;
    private JButton[] stopButtons;
    private JButton[] continueButtons;
    private JButton[] killButtons;
    private JButton addThreadButton;
    private JButton deleteThreadButton;
    private Timer timer;
    private int threadCount = 3; // Initial number of threads

    public ThreadGUI() {
        setTitle("Threads App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 6, 10, 5));
        panel.setBackground(new Color(173, 216, 230)); // Light blue background for the table
        add(panel, BorderLayout.CENTER);

        // Create and start threads
        threads = new MyThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MyThread("T" + (i + 1), 10); // Example initial remaining time is set to 10
            threads[i].start();
        }

        // Initialize GUI components arrays
        threadLabels = new JLabel[threadCount][3];
        stopButtons = new JButton[threadCount];
        continueButtons = new JButton[threadCount];
        killButtons = new JButton[threadCount];

        // Create header labels with a darker shade of blue
        Color headerColor = new Color(70, 130, 180); // Darker blue color for header labels
        String[] headers = {"Name", "Thread Name", "Remaining Time", "Stop", "Continue", "Kill"};
        for (String header : headers) {
            JLabel headerLabel = new JLabel(header);
            headerLabel.setHorizontalAlignment(JLabel.CENTER);
            headerLabel.setForeground(headerColor); // Set foreground color to the darker blue
            panel.add(headerLabel);
        }

        // Create GUI components for each thread
        for (int i = 0; i < threadCount; i++) {
            MyThread thread = threads[i];

            JLabel[] labels = new JLabel[3];
            labels[0] = new JLabel("Thread " + (i + 1));
            labels[1] = new JLabel(thread.getThreadName());
            labels[2] = new JLabel(String.valueOf(thread.getRemainingTime()));
            for (JLabel label : labels) {
                label.setHorizontalAlignment(JLabel.CENTER);
                panel.add(label);
            }
            threadLabels[i] = labels;

            // Black circle for stop button
            JButton stopButton = new JButton();
            stopButton.setPreferredSize(new Dimension(20, 20));
            stopButton.setBackground(Color.BLACK);
            stopButton.setOpaque(true);
            stopButton.setBorderPainted(false);
            stopButton.addActionListener(new StopButtonListener(thread));
            stopButtons[i] = stopButton;
            panel.add(stopButton);

            // Green circle for continue button
            JButton continueButton = new JButton();
            continueButton.setPreferredSize(new Dimension(20, 20));
            continueButton.setBackground(Color.GREEN);
            continueButton.setOpaque(true);
            continueButton.setBorderPainted(false);
            continueButton.addActionListener(new ContinueButtonListener(thread));
            continueButtons[i] = continueButton;
            panel.add(continueButton);

            // Red circle for kill button
            JButton killButton = new JButton();
            killButton.setPreferredSize(new Dimension(20, 20));
            killButton.setBackground(Color.RED);
            killButton.setOpaque(true);
            killButton.setBorderPainted(false);
            killButton.addActionListener(new KillButtonListener(thread));
            killButtons[i] = killButton;
            panel.add(killButton);
        }

        // Button to add a new thread
        addThreadButton = new JButton("Add Thread");
        addThreadButton.addActionListener(new AddThreadButtonListener());
        add(addThreadButton, BorderLayout.NORTH);

        // Button to delete a thread
        deleteThreadButton = new JButton("Delete Thread");
        deleteThreadButton.addActionListener(new DeleteThreadButtonListener());
        add(deleteThreadButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);

        // Create and start a Timer to update remaining time labels
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateRemainingTimeLabels();
            }
        });
        timer.start();

    }

    // ActionListener for stop button
    private class StopButtonListener implements ActionListener {
        private MyThread thread;

        public StopButtonListener(MyThread thread) {
            this.thread = thread;
        }

        public void actionPerformed(ActionEvent e) {
            thread.pauseThread();
        }
    }

    // ActionListener for continue button
    private class ContinueButtonListener implements ActionListener {
        private MyThread thread;

        public ContinueButtonListener(MyThread thread) {
            this.thread = thread;
        }

        public void actionPerformed(ActionEvent e) {
            thread.resumeThread();
        }
    }

    // ActionListener for kill button
    private class KillButtonListener implements ActionListener {
        private MyThread thread;

        public KillButtonListener(MyThread thread) {
            this.thread = thread;
        }

        public void actionPerformed(ActionEvent e) {
            thread.killThread();
        }
    }

    // ActionListener for adding a new thread
    // ActionListener for adding a new thread
    private class AddThreadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int randomRemainingTime = (int) (Math.random() * 30) + 1;
            MyThread newThread = new MyThread("T" + (threadCount + 1), randomRemainingTime); // Example initial remaining time is set to 10
            threads = Arrays.copyOf(threads, threads.length + 1);
            threads[threadCount] = newThread;
            newThread.start();
            // Update GUI components arrays
            JLabel[] labels = new JLabel[3];
            labels[0] = new JLabel("Thread " + (threadCount + 1));
            labels[1] = new JLabel(newThread.getThreadName());
            labels[2] = new JLabel(String.valueOf(newThread.getRemainingTime()));
            for (JLabel label : labels) {
                label.setHorizontalAlignment(JLabel.CENTER);
                panel.add(label);
            }
            // Buttons for the new thread
            JButton stopButton = new JButton();
            stopButton.setPreferredSize(new Dimension(20, 20));
            stopButton.setBackground(Color.BLACK);
            stopButton.setOpaque(true);
            stopButton.setBorderPainted(false);
            stopButton.addActionListener(new StopButtonListener(newThread));
            stopButtons = Arrays.copyOf(stopButtons, stopButtons.length + 1);
            stopButtons[threadCount] = stopButton;
            panel.add(stopButton);

            JButton continueButton = new JButton();
            continueButton.setPreferredSize(new Dimension(20, 20));
            continueButton.setBackground(Color.GREEN);
            continueButton.setOpaque(true);
            continueButton.setBorderPainted(false);
            continueButton.addActionListener(new ContinueButtonListener(newThread));
            continueButtons = Arrays.copyOf(continueButtons, continueButtons.length + 1);
            continueButtons[threadCount] = continueButton;
            panel.add(continueButton);

            JButton killButton = new JButton();
            killButton.setPreferredSize(new Dimension(20, 20));
            killButton.setBackground(Color.RED);
            killButton.setOpaque(true);
            killButton.setBorderPainted(false);
            killButton.addActionListener(new KillButtonListener(newThread));
            killButtons = Arrays.copyOf(killButtons, killButtons.length + 1);
            killButtons[threadCount] = killButton;
            panel.add(killButton);

            threadLabels = Arrays.copyOf(threadLabels, threadLabels.length + 1);
            threadLabels[threadCount] = labels;

            // Increment thread count
            threadCount++;

            // Refresh UI
            pack();
            setLocationRelativeTo(null);
        }
    }

    // ActionListener for deleting a thread
    private class DeleteThreadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (threadCount > 0) {
                MyThread threadToDelete = threads[threadCount - 1];
                threadToDelete.killThread();
                threads = Arrays.copyOf(threads, threads.length - 1);
                // Remove GUI components corresponding to the deleted thread
                panel.remove(threadLabels[threadCount - 1][0]);
                panel.remove(threadLabels[threadCount - 1][1]);
                panel.remove(threadLabels[threadCount - 1][2]);
                panel.remove(stopButtons[threadCount - 1]);
                panel.remove(continueButtons[threadCount - 1]);
                panel.remove(killButtons[threadCount - 1]);
                // Refresh UI
                panel.revalidate();
                panel.repaint();
                threadCount--;
            }
        }
    }

    // Update remaining time labels for all threads
    private void updateRemainingTimeLabels() {
        for (int i = 0; i < threads.length; i++) {
            final int index = i;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    threadLabels[index][2].setText(String.valueOf(threads[index].getRemainingTime()));
                }
            });
        }
    }

    // Update the UI after adding or deleting threads
    private void updateUI() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        add(panel, BorderLayout.CENTER);
        for (int i = 0; i < threads.length; i++) {
            MyThread thread = threads[i];
            JLabel[] labels = new JLabel[3];
            labels[0] = new JLabel("Thread " + (i + 1));
            labels[1] = new JLabel(thread.getThreadName());
            labels[2] = new JLabel(String.valueOf(thread.getRemainingTime()));
            for (JLabel label : labels) {
                label.setHorizontalAlignment(JLabel.CENTER);
                panel.add(label);
            }
            // Rest of the code for buttons, similar
        }
        pack();
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ThreadGUI();
            }
        });
    }
}