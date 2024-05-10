package view;

import model.GridNumber;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
    private ArrayList<Integer> eachScore = new ArrayList<>();

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
        initialGame();

    }

    public GridNumber getModel() {
        return model;
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
        int[][] initialArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                initialArray[i][j] = model.getNumber(i, j);
            }
        }
        this.model.moveRight();
        int[][] lastArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < lastArray.length; i++) {
            for (int j = 0; j < lastArray[i].length; j++) {
                lastArray[i][j] = model.getNumber(i, j);
            }
        }
        if (this.checkValidMove(initialArray, lastArray)) {
            System.out.println("Click VK_RIGHT");
            this.afterMove();
            this.model.addRandomNumber();
            this.model.addEachArray(initialArray);
            this.updateGridsNumber();
            this.setScore();
        } else {
            System.out.println("Unable to move right,try another direction");
            if (checkIfEnded()) {
                System.out.println("game is over");
                //结束游戏界面
            }
        }
    }

    @Override
    public void doMoveLeft() {
        int[][] initialArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                initialArray[i][j] = model.getNumber(i, j);
            }
        }
        this.model.moveLeft();
        int[][] lastArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < lastArray.length; i++) {
            for (int j = 0; j < lastArray[i].length; j++) {
                lastArray[i][j] = model.getNumber(i, j);
            }
        }
        if (this.checkValidMove(initialArray, lastArray)) {
            System.out.println("Click VK_LEFT");
            this.afterMove();
            this.model.addRandomNumber();
            this.model.addEachArray(initialArray);
            this.updateGridsNumber();
            this.setScore();
        } else {
            System.out.println("Unable to move left,try another direction");
            if (checkIfEnded()) {
                System.out.println("game is over");
                //结束游戏界面
            }
        }
    }

    @Override
    public void doMoveUp() {
        int[][] initialArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                initialArray[i][j] = model.getNumber(i, j);
            }
        }
        this.model.moveUp();
        int[][] lastArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < lastArray.length; i++) {
            for (int j = 0; j < lastArray[i].length; j++) {
                lastArray[i][j] = model.getNumber(i, j);
            }
        }
        if (this.checkValidMove(initialArray, lastArray)) {
            System.out.println("Click VK_UP");
            this.afterMove();
            this.model.addRandomNumber();
            this.model.addEachArray(initialArray);
            this.updateGridsNumber();
            this.setScore();
        } else {
            System.out.println("Unable to move up,try another direction");
            if (checkIfEnded()) {
                System.out.println("game is over");
                //结束游戏界面
            }
        }
    }

    @Override
    public void doMoveDown() {
        int[][] initialArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                initialArray[i][j] = model.getNumber(i, j);
            }
        }
        this.model.moveDown();
        int[][] lastArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < lastArray.length; i++) {
            for (int j = 0; j < lastArray[i].length; j++) {
                lastArray[i][j] = model.getNumber(i, j);
            }
        }
        if (this.checkValidMove(initialArray, lastArray)) {
            System.out.println("Click VK_DOWN");
            this.afterMove();
            this.model.addRandomNumber();
            this.model.addEachArray(initialArray);
            this.updateGridsNumber();
            this.setScore();
        } else {
            System.out.println("Unable to move down,try another direction");
            if (checkIfEnded()) {
                System.out.println("game is over");
                //结束游戏界面
            }
        }
    }

    public void doUndo() {
        if (model.getCheckIfOnlyOneUndo() == false) {
            if (this.model.getEachArray().size() > 1) {
                System.out.println("You hit the undo button");
                int result = JOptionPane.showConfirmDialog(this, "Are you sure to undo the last step?", "Undo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // 根据用户的选择执行操作
                if (result == JOptionPane.NO_OPTION) {
                    System.out.println("You choose not to undo the last step");
                } else {
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("You choose to undo the last step");
                        model.setCheckIfOnlyOneUndo(true);
                        this.model.undo();
                        eachScore.remove(eachScore.size() - 1);
                        this.updateGridsNumber();//更新游戏面板
                        //为什么游戏面板没有更新
                        this.steps--;
                        this.stepLabel.setText(String.format("<html>Step:<br> %d</html>", this.steps));
                        this.score = eachScore.get(eachScore.size() - 1);
                        this.scoreLabel.setText(String.format("<html>Score:<br> %d</html>", this.score));
                    }
                }
            } else {
                System.out.println("No more steps to undo");
            }
        } else {
            System.out.println("You can only undo once");
            JOptionPane.showMessageDialog(this, "You can only undo once", "Warning", JOptionPane.WARNING_MESSAGE);
        }
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

    //判断游戏是否终止
    public boolean checkIfEnded() {
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

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }




}