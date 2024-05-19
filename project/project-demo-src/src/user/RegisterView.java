package user;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JDialog {
    private JTextField usernameField;//JTextField是一个文本框组件,用于用户输入文本信息
    private JPasswordField passwordField;//JPasswordField是一个密码框组件,用于用户输入密码信息
    private JPasswordField confirmPasswordField;//JPasswordField是一个密码框组件,用于用户输入密码信息
    private JButton registerButton;//JButton是一个按钮组件,用于用户点击按钮时触发事件
    private JButton cancelButton;//JButton是一个按钮组件,用于用户点击按钮时触发事件
    private UserManager userManager;//UserManager是一个用户管理类,用于管理用户的登录、注册等操作
    private JLabel usernameLabel;//JLabel是一个标签组件,用于显示提示信息
    private JLabel passwordLabel;//JLabel是一个标签组件,用于显示提示信息
    private JLabel confirmPasswordLabel;//JLabel是一个标签组件,用于显示提示信息

    public RegisterView(LoginView parent, UserManager userManager) {
        super(parent, "Register", true);//设置对话框的标题和是否可见,这个对话框是模态的，即用户必须先关闭对话框才能回到父窗口
        this.userManager = userManager;//将传入的userManager赋值给类的userManager变量,这个userManager是用来管理用户的类,这个类的作用是用来管理用户的登录、注册等操作
        this.setLayout(null);//设置对话框的布局方式为null,即不使用布局管理器
        this.setSize(500, 500);//设置对话框的大小
        this.setLocationRelativeTo(null);//设置对话框的位置,这里设置为居中显示
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置对话框关闭时的默认操作,这里设置为关闭对话框
        this.setResizable(false);//设置对话框是否可以改变大小
        this.getContentPane().setBackground(new Color(0xF6ECDF));//设置对话框的背景颜色

        this.usernameLabel = new JLabel("Username:");//创建一个标签组件,用于显示提示信息
        this.usernameLabel.setBounds(10, 10, 100, 30); // 可以调整位置和大小
        this.add(this.usernameLabel);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(10, 50, 100, 30);
        this.add(this.passwordLabel);

        this.confirmPasswordLabel = new JLabel("Confirm Password:");
        this.confirmPasswordLabel.setBounds(10, 90, 150, 30);
        this.add(this.confirmPasswordLabel);

        this.usernameField = new JTextField();
        this.usernameField.setBounds(120, 10, 280, 30);
        this.add(this.usernameField);

        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(120, 50, 280, 30);
        this.add(this.passwordField);

        this.confirmPasswordField = new JPasswordField();
        this.confirmPasswordField.setBounds(120, 90, 280, 30);
        this.add(this.confirmPasswordField);

        this.registerButton = new JButton("Register");
        this.registerButton.setBounds(10, 130, 100, 30);
        this.registerButton.addActionListener(e -> {
            String username = this.usernameField.getText();
            String password = new String(this.passwordField.getPassword());
            String confirmPassword = new String(this.confirmPasswordField.getPassword());
            //首先判断两次输入的密码是否一致
            if (password.equals(confirmPassword)) {
                //然后调用userManager的registerUser方法判断用户是否存在,如果用户不存在,则注册用户,如果用户存在,则提示用户已存在
              if(userManager.isUserExists(username)){//判断用户是否存在,如果用户存在,则提示用户用户名已存在，就提醒用户名已存在，让用户重新输入用户名
                  JOptionPane.showMessageDialog(null, "Username already exists! Please try again with a different username.");
                }else{//如果用户不存在,则注册用户,并将用户的用户名和密码存储到users中
                  userManager.registerUser(username, password);
                  dispose();//关闭对话框
            }
            } else {
                JOptionPane.showMessageDialog(null, "Passwords do not match! Please try again.");
            }
        });
        this.add(this.registerButton);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBounds(200, 130, 100, 30);
        this.cancelButton.addActionListener(e -> this.dispose());
        this.add(this.cancelButton);

        this.setVisible(true);
    }
}
