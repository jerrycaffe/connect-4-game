public class ColorLogs {


    //info
    public static void green(String message) {
        System.out.println(COLORS.ANSI_GREEN + " : " + message + COLORS.ANSI_RESET);
    }

    //error
    public static void red(String className, String message) {
        System.out.println(COLORS.ANSI_RED + className + " : " + message + COLORS.ANSI_RESET);
    }

    //debug
    public static void d(String className, String message) {
        System.out.println(COLORS.ANSI_BLUE + className + " : " + message + COLORS.ANSI_RESET);
    }

    //warning
    public static void yellow(String message) {
        System.out.println(COLORS.ANSI_YELLOW + " : " + message + COLORS.ANSI_RESET);
    }
}
