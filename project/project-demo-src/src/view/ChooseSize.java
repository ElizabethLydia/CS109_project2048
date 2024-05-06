package view;

import javax.swing.*;
import java.awt.*;

public class ChooseSize extends JDialog {

    private JButton Btn4x4;
    private JButton Btn5x5;
    private JButton Btn6x6;
    private JLabel titleLabel;
    public ChooseSize(JFrame parent) {
        super(parent, "Choose the Size of the Game", true);
        this.setLayout(null);
        this.setSize(200, 150);
        //待调整位置
        JLabel titleLabel = createLabel("Choose the Size of the Game", new Font("serif", Font.ITALIC, 50), new Point(480, 50), 180, 50);
        JButton Btn4x4 = createButton("4x4", new Point(100, 150), 100, 50);
        JButton Btn5x5 = createButton("5x5", new Point(100, 250), 100, 50);
        JButton Btn6x6 = createButton("6x6", new Point(100, 350), 100, 50);
        this.Btn4x4.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(700, 500);//需要改GameFrame的constructor以传入4*4的值
            gameFrame.setVisible(true);
            this.dispose();
            parent.setVisible(false);
        });
        this.Btn5x5.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(700, 500);
            gameFrame.setVisible(true);
            this.dispose();
            parent.setVisible(false);
        });
        this.Btn6x6.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(700, 500);
            gameFrame.setVisible(true);
            this.dispose();
            parent.setVisible(false);
        });
        this.setLocationRelativeTo(null);

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
