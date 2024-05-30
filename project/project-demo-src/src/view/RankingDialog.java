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
        if(parent instanceof TimingGameFrame) {
            JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/TimeRanking.png"));
            //修改图片的大小
            ImageIcon icon = (ImageIcon) imageLabel.getIcon();
            Image img = icon.getImage().getScaledInstance(350, 200, Image.SCALE_DEFAULT);
            imageLabel.setIcon(new ImageIcon(img));
            imageLabel.setBounds(0, 0, 500, 220);
            this.add(imageLabel);
        }else{
            JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/ClassicRanking.png"));
            //修改图片的大小
            ImageIcon icon = (ImageIcon) imageLabel.getIcon();
            Image img = icon.getImage().getScaledInstance(370, 200, Image.SCALE_DEFAULT);
            imageLabel.setIcon(new ImageIcon(img));
            imageLabel.setBounds(0, 0, 500, 220);
            this.add(imageLabel);

        }

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
                        case 1:
                            YourRankLabel = createLabel(rank+"st", new Font("Arial", Font.BOLD, 30), new Point(95, 570), 500, 70, this, 0xFF5B1E);
                            break;
                        case 2:
                            YourRankLabel = createLabel(rank+"nd", new Font("Arial", Font.BOLD, 30), new Point(95, 570), 500, 70, this, 0xFF924E);
                            break;
                        case 3:
                            YourRankLabel = createLabel(rank+"rd", new Font("Arial", Font.BOLD, 30), new Point(95, 570), 500, 70, this, 0xFADB54);
                            break;
                        default:
                            YourRankLabel = createLabel(rank+"th", new Font("Arial", Font.BOLD, 30), new Point(95, 570), 500, 70, this, 0xD86F2F);
                            break;
                    }
                    break;
                }
                rank++;
            }
            FirstLabel = createLabel(userList.get(0).name + ":" , new Font("Arial", Font.BOLD, 40), new Point(150, 240), 500, 70, this, 0x463627);
            JLabel label1 = createLabel(String.valueOf(userList.get(0).HighestScore), new Font("Arial", Font.BOLD, 40), new Point(300, 240), 500, 70, this, 0x463627);
            SecondLabel = createLabel(userList.get(1).name + ":" , new Font("Arial", Font.BOLD, 40), new Point(150, 323), 500, 70, this, 0x463627);
            JLabel label2 = createLabel(String.valueOf(userList.get(1).HighestScore), new Font("Arial", Font.BOLD, 40), new Point(300, 323), 500, 70, this, 0x463627);
            ThirdLabel = createLabel(userList.get(2).name + ":" , new Font("Arial", Font.BOLD, 40), new Point(150, 406), 500, 70, this, 0x463627);
            JLabel label3 = createLabel(String.valueOf(userList.get(2).HighestScore), new Font("Arial", Font.BOLD, 40), new Point(300, 406), 500, 70, this, 0x463627);
            YouLabel = createLabel("You:" , new Font("Arial", Font.BOLD, 40), new Point(150, 550), 500, 70, this, 0x463627);
            JLabel labelY = createLabel(String.valueOf(user.HighestScore), new Font("Arial", Font.BOLD, 40), new Point(300, 550), 500, 70, this, 0x463627);

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
                        case 1:
                            YourRankLabel = createLabel(rank+"st", new Font("Arial", Font.BOLD, 30), new Point(95, 570), 500, 70, this, 0xFF5B1E);
                            break;
                        case 2:
                            YourRankLabel = createLabel(rank+"nd", new Font("Arial", Font.BOLD, 30), new Point(95, 570), 500, 70, this, 0xFF924E);
                            break;
                        case 3:
                            YourRankLabel = createLabel(rank+"rd", new Font("Arial", Font.BOLD, 30), new Point(95, 570), 500, 70, this, 0xFADB54);
                            break;
                        default:
                            YourRankLabel = createLabel(rank+"th", new Font("Arial", Font.BOLD, 30), new Point(95, 570), 500, 70, this, 0xD86F2F);
                            break;
                    }
                    break;
                }
                rank++;
            }
            FirstLabel = createLabel(userList.get(0).name + ":" , new Font("Arial", Font.BOLD, 40), new Point(150, 240), 500, 70, this, 0x463627);
            JLabel label1 = createLabel(String.valueOf(userList.get(0).HighestScore), new Font("Arial", Font.BOLD, 40), new Point(300, 240), 500, 70, this, 0x463627);
            SecondLabel = createLabel(userList.get(1).name + ":" , new Font("Arial", Font.BOLD, 40), new Point(150, 323), 500, 70, this, 0x463627);
            JLabel label2 = createLabel(String.valueOf(userList.get(1).HighestScore), new Font("Arial", Font.BOLD, 40), new Point(300, 323), 500, 70, this, 0x463627);
            ThirdLabel = createLabel(userList.get(2).name + ":" , new Font("Arial", Font.BOLD, 40), new Point(150, 406), 500, 70, this, 0x463627);
            JLabel label3 = createLabel(String.valueOf(userList.get(2).HighestScore), new Font("Arial", Font.BOLD, 40), new Point(300, 406), 500, 70, this, 0x463627);
            YouLabel = createLabel("You:" , new Font("Arial", Font.BOLD, 40), new Point(150, 550), 500, 70, this, 0x463627);
            JLabel labelY = createLabel(String.valueOf(user.HighestScore), new Font("Arial", Font.BOLD, 40), new Point(300, 550), 500, 70, this, 0x463627);
        }
        JLabel rankingLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/RankingLabel.png"));
        //修改图片的大小
        ImageIcon icon2 = (ImageIcon) rankingLabel.getIcon();
        Image img2 = icon2.getImage().getScaledInstance(430, 430, Image.SCALE_DEFAULT);
        rankingLabel.setIcon(new ImageIcon(img2));
        rankingLabel.setBounds(0, 170, 500, 500);
        this.add(rankingLabel);

    }
}
