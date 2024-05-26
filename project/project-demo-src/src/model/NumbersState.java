package model;
import java.util.ArrayList;
import java.util.List;

public class NumbersState {
    int X_COUNT ;
    int Y_COUNT ;

    int[][] numbers;//最开始预测前没改变过的棋盘
    int[][] tmp;//用于运行预测棋盘
    final double r;//0.2

    public NumbersState(int[][] state, double hr, int x_count, int y_count) {
        X_COUNT = x_count;
        Y_COUNT = y_count;
        r = hr;
        numbers = new int[X_COUNT][Y_COUNT];
        tmp = new int[X_COUNT][Y_COUNT];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numbers[i][j] = state[i][j];
                tmp[i][j] = numbers[i][j];
            }
        }
    }

    private void rotateBoard(int times) {
        for (int i = 0; i < times; i++) {   //rotate the chess numbers
            if (X_COUNT ==4) {
                int tmpNum;
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        tmpNum = tmp[j][k];
                        tmp[j][k] = tmp[3 - k][j];
                        tmp[3 - k][j] = tmp[3 - j][3 - k];
                        tmp[3 - j][3 - k] = tmp[k][3 - j];
                        tmp[k][3 - j] = tmpNum;
                    }
                }
            }else {
                int tmpNum;
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 3; k++) {
                        tmpNum = tmp[j][k];
                        tmp[j][k] = tmp[4 - k][j];
                        tmp[4 - k][j] = tmp[4 - j][4 - k];
                        tmp[4 - j][4 - k] = tmp[k][4 - j];
                        tmp[k][4 - j] = tmpNum;
                    }
                }
            }
        }
    }

    public boolean moveChess(int derection) {     //0-up, 1-left, 2-down, 3-right
        for (int i = 0; i < X_COUNT; i++)//先记录原来的棋盘board，对tmp进行操作就不用改变原来的棋盘了
            for (int j = 0; j < Y_COUNT; j++)
                tmp[i][j] = numbers[i][j];

        this.rotateBoard(derection);

        for (int i = 0; i < X_COUNT; i++) {   //make the move
            List<Integer> merge = new ArrayList<Integer>();
            for (int j = 0; j < Y_COUNT; j++)
                if (tmp[j][i] != 0) merge.add(tmp[j][i]);
            for (int j = 0; j < merge.size()-1; j++) {
                if ((int)merge.get(j) == (int)merge.get(j+1) && merge.get(j) != 0) {
                    merge.set(j, merge.get(j+1) * 2);
                    merge.set(j+1, 0);
                }
            }
            for (int j = 0; j < Y_COUNT; j++)
                tmp[j][i] = 0;
            int it = 0;
            for (Integer j: merge)
                if (j != 0) {
                    tmp[it][i] = j;
                    it++;
                }
        }

        this.rotateBoard(4 - derection);

        for (int i = 0; i < X_COUNT; i++)
            for (int j = 0; j < Y_COUNT; j++)
                if (tmp[i][j] != numbers[i][j]) return true;

        return false;
    }

    public boolean create4(int x, int y) {//输入一个位置在这个位置创建4
        for (int i = 0; i < X_COUNT; i++)
            for (int j = 0; j < Y_COUNT; j++)
                tmp[i][j] = numbers[i][j];
        if (tmp[x][y] == 0) {
            tmp[x][y] = 4;
            return true;
        }
        return false;
    }

    public boolean create2(int x, int y) {//输入一个位置在这个位置创建2
        for (int i = 0; i < X_COUNT; i++)
            for (int j = 0; j < Y_COUNT; j++)
                tmp[i][j] = numbers[i][j];
        if (tmp[x][y] == 0) {
            tmp[x][y] = 2;
            return true;
        }
        return false;
    }

    public List<List<Integer>> getEmpty() {//记录哪些位置是空的（坐标）
        List<Integer> pair = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < X_COUNT; i++)
            for (int j = 0; j < Y_COUNT; j++)
                if (tmp[i][j] == 0) {
                    pair.add(i);
                    pair.add(j);
                    ans.add(pair);
                    pair = new ArrayList<Integer>();
                }
        return ans;
    }

    public int[][] getNextState() {
        return tmp;
    }//进行操作后的棋盘

    public int[][] getOriginState() {
        return numbers;
    }

    public double getHeuristicScore(int mode){
        double score=0;
        for(int i=1;i<=mode;i++){//mode为8，共8种旋转方式
            score=Math.max(score,this.getStateHeuristic(i));
        }
        return score;
    }

    public double getStateHeuristic(int direction) {
        //start: 0 ,1,2,3
        //direction:1~8
        double value = 0;
        double rn = 1;
        if(X_COUNT==4 && Y_COUNT==4) {
            for (int count = 0; count < 16; count++) {//蛇形排列，一个角有两种排列方法，共八种

                int i, j;
                i = count % 4;//0~3和两个fori循环一样
                j = count / 4;//0~3和两个fori循环一样
                if (direction == 1) {//左上角，第2，4列反向
                    if (j == 1 || j == 3) {
                        i = 3 - i;
                    }
                    value += (rn * numbers[i][j]);
                }
                if (direction == 2) {//左上角，第2，4行反向
                    if (j == 1 || j == 3) {
                        i = 3 - i;
                    }
                    value += (rn * numbers[j][i]);
                }
                if (direction == 3) {//右上角，第2，4列反向
                    if (j == 1 || j == 3) {
                        i = 3 - i;
                    }
                    j = 3 - j;
                    value += (rn * numbers[i][j]);
                }
                if (direction == 4) {//左下角，第2，4行反向
                    if (j == 1 || j == 3) {
                        i = 3 - i;
                    }
                    j = 3 - j;
                    value += (rn * numbers[j][i]);
                }
                if (direction == 5) {//左下角，第2，4列反向
                    if (j == 1 || j == 3) {
                        i = 3 - i;
                    }
                    i = 3 - i;
                    value += (rn * numbers[i][j]);
                }
                if (direction == 6) {//右上角，第2，4行反向
                    if (j == 1 || j == 3) {
                        i = 3 - i;
                    }
                    i = 3 - i;
                    value += (rn * numbers[j][i]);
                }
                if (direction == 7) {//右下角，第2，4列反向
                    if (j == 1 || j == 3) {
                        i = 3 - i;
                    }
                    i = 3 - i;
                    j = 3 - j;
                    value += (rn * numbers[i][j]);
                }
                if (direction == 8) {//右下角，第2，4行反向
                    if (j == 1 || j == 3) {
                        i = 3 - i;
                    }
                    i = 3 - i;
                    j = 3 - j;
                    value += (rn * numbers[j][i]);
                }
                rn *= r;
            }
        }else{
            for (int count=0;count<25;count++){

                int i,j;
                i=count%5;//0~4和两个fori循环一样
                j=count/5;//0~4和两个fori循环一样
                if (direction == 1) {//左上角，第2，4列反向
                    if (j == 1 || j == 3) {
                        i = 4 - i;
                    }
                    value += (rn * numbers[i][j]);
                }
                if (direction == 2) {//左上角，第2，4行反向
                    if (j == 1 || j == 3) {
                        i = 4 - i;
                    }
                    value += (rn * numbers[j][i]);
                }
                if (direction == 3) {//右上角，第2，4列反向
                    if (j == 1 || j == 3) {
                        i = 4 - i;
                    }
                    j = 4 - j;
                    value += (rn * numbers[i][j]);
                }
                if (direction == 4) {//左下角，第2，4行反向
                    if (j == 1 || j == 3) {
                        i = 4 - i;
                    }
                    j = 4 - j;
                    value += (rn * numbers[j][i]);
                }
                if (direction == 5) {//左下角，第2，4列反向
                    if (j == 1 || j == 3) {
                        i = 4 - i;
                    }
                    i = 4 - i;
                    value += (rn * numbers[i][j]);
                }
                if (direction == 6) {//右上角，第2，4行反向
                    if (j == 1 || j == 3) {
                        i = 4 - i;
                    }
                    i = 4 - i;
                    value += (rn * numbers[j][i]);
                }
                if (direction == 7) {//右下角，第2，4列反向
                    if (j == 1 || j == 3) {
                        i = 4 - i;
                    }
                    i = 4 - i;
                    j = 4 - j;
                    value += (rn * numbers[i][j]);
                }
                if (direction == 8) {//右下角，第2，4行反向
                    if (j == 1 || j == 3) {
                        i = 4 - i;
                    }
                    i = 4 - i;
                    j = 4 - j;
                    value += (rn * numbers[j][i]);
                }
                rn *= r;
            }
        }
        return value;
    }
}