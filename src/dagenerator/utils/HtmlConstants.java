package dagenerator.utils;

public class HtmlConstants {
    public static String vertical_bar = "|";
    public static String program_start = "┌──";
    public static String program_end = "└──";

    public static String if_start_text = "┌──<span style=\"color:" + ColorConstants.KEY_WORD_BLUE + "\";>if</span>(%s)";
    public static String if_else_text = "├──<span style=\"color:" + ColorConstants.KEY_WORD_BLUE + "\";>else</span>";
    public static String if_end_text = "└──<span style=\"color:" + ColorConstants.KEY_WORD_BLUE + "\";>end if</span>";

    public static String while_start_text = "╔══<span style=\"color:" + ColorConstants.KEY_WORD_BLUE + "\";>while</span>(%s)";
    public static String while_current = "║";
    public static String while_end = "╙──<span style=\"color:" + ColorConstants.KEY_WORD_BLUE + "\";>end while</span>";


    public static String upper_left_module_angle = "<span style=\"color:" + ColorConstants.MODULE_BLUE + "\";>┌";
    public static String upper_right_module_angle = "┐";
    public static String lower_left_module_angle = "<span style=\"color:" + ColorConstants.MODULE_BLUE + "\";>└";
    public static String lower_right_module_angle = "┘";
    public static String left_vertical_module_bar = "<span style=\"color:" + ColorConstants.MODULE_BLUE + "\";>|";
    public static String right_vertical_module_bar = "|</span>";
    public static String io_module_arrow = "↓%s</span>";
    public static String repetitive_module_bar = "─";
}
