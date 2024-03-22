import java.awt.*;

class BorderLayoutExp {
    public static void main(String[] args) {
        Frame f=new Frame("BorderLayout Example");
        Panel p=new Panel();

        p.setLayout(new BorderLayout());
        p.add("North",new Button("Up"));
        p.add("South",new Button("Down"));
        p.add("West",new Button("Left"));
        p.add("East",new Button("Right"));
        p.add("Center",new Button("Center"));
        f.add(p);
        f.setSize(250,150);
        f.setVisible(true);
    }
}