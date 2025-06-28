package sys;

import java.util.HashMap;
import java.util.Map;

public class HelpManager {
    private final static Map<String, String> helpList = new HashMap<>() {{
        put("time", "Show the current time");
        put("calc", "Simple calculator");
        put("help", "Show the help of DumpOS");
        put("ls", "View the directory");
        put("clear", "Clear the screen");
        put("exit", "Log out");
        put("shutdown", "Shutdown the system");
        put("restart", "Restart the system");
    }};

    public static void show() {
        for (Map.Entry<String, String> ent : helpList.entrySet()) {
            System.out.printf("%-15s%s\n", ent.getKey(), ent.getValue());
        }
    }
}
