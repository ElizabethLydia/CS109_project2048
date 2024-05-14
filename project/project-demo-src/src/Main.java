import view.Menu1;
import view.Menu2;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu1 menu = new Menu1();
            menu.setVisible(true);
            //要改变逻辑了先调出menu界面
            //fjy同学生日快乐！！
        });
    }
}
