import org.fusesource.jansi.AnsiConsole;
import sys.DumpOS;

public class Main {
    public static void main(String[] args) {
        System.setProperty("jansi.force", "true");
        AnsiConsole.systemInstall();
        new DumpOS();
        AnsiConsole.systemUninstall();
    }
}
