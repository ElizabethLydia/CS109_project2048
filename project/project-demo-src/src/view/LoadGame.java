package view;

import util.CreateButtonAndLabel;

import javax.swing.*;
import java.awt.*;


public class LoadGame extends JDialog implements CreateButtonAndLabel {
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
         this.setLayout(null);//使用linear布局不用绝对布局了，待调整
         this.setSize(200, 150);
         setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);




     }


}
