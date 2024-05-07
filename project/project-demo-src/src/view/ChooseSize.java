package view;

import util.CreateButtonAndLabel;

import javax.swing.*;
import java.awt.*;

public class ChooseSize extends JDialog implements CreateButtonAndLabel {

    private JButton Btn4x4;
    private JButton Btn5x5;
    private JButton Btn6x6;
    private JLabel titleLabel;
    public ChooseSize(JFrame parent) {
        super(parent, "Choose the Size of the Game", true);
        this.setLayout(null);
        this.setSize(200, 150);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(parent);
        //待调整位置
        this.titleLabel = createLabel("Choose the Size of the Game", new Font("serif", Font.ITALIC, 50), new Point(480, 50), 180, 50, this);
        this.Btn4x4 = createButton("4x4", new Point(100, 150), 100, 50, this);
        this.Btn5x5 = createButton("5x5", new Point(100, 250), 100, 50, this);
        this.Btn6x6 = createButton("6x6", new Point(100, 350), 100, 50, this);
        this.Btn4x4.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(700, 500);//需要改GameFrame的constructor以传入4*4的值
            gameFrame.setVisible(true);
        });
        this.Btn5x5.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(700, 500);
            gameFrame.setVisible(true);
        });
        this.Btn6x6.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(700, 500);
            gameFrame.setVisible(true);
        });
        this.dispose();
        parent.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
