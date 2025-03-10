package view;

import user.LoginView;
import user.UserManager;
import util.Create;


import javax.swing.*;
import java.awt.*;
public class Menu1 extends JFrame implements Create {
    UserManager userManager;
    private JButton usersloginBtn;
    private JButton visitorsloginBtn;
    private JButton settingsBtn;
    private JButton exitBtn;
    Settings settings= new Settings(this);

    public Menu1() {//用户登录界面
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(500, 700);
        this.setResizable(false);
        getContentPane().setBackground(new Color(0xF6ECDF));//设置窗口背景颜色
        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/2048title.png"));
        //修改图片的大小
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setBounds(0, 0, 500, 300);
        this.add(imageLabel);
        this.userManager = new UserManager();
        this.usersloginBtn = createButtonWithIcon("usersLogin", new Point(120, 300), 250, 70, this);
        this.visitorsloginBtn = createButtonWithIcon("visitorsLogin", new Point(120, 400), 250, 70, this);
        this.settingsBtn = createButtonWithIcon("Settings", new Point(120, 500), 80, 80, this);
        this.exitBtn = createButtonWithIcon("Exit", new Point(290, 500), 80, 80, this);
        //还要setVisible(true)和setVisible(false)
//        settings.music.start(true);
        this.usersloginBtn.addActionListener(e -> {
            LoginView loginView = new LoginView(this, userManager,this);
            loginView.setVisible(true);
        });
        this.visitorsloginBtn.addActionListener(e -> {
            ChooseGamemode chooseSize = new ChooseGamemode(this,null,this);
            //此时是游客模式，所以userManager为null
            chooseSize.setVisible(true);
        });
        this.settingsBtn.addActionListener(e -> {
            settings.setVisible(true);
        });
        this.exitBtn.addActionListener(e -> {
            System.exit(0);
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
