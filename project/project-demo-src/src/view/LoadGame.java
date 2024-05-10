package view;

import util.Create;

import javax.swing.*;
import java.awt.*;


public class LoadGame extends JDialog implements Create {
     private JLabel autoSaveLabel;
     private JButton autoSaveButton;
     private JLabel Save1Label;
     private JButton Save1Button;
     private JLabel Save2Label;
     private JButton Save2Button;
     private JLabel Save3Label;
     private JButton Save3Button;
     private JLabel Save4Label;
     private JButton Save4Button;
     private JLabel Save5Label;
     private JButton Save5Button;

     public LoadGame(JFrame parent) {
         super(parent, "Load Game", true);
         this.setLayout(null);//使用linear布局
         this.setSize(200, 150);
         setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         this.setLocationRelativeTo(parent);// 方法会将子窗口相对于父窗口进行定位，通常是使子窗口显示在父窗口的中央位置。
         this.setResizable(false);//用于设置窗口是否可调整大小的方法。调用这个方法可以防止用户通过拖拽边框改变窗口大小，使窗口变为固定大小。
         //待调整
         autoSaveLabel = createLabel("Auto Save", new Font("serif", Font.ITALIC, 50), new Point(480, 50), 180, 50, this,0xF3E0D1);
         //创建一个自动保存标签
         autoSaveButton = createButton("Load", new Point(100, 150), 100, 50, this);

         Save1Label = createLabel("Save 1", new Font("serif", Font.ITALIC, 50), new Point(480, 50), 180, 50, this,0xF3E0D1);
         Save1Button = createButton("Load", new Point(100, 150), 100, 50, this);

         Save2Label = createLabel("Save 2",  new Font("serif", Font.ITALIC, 50), new Point(480, 50), 180, 50, this,0xF3E0D1);
         Save2Button = createButton("Load",new Point(100, 150), 100, 50, this);

         Save3Label = createLabel("Save 3",  new Font("serif", Font.ITALIC, 50), new Point(480, 50), 180, 50, this,0xF3E0D1);
         Save3Button = createButton("Load", new Point(100, 150), 100, 50, this);

         Save4Label = createLabel("Save 4", new Font("serif", Font.ITALIC, 50), new Point(480, 50), 180, 50, this,0xF3E0D1);
         Save4Button = createButton("Load",new Point(100, 150), 100, 50, this);

         Save5Label = createLabel("Save 5", new Font("serif", Font.ITALIC, 50), new Point(480, 50), 180, 50, this,0xF3E0D1);
         Save5Button = createButton("Load",new Point(100, 150), 100, 50, this);

         this.autoSaveButton.addActionListener(e -> {//给按钮添加监听器,当按钮被点击时，执行以下操作
             GameFrame gameFrame = new GameFrame(4,4);//需要改GameFrame的constructor以传入4*4的值
             gameFrame.getController().loadGame("autoSave.txt");
             gameFrame.setVisible(true);
             //我觉得这个还有问题，并没有真正实现自动保存，自动保存不应该是按钮按下之后的操作，而应该是在游戏进行中根据秒数自动保存
         });

         this.Save1Button.addActionListener(e -> {
             GameFrame gameFrame = new GameFrame(4,4);
             gameFrame.getController().loadGame("Save1.txt");
             gameFrame.setVisible(true);
         });

         this.Save2Button.addActionListener(e -> {
             GameFrame gameFrame = new GameFrame(4,4);
             gameFrame.getController().loadGame("Save2.txt");
             gameFrame.setVisible(true);
         });

         this.Save3Button.addActionListener(e -> {
             GameFrame gameFrame = new GameFrame(4,4);
             gameFrame.getController().loadGame("Save3.txt");
             gameFrame.setVisible(true);
         });

         this.Save4Button.addActionListener(e -> {
             GameFrame gameFrame = new GameFrame(4,4);
             gameFrame.getController().loadGame("Save4.txt");
             gameFrame.setVisible(true);
         });

         this.Save5Button.addActionListener(e -> {
             GameFrame gameFrame = new GameFrame(4,4);
             gameFrame.getController().loadGame("Save5.txt");
             gameFrame.setVisible(true);
         });

         this.dispose();//这句话的意思是销毁这个对话框
         parent.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//当父窗口关闭时，这个对话框也会关闭
     }
}
