package view;

import controller.GameController;
import model.GridNumber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class GamePanel extends ListenerPanel {
    private final int XCOUNT;
    private final int YCOUNT;
    private GridComponent[][] grids;
    private GridNumber model;
    private JLabel stepLabel;
    private int steps;
    private static int GRID_SIZE;

    private int score;
    private JLabel scoreLabel;
    ArrayList<Integer> eachScore = new ArrayList<>();
    GameOverDialog gameOverDialog;
    GameWinDialog gameWinDialog;
    GameController controller;
    private int state;
    boolean checkIfHadInformWin;

    public GamePanel(int size, int xcount, int ycount) {//构造方法
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(new Color(0xbbada0));
        this.setSize(size, size);
        this.XCOUNT = xcount;
        this.YCOUNT = ycount;
        this.GRID_SIZE = size / (Math.max(XCOUNT, YCOUNT));
        this.grids = new GridComponent[XCOUNT][YCOUNT];
        this.model = new GridNumber(XCOUNT, YCOUNT);
        this.state=0;
        checkIfHadInformWin=false;
        initialGame();
        int[][] initialArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i <XCOUNT ; i++) {
            for (int j = 0; j <YCOUNT ; j++) {
                initialArray[i][j] = model.getNumber(i, j);
            }
        }
        this.model.addEachArray(initialArray);
        this.score = 0;
        this.eachScore.add(score);
        this.model.addEachScore(score);
    }

    public void initialGame() {
        this.steps = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j] = new GridComponent(i, j, model.getNumber(i, j), this.GRID_SIZE);
                grids[i][j].setLocation(j * GRID_SIZE, i * GRID_SIZE);
                this.add(grids[i][j]);
            }
        }
        model.printNumber();//check the 4*4 numbers in game
        this.repaint();
    }

    public void updateGridsNumber() {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j].setNumber(model.getNumber(i, j));
            }
        }
        repaint();
    }


    /**
     * Implement the abstract method declared in ListenerPanel.
     * Do move right.
     */
    @Override
    public void doMoveRight() {//记得改现在是4*4的情况
        if (this.model.moveRight()) {
            System.out.println("Click VK_RIGHT");
            this.afterMove();
            this.model.addRandomNumber();
            int[][] afterArray = new int[XCOUNT][YCOUNT];
            for (int i = 0; i < afterArray.length; i++) {
                for (int j = 0; j < afterArray[i].length; j++) {
                    afterArray[i][j] = model.getNumber(i, j);
                }
            }
            this.model.addEachArray(afterArray);
            this.updateGridsNumber();
            this.setScore();
            if (checkIf2048() && checkIfHadInformWin==false){
                System.out.println("You win!");
                if (controller.frame instanceof TimingGameFrame) {
                    TimingGameFrame timingGameFrame = (TimingGameFrame) controller.frame;
                    timingGameFrame.timer.stop();
                    // 停止定时器
                }
                setWinDialog();
                //结束游戏界面
            }
        }else{
            System.out.println("Unable to move right,try another direction");
            JOptionPane.showMessageDialog(null, "Unable to move right,try another direction", "Warning", JOptionPane.WARNING_MESSAGE);
            if (checkIfEnded()) {
                System.out.println("game is over.");
                if (controller.frame instanceof TimingGameFrame) {
                    TimingGameFrame timingGameFrame = (TimingGameFrame) controller.frame;
                    timingGameFrame.timer.stop();
                    // 停止定时器
                }
                setGameOverDialog();
                //结束游戏界面
            }
        }
    }
    @Override
    public void doMoveLeft() {
        if (this.model.moveLeft()) {
            System.out.println("Click VK_LEFT");
            this.afterMove();
            this.model.addRandomNumber();
            int[][] afterArray = new int[XCOUNT][YCOUNT];
            for (int i = 0; i < afterArray.length; i++) {
                for (int j = 0; j < afterArray[i].length; j++) {
                    afterArray[i][j] = model.getNumber(i, j);
                }
            }
            this.model.addEachArray(afterArray);
            this.updateGridsNumber();
            this.setScore();
            if (checkIf2048() && checkIfHadInformWin==false){
                System.out.println("You win!");
                if (controller.frame instanceof TimingGameFrame) {
                    TimingGameFrame timingGameFrame = (TimingGameFrame) controller.frame;
                    timingGameFrame.timer.stop();
                    // 停止定时器
                }
                setWinDialog();
                //结束游戏界面
            }
        }else{
            System.out.println("Unable to move left,try another direction");
            JOptionPane.showMessageDialog(null, "Unable to move left,try another direction", "Warning", JOptionPane.WARNING_MESSAGE);
            if (checkIfEnded()) {
                System.out.println("game is over.");
                if (controller.frame instanceof TimingGameFrame) {
                    TimingGameFrame timingGameFrame = (TimingGameFrame) controller.frame;
                    timingGameFrame.timer.stop();
                    // 停止定时器
                }
                setGameOverDialog();
                //结束游戏界面
            }
        }
    }

    @Override
    public void doMoveUp() {
        if (this.model.moveUp()) {
            System.out.println("Click VK_UP");
            this.afterMove();
            this.model.addRandomNumber();
            int[][] afterArray = new int[XCOUNT][YCOUNT];
            for (int i = 0; i < afterArray.length; i++) {
                for (int j = 0; j < afterArray[i].length; j++) {
                    afterArray[i][j] = model.getNumber(i, j);
                }
            }
            this.model.addEachArray(afterArray);
            this.updateGridsNumber();
            this.setScore();
            if (checkIf2048() && checkIfHadInformWin==false){
                System.out.println("You win!");
                if (controller.frame instanceof TimingGameFrame) {
                    TimingGameFrame timingGameFrame = (TimingGameFrame) controller.frame;
                    timingGameFrame.timer.stop();
                    // 停止定时器
                }
                setWinDialog();
                //结束游戏界面
            }
        }else{
            System.out.println("Unable to move up,try another direction");
            JOptionPane.showMessageDialog(null, "Unable to move up ,try another direction", "Warning", JOptionPane.WARNING_MESSAGE);
            if (checkIfEnded()) {
                System.out.println("game is over.");
                if (controller.frame instanceof TimingGameFrame) {
                    TimingGameFrame timingGameFrame = (TimingGameFrame) controller.frame;
                    timingGameFrame.timer.stop();
                    // 停止定时器
                }
                setGameOverDialog();
                //结束游戏界面
            }
        }
    }

    @Override
    public void doMoveDown() {
        if (this.model.moveDown()) {
            System.out.println("Click VK_DOWN");
            this.afterMove();
            this.model.addRandomNumber();
            int[][] afterArray = new int[XCOUNT][YCOUNT];
            for (int i = 0; i < afterArray.length; i++) {
                for (int j = 0; j < afterArray[i].length; j++) {
                    afterArray[i][j] = model.getNumber(i, j);
                }
            }
            this.model.addEachArray(afterArray);
            this.updateGridsNumber();
            this.setScore();
            if (checkIf2048() && checkIfHadInformWin==false){
                System.out.println("You win!");
                if (controller.frame instanceof TimingGameFrame) {
                    TimingGameFrame timingGameFrame = (TimingGameFrame) controller.frame;
                    timingGameFrame.timer.stop();
                    // 停止定时器
                }
                setWinDialog();
                //结束游戏界面
            }
        }else{
            System.out.println("Unable to move down,try another direction");
            JOptionPane.showMessageDialog(null, "Unable to move down,try another direction", "Warning", JOptionPane.WARNING_MESSAGE);
            if (checkIfEnded()) {
                System.out.println("game is over.");
                if (controller.frame instanceof TimingGameFrame) {
                    TimingGameFrame timingGameFrame = (TimingGameFrame) controller.frame;
                    timingGameFrame.timer.stop();
                    // 停止定时器
                }
                setGameOverDialog();
                //结束游戏界面
            }
        }
    }

    public void doUndo() {
        if (model.getCheckIfOnlyOneUndo() == false) {
            if (this.model.getEachArray().size() > 1) {
                System.out.println("You hit the undo button.");
                int result = JOptionPane.showConfirmDialog(null, "Are you sure to undo the last step?", "Undo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // 根据用户的选择执行操作
                if (result == JOptionPane.NO_OPTION) {
                    System.out.println("You choose not to undo the last step.");
                } else {
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("You choose to undo the last step.");
                        model.setCheckIfOnlyOneUndo(true);
                        this.model.undo();
                        eachScore.remove(eachScore.size() - 1);
                        this.updateGridsNumber();//更新游戏面板
                        this.steps--;
                        this.stepLabel.setText(String.format("<html>Step:<br> %d</html>", this.steps));
                        this.score = eachScore.get(eachScore.size() - 1);
                        this.scoreLabel.setText(String.format("<html>Score:<br> %d</html>", this.score));
                    }
                }
            } else if (steps == 0) {
                System.out.println("No more steps to undo.");
                JOptionPane.showMessageDialog(null, "No more steps to undo.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            System.out.println("You can only undo once.");
            JOptionPane.showMessageDialog(null, "You can only undo once.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void dead() {
        this.model.dead();
        this.updateGridsNumber();
    }

    //判断是否是有效移动
    public boolean checkValidMove(int[][] initialArray, int[][] lastArray) {
        boolean check = false;
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                if (initialArray[i][j] != lastArray[i][j]) {
                    check = true;
                }
            }
        }
        return check;
    }

    //判断游戏是否达到2048
    public boolean checkIf2048() {
        boolean check = false;
        for (int i = 0; i < XCOUNT; i++) {
            for (int j = 0; j < YCOUNT; j++) {
                if (model.getNumber(i, j) == 2048) {
                    check = true;
                }
            }
        }
        return check;
    }

    //判断游戏是否终止
    public boolean checkIfEnded() {//false为未结束
        boolean check = true;
        for (int k = 0; k < 4; k++) {
            int[][] originalArray = new int[XCOUNT][YCOUNT];
            for (int i = 0; i < originalArray.length; i++) {
                for (int j = 0; j < originalArray[i].length; j++) {
                    originalArray[i][j] = this.model.getNumber(i, j);
                }
            }
            switch (k) {
                case 0:
                    this.model.moveRight();
                    break;
                case 1:
                    this.model.moveLeft();
                    break;
                case 2:
                    this.model.moveUp();
                    break;
                case 3:
                    this.model.moveDown();
                    break;
            }
            for (int i = 0; i < XCOUNT; i++) {
                for (int j = 0; j < YCOUNT; j++) {
                    if (originalArray[i][j] != this.model.getNumber(i, j)) {
                        check = false;
                        this.model.setNumbers(originalArray);
                        return check;
                    }
                }
            }
            this.model.setNumbers(originalArray);
        }
        return check;
    }


    public void afterMove() {//用于步数加1
        this.steps++;
        this.stepLabel.setText(String.format("<html>Step:<br> %d</html>", this.steps));
    }

    public void setStepLabel(JLabel stepLabel) {//用于设置步数
        this.stepLabel = stepLabel;
    }

    public void setScore() {
        this.score = model.getScore();
        scoreLabel.setText(String.format("<html>Score:<br> %d</html>", model.getScore()));
        eachScore.add(model.getScore());
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public int getXCOUNT() {
        return XCOUNT;
    }

    public int getYCOUNT() {
        return YCOUNT;
    }

    //建立一个游戏胜利的对话框
    public void setWinDialog() {
        if (controller.frame instanceof AIGameFrame) {
            AIGameFrame aiGameFrame = (AIGameFrame) controller.frame;
            aiGameFrame.timer.stop();
            //先做一个弹窗，询问是否继续游戏,如果是则继续游戏，就focus到这个窗口上，如果不是就弹出游戏胜利窗口
            int result = JOptionPane.showConfirmDialog(null, "You win! Do you want to continue the game?", "Win", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                //关闭弹窗，继续游戏
                checkIfHadInformWin=true;
                aiGameFrame.timer.start();
                this.requestFocus();
            } else {
                if (this.controller.frame.user!=null){
                    if(this.controller.frame instanceof TimingGameFrame){
                        if(this.score >= this.controller.frame.user.TimeModeHighestScore) {
                            this.controller.frame.user.TimeModeHighestScore = this.score;
                        }
                        gameWinDialog = new GameWinDialog(this.controller.frame, "Game Over.", this.score, this.controller.frame.user.HighestScore, this.steps, controller);
                    }else {
                        if (this.score >= this.controller.frame.user.HighestScore) {
                            this.controller.frame.user.HighestScore = this.score;
                        }
                        gameWinDialog = new GameWinDialog(this.controller.frame, "Game Over.", this.score, this.controller.frame.user.HighestScore, this.steps, controller);
                    }
                }else {
                    gameWinDialog = new GameWinDialog(this.controller.frame, "Game Over.", this.score, 0, this.steps, controller);
                }
                //this.controller.frame和null的区别，null是为了在游戏结束时关闭窗口，而controller.frame是为了显示游戏结束的对话框
                gameWinDialog.setVisible(true);
            }
        }else if (controller.frame instanceof TimingGameFrame) {
            TimingGameFrame timingGameFrame = (TimingGameFrame) controller.frame;
            timingGameFrame.timer.stop();
            //先做一个弹窗，询问是否继续游戏,如果是则继续游戏，就focus到这个窗口上，如果不是就弹出游戏胜利窗口
            int result = JOptionPane.showConfirmDialog(null, "You win! Do you want to continue the game?", "Win", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                //关闭弹窗，继续游戏
                checkIfHadInformWin=true;
                timingGameFrame.timer.start();
                this.requestFocus();
            } else {
                if (this.controller.frame.user!=null){
                    if(this.controller.frame instanceof TimingGameFrame){
                        if(this.score >= this.controller.frame.user.TimeModeHighestScore) {
                            this.controller.frame.user.TimeModeHighestScore = this.score;
                        }
                        gameWinDialog = new GameWinDialog(this.controller.frame, "Game Over.", this.score, this.controller.frame.user.HighestScore, this.steps, controller);
                    }else {
                        if (this.score >= this.controller.frame.user.HighestScore) {
                            this.controller.frame.user.HighestScore = this.score;
                        }
                        gameWinDialog = new GameWinDialog(this.controller.frame, "Game Over.", this.score, this.controller.frame.user.HighestScore, this.steps, controller);
                    }
                }else {
                    gameWinDialog = new GameWinDialog(this.controller.frame, "Game Over.", this.score, 0, this.steps, controller);
                }
                //this.controller.frame和null的区别，null是为了在游戏结束时关闭窗口，而controller.frame是为了显示游戏结束的对话框
                gameWinDialog.setVisible(true);
            }
        }else {//先做一个弹窗，询问是否继续游戏,如果是则继续游戏，就focus到这个窗口上，如果不是就弹出游戏胜利窗口
            int result = JOptionPane.showConfirmDialog(null, "You win! Do you want to continue the game?", "Win", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                //关闭弹窗，继续游戏
                checkIfHadInformWin = true;
                this.requestFocus();
            } else {
                if (this.controller.frame.user != null) {
                    if (this.controller.frame instanceof TimingGameFrame) {
                        if (this.score >= this.controller.frame.user.TimeModeHighestScore) {
                            this.controller.frame.user.TimeModeHighestScore = this.score;
                        }
                        gameWinDialog = new GameWinDialog(this.controller.frame, "Game Over.", this.score, this.controller.frame.user.HighestScore, this.steps, controller);
                    } else {
                        if (this.score >= this.controller.frame.user.HighestScore) {
                            this.controller.frame.user.HighestScore = this.score;
                        }
                        gameWinDialog = new GameWinDialog(this.controller.frame, "Game Over.", this.score, this.controller.frame.user.HighestScore, this.steps, controller);
                    }
                } else {
                    gameWinDialog = new GameWinDialog(this.controller.frame, "Game Over.", this.score, 0, this.steps, controller);
                }
                //this.controller.frame和null的区别，null是为了在游戏结束时关闭窗口，而controller.frame是为了显示游戏结束的对话框
                gameWinDialog.setVisible(true);
            }
        }

    }

    //建立一个游戏结束的对话框
    public void setGameOverDialog() {
        if (this.controller.frame.user!=null){
            if(this.controller.frame instanceof TimingGameFrame){
                if(this.score >= this.controller.frame.user.TimeModeHighestScore) {
                    this.controller.frame.user.TimeModeHighestScore = this.score;
                }
                gameOverDialog = new GameOverDialog(this.controller.frame, "Game Over.", this.score, this.controller.frame.user.HighestScore, this.steps, controller);
            }else {
                if (this.score >= this.controller.frame.user.HighestScore) {
                    this.controller.frame.user.HighestScore = this.score;
                }
                gameOverDialog = new GameOverDialog(this.controller.frame, "Game Over.", this.score, this.controller.frame.user.HighestScore, this.steps, controller);
            }
        }else {
            gameOverDialog = new GameOverDialog(this.controller.frame, "Game Over.", this.score, 0, this.steps, controller);
        }
        //this.controller.frame和null的区别，null是为了在游戏结束时关闭窗口，而controller.frame是为了显示游戏结束的对话框
        gameOverDialog.setVisible(true);
    }

    public void setScoreLabel(int score) {
        this.score = score;
        scoreLabel.setText(String.format("<html>Score:<br> %d</html>", score));
    }

    public void setStepLabel(int step) {
        this.steps = step;
        stepLabel.setText(String.format("<html>Step:<br> %d</html>", step));
    }

    public int getSteps() {
        return steps;
    }

    public GridNumber getModel() {
        return model;
    }

    public void doMagic() {
        if (model.getCheckIfOnlyOneMagic() == true) {
            JOptionPane.showMessageDialog(null, "You can only use magic once.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            boolean check = false;
            for (int i = 0; i < XCOUNT; i++) {
                for (int j = 0; j < YCOUNT; j++) {
                    if (model.getNumber(i, j) > 4) {
                        check = true;
                    }

                }
            }
            if (check == false) {
                JOptionPane.showMessageDialog(null, "You can only use magic after generating a number greater than 4.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }else {
                int result = JOptionPane.showConfirmDialog(null, "This magic button will eliminate all 2 and 4 in the game board, do you want to use it?", "Magic", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    model.doMagic();
                    updateGridsNumber();
                    model.setCheckIfOnlyOneMagic(true);
                }
            }
        }
    }
}