package model;

import java.util.ArrayList;
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
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];//面板大小如4x4，5x5，6x6等
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
        //滑动和合并：当玩家滑动网格时，所有瓷砖会向所选方向移动，直到它们撞到边缘或另一个不可移动的瓷砖。
        //如果两个相邻的瓷砖编号相同，它们将在停止移动后合并为一个瓷砖，其数值等于它们值的总和，
        //并且这个新合并的瓷砖也将沿着移动方向继续移动，直到不能再移动。
        //如果存在三个相邻的相同值的瓷砖，滑动方向末端的两个块将合并在一起。
        //最后一个位置的索引
        int indexX_last = X_COUNT - 1;
        int indexY_last = Y_COUNT - 1;
        for (int i = 0; i < numbers.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<Integer> y = new ArrayList<>();
            for (int j = 0; j < numbers[i].length; j++) {
                if (numbers[i][j] != 0) {
                    list.add(numbers[i][j]);
                    y.add(j);
                }
            }
            for (int j = list.size() - 1; j >= 0; j--) {
                numbers[i][indexY_last] = list.get(j);
                indexY_last--;
            }
            indexY_last = Y_COUNT - 1;
            ////如果两个相邻的瓷砖编号相同，它们将在停止移动后合并为一个瓷砖，其数值等于它们值的总和
            if (list.size() > 1) {
                for (int j = indexY_last; j > 0; j--) {
                    if (numbers[i][j] == numbers[i][j - 1]) {
                        numbers[i][j] = numbers[i][j] + numbers[i][j - 1];
                        numbers[i][j - 1] = 0;
                        //并且这个新合并的瓷砖也将沿着移动方向继续移动，直到不能再移动。
                        for (int k = j - 1; k < indexY_last; k++) {
                            numbers[i][k] = numbers[i][k + 1];
                        }
                        numbers[i][indexY_last] = 0;
                    }
                }
            }else if (list.size() == 1){
                numbers[i][indexY_last] = list.get(0);
                //原来的位置要清零
                numbers[i][y.get(0)] = 0;
            }
        }
    }
    public void moveLeft() {}
    public void moveUp() {}
    public void moveDown() {}


    public void addRandomNumber() {//用于添加随机数字，需要加到上面的moveLeft()等方法最后
        while (true) {
            int X = random.nextInt(X_COUNT);
            int Y = random.nextInt(Y_COUNT);
            if (numbers[X][Y] == 0) {
                int randomNum2 = random.nextInt(2);//生成0和1的随机整数,0就对应2，1就对应4
                if (randomNum2 == 0) {
                    numbers[X][Y] = 2;
                } else {
                    numbers[X][Y] = 4;
                }
                break;
            }
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
