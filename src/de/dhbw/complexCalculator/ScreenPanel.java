package de.dhbw.complexCalculator;

import javax.swing.*;
import java.awt.*;

public class ScreenPanel extends JPanel {

    private JTextArea screenTextArea = new JTextArea(Constants.SCREEN_ROWS, Constants.SCREEN_COLUMNS);
    private JLabel radiansLabel = new JLabel();

    public ScreenPanel(LayoutManager layout) {
        super(layout);
    }

    public String getScreenText() {
        return screenTextArea.getText();
    }

    public void setScreenText(String screenText) {
        screenTextArea.setText(screenText);
    }

    public void initializeScreenPanel() {
        //Anzeige bearbeiten
        screenTextArea.setBackground(Color.BLACK);
        screenTextArea.setForeground(Color.WHITE);
        screenTextArea.setFont(new Font("Arial", Font.BOLD, Constants.SCREEN_FONT_SIZE));
        screenTextArea.setEditable(false);
        add(screenTextArea, BorderLayout.CENTER);

        setBackground(Color.BLACK);
        radiansLabel.setText("RAD");
        radiansLabel.setFont(new Font("Arial", Font.BOLD, Constants.RAD_LABEL_FONT_SIZE));
        radiansLabel.setBackground(Color.BLACK);
        radiansLabel.setForeground(Color.WHITE);
        add(radiansLabel, BorderLayout.NORTH);
    }
}
