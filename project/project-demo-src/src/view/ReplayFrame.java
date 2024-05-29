package view;

import util.Create;
import util.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

public class ReplayFrame extends JFrame implements Create{
    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JPanel scorePanel;
    private JPanel stepPanel;
    protected GamePanel gamePanel;
    private ArrayList<Integer> eachScore ;
    private ArrayList<int[][]> eachArray ;
    private JButton replayButton;
    private JButton pauseButton;
    private int x=0;
    public static Timer timer;

    public ReplayFrame(GamePanel gamePanel) {
        this.setTitle("Replay");
        this.setLayout(null);
        getContentPane().setBackground(new Color(0xF6ECDF));//设置窗口背景颜色
        this.setSize(480, 600);
        this.setResizable(false);//设置窗口大小不可变
        this.setLocationRelativeTo(null);//调用这个方法后，窗口将在屏幕的中心位置显示，而不是默认的窗口初始位置（通常是在左上角）
        this.gamePanel = new GamePanel((int) (500 * 0.8), gamePanel.getXCOUNT(), gamePanel.getYCOUNT());//这行代码设置了游戏面板的大小，以及游戏4*4、5*5的大小之后要改
        this.gamePanel.setLocation(500 / 15, 500 / 15 + 100);
        this.add(this.gamePanel);
        this.eachScore = gamePanel.eachScore;
        this.eachArray = gamePanel.getModel().getEachArray();
        scorePanel = createPanel(33, 30, 100, 70, false);
        stepPanel = createPanel(335, 30, 100, 70, false);

        this.stepLabel = createLabel("<html>Step:<br> 0 </html>", new Font("Arial", Font.BOLD, 22), new Point(10, 10), 180, 50, this, 0xF1EDEA);
        this.scoreLabel = createLabel("<html>Score:<br>0  </html>", new Font("Arial", Font.BOLD, 22), new Point(10, 10), 180, 50, this, 0xF1EDEA);
        stepPanel.setComponentZOrder(stepLabel, 0);//设置层次，0表示最底层
        scorePanel.setComponentZOrder(scoreLabel, 0);
        this.gamePanel.getModel().setNumbers(eachArray.get(0));
        this.gamePanel.updateGridsNumber();
        replayButton = createButtonWithIcon("Replay", new Point(190, 50), 100, 50, this);
        pauseButton = createButtonWithIcon("Pause", new Point(190, 50), 100, 50, this);
        this.pauseButton.setEnabled(false);
        this.pauseButton.setVisible(false);
        this.replayButton.addActionListener(e -> {
            timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (ReplayFrame.this.x < ReplayFrame.this.eachArray.size()) {
                        ReplayFrame.this.gamePanel.getModel().setNumbers(ReplayFrame.this.eachArray.get(ReplayFrame.this.x));
                        ReplayFrame.this.gamePanel.updateGridsNumber();
                        ReplayFrame.this.stepLabel.setText("<html>Step:<br> " + ReplayFrame.this.x + " </html>");
                        ReplayFrame.this.scoreLabel.setText("<html>Score:<br> " + ReplayFrame.this.eachScore.get(ReplayFrame.this.x) + " </html>");
                        ReplayFrame.this.x++;
                    } else {
                        ReplayFrame.this.timer.stop();
                        ReplayFrame.this.x=0;
                        ReplayFrame.this.replayButton.setEnabled(true);
                        ReplayFrame.this.replayButton.setVisible(true);
                        ReplayFrame.this.pauseButton.setEnabled(false);
                        ReplayFrame.this.pauseButton.setVisible(false);
                    }

                }
            });
            timer.start();
            this.replayButton.setEnabled(false);
            this.replayButton.setVisible(false);
            this.pauseButton.setEnabled(true);
            this.pauseButton.setVisible(true);
        });
        this.pauseButton.addActionListener(e -> {
            timer.stop();
            this.replayButton.setEnabled(true);
            this.replayButton.setVisible(true);
            this.pauseButton.setEnabled(false);
            this.pauseButton.setVisible(false);
            this.requestFocusInWindow();//这句话的意思是让这个窗口获得焦点
        });
    }
    public JPanel createPanel(int x, int y, int width, int height, boolean isOpaque) {
        RoundedPanel panel = new RoundedPanel(new Color(0xbbada0), x, y, width, height, this, isOpaque);
        panel.setLayout(null);
        this.add(panel);
        return panel;
    }
}
