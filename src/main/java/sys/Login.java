package sys;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Login {
    public Login() {
        Scanner in = new Scanner(System.in);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");

        printInfo();

        String user;
        while (true) {
            System.out.print(DumpOS.hostname + " login: ");
            user = in.nextLine().trim();
            
            if (user.isEmpty()) {
                continue;
            }
            
            if (DumpOS.defUser.equals(user)) {
                DumpOS.user = user;
                break;
            }
            System.err.println("Invalid username.");
            System.err.flush();
        }

        while (true) {
            System.out.print("Password: ");
            String passwd = Utils.getHiddenPasswd();

            if (DumpOS.defPasswd.equals(passwd)) {
                DumpOS.passwd = passwd;
                System.out.println("Last login: " + ansi().fgCyan().a(now.format(fmt)).reset());
                break;
            }
            System.err.println("Password incorrect.");
            System.err.flush();
        }
    }

    private void printInfo() {
        String[] tipsList = {
            "You can find the default password in the passwd file.",
            "Maybe the converter is useless :)",
            "'root' is the default user.",
            "Is this file system real?",
            "Columns make the calculator work.",
        };

        String[] printList = {
            "Welcome to MPGA DumpOS!",
            "A fake system based on Java\n",
            "Made by:        MPGA Team & Other contributors",
            "Github Repo:    Meltide/DumpOS",
            "Telegram Group: t.me/MPGATeam",
            "Matrix Group:   #MPGATeam:mozilla.org\n",
            "Tips: " + Utils.choice(tipsList) + "\n",
                "Type 'help' for help.\n",
        };

        for (String i : printList) {
            System.out.println(i);
            System.out.flush();
            Utils.sleepFor(0.25);
        }
    }
}
