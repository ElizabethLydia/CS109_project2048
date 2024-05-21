package view;

import user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimingGameFrame extends GameFrame {
    public Timer timer;
    int timeInSeconds ; // 倒计时时间，以秒为单位
    public int timeLeft;
    public TimingGameFrame(int xcount, int ycount, User user, int time) {
        super(xcount, ycount,user);
        timeInSeconds =time; // 倒计时时间，以秒为单位
        timeLeft = timeInSeconds;
        this.HighestScoreLabel.setText(String.format("<html>Time:<br> %d:%02d</html>", timeInSeconds / 60, timeInSeconds % 60));
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    int seconds = timeLeft % 60;
                    int minutes = timeLeft / 60;
                    TimingGameFrame.this.HighestScoreLabel.setText(String.format("<html>Time:<br> %d:%02d</html>", minutes, seconds));
                    timeLeft--;
                } else {
                    ((Timer)e.getSource()).stop(); // 当时间到达后停止倒计时
                    TimingGameFrame.this.HighestScoreLabel.setText("<html>Game <br>Over<html>");
                    TimingGameFrame.this.gamePanel.setGameOverDialog();
                }
            }
        });
        timer.start();
    }
}
