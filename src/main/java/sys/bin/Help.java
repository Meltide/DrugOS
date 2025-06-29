package sys.bin;

import java.util.HashMap;
import java.util.Map;

import static org.fusesource.jansi.Ansi.ansi;

public class Help {
    private final Map<String, String> helpList = new HashMap<>() {{
        put("time", "Show the current time");
        put("calc", "Simple calculator");
        put("help", "Show the help of DumpOS");
        put("ls", "View the directory");
        put("clear", "Clear the screen");
        put("exit", "Log out");
        put("shutdown", "Shutdown the system");
        put("restart", "Restart the system");
    }};

    public Help(String[] args) {
        if (!args[0].isEmpty()) {
            if (helpList.containsKey(args[0])) {
                System.out.println(ansi().a("\u001B[44m Description of " + args[0] + " \u001B[0m"));
                System.out.println(helpList.get(args[0]));
            } else {
                throw new IllegalArgumentException("Unknown command: " + ansi().fgRed().a(args[0]).reset());
            }
            return;
        }

        for (Map.Entry<String, String> ent : helpList.entrySet()) {
            System.out.printf(ansi().a("\u001B[34m%-15s\u001B[0m%s\n").toString(), ent.getKey(), ent.getValue());
        }
    }
}
