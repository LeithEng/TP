import java.awt.*;
import java.awt.event.*;
 class CustomFlowLayoutExample {
     public static void main(String[] args) {
         Frame frame = new Frame("Custom FlowLayout Example");
         frame.setSize(700, 400);
         frame.setLayout(new FlowLayout());
         frame.setVisible(true);
         final Panel northPanel = new Panel();
         northPanel.setBackground(Color.GREEN);
         northPanel.setPreferredSize(new Dimension(700, 200));

         Button button1 = new Button("New");
         Button button2 = new Button("Open");
         Button button3 = new Button("Save");
         Button button4 = new Button("Exit");

         northPanel.add(button1);
         northPanel.add(button2);
         northPanel.add(button3);
         northPanel.add(button4);

         final Panel southPanel = new Panel();
         southPanel.setBackground(Color.YELLOW);
         southPanel.setPreferredSize(new Dimension(700, 200));

         final TextArea textArea = new TextArea();
         textArea.setPreferredSize(new Dimension(380, 80));
         southPanel.add(textArea);
         frame.add(northPanel);
         frame.add(southPanel);

         final Scrollbar redScrollbar = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 256);
         final Scrollbar greenScrollbar = new Scrollbar(Scrollbar.VERTICAL, 256, 1, 0, 256);
         final Scrollbar blueScrollbar = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 256);

         redScrollbar.setPreferredSize(new Dimension(20, 100));
         greenScrollbar.setPreferredSize(new Dimension(20, 100));
         blueScrollbar.setPreferredSize(new Dimension(20, 100));
         redScrollbar.addAdjustmentListener(new AdjustmentListener() {
             public void adjustmentValueChanged(AdjustmentEvent e) {
                 northPanel.setBackground(new Color(redScrollbar.getValue(), greenScrollbar.getValue(), blueScrollbar.getValue()));
                 southPanel.setBackground(new Color(redScrollbar.getValue(), greenScrollbar.getValue(), blueScrollbar.getValue()));
             }
         });

         greenScrollbar.addAdjustmentListener(new AdjustmentListener() {
             public void adjustmentValueChanged(AdjustmentEvent e) {
                 northPanel.setBackground(new Color(redScrollbar.getValue(), greenScrollbar.getValue(), blueScrollbar.getValue()));
                 southPanel.setBackground(new Color(redScrollbar.getValue(), greenScrollbar.getValue(), blueScrollbar.getValue()));
             }
         });

         blueScrollbar.addAdjustmentListener(new AdjustmentListener() {
             public void adjustmentValueChanged(AdjustmentEvent e) {
                 northPanel.setBackground(new Color(redScrollbar.getValue(), greenScrollbar.getValue(), blueScrollbar.getValue()));
                 southPanel.setBackground(new Color(redScrollbar.getValue(), greenScrollbar.getValue(), blueScrollbar.getValue()));
             }
         });
         Menu font = new Menu("Font size");
         MenuItem size10 = new MenuItem("10");
         MenuItem size12 = new MenuItem("12");
         MenuItem size14 = new MenuItem("14");
         MenuItem size16 = new MenuItem("16");
         MenuItem size18 = new MenuItem("18");
         size10.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Font f = textArea.getFont();
                 textArea.setFont(new Font(f.getFontName(), f.getStyle(), 10));
             }
         });
         size12.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Font f = textArea.getFont();
                 textArea.setFont(new Font(f.getFontName(), f.getStyle(), 12));
             }
         });
         size14.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Font f = textArea.getFont();
                 textArea.setFont(new Font(f.getFontName(), f.getStyle(), 14));
             }
         });
         size16.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Font f = textArea.getFont();
                 textArea.setFont(new Font(f.getFontName(), f.getStyle(), 16));
             }
         });
         size18.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Font f = textArea.getFont();
                 textArea.setFont(new Font(f.getFontName(), f.getStyle(), 18));
             }
         });

             font.add(size10);
             font.add(size12);
             font.add(size14);
             font.add(size16);
             font.add(size18);
             MenuBar m = new MenuBar();
             m.add(font);

             frame.setMenuBar(m);
             northPanel.add(new Label("Red:"));
             northPanel.add(redScrollbar);
             northPanel.add(new Label("Green:"));
             northPanel.add(greenScrollbar);
             northPanel.add(new Label("Blue:"));
             northPanel.add(blueScrollbar);
         }
     }
