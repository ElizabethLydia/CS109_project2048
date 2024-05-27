package view;

import controller.AudioPlayer;
import user.User;
import util.Create;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JDialog implements Create {//现在Settings类copies自chooser，方便之后调整
    private JButton ChangeBgm;
    private JButton VolumeUp;
    private JButton VolumeDown;
    private JButton RemoveUser;
    boolean bofang = true;//true为循环播放
    int song=0;
    float ylforce = 0;
    AudioPlayer music=null;
    JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    User user=null;
    JFrame frame;
    public Settings(Menu1 parent) {//JFrame parent是父窗口
        super(parent, "Settings", true);
        //modal为true时，表示对话框是模态的，即用户必须先关闭对话框才能回到父窗口
        this.setLayout(null);//使用linear布局,这句话说的是这个对话框的布局方式是null
        this.setSize(500, 700);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//设置关闭操作，这个对话框关闭时，只关闭这个对话框
        this.setLocationRelativeTo(parent);// 方法会将子窗口相对于父窗口进行定位，通常是使子窗口显示在父窗口的中央位置。
        getContentPane().setBackground(new Color(0xF6ECDF));//设置窗口背景颜色
        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/SettingsTitle.png"));
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setBounds(0, 0, 500, 300);
        this.add(imageLabel);
        init();
        this.RemoveUser= createButtonWithIcon("RemoveUser", new Point(120, 300), 250, 70, this);
        this.ChangeBgm = createButtonWithIcon("ChangeBgm", new Point(120, 400), 250, 70, this);
        this.VolumeUp = createButtonWithIcon("VolumeUp", new Point(120, 500), 80, 80, this);
        this.VolumeDown = createButtonWithIcon("VolumeDown", new Point(290, 500), 80, 80, this);
        this.RemoveUser.addActionListener(e -> {
            if(user==null){
                JOptionPane.showMessageDialog(null, "Please login first","warning",JOptionPane.WARNING_MESSAGE);
            }else {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure to remove this user?", "Remove User", JOptionPane.YES_NO_OPTION);
                if(result==JOptionPane.YES_OPTION){
                    parent.userManager.users.remove(user);
                    this.dispose();
                    frame.dispose();
                    parent.userManager.updateUser();
                    parent.setVisible(true);

                }
            }
        });
        this.ChangeBgm.addActionListener(e -> {//给按钮添加监听器,当按钮被点击时，执行以下操作
            music.stop();
            switch (song){
                case 0:
                    music = new AudioPlayer("project/project-demo-src/src/controller/Brian Crain - Song for Sienna.wav");
                    song = 1;
                    break;
                case 1:
                    music = new AudioPlayer("project/project-demo-src/src/controller/Nagareyuku Kumo.wav");
                    song = 2;
                    break;
                case 2:
                    music = new AudioPlayer("project/project-demo-src/src/controller/Yiruma - River Flows in You.wav");
                    song = 0;
                    break;
            }
             music.start(true);
        });
        this.VolumeUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = slider.getValue();
                slider.setValue(value + 10); // 向左移动10个单位
            }
        });
        this.VolumeDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = slider.getValue();
                slider.setValue(value - 10); // 向右移动10个单位
            }
        });
        parent.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    private void init() {
        music = new AudioPlayer("project/project-demo-src/src/controller/Yiruma - River Flows in You.wav");
        slider.setValue(100);
//        slider.setPaintTicks(true);// setPaintTicks()方法是设置是否在JSlider加上刻度，若为true则下面两行才有作用。
//        slider.setMajorTickSpacing(20);
//        slider.setMinorTickSpacing(5);
//        slider.setPaintLabels(true);// setPaintLabels()方法为设置是否数字标记，若设为true，则JSlider刻度上就会有数值出现。
//        slider.setPaintTrack(true);// setPaintTrack()方法表示是否出现滑动杆的横杆。默认值为true.
//        slider.setSnapToTicks(true);// setSnapToTicks()方法表示一次移动一个小刻度，而不再是一次移动一个单位刻度。
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ylforce = (float) ((0.86) * slider.getValue() - 80);
                music.setVol(ylforce);
            }
        });
        music.start(bofang);
        slider.setBounds(100, 600, 200, 50);
        this.add(slider);
        slider.setVisible(false);
    }
   /* private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (bofang == false) {
                bofang = true;
                bftime = 0;
                music.start(bofang);

            } else {
                music.start(bofang);
            }
        }
    }
    private class ButtonHandler1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (bofang == true) {
                bofang = false;
                music.stop();
            } else {
                music.stop();
            }
        }
    }

    private class ButtonHandler2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (bofang == false) {
                bofang = true;
                music.continues();
            } else {
                music.continues();
            }
        }
    }*/
    public void setUser(User user){
        this.user = user;
    }
    public void setFrame(JFrame frame){
        this.frame = frame;
    }


}
