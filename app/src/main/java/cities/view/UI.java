package cities.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.FocusManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.net.URL;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class UI {
    private static final String GREETING_STRING = "Вітаємо вас у грі дитинства і всіх розумників!";
    private static final String TITLE = "Міста";
    private static final String OK_STRING = "Почати";
    
    private JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
    private JButton actionButton = new JButton("Зробити хід");
    private JTextField cityTextField = getCustomeTextField();
    private JLabel compCityLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel userScoreLabel = new JLabel("0", SwingConstants.CENTER);
    private JLabel compScoreLabel = new JLabel("0", SwingConstants.CENTER);
    private JFrame mainFrame;
    
    private void createAndShowGreeting() {
        //Create and set up the window.
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel(GREETING_STRING);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton button = new JButton(OK_STRING);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            frame.dispose();
            createSecondWindow(); // Створюємо та показуємо друге
        });
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue()); 
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(button);
        panel.add(Box.createVerticalGlue());

        frame.add(panel);

        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JTextField getCustomeTextField(){
        return new JTextField(10) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty() && (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() != this)) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(Color.GRAY);
                    g2.setFont(getFont().deriveFont(Font.ITALIC));
                    // Малюємо підказку всередині поля
                    g2.drawString("Напишіть назву міста...", 5, 15); 
                    g2.dispose();
                }
            }
        };
    }

    private void createSecondWindow() {
        mainFrame = new JFrame(TITLE);
        setIcon(mainFrame);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 500);
        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        mainFrame.add(messageLabel, gbc);


        gbc.gridy = 1;        
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        JLabel label1 = new JLabel("Ви", SwingConstants.CENTER);
        mainFrame.add(label1, gbc);

        gbc.gridx = 1;
        JLabel label2 = new JLabel("Комп'ютер", SwingConstants.CENTER);
        mainFrame.add(label2, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        mainFrame.add(cityTextField, gbc);

        gbc.gridx = 1;
        mainFrame.add(compCityLabel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        mainFrame.add(userScoreLabel, gbc);

        gbc.gridx = 1;
        mainFrame.add(compScoreLabel, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        mainFrame.add(actionButton, gbc);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void setIcon(JFrame frame){
        try {
            URL iconURL = getClass().getResource("/icon.png");
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                frame.setIconImage(icon.getImage());
            } else {
                System.err.println("Icon file not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JButton getActionButton(){
        return actionButton;
    }

    public String getCityInputText(){
        return cityTextField.getText();
    }

    public void setCityInputText(String text){
        cityTextField.setText(text);
    }

    public void show() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            createAndShowGreeting();
        });
    }

    public void showMessage(String text){
        messageLabel.setText(text);
    }

    public void setCompCityText(String text){
        compCityLabel.setText(text);
    }

    public void setCompScoreText(int score){
        compScoreLabel.setText(Integer.toString(score));
    }

    public void setUserScoreText(int score){
        userScoreLabel.setText(Integer.toString(score));
    }

    public JFrame getMainFrame(){
        return mainFrame;
    }
}
