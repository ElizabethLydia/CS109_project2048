package user;

import util.Create;
import view.Menu2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//ChooseGamemode chooseSize = new ChooseGamemode(userManager);
//这个是在用户登录界面点击游客登录按钮后，弹出的选择游戏模式的窗口

public class LoginView extends JDialog implements Create {
    private JTextField usernameField;//JTextField是一个文本框组件,用于用户输入文本信息
    private JPasswordField passwordField;//JPasswordField是一个密码框组件,用于用户输入密码信息
    private JLabel usernameLabel;
    private JLabel passwordLabel;//JLabel是一个标签组件,用于显示文本信息
    private JButton loginButton;
    private JButton registerButton;
    private UserManager userManager;
    private User user;

    public LoginView(JFrame parent, UserManager userManager) {
        super(parent, "Login", true);//设置对话框的标题和是否可见,这个对话框是模态的，即用户必须先关闭对话框才能回到父窗口
        this.userManager = userManager;//将传入的userManager赋值给类的userManager变量,这个userManager是用来管理用户的类,这个类的作用是用来管理用户的登录、注册等操作,这个类的实例化是在Main中进行的
        this.setLayout(null);//设置对话框的布局方式为null,即不使用布局管理器
        this.setSize(500, 500);//设置对话框的大小
        this.setLocationRelativeTo(null);//设置对话框的位置,这里设置为居中显示
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置对话框关闭时的默认操作,这里设置为关闭对话框
        this.setResizable(false);//设置对话框是否可以改变大小
        this.getContentPane().setBackground(new Color(0xF6ECDF));//设置对话框的背景颜色

        //当用户关闭对话框时,默认操作是关闭对话框
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //设置对话框的界面元素
        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/LoginTitle.png"));
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(380, 250, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setBounds(0, 0, 500, 250);
        this.add(imageLabel);

        this.usernameLabel = new JLabel("Username:");//设置标签的文本内容
        this.usernameLabel.setBounds(40, 250, 200, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.usernameLabel);


        this.usernameField = new JTextField(); //设置文本框的大小和位置
        this.usernameField.setBounds(180, 250, 280, 30);
        this.usernameField.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(this.usernameField);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(40, 300, 200, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.passwordLabel);

        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(180, 300, 280, 30);
        this.passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(this.passwordField);

        this.loginButton = createButtonWithIcon("Login", new Point(80, 360), 140, 60, this);
        this.loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            this.user = userManager.validateUser(username, password);
            if (user!= null) {
                JOptionPane.showMessageDialog(null, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                parent.dispose();
                Menu2 menu2 = new Menu2(user);
                menu2.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.registerButton = createButtonWithIcon("Register", new Point(280, 360), 140, 60, this);
        this.registerButton.addActionListener(e -> {
            RegisterView registerView = new RegisterView(LoginView.this, userManager);
            registerView.setVisible(true);
            this.dispose();//关闭登录界面
            //注册成功后,返回登录界面
        });
    }
    public JFrame getParent() {
        return (JFrame) super.getParent();
    }
}
