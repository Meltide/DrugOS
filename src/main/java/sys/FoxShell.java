package sys;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class FoxShell {
    public final String greet = "Welcome to FoxShell, a friendly shell.";

    public FoxShell() {
        Scanner in = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println("\n" + greet);

        while (true) {
            LocalDateTime now = LocalDateTime.now();

            System.out.printf(genShell(), now.format(fmt), DumpOS.hostname, DumpOS.passwd);
            String[] cmd = in.nextLine().trim().split(" ");

            if (cmd[0].isEmpty()) continue;
            CmdRunner.run(cmd);
        }
    }

    private String genShell() {
        return ansi().a("[%s] \u001B[32m%s\u001B[0m@%s \u001B[34m~\u001B[0m > ").toString();
    }
}
