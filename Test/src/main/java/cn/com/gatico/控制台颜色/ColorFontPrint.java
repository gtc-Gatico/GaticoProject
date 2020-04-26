package cn.com.gatico.控制台颜色;

import java.io.PrintStream;

public class ColorFontPrint {

    public static final int BLACK = 30;             // 黑色
    public static final int BACKGROUND_BLACK = 40;  // 黑色背景
    public static final int HEIGHT_BLACK = 90;      // 黑色高亮色
    public static final int RED = 31;               // 红色
    public static final int BACKGROUND_RED = 41;    // 红色背景
    public static final int HEIGHT_RED = 91;        // 红色高亮色
    public static final int GREEN = 32;             // 绿色
    public static final int BACKGROUND_GREEN = 42;  // 绿色背景
    public static final int HEIGHT_GREEN = 92;      // 绿色高亮色
    public static final int YELLOW = 33;            // 黄色
    public static final int BACKGROUND_YELLOW = 43; // 黄色背景
    public static final int HEIGHT_YELLOW = 93;     // 黄色高亮色
    public static final int BLUE = 34;              // 蓝色
    public static final int BACKGROUND_BLUE = 44;   // 蓝色背景
    public static final int HEIGHT_BLUE = 94;       // 蓝色高亮色
    public static final int MAGENTA = 35;           // 紫色
    public static final int BACKGROUND_MAGENTA = 45;// 紫色背景
    public static final int HEIGHT_MAGENTA = 95;    // 紫色高亮色
    public static final int CYAN = 36;              // 蓝绿
    public static final int BACKGROUND_CYAN = 46;   // 蓝绿背景
    public static final int HEIGHT_CYAN = 96;       // 蓝绿高亮色
    public static final int GRAY = 37;              // 灰色
    public static final int BACKGROUND_GRAY = 47;   // 灰色背景
    public static final int HEIGHT_GRAY = 97;       // 灰色高亮色

    public static int[] fontColors = new int[]{
            ColorFontPrint.BLACK,
            ColorFontPrint.RED,
            ColorFontPrint.GREEN,
            ColorFontPrint.YELLOW,
            ColorFontPrint.BLUE,
            ColorFontPrint.MAGENTA,
            ColorFontPrint.CYAN,
            ColorFontPrint.GRAY,

            ColorFontPrint.HEIGHT_BLACK,
            ColorFontPrint.HEIGHT_RED,
            ColorFontPrint.HEIGHT_YELLOW,
            ColorFontPrint.HEIGHT_BLUE,
            ColorFontPrint.HEIGHT_MAGENTA,
            ColorFontPrint.HEIGHT_CYAN,
            ColorFontPrint.HEIGHT_GRAY,
    };

    public static int[] backColors = new int[]{
            ColorFontPrint.BACKGROUND_BLACK,
            ColorFontPrint.BACKGROUND_RED,
            ColorFontPrint.BACKGROUND_GREEN,
            ColorFontPrint.BACKGROUND_YELLOW,
            ColorFontPrint.BACKGROUND_BLUE,
            ColorFontPrint.BACKGROUND_MAGENTA,
            ColorFontPrint.BACKGROUND_CYAN,
            ColorFontPrint.BACKGROUND_GRAY,
    };

    /**
     * 粗体
     */
    public static final int STYLE_BOLD = 1;
    /**
     * 斜体
     */
    public static final int STYLE_ITATIC = 3;
    /**
     * 下划线
     */
    public static final int STYLE_UNDERLINE = 4;
    /**
     * 反转
     */
    public static final int STYLE_REVERSE = 7;

    private static int defaultFontColor = -1;
    private static int defaultBackColor = -1;
    private static int defaultStyle = -1;

    private static int fontColor = -1;
    private static int backColor = -1;
    private static int style = -1;

    private static PrintStream out = null;

    public static void setOut(PrintStream out) {
        ColorFontPrint.out = out;
    }

    public static void setFontColor(int fontColor) {
        ColorFontPrint.fontColor = fontColor;
    }

    public static void setBackColor(int backColor) {
        ColorFontPrint.backColor = backColor;
    }

    public static void setStyle(int style) {
        ColorFontPrint.style = style;
    }

    public static void print(Object txt) {
        int[] codes = new int[]{
                (fontColor != -1 ? fontColor : defaultFontColor),
                (backColor != -1 ? backColor : defaultBackColor),
                (style != -1 ? style : defaultStyle)
        };
        if (out == null) {
            ColorFontPrint.setOut(System.out);
        }
        out.print(convert(txt, codes));
    }

    public static void print(Object txt, int... code) {
        int[] codes = new int[]{
                (code.length > 0 && code[0] != -1 ? code[0] : defaultFontColor),
                (code.length > 1 && code[1] != -1 ? code[1] : defaultBackColor),
                (code.length > 2 && code[2] != -1 ? code[2] : defaultStyle)
        };
        if (out == null) {
            ColorFontPrint.setOut(System.out);
        }
        out.print(convert(txt, codes));
    }

    public static void println(Object txt) {
        int[] codes = new int[]{
                (fontColor != -1 ? fontColor : defaultFontColor),
                (backColor != -1 ? backColor : defaultBackColor),
                (style != -1 ? style : defaultStyle)
        };
        if (out == null) {
            ColorFontPrint.setOut(System.out);
        }
        out.println(convert(txt, codes));
    }

    public static void println(Object txt, int... code) {
        int[] codes = new int[]{
                (code.length > 0 && code[0] != -1 ? code[0] : defaultFontColor),
                (code.length > 1 && code[1] != -1 ? code[1] : defaultBackColor),
                (code.length > 2 && code[2] != -1 ? code[2] : defaultStyle)
        };
        if (out == null) {
            ColorFontPrint.setOut(System.out);
        }
        out.println(convert(txt, codes));
    }

    public static String convert(Object txt, int... codes) {
        StringBuffer sb = new StringBuffer();
        for (int code : codes) {
            if (code != -1) {
                sb.append(code + ";");
            }
        }
        String _code = sb.toString();
        if (_code.endsWith(";")) {
            _code = _code.substring(0, _code.length() - 1);
        }
        return "\033" + "[" + _code + "m" + String.valueOf(txt) + "\033" + "[0m";
    }

    public static String convertLine(Object txt, int... codes) {
        int[] color = fontColors;
        if (codes != null && codes.length > 0) {
            color = codes;
        }
        StringBuffer sb = new StringBuffer();
        char[] chars = String.valueOf(txt).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append(convert(chars[i], (color[i < color.length - 1 ? i : i % color.length])));
        }
        return sb.toString();
    }

}
