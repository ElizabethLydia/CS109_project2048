package user;

import util.Create;

import javax.swing.*;
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

    public LoginView(JFrame parent, UserManager userManager) {
        super(parent, "Login", true);//设置对话框的标题和是否可见,这个对话框是模态的，即用户必须先关闭对话框才能回到父窗口
        this.userManager = userManager;//将传入的userManager赋值给类的userManager变量,这个userManager是用来管理用户的类,这个类的作用是用来管理用户的登录、注册等操作,这个类的实例化是在Main中进行的
        this.setLayout(null);//设置对话框的布局方式为null,即不使用布局管理器
        this.setSize(300, 250);//设置对话框的大小
        this.setLocationRelativeTo(null);//设置对话框的位置,这里设置为居中显示
        this.setModal(true);//设置对话框是否为模态的,即用户必须先关闭对话框才能回到父窗口

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//设置对话框关闭时的默认操作,这里设置为关闭对话框
        initializeComponents();
        layoutComponents();
        setListeners();
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);//设置对话框是否可以改变大小
    }

    private void setListeners() {//设置监听器,当用户点击登录按钮时,触发登录事件,并进行登录验证
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (userManager.validateUser(username, password)) {
                    System.out.println("Login successful!");
                    dispose();
                    // Open the main window
//                    ChooseGameMode chooseGameMode = new ChooseGameMode(null, userManager);
//                    chooseGameMode.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterView registerView = new RegisterView(null, userManager);
                registerView.setVisible(true);
                dispose();
            }
        });
    }

    private void initializeComponents() {
        usernameField = new JTextField();//创建一个文本框组件,用于用户输入文本信息,这个文本框是空的
        passwordField = new JPasswordField();//创建一个密码框组件,用于用户输入密码信息,这个密码框是空的
        loginButton = new JButton("Login");//创建一个按钮组件,用于用户点击登录
        loginButton.setBounds(10, 180, 80, 30);
        registerButton = new JButton("Register");//创建一个按钮组件,用于用户点击注册
        registerButton.setBounds(100, 180, 80, 30);
        usernameLabel = new JLabel("Username:");//创建一个标签组件,用于显示文本信息
        usernameLabel.setBounds(10, 10, 80, 30);
        passwordLabel = new JLabel("Password:");//创建一个标签组件,用于显示文本信息
        passwordLabel.setBounds(10, 50, 80, 30);
    }

    private void layoutComponents() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.setBounds(10, 10, 280, 160);
        panel.setLayout(null);
        add(panel);
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        LoginView loginView = new LoginView(null, userManager);
        loginView.setVisible(true);
    }
}
