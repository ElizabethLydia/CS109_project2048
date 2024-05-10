package view;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JMenuBar {
    //这个用于显示游戏菜单（游戏上面的一栏）
    public GameMenu(GameFrame gameFrame) {
        Font font = new Font("Arial", Font.PLAIN, 20);
        Color color = new Color(0x545151);
        JMenuBar menuBar = new JMenuBar();
        JMenu newGame = new JMenu("newGame");
        JMenu loadGame = new JMenu("load");
        JMenu saveGame = new JMenu("save");
        JMenu settings = new JMenu("settings");
        JMenu help = new JMenu("help");
        JMenu exit = new JMenu("exit");
        menuBar.add(newGame);
        menuBar.add(loadGame);
        menuBar.add(saveGame);
        menuBar.add(settings);
        menuBar.add(help);
        menuBar.add(exit);
        newGame.setFont(font);
        loadGame.setFont(font);
        saveGame.setFont(font);
        settings.setFont(font);
        help.setFont(font);
        exit.setFont(font);
        newGame.setForeground(color);
        loadGame.setForeground(color);
        saveGame.setForeground(color);
        settings.setForeground(color);
        help.setForeground(color);
        exit.setForeground(color);
        //创建子项
        JMenuItem newGame1 = new JMenuItem("4*4");
        JMenuItem newGame2 = new JMenuItem("5*5");
        newGame1.setFont(font);
        newGame2.setFont(font);
        newGame.add(newGame1);
        newGame.add(newGame2);

        JMenuItem settings1 = new JMenuItem("volume");
        JMenuItem settings2 = new JMenuItem("returnToHomePage");
        settings1.setFont(font);
        settings2.setFont(font);
        settings.add(settings1);
        settings.add(settings2);

        JMenuItem help1 = new JMenuItem("howToPlay");
        JMenuItem help2 = new JMenuItem("howToWin");
        help1.setFont(font);
        help2.setFont(font);
        help.add(help1);
        help.add(help2);

        newGame1.addActionListener(e -> {
            System.out.println("you choose new game");
            int result = JOptionPane.showConfirmDialog(null, "Are you sure to start a new game?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                gameFrame.dispose(); // 关闭当前窗口
                GameFrame newGameFrame = new GameFrame(4,4); // 创建新的游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
            }
        });
        newGame2.addActionListener(e -> {
            System.out.println("you choose new game");
            int result = JOptionPane.showConfirmDialog(null, "Are you sure to start a new game?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                gameFrame.dispose(); // 关闭当前窗口
                GameFrame newGameFrame = new GameFrame(5,5); // 创建新的游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
            }
        });
        loadGame.addActionListener(e -> {
            System.out.println("you choose load game");
            String string = JOptionPane.showInputDialog(null, "Input path:");
            System.out.println(string);
            gameFrame.getController().loadGame(string);
        });
        saveGame.addActionListener(e -> {
            System.out.println("you choose save game");
            gameFrame.getController().saveGame();
        });
        exit.addActionListener(e -> {
            System.out.println("you choose exit");
            int result = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });


        settings1.addActionListener(e -> {
            System.out.println("you choose settings");
            gameFrame.getController().setVolume();
        });
        settings2.addActionListener(e -> {
            System.out.println("you choose settings");
            int result = JOptionPane.showConfirmDialog(null, "Are you sure to return to home page?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                gameFrame.getController().returnToHomePage();
            }
        });

        help1.addActionListener(e -> {
            System.out.println("how to play");
            JOptionPane.showMessageDialog(null, "Use arrow keys to move the tiles. When two tiles with the same number touch, they merge into one!", "How to play", JOptionPane.INFORMATION_MESSAGE);
        });
        help2.addActionListener(e -> {
            System.out.println("how to win");
            JOptionPane.showMessageDialog(null, "Get to the 2048 tile!", "How to win", JOptionPane.INFORMATION_MESSAGE);
        });
        this.add(menuBar);
    }
}
