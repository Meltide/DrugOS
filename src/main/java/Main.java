import org.fusesource.jansi.AnsiConsole;
import sys.DrugOS;

public class Main {
    public static void main(String[] args) {
        System.setProperty("jansi.force", "true");
        AnsiConsole.systemInstall();
        new DrugOS();
        AnsiConsole.systemUninstall();
    }
}
