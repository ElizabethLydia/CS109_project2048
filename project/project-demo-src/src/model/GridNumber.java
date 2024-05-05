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

        //首先生成一个最初的数组，为了后面和移动后的数组进行比较，如果是一样的，就说明无效移动，不添加新的随机数
        //如果不一样，就说明有效移动，生成新的随机数
        int[][] initialArray = new int[X_COUNT][Y_COUNT];
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                initialArray[i][j] = numbers[i][j];
            }
        }

        int indexY_last = Y_COUNT - 1;
        for (int i = 0; i < numbers.length; i++) {
            //每一行都生成一个list，用于存储非0的数字
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < numbers[i].length; j++) {
                if (numbers[i][j] != 0) {
                    list.add(numbers[i][j]);
                }
            }
            //将这一行的所有数字都置为0，因为数字已经被记录下来了嘛
            for (int j = 0; j < numbers[i].length; j++) {
                numbers[i][j] = 0;
            }
            //将list中的数字从右向左填充到这一行中
            if (indexY_last != -1) {
                for (int j = list.size() - 1; j >= 0; j--) {
                    numbers[i][indexY_last] = list.get(j);
                    indexY_last--;
                }
            }
            //由于前面索引进行了加减，这里得恢复此时的最后一个位置的索引
            indexY_last = Y_COUNT - 1;
            //如果里面元素大于等于2，就要判断两个相邻的瓷砖编号是否相同，
            //如果相同，它们将在停止移动后合并为一个瓷砖，其数值等于它们值的总和，前面的置为0
            if (list.size() > 1) {
                for (int j = indexY_last; j > 0; j--) {
                    if (numbers[i][j] == numbers[i][j - 1]) {
                        numbers[i][j] = numbers[i][j] + numbers[i][j - 1];
                        numbers[i][j - 1] = 0;
                    }
                }
                //并且这个新合并的瓷砖也将沿着移动方向继续移动，直到不能再移动。
                //重新上面的思路，生成一个list2，用于存储非0的数字，
                //然后清零这一行，再从右往左填充
                ArrayList<Integer> list2 = new ArrayList<>();
                for (int j = 0; j < numbers[i].length; j++) {
                    if (numbers[i][j] != 0) {
                        list2.add(numbers[i][j]);
                    }
                }
                //将这一行的所有数字都置为0，因为数字已经被记录下来了嘛
                for (int j = 0; j < numbers[i].length; j++) {
                    numbers[i][j] = 0;
                }
                //将list2中的数字从右向左填充到这一行中
                for (int j = list2.size() - 1; j >= 0; j--) {
                    numbers[i][indexY_last] = list2.get(j);
                    indexY_last--;
                }
            }
            //如果一行就一个数，那么直接填充到最后一个位置
            if (list.size() == 1){
                numbers[i][indexY_last] = list.get(0);
            }
        }
        //首先生成一个最后的数组，和移动后的数组进行比较，如果是一样的，就说明无效移动，不添加新的随机数
        //如果不一样，就说明有效移动，生成新的随机数
        int[][] lastArray = new int[X_COUNT][Y_COUNT];
        for (int i = 0; i < lastArray.length; i++) {
            for (int j = 0; j < lastArray[i].length; j++) {
                lastArray[i][j] = numbers[i][j];
            }
        }
        //添加一个随机数，目前还没实现有效移动判断的逻辑
        if (checkValidMove(initialArray, lastArray) == true) {
            addRandomNumber();
        }
    }

    public void moveLeft() {
    }

    public void moveUp() {
    }

    public void moveDown() {
    }

    public boolean checkValidMove(int[][] initialArray,int[][] lastArray){
        boolean check = true;
        for (int i = 0; i < X_COUNT ; i++) {
            for (int j = 0; j < Y_COUNT; j++) {
                if (initialArray[i][j] != lastArray[i][j]){
                    check = false;
                }
            }
        }
        return check;
    }
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
