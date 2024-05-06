package util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorMap {
    static Map<Integer, Color> colorMap = new HashMap<>();

    //todo: complete the method to add other color
    public static void InitialColorMap() {
        colorMap.put(2, Color.orange);
        colorMap.put(4, Color.red);







    }

    public static Color getColor(int i) {
        return colorMap.getOrDefault(i, Color.black);
    }
}
