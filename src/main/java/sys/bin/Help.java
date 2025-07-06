package sys.bin;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.fusesource.jansi.Ansi.ansi;

public class Help {
    Properties helps = new Properties();

    public Help(String[] args) {
        try (InputStream is = getClass().getResourceAsStream("/help.properties")) {
            helps.load(is);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        if (args.length > 1) {
            if (helps.containsKey(args[0])) {
                System.out.println(ansi().a("\u001B[44m Description of " + args[0] + " \u001B[0m"));
                System.out.println(helps.getProperty(args[0]));
            } else {
                throw new IllegalArgumentException("Unknown command: " + ansi().fgRed().a(args[0]).reset());
            }
            return;
        }

        helps.stringPropertyNames().stream().sorted().forEach(key ->
            System.out.printf(ansi().a("\u001B[34m%-15s\u001B[0m%s\n").toString(), key, helps.getProperty(key))
        );
    }
}
