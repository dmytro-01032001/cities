package cities.view;

import javax.swing.*;
import java.awt.*;

public class ModalWindow extends JDialog {

    public ModalWindow(Frame parent, String title, int userScore, int compScore) {
        super(parent, title, true);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel messageLabel = new JLabel("Гру закінчено. Нижче - рахунок.", SwingConstants.CENTER);
        add(messageLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Ви:", SwingConstants.CENTER), gbc);

        gbc.gridx = 1;
        add(new JLabel("Комп'ютер:", SwingConstants.CENTER), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel(Integer.toString(userScore), SwingConstants.CENTER), gbc);

        gbc.gridx = 1;
        add(new JLabel(Integer.toString(compScore), SwingConstants.CENTER), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        JButton okButton = new JButton("ОК");
        okButton.addActionListener(e -> dispose());
        add(okButton, gbc);

        pack();
        setLocationRelativeTo(parent);
    }
}
