package user;

public class User {
    public String name;
    public int HighestScore ;
    public int TimeModeHighestScore;
    public int xCount;
    public int yCount;
    public int[][]numbers ;
    public int score ;
    public int step;
    public int time ;
    public int indexLine;//行索引
    public UserManager userManager;
    public User(UserManager userManager){
        HighestScore = 0;
        TimeModeHighestScore = 0;
        xCount = 4;
        yCount = 4;
        numbers = new int[][]{{0, 0, 2, 0}, {0, 0, 0, 0}, {0, 0, 4, 0}, {0, 0, 0, 0}};
        score = 0;
        step = 0;
        time = 0;
        indexLine = 0;
        this.userManager = userManager;
    }
}
