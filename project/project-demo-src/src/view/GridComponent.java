package view;

import javax.swing.*;
import java.awt.*;

public class GridComponent extends JComponent {//每一个小方格
    private int row;
    private int col;
    private int number;
    static Font font = new Font("Serif", Font.BOLD, 42);//设置字体大小用

    public GridComponent(int row, int col, int gridSize) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;
        this.number = 0;
    }

    public GridComponent(int row, int col, int number, int gridSize) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;
        this.number = number;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        if (number > 0) {
            g.setColor(this.getBackground());
            g.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10,10,10);
            g.setColor(this.getForeground());
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            int textWidth = metrics.stringWidth(String.valueOf(number));
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g.drawString(String.valueOf(number), x, y);
        } else {
            g.setColor(new Color(0xcdc1b4));
            g.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10,10,10);
        }
    }
    public Color getForeground() {
        return number < 8 ? new Color(0x776e65) :  new Color(0xf9f6f2);
    }

    public Color getBackground() {
        switch (number) {
            case 2:    return new Color(0xF3E0D1);
            case 4:    return new Color(0xede0c8);
            case 8:    return new Color(0xf2b179);
            case 16:   return new Color(0xf59563);
            case 32:   return new Color(0xf67c5f);
            case 64:   return new Color(0xf65e3b);
            case 128:  return new Color(0xedcf72);
            case 256:  return new Color(0xEAC54F);
            case 512:  return new Color(0xEAD231);
            case 1024: return new Color(0xEDE43F);
            case 2048: return new Color(0xD4ED2E);
            case 4096: return new Color(0xC7ED1E);
            case 8192: return new Color(0x0EAAED);
            case 16384:return new Color(0x0E7FED);
        }
        return new Color(0x0E7FED);
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
