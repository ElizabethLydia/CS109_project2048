package view;

import model.AISolver;
import user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AIGameFrame extends GameFrame {
    public static Timer timer;
    public JButton StartAIBtn;
    public JButton StopAIBtn;
    public AIGameFrame(int xcount, int ycount, User user,Menu1 menu1) {
        super(xcount, ycount, user, menu1);
        this.StartAIBtn = createButton("Start AI", new Point(500, 220), 110, 50, this);
        this.StartAIBtn.addActionListener(e -> {
            timer = new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    testAI(gamePanel);
                    if (gamePanel.checkIfEnded()) {
                        AIGameFrame.timer.stop();
                        System.out.println("Game Over!");
                        JOptionPane.showMessageDialog(null, "Game Over!");
                    }
                }
            });
            timer.start();
            this.StartAIBtn.setEnabled(false);
            this.StartAIBtn.setVisible(false);
            this.StopAIBtn.setEnabled(true);
            this.StopAIBtn.setVisible(true);
        });
        this.StopAIBtn = createButton("Stop AI", new Point(500, 220), 110, 50, this);
        this.StopAIBtn.addActionListener(e -> {
            AIGameFrame.timer.stop();
            this.StartAIBtn.setEnabled(true);
            this.StartAIBtn.setVisible(true);
            this.StopAIBtn.setEnabled(false);
            this.StopAIBtn.setVisible(false);
            this.requestFocusInWindow();//这句话的意思是让这个窗口获得焦点
        });
    }
    public void testAI(GamePanel gamePanel) {
        String b;
        AISolver solver = new AISolver(gamePanel.getXCOUNT(), gamePanel.getYCOUNT(), 0.2, 2, 1);
        solver.setNumbers(gamePanel.getModel().numbers);
        b = solver.minMaxAI();
        System.out.println("AI Choose the move " + b);
        if (b.equals("w")) gamePanel.doMoveUp();
        if (b.equals("s")) gamePanel.doMoveDown();
        if (b.equals("a")) gamePanel.doMoveLeft();
        if (b.equals("d")) gamePanel.doMoveRight();
        gamePanel.updateGridsNumber();
    }

}
