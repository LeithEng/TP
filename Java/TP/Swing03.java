import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class Swing03 extends JFrame {
    JButton b1 = new JButton("Nord");
    JButton b2 = new JButton("Ouest");
    JButton b3 = new JButton("Est");

    public static void main(String args[]) {
        new Swing03();
    }

    public Swing03() {
        initGUI();
    }

    public void initGUI() {
        this.getContentPane().add(b1, BorderLayout.NORTH);
        this.getContentPane().add(b2, BorderLayout.WEST);
        this.getContentPane().add(b3, BorderLayout.EAST);
        this.setSize(new Dimension(500, 400));
        this.setVisible(true);
    }
}