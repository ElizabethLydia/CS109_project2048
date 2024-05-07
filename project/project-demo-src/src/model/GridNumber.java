package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GridNumber {
    private final int X_COUNT;
    private final int Y_COUNT;
    private int score;

    public int[][] numbers;

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

            //没有必要在这时候就把数组全填进去因为还要进行操作,直接对arraylist进行操作就行了得到这行的最终结果之后再输入
            //如果里面元素大于等于2，就要判断两个相邻的瓷砖编号是否相同，
            //如果相同，它们将在停止移动后合并为一个瓷砖，其数值等于它们值的总和，前面的置为0
            int indexY_last = Y_COUNT - 1;//这样每行判断时都能从最后一个位置开始
            if (list.size() > 1) {
                for (int j = list.size()-1; j >0; j--) {
                    if (list.get(j).equals(list.get(j - 1))) {
                        list.set(j, list.get(j)*2);
                        list.set(j-1, 0);
                        score+= list.get(j);
                    }
                }
                //并且这个新合并的瓷砖也将沿着移动方向继续移动，直到不能再移动。
                //重新上面的思路，生成一个list2，用于存储非0的数字，
                //然后清零这一行，再从右往左填充
                ArrayList<Integer> list2 = new ArrayList<>();
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j)!= 0) {
                        list2.add(list.get(j));
                    }
                }
                //将list2中的数字从右向左填充到这一行中
                for (int j = list2.size() - 1; j >= 0; j--) {
                    numbers[i][indexY_last] = list2.get(j);
                    indexY_last--;
                }
            }
            if (list.size() == 1){
                numbers[i][indexY_last] = list.get(0);
            }
        }
    }

    public void moveLeft() {
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

            //没有必要在这时候就把数组全填进去因为还要进行操作,直接对arraylist进行操作就行了得到这行的最终结果之后再输入
            //如果里面元素大于等于2，就要判断两个相邻的瓷砖编号是否相同，
            //如果相同，它们将在停止移动后合并为一个瓷砖，其数值等于它们值的总和，前面的置为0
            if (list.size() > 1) {
                for (int j = 0; j < list.size()-1; j++) {
                    if (list.get(j).equals(list.get(j + 1))) {
                        list.set(j, list.get(j)*2);
                        list.set(j+1, 0);
                        score+= list.get(j);

                    }
                }
                //并且这个新合并的瓷砖也将沿着移动方向继续移动，直到不能再移动。
                //重新上面的思路，生成一个list2，用于存储非0的数字，
                //然后清零这一行，再从左往右填充
                ArrayList<Integer> list2 = new ArrayList<>();
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) != 0) {
                        list2.add(list.get(j));
                    }
                }
                //将list2中的数字从左向右填充到这一行中
                //反正都从第0未开始就没必要设置indexY_last了
                for (int j = 0; j < list2.size(); j++) {
                    numbers[i][j] = list2.get(j);
                }
            }
            if (list.size() == 1){
                numbers[i][0] = list.get(0);
            }
        }
    }


    public void moveUp() {
        for (int j = 0; j < numbers[0].length; j++) {
            //每一列都生成一个list，用于存储非0的数字
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i][j] != 0) {
                    list.add(numbers[i][j]);
                }
            }
            //将这一列的所有数字都置为0，因为数字已经被记录下来了嘛
            for (int i = 0; i < numbers.length; i++) {
                numbers[i][j] = 0;
            }
            //没有必要在这时候就把数组全填进去因为还要进行操作,直接对arraylist进行操作就行了得到这行的最终结果之后再输入
            //如果里面元素大于等于2，就要判断两个相邻的瓷砖编号是否相同，
            //如果相同，它们将在停止移动后合并为一个瓷砖，其数值等于它们值的总和，前面的置为0
            if (list.size() > 1) {
                for (int i = 0; i < list.size() - 1; i++) {
                    if (list.get(i).equals(list.get(i + 1))) {
                        list.set(i, list.get(i)*2);
                        list.set(i + 1, 0);
                        score+= list.get(i);
                    }
                }
                //并且这个新合并的瓷砖也将沿着移动方向继续移动，直到不能再移动。
                //重新上面的思路，生成一个list2，用于存储非0的数字，
                //然后清零这一行，再从上往下填充
                ArrayList<Integer> list2 = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != 0) {
                        list2.add(list.get(i));
                    }
                }
                //将list2中的数字从上向下填充到这一行中
                for (int i = 0; i < list2.size(); i++) {
                    numbers[i][j] = list2.get(i);
                }
            }
            if (list.size() == 1) {
                numbers[0][j] = list.get(0);
            }
        }
    }

    public void moveDown() {
        for (int j = 0; j < numbers[0].length; j++) {
            int indexX_last = X_COUNT - 1;//这样每列判断时都能从最后一个位置开始
            //每一列都生成一个list，用于存储非0的数字
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i][j] != 0) {
                    list.add(numbers[i][j]);
                }
            }
            //将这一列的所有数字都置为0，因为数字已经被记录下来了嘛
            for (int i = 0; i < numbers.length; i++) {
                numbers[i][j] = 0;
            }
            //没有必要在这时候就把数组全填进去因为还要进行操作,直接对arraylist进行操作就行了得到这行的最终结果之后再输入
            //如果里面元素大于等于2，就要判断两个相邻的瓷砖编号是否相同，
            //如果相同，它们将在停止移动后合并为一个瓷砖，其数值等于它们值的总和，前面的置为0
            if (list.size() > 1) {
                for (int i = list.size() - 1; i > 0; i--) {
                    if (list.get(i).equals(list.get(i - 1))) {
                        list.set(i, list.get(i)*2);
                        list.set(i - 1, 0);
                        score+= list.get(i);
                    }
                }
                //并且这个新合并的瓷砖也将沿着移动方向继续移动，直到不能再移动。
                //重新上面的思路，生成一个list2，用于存储非0的数字，
                //然后清零这一行，再从下往上填充
                ArrayList<Integer> list2 = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != 0) {
                        list2.add(list.get(i));
                    }
                }
                //将list2中的数字从下向上填充到这一行中
                for (int i = list2.size() - 1; i >= 0; i--) {
                    numbers[indexX_last][j] = list2.get(i);
                    indexX_last--;
                }
            }
            if (list.size() == 1) {
                numbers[indexX_last][j] = list.get(0);
            }
        }
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
    public int[][] getNumbers() {
        return numbers;
    }
    public void setNumbers(int[][] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                this.numbers[i][j] = numbers[i][j];
            }
        }
    }

    public void printNumber() {
        for (int[] line : numbers) {
            System.out.println(Arrays.toString(line));
        }
    }
    public int getScore() {
        return score;
    }


    public void setNumber(int[][] originalArray) {
        for (int i = 0; i < originalArray.length; i++) {
            for (int j = 0; j < originalArray[i].length; j++) {
                this.numbers[i][j] = originalArray[i][j];
            }
        }
    }
}
