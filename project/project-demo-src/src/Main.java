import view.GameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame(4,4);
            gameFrame.setVisible(true);
            //要改变逻辑了先调出menu界面
        });
    }
}
