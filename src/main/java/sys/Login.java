package sys;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Login {
    private final Scanner in = new Scanner(System.in);

    private final LocalDateTime now = LocalDateTime.now();
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss", Locale.ENGLISH);

    public Login() {
        printInfo();
        loginUser();
        loginPwd();
    }

    public static void printInfo() {
        try {
            String msg = Utils.readTxt("/sys/startupMsg.txt");
            Utils.printa(msg);
            System.out.println();
        } catch (IOException e) {
            System.err.println(ansi().a("\u001B[31mError: " + e.getMessage() + "\u001B[0m"));
            System.exit(1);
        }
    }

    public void loginUser() {
        String user;
        while (true) {
            System.out.print(DrugOS.hostname + " login: ");
            user = in.nextLine().trim();

            if (user.isEmpty()) {
                continue;
            }

            if (DrugOS.defUser.equals(user)) {
                DrugOS.user = user;
                break;
            }
            System.err.println("Invalid username.");
            System.err.flush();
        }
    }

    public void loginPwd() {
        while (true) {
            System.out.print("Password: ");
            String passwd = Utils.getHiddenPasswd();

            if (DrugOS.defPasswd.equals(passwd)) {
                DrugOS.passwd = passwd;
                System.out.println("Last login: " + ansi().fgCyan().a(now.format(fmt)).reset());
                break;
            }
            System.err.println("Password incorrect.");
            System.err.flush();
        }
    }
}
