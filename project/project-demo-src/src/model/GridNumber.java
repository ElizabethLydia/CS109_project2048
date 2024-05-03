package model;

import java.util.Arrays;
import java.util.Random;

public class GridNumber {
    private final int X_COUNT;
    private final int Y_COUNT;

    private int[][] numbers;

    static Random random = new Random();

    public GridNumber(int xCount, int yCount) {
        this.X_COUNT = xCount;
        this.Y_COUNT = yCount;
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];
        this.initialNumbers();
    }

    public void initialNumbers() {
        //要求：在面板上随机两个位置生成2和4，其余地方都为0
        //初始整个面板都为0
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                numbers[i][j] = 0;//初始化为0
                //todo: update generate numbers method
            }
        }
        int initialX_1 = random.nextInt(X_COUNT);
        int initialY_1 = random.nextInt(Y_COUNT);
        int randomNum1 = random.nextInt(2);//生成0和1的随机整数,0就对应2，1就对应4
        if (randomNum1 == 0) {
            numbers[initialX_1][initialY_1] = 2;
        } else {
            numbers[initialX_1][initialY_1] = 4;
        }
        while (true) {
            int initialX_2 = random.nextInt(X_COUNT);
            int initialY_2 = random.nextInt(Y_COUNT);
            if (initialX_1 != initialX_2 && initialY_1 != initialY_2) {
                int randomNum2 = random.nextInt(2);//生成0和1的随机整数,0就对应2，1就对应4
                if (randomNum2 == 0) {
                    numbers[initialX_2][initialY_2] = 2;
                } else {
                    numbers[initialX_2][initialY_2] = 4;
                }
                break;
            }
        }

    }

    //todo: finish the method of four direction moving.
    public void moveRight() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i][1] += numbers[i][0];
            numbers[i][0] = 0;
        }
    }

    public int getNumber(int i, int j) {
        return numbers[i][j];
    }

    public void printNumber() {
        for (int[] line : numbers) {
            System.out.println(Arrays.toString(line));
        }
    }
}
