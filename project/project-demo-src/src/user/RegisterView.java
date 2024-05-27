package user;

import util.Create;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView extends JDialog implements Create {
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

        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/RegisterTitle.png"));
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(330, 200, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setBounds(0, 10, 500, 200);
        this.add(imageLabel);

        this.usernameLabel = new JLabel("Username:");//创建一个标签组件,用于显示提示信息
        this.usernameLabel.setBounds(20, 230, 200, 30); // 可以调整位置和大小
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.usernameLabel);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setBounds(20, 270, 200, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.passwordLabel);

        this.confirmPasswordLabel = new JLabel("Confirm Password:");
        this.confirmPasswordLabel.setBounds(15, 310, 200, 30);
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.confirmPasswordLabel);

        this.usernameField = new JTextField();
        this.usernameField.setBounds(200, 230, 280, 30);
        this.usernameField.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.usernameField);

        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(200, 270, 280, 30);
        this.passwordField.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.passwordField);

        this.confirmPasswordField = new JPasswordField();
        this.confirmPasswordField.setBounds(200, 310, 280, 30);
        this.confirmPasswordField.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(this.confirmPasswordField);

        this.registerButton = createButtonWithIcon("Register",new Point(80, 360), 140, 60, this);
        this.registerButton.addActionListener(e -> {
            String username = this.usernameField.getText();
            String password = new String(this.passwordField.getPassword());
            String confirmPassword = new String(this.confirmPasswordField.getPassword());
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required! Please try again.");
            }else {
                //首先判断两次输入的密码是否一致
                if (password.equals(confirmPassword)) {
                    //然后调用userManager的registerUser方法判断用户是否存在,如果用户不存在,则注册用户,如果用户存在,则提示用户已存在
                    if(userManager.isUserExists(username)){//判断用户是否存在,如果用户存在,则提示用户用户名已存在，就提醒用户名已存在，让用户重新输入用户名
                        JOptionPane.showMessageDialog(null, "Username already exists! Please try again with a different username.");
                    }else{//如果用户不存在,则注册用户,并将用户的用户名和密码存储到users中
                        userManager.registerUser(username, password);
                        this.dispose();//关闭对话框
                        LoginView loginView = new LoginView(parent.getParent(), userManager,parent.mainMenu);
                        loginView.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match! Please try again.");
                }
            }
        });
        this.cancelButton = createButtonWithIcon("Cancel",new Point(280, 360), 140, 60, this);
        this.cancelButton.addActionListener(e -> {
            this.dispose();
            parent.setVisible(true);
        });
    }
}
