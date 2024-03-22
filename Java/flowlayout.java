import java.awt.*;



class FlowLayoutExp {
    public static void main(String[] args) {
        Frame f=new Frame("FlowLayout Example");
        Panel p=new Panel();
        p.setLayout(new FlowLayout());
        p.add(new Button("1"));
        p.add(new Button("2"));
        p.add(new Button("3"));
        p.add(new Button("4"));
        p.setBackground(Color.blue);
        f.add(p);
        f.setSize(250,150);
        f.setVisible(true);


        
    }
}