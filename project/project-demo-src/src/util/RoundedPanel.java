package util;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {
    private Color backgroundColor;// 添加一个用于存储背景颜色的成员变量
    private int x, y, width, height;
    private Container parent;
    private boolean setOpaque;

    // 为RoundedPanelExample类添加带参数的构造函数
    public RoundedPanel(Color backgroundColor, int x, int y, int width, int height,Container parent,boolean setOpaque) {
        this.backgroundColor = backgroundColor;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.parent = parent;
        this.setOpaque = setOpaque;
        setBounds(x, y, width, height);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int arc = 20;
        int width = getWidth() - 1;
        int height = getHeight() - 1;

        RoundRectangle2D round = new RoundRectangle2D.Float(0, 0, width, height, arc, arc);

        // 使用传入的背景颜色
        if (!setOpaque) {
            g2d.setColor(backgroundColor);
        } else {
            g2d.setColor(new Color(0, 0, 0, 0));
        }
        g2d.fill(round);

        g2d.dispose();
    }
}