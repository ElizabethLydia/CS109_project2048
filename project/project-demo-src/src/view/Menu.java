package view;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    private JButton startBtn;
    private JButton loadGameBtn;
    private JButton settingsBtn;
    private JButton exitBtn;
    private JLabel titleLabel;
    public Menu() {
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(300, 500);
        //待调整位置
        this.titleLabel = createLabel("2048", new Font("serif", Font.ITALIC, 50), new Point(480, 50), 180, 50);
        this.startBtn = createButton("Start", new Point(100, 150), 100, 50);
        this.loadGameBtn = createButton("Load Game", new Point(100, 250), 100, 50);
        this.settingsBtn = createButton("Settings", new Point(100, 350), 100, 50);
        this.exitBtn = createButton("Exit", new Point(100, 450), 100, 50);
        //还要setVisible(true)和setVisible(false)
        this.startBtn.addActionListener(e -> {
            ChooseSize chooseSize = new ChooseSize(this);
            chooseSize.setVisible(true);
        });
        this.loadGameBtn.addActionListener(e -> {
            LoadGame loadGame = new LoadGame(this);
            loadGame.setVisible(true);
        });
        this.settingsBtn.addActionListener(e -> {
            Settings settings = new Settings();
            settings.setVisible(true);
        });
        this.exitBtn.addActionListener(e -> {
            System.exit(0);
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private JButton createButton(String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        this.add(button);
        return button;
    }
    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }

}
