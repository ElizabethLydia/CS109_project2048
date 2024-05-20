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
        this.setSize(300, 250);//设置对话框的大小
        this.setLocationRelativeTo(null);//设置对话框的位置,这里设置为居中显示
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置对话框关闭时的默认操作,这里设置为关闭对话框
        this.setResizable(false);//设置对话框是否可以改变大小
        this.getContentPane().setBackground(new Color(0xF6ECDF));//设置对话框的背景颜色

        //当用户关闭对话框时,默认操作是关闭对话框
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //设置对话框的界面元素
        this.usernameLabel = new JLabel("Username:");//设置标签的文本内容
        this.usernameLabel.setBounds(10, 10, 100, 25);
        this.add(this.usernameLabel);


        this.usernameField = new JTextField(); //设置文本框的大小和位置
        this.usernameField.setBounds(10, 35, 280, 25);
        this.add(this.usernameField);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(10, 70, 100, 25);
        this.add(this.passwordLabel);

        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(10, 95, 280, 25);
        this.add(this.passwordField);

        this.loginButton = new JButton("Login");
        this.loginButton.setBounds(10, 130, 100, 25);
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
        this.add(this.loginButton);

        this.registerButton = new JButton("Register");
        this.registerButton.setBounds(120, 130, 100, 25);
        this.registerButton.addActionListener(e -> {
            this.setVisible(false);
            RegisterView registerView = new RegisterView(LoginView.this, userManager);
            registerView.setVisible(true);
        });
        this.add(this.registerButton);
    }
}
