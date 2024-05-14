package view;

import util.Create;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu1 extends JFrame implements Create {
    private JButton usersloginBtn;
    private JButton visitorsloginBtn;
    private JButton settingsBtn;
    private JButton exitBtn;

    public Menu1() {//用户登录界面
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(500, 700);
        this.setResizable(false);
        getContentPane().setBackground(new Color(0xF6ECDF));//设置窗口背景颜色
        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/2048title.png"));
        //修改图片的大小
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setBounds(0, 0, 500, 300);
        this.add(imageLabel);
        this.usersloginBtn = createButtonWithIcon("usersLogin", new Point(120, 300), 250, 70, this);
        this.visitorsloginBtn = createButtonWithIcon("visitorsLogin", new Point(120, 400), 250, 70, this);
        this.settingsBtn = createButtonWithIcon("Settings", new Point(120, 500), 80, 80, this);
        this.exitBtn = createButtonWithIcon("Exit", new Point(290, 500), 80, 80, this);
        //还要setVisible(true)和setVisible(false)
        this.usersloginBtn.addActionListener(e -> {
            Menu2 menu2 = new Menu2();
            menu2.setVisible(true);
            this.setVisible(false);
        });
        this.visitorsloginBtn.addActionListener(e -> {
            Menu2 menu2 = new Menu2();
            menu2.setVisible(true);
            this.setVisible(false);
        });
        this.settingsBtn.addActionListener(e -> {
            Settings settings = new Settings(this);
            settings.setVisible(true);
        });
        this.exitBtn.addActionListener(e -> {
            System.exit(0);
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
