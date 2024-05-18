import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ThreadExample implements ActionListener {
    private JTextArea textArea;
    private JButton printAllButton;
    private JButton waitButton;

    public ThreadExample() {
        JFrame frame = new JFrame("Thread Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        printAllButton = new JButton("Print All Threads");
        printAllButton.addActionListener(this);
        panel.add(printAllButton, BorderLayout.WEST);

        waitButton = new JButton("Wait for Each Thread");
        waitButton.addActionListener(this);
        panel.add(waitButton, BorderLayout.EAST);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == printAllButton) {
            printAllThreads();
        } else if (e.getSource() == waitButton) {
            waitForEachThread();
        }
    }

    private void printAllThreads() {
        textArea.setText("");
        Thread t1 = new Thread(new Printer("t1"));
        Thread t2 = new Thread(new Printer("t2"));
        t1.start();
        t2.start();
    }

    private void waitForEachThread() {
        textArea.setText("");

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread t1 = new Thread(new Printer("t1"));
                Thread t2 = new Thread(new Printer("t2"));
                t1.start();
                t1.join();
                t2.start();
                t2.join();
                return null;
            }

            @Override
            protected void done() {
                // Update GUI if needed after threads complete
            }
        };

        worker.execute(); // Start the SwingWorker
    }

    private class Printer implements Runnable {
        private String threadName;

        public Printer(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            for (int i = 5; i > 0; i--) {
                String output = String.format("je suis le thread %s, mon thread id est %d et il me reste %d secondes\n", threadName, Thread.currentThread().getId(), i);
                SwingUtilities.invokeLater(() -> {
                    textArea.append(output);
                    textArea.update(textArea.getGraphics());
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ThreadExample::new);
    }
}
