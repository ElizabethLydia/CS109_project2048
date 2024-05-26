package model;
import java.util.List;

/**
 *
 * @author Jason
 */
public class AISolver {//先记录原始棋盘，然后进行移动预测，再创建每次新出现的棋子，并进行评估，最后选择最优的移动。
    int xcount;
    int ycount;
    int[][] numbers ;
    double r;
    int d;
    int mode;

    public AISolver(int xcount, int ycount, double hr, int hd, int hm) {
        r = hr;//0.2
        d = hd;//2
        mode = 2;
        if (hm == 1) mode = 8;//8
        this.xcount = xcount;
        this.ycount = ycount;
        numbers = new int[xcount][ycount];
    }

    public void setNumbers(int[][] state) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                numbers[i][j] = state[i][j];
    }

    public String minMaxAI() {
        double max = 0;
        int w = -1;
        NumbersState tb = new NumbersState(numbers, r,xcount,ycount);
        double[] prediction =minimax(numbers,d,r);
        for(int i=0;i<4;i++){
            if(max<= prediction[i] && tb.moveChess(i)){
                max= prediction[i];
                w=i;
            }
        }


        if (w == 0) return "w";
        if (w == 1) return "a";
        if (w == 2) return "s";
        if (w == 3) return "d";
        return "haha";
    }
    public double[] minimax(int[][] mat, int depth,double r) {//输入棋盘当前状态，深度（进行预测的层数，暂设定3层为）=2，和评估函数=0.2
        double[] NodeScore= new double[4];//初始化节点得分数组
        if (depth==0 ){
            for(int i=0;i<4;i++){
                NumbersState l=new NumbersState(mat, r,xcount,ycount);//创建当前棋盘状态
                if (l.moveChess(i)){
                    NumbersState l2 = new NumbersState(l.getNextState(), r,xcount,ycount);//向一个方向移动后棋盘状态
                    NodeScore[i]=l2.getHeuristicScore(mode);
                }else{
                    NodeScore[i]=Double.MIN_VALUE;
                }

            }
        }else{
            for(int i=0;i<4;i++){
                NumbersState l=new NumbersState(mat, r,xcount,ycount);//创建当前棋盘状态
                if (!l.moveChess(i)){//在这就进行了四个方向的移动预测
                    NodeScore[i]=Double.MIN_VALUE;//不能移动=>评分函数为最小值（最差状况）（评分越高越好）
                }else{
                    double mins=Double.MAX_VALUE;
                    List<List<Integer>> empty = l.getEmpty();//得到移动预测后棋盘中所有空位置的坐标
                    for (List<Integer> location : empty) {//在所有空位置均尝试添加棋子，并进行递归
                        int x = location.get(0);
                        int y = location.get(1);

                        NumbersState l2 = new NumbersState(l.getNextState(), r,xcount,ycount);//得到移动预测后棋盘状态
                        l2.create2(x, y);//在空位置添加棋子2
                        double minOfmax2 =Double.MIN_VALUE;
                        double[] minOfmaxlist2 =minimax( l2.getNextState(), depth-1 ,r);//下一层的预测，为一个数组，分别对应四个方向的预测值
                        for(int j = 0; j <4; j++){//取子层预测这四个方向中最大值作为当前层的预测值
                            if (minOfmaxlist2[j]> minOfmax2)
                                minOfmax2 = minOfmaxlist2[j];
                        }
                        if (empty.isEmpty()) minOfmax2 = Double.MIN_VALUE;//没有空格就为最小值（最差状况）

                        NumbersState l3 = new NumbersState(l.getNextState(), r,xcount,ycount);//得到移动预测后棋盘状态
                        l3.create4(x, y);//在空位置添加棋子4
                        double minOfmax4 =Double.MIN_VALUE;
                        double[] minOfmaxlist4 =minimax( l3.getNextState(), depth-1 ,r);//下一层的预测，为一个数组，分别对应四个方向的预测值
                        for(int j = 0; j <4; j++){//取子层预测这四个方向中最大值作为当前层的预测值
                            if (minOfmaxlist4[j]> minOfmax4)
                                minOfmax4 = minOfmaxlist4[j];
                        }
                        if (empty.isEmpty()) minOfmax4 = Double.MIN_VALUE;//没有空格就为最小值（最差状况）
                        mins = Math.min(mins,(minOfmax2 + minOfmax4)/2);
                        //你可能会疑惑为什么要有这么一个mins的比较，实际上每次计入到NodeScore的值都是minOfmax，要么就是最小值，要么是子层预测这四个方向中最大值
                        //因为不这样写的话，我的逻辑模型会产生r的15次方的值，会超出double的范围，导致程序报错
                    }
                    NodeScore[i]=mins;//记录四个方向的预测值
                }

            }

        }//

        return NodeScore;
    }
}
