import java.awt.*;

class GridLayoutExp {
    public static void main(String[] args) {
        Frame f=new Frame("GridLayout Example");
        Panel p=new Panel(new GridLayout(2,3,10,10));
        p.add(new Button("1"));
        p.add(new Button("2"));
        p.add(new Button("3"));
        p.add(new Button("4"));
        p.add(new Button("5"));
        p.add(new Button("6"));
        p.setBackground(Color.CYAN);
        f.add(p);
        f.setSize(250,150);
        f.setVisible(true);
        
    }
}