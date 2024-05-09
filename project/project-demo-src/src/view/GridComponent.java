package view;

import javax.swing.*;
import java.awt.*;

public class GridComponent extends JComponent {//每一个小方格
    private int row;
    private int col;
    private int number;
    static Font font = new Font("Monaco", Font.BOLD,42);//设置字体大小用

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
            g.setColor(this.getBackground());//设置背景颜色，根据数字不同设置不同的颜色
            g.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10,10,10);//设置圆角矩形
            g.setColor(this.getForeground());//设置字体颜色
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(g.getFont());//获取字体的相关信息
            int textWidth = metrics.stringWidth(String.valueOf(number));//获取字体的宽度
            int x = (getWidth() - textWidth) / 2;//设置字体的x坐标
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();//设置字体的y坐标
            g.drawString(String.valueOf(number), x, y);//画出数字
        } else {
            g.setColor(new Color(0xcdc1b4));//设置背景颜色,这一步和 g.setColor(this.getForeground());不同的是这个是设置空白的背景颜色
            g.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10,10,10);//设置圆角矩形
        }
    }
    @Override
    public Color getForeground() {
        return number < 8 ? new Color(0x776e65) :  new Color(0xf9f6f2);
    }
    @Override
    public Color getBackground() {
        switch (number) {
            case 2:    return new Color(0xF3E0D1);
            case 4:    return new Color(0xede0c8);
            case 8:    return new Color(0xf2b179);
            case 16:   return new Color(0xf59563);
            case 32:   return new Color(0xf67c5f);
            case 64:   return new Color(0xf65e3b);
            case 128:  return new Color(0xF3D16A);
            case 256:  return new Color(0xF4CF58);
            case 512:  return new Color(0xF4CF38);
            case 1024: return new Color(0xEFC82F);
            case 2048: return new Color(0xEBC218);
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
