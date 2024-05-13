import view.GameFrame;
import view.Menu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            //要改变逻辑了先调出menu界面
        });
    }
}
