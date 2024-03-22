import java.awt.*;
import java.awt.event.*;

 class CustomFlowLayoutExample {
    public static void main(String[] args) {
        final Frame frame = new Frame("Custom FlowLayout Example");
        frame.setSize(500, 800);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center align with 10 pixels gap
        // Text area
        final TextArea textArea = new TextArea();
        textArea.setPreferredSize(new Dimension(460, 130));
        // Panel for buttons
        final Panel buttonPanel = new Panel(new FlowLayout());
        Button newButton = new Button("New");
        Button openButton = new Button("Open");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");

        // Font size selector button
        Button fontSizeButton = new Button("Font Size");
        fontSizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create font size dialog
                final Dialog fontSizeDialog = new Dialog(frame, "Select Font Size", true);
                fontSizeDialog.setLayout(new FlowLayout());

                // Font sizes
                String[] fontSizes = {"10", "12", "14", "16", "18"};

                // Create font size buttons
                for (final String size : fontSizes) {
                    Button sizeButton = new Button(size);
                    sizeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int fontSize = Integer.parseInt(size);
                            Font font = textArea.getFont().deriveFont((float) fontSize);
                            textArea.setFont(font); // Change font size of text area
                            fontSizeDialog.dispose(); // Close dialog after selecting font size
                        }
                    });
                    fontSizeDialog.add(sizeButton);
                }

                // Set dialog size and visibility
                fontSizeDialog.setSize(200, 100);
                fontSizeDialog.setLocationRelativeTo(frame);
                fontSizeDialog.setVisible(true);
            }
        });

        // Font family selector button
        Button fontFamilyButton = new Button("Font Family");
        fontFamilyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create font family dialog
                final Dialog fontFamilyDialog = new Dialog(frame, "Select Font Family", true);
                fontFamilyDialog.setLayout(new FlowLayout());

                // Font families
                String[] fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

                // Create font family buttons
                for (final String fontFamily : fontFamilies) {
                    Button familyButton = new Button(fontFamily);
                    familyButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Font font = new Font(fontFamily, Font.PLAIN, textArea.getFont().getSize());
                            textArea.setFont(font); // Change font family of text area
                            fontFamilyDialog.dispose(); // Close dialog after selecting font family
                        }
                    });
                    fontFamilyDialog.add(familyButton);
                }

                // Set dialog size and visibility
                fontFamilyDialog.setSize(300, 200);
                fontFamilyDialog.setLocationRelativeTo(frame);
                fontFamilyDialog.setVisible(true);
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(newButton);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(fontSizeButton);
        buttonPanel.add(fontFamilyButton);

        // Panel for north
        final Panel northPanel = new Panel();
        northPanel.setPreferredSize(new Dimension(500, 180));
        northPanel.add(buttonPanel);

        // Scroll bars for RGB
        final Scrollbar redScrollbar = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 256);
        final Scrollbar greenScrollbar = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 256);
        final Scrollbar blueScrollbar = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, 256);

        redScrollbar.setPreferredSize(new Dimension(20, 150)); // Make it vertical
        greenScrollbar.setPreferredSize(new Dimension(20, 150)); // Make it vertical
        blueScrollbar.setPreferredSize(new Dimension(20, 150)); // Make it vertical

        // Text fields for RGB input
        final TextField redInput = new TextField("0", 5);
        final TextField greenInput = new TextField("0", 5);
        final TextField blueInput = new TextField("0", 5);

        // Action listener for text fields
        ActionListener textFieldAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int red = Integer.parseInt(redInput.getText());
                int green = Integer.parseInt(greenInput.getText());
                int blue = Integer.parseInt(blueInput.getText());

                northPanel.setBackground(new Color(red, green, blue));

                redScrollbar.setValue(red);
                greenScrollbar.setValue(green);
                blueScrollbar.setValue(blue);
            }
        };

        redInput.addActionListener(textFieldAction);
        greenInput.addActionListener(textFieldAction);
        blueInput.addActionListener(textFieldAction);

        // Adjustment listener for scroll bars
        AdjustmentListener scrollbarAction = new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int red = redScrollbar.getValue();
                int green = greenScrollbar.getValue();
                int blue = blueScrollbar.getValue();

                northPanel.setBackground(new Color(red, green, blue));

                redInput.setText(Integer.toString(red));
                greenInput.setText(Integer.toString(green));
                blueInput.setText(Integer.toString(blue));
            }
        };

        redScrollbar.addAdjustmentListener(scrollbarAction);
        greenScrollbar.addAdjustmentListener(scrollbarAction);
        blueScrollbar.addAdjustmentListener(scrollbarAction);


        // Panel for south
        final Panel southPanel = new Panel();
        southPanel.setBackground(Color.YELLOW);
        southPanel.setPreferredSize(new Dimension(500, 170));
        southPanel.add(textArea);

        // Panel for each color
        final Panel redPanel = new Panel(new BorderLayout());
        redPanel.add(new Label("Red"), BorderLayout.NORTH);
        redPanel.add(redScrollbar, BorderLayout.CENTER);
        redPanel.add(redInput, BorderLayout.SOUTH);

        final Panel greenPanel = new Panel(new BorderLayout());
        greenPanel.add(new Label("Green"), BorderLayout.NORTH);
        greenPanel.add(greenScrollbar, BorderLayout.CENTER);
        greenPanel.add(greenInput, BorderLayout.SOUTH);

        final Panel bluePanel = new Panel(new BorderLayout());
        bluePanel.add(new Label("Blue"), BorderLayout.NORTH);
        bluePanel.add(blueScrollbar, BorderLayout.CENTER);
        bluePanel.add(blueInput, BorderLayout.SOUTH);

        frame.add(northPanel);
        frame.add(redPanel);
        frame.add(greenPanel);
        frame.add(bluePanel);
        frame.add(southPanel);

        frame.setVisible(true);
    }
}