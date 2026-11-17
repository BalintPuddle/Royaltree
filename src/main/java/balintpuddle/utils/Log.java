package balintpuddle.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Log {
    public static void info(String message){
        System.out.println(getTime() + "(INFO) " + message);
    }

    public static void warning(String message){
        System.out.print(getTime() + "[WARNING!] " + message);
    }

    public static void error(String message){
        System.out.print(getTime() + "{ERROR!} " + message);
    }

    public static String getTime() {
        return "[" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] ";
    }
}
