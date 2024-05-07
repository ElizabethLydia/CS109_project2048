package view;

import javax.swing.*;

public class LoadGame extends JDialog {
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
         this.setLayout(null);
         this.setSize(200, 150);
         setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
     }


}
