package sys;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FoxShell {
    public final String greet = "Welcome to FoxShell, a friendly shell.";

    public FoxShell() {
        Scanner in = new Scanner(System.in);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println("\n" + greet);

        while (true) {
            System.out.print(genShell().formatted(now.format(fmt), DumpOS.hostname, DumpOS.passwd));
            String[] cmd = in.nextLine().trim().split(" ");

            if (cmd[0].isEmpty()) continue;
            CmdRunner.run(cmd);
        }
    }

    private String genShell() {
        return "[%s] %s@%s ~ # ";
    }
}
