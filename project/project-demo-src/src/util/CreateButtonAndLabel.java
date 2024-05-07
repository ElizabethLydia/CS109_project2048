package util;

import javax.swing.*;
import java.awt.*;

public interface CreateButtonAndLabel {
    default JButton createButton(String name, Point location, int width, int height,JDialog parent) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        parent.add(button);
        return button;
    }
    default JLabel createLabel(String name, Font font, Point location, int width, int height,JDialog parent) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        parent.add(label);
        return label;
    }
}
