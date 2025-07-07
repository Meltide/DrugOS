package sys;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class FoxShell {
    public String greet;

    public FoxShell() {
        Scanner in = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");

        loadConfig();
        System.out.println("\n" + greet);

        while (true) {
            LocalDateTime now = LocalDateTime.now();

            System.out.printf(genShell(), now.format(fmt), DrugOS.user, DrugOS.hostname, DrugOS.errorCode);
            String[] cmd = in.nextLine().trim().split(" ");

            if (cmd[0].isEmpty()) continue;
            else if ("clear".equals(cmd[0])) Utils.clear();

            CmdRunner.run(cmd);
        }
    }

    public void loadConfig() {
        try {
            greet = Utils.readTxt("/Fox/greeting.txt").trim();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String genShell() {
        return ansi().a("[%s] \u001B[32m%s\u001B[0m@%s \u001B[34m~\u001B[0m " +
                (DrugOS.errorCode != 0 ? "[\u001B[31m%d\u001B[0m]" : "") +
                "> ").toString();
    }
}
