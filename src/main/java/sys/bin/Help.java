package sys.bin;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;

import static org.fusesource.jansi.Ansi.ansi;
import static sys.DrugOS.commands;

public class Help {
    public static final Map<String, String> usage = new HashMap<>() {{
        put("[command]", "Show the details of the command");
    }};

    private JSONObject helpData;

    public void execute(String[] args) {
        try (InputStream is = getClass().getResourceAsStream("/sys/help.json")) {
            helpData = new JSONObject(new JSONTokener(is));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        if (args.length > 0) {
            String command = args[0];
            if (!commands.containsKey(command)) {
                throw new IllegalArgumentException("Unknown command: " + command);
            }

            String description = findCommandDescription(command);
            if (description != null) {
                printCommandHelp(command, description);
            } else {
                throw new IllegalArgumentException("Unknown command: " + command);
            }
        } else {
            printAllCommands();
        }
    }

    private String findCommandDescription(String command) {
        for (String category : helpData.keySet()) {
            JSONObject categoryCommands = helpData.getJSONObject(category);
            if (categoryCommands.has(command)) {
                return categoryCommands.getString(command);
            }
        }
        return null;
    }

    private void printCommandHelp(String command, String description) {
        System.out.println(ansi().a("\u001B[44m Help for: " + command + " \u001B[0m"));
        System.out.println("Description:");
        System.out.println(description);

        System.out.println(ansi().a("\u001B[44m Usage: \u001B[0m"));
        Class<?> cmd = commands.get(command);
        try {
            @SuppressWarnings("unchecked")
            Map<String, String> usage = (Map<String, String>) cmd.getDeclaredField("usage").get(cmd);

            for (Map.Entry<String, String> ent : usage.entrySet()) {
                System.out.printf(ansi().a("  %s \u001B[32m%-15s\u001B[0m%s\n").toString(), command, ent.getKey(), ent.getValue());
            }
        } catch (NoSuchFieldException e) {
            System.out.println("No usage available");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void printAllCommands() {
        for (String category : helpData.keySet()) {
            System.out.println(ansi().a("\u001B[44m " + category + " \u001B[0m"));

            JSONObject commands = helpData.getJSONObject(category);
            for (String cmd : commands.keySet()) {
                System.out.printf(ansi().a("%-15s%s\n").toString(),
                        cmd, commands.getString(cmd));
            }
        }
    }
}