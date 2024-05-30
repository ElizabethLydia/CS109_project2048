package view;

import user.*;
import util.Create;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RankingDialog extends JDialog implements Create {
    JLabel FirstLabel;
    JLabel SecondLabel;
    JLabel ThirdLabel;
    JLabel YouLabel;
    JLabel YourRankLabel;
    public RankingDialog(UserManager userManager, JFrame parent, User user) {
        super(parent, "Ranking", true);
        this.setSize(500, 700); // 对话框大小
        this.setLayout(null);
        this.setLocationRelativeTo(parent); // 居中显示
        this.setResizable(false); // 不可调整大小
        getContentPane().setBackground(new Color(0xF6ECDF));//设置窗口背景颜色

        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/RankingTitle.png"));
        //修改图片的大小
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(500, 280, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setBounds(0, 0, 500, 260);
        this.add(imageLabel);

        JLabel rankingLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/RankingLabel.png"));
        //修改图片的大小
        ImageIcon icon2 = (ImageIcon) rankingLabel.getIcon();
        Image img2 = icon2.getImage().getScaledInstance(420, 250, Image.SCALE_DEFAULT);
        rankingLabel.setIcon(new ImageIcon(img2));
        rankingLabel.setBounds(0, 260, 500, 250);
        this.add(rankingLabel);

        if(parent instanceof TimingGameFrame) {
            ArrayList<User> userList = new ArrayList<>(userManager.users.keySet());
            Collections.sort(userList, new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    return Integer.compare(u2.TimeModeHighestScore, u1.TimeModeHighestScore); // 降序排序
                }
            });
            int rank = 1;
            for(User u : userList) {
                if (u.equals(user)) {
                    switch (rank%10) {
                        case 1:YourRankLabel = createLabel(rank+"st", new Font("Arial", Font.BOLD, 40), new Point(100, 340), 500, 70, this, 0x545454);
                        case 2:YourRankLabel = createLabel(rank+"nd", new Font("Arial", Font.BOLD, 40), new Point(100, 340), 500, 70, this, 0x545454);
                        case 3:YourRankLabel = createLabel(rank+"rd", new Font("Arial", Font.BOLD, 40), new Point(100, 340), 500, 70, this, 0x545454);
                        default:YourRankLabel = createLabel(rank+"th", new Font("Arial", Font.BOLD, 40), new Point(100, 340), 500, 70, this, 0x545454);
                    }
                    break;
                }
                rank++;
            }
            FirstLabel = createLabel(userList.get(0).name + ":" + userList.get(0).TimeModeHighestScore, new Font("Arial", Font.BOLD, 40), new Point(100, 15), 500, 70, this, 0x545454);
            FirstLabel.setComponentZOrder(rankingLabel, 0);
            SecondLabel = createLabel(userList.get(1).name + ":" + userList.get(1).TimeModeHighestScore, new Font("Arial", Font.BOLD, 40), new Point(100, 100), 500, 70, this, 0x545454);
            SecondLabel.setComponentZOrder(rankingLabel, 1);
            ThirdLabel = createLabel(userList.get(2).name + ":" + userList.get(2).TimeModeHighestScore, new Font("Arial", Font.BOLD, 40), new Point(100, 180), 500, 70, this, 0x545454);
            ThirdLabel.setComponentZOrder(rankingLabel, 2);
            YouLabel = createLabel("You:" + user.TimeModeHighestScore, new Font("Arial", Font.BOLD, 40), new Point(100, 260), 500, 70, this, 0x545454);
            YouLabel.setComponentZOrder(rankingLabel, 3);
        }else{
            ArrayList<User> userList = new ArrayList<>(userManager.users.keySet());
            Collections.sort(userList, new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    return Integer.compare(u2.HighestScore, u1.HighestScore); // 降序排序
                }
            });
            int rank = 1;
            for(User u : userList) {
                if (u.equals(user)) {
                    switch (rank%10) {
                        case 1:YourRankLabel = createLabel(rank+"st", new Font("Arial", Font.BOLD, 40), new Point(100, 340), 500, 70, this, 0x545454);
                        case 2:YourRankLabel = createLabel(rank+"nd", new Font("Arial", Font.BOLD, 40), new Point(100, 340), 500, 70, this, 0x545454);
                        case 3:YourRankLabel = createLabel(rank+"rd", new Font("Arial", Font.BOLD, 40), new Point(100, 340), 500, 70, this, 0x545454);
                        default:YourRankLabel = createLabel(rank+"th", new Font("Arial", Font.BOLD, 40), new Point(100, 340), 500, 70, this, 0x545454);
                    }
                    break;
                }
                rank++;
            }
            FirstLabel = createLabel(userList.get(0).name + ":" + userList.get(0).HighestScore, new Font("Arial", Font.BOLD, 40), new Point(100, 15), 500, 70, this, 0x545454);
            FirstLabel.setComponentZOrder(rankingLabel, 0);
            SecondLabel = createLabel(userList.get(1).name + ":" + userList.get(1).HighestScore, new Font("Arial", Font.BOLD, 40), new Point(100, 100), 500, 70, this, 0x545454);
            SecondLabel.setComponentZOrder(rankingLabel, 1);
            ThirdLabel = createLabel(userList.get(2).name + ":" + userList.get(2).HighestScore, new Font("Arial", Font.BOLD, 40), new Point(100, 180), 500, 70, this, 0x545454);
            ThirdLabel.setComponentZOrder(rankingLabel, 2);
            YouLabel = createLabel("You:" + user.HighestScore, new Font("Arial", Font.BOLD, 40), new Point(100, 260), 500, 70, this, 0x545454);
            YouLabel.setComponentZOrder(rankingLabel, 3);
        }


    }
}
