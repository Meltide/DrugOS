package sys.bin;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import static org.fusesource.jansi.Ansi.ansi;

public class Help {
    private JSONObject helpData;

    public Help(String[] args) {
        try (InputStream is = getClass().getResourceAsStream("/sys/help.json")) {
            helpData = new JSONObject(new JSONTokener(is));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        if (args.length > 0) {
            String command = args[0];
            String description = findCommandDescription(command);

            if (description != null) {
                printCommandHelp(command, description);
            } else {
                throw new IllegalArgumentException("Unknown command: " + ansi().fgRed().a(command).reset());
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
        System.out.println(ansi().a("\u001B[44m Description of " + command + " \u001B[0m"));
        System.out.println(description);
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