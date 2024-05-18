import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.regex.*;

class Swing04 extends JFrame implements ActionListener {
    JButton searchButton = new JButton("Search");
    JTextField mainStringField = new JTextField(20);
    JTextField subStringField = new JTextField(20);
    JLabel resultLabel = new JLabel("Match Positions: ");

    public static void main(String args[]) {
        new Swing04();
    }

    public Swing04() {
        initGUI();
    }

    public void initGUI() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Main String:"));
        inputPanel.add(mainStringField);
        inputPanel.add(new JLabel("SubString:"));
        inputPanel.add(subStringField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(searchButton);
        bottomPanel.add(resultLabel);

        this.getContentPane().add(inputPanel, BorderLayout.NORTH);
        this.getContentPane().add(bottomPanel, BorderLayout.CENTER);

        searchButton.addActionListener(this);

        this.setSize(new Dimension(500, 150));
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String mainString = mainStringField.getText();
            String subString = subStringField.getText();
            
            if(mainString.isEmpty() || subString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both main string and substring.");
                return;
            }
            
            Pattern p = Pattern.compile(Pattern.quote(subString));
            Matcher m = p.matcher(mainString);
            StringBuilder positions = new StringBuilder();
            while (m.find()) {
                positions.append(m.start()).append(" ");
            }
            resultLabel.setText("Match Positions: " + (positions.length() > 0 ? positions.toString() : "No match found"));
        }
    }
}
