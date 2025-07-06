package sys.bin;

import sys.*;
import static org.fusesource.jansi.Ansi.ansi;

public class Version {
    public Version(String[] args) {
        if (!(args.length < 1)) {
            switch (args[0]) {
                case "system", "sys", "drug" -> printSysVersion();
                case "shell", "fox" -> {
                    System.out.print(ansi().fgYellow().a("""
                              ___         ___ _        _ _\s
                             | __|____ __/ __| |_  ___| | |
                             | _/ _ \\ \\ /\\__ \\ ' \\/ -_) | |
                             |_|\\___/_\\_\\|___/_||_\\___|_|_|
                            """).reset());
                    System.out.println("FoxShell Version " + DrugOS.shellVersion);
                }
                case "kernel", "core" -> {
                    System.out.print(ansi().fgGreen().a("""
                              ___  ___ _____ _ _____ ___ \s
                             | _ \\/ _ \\_   _/_\\_   _/ _ \\\s
                             |  _/ (_) || |/ _ \\| || (_) |
                             |_|  \\___/ |_/_/ \\_\\_| \\___/\s
                            """).reset());
                    System.out.println("Potato PTCore JE Version " + DrugOS.coreVersion);
                }
                default -> throw new IllegalArgumentException("Unknown argument.");
            }
            return;
        }

        printSysVersion();
    }

    static void printSysVersion() {
        System.out.print(ansi().fgBlue().a(Utils.genLogo()).reset());
        System.out.println("DrugTeam DrugOS codename \"DrugMySick!\" version " + DrugOS.version);
    }
}