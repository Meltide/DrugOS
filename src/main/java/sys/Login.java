package sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Login {
    public Login() {
        Scanner in = new Scanner(System.in);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss", Locale.ENGLISH);

        printInfo();

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

    public static void printInfo() {
        try (InputStream is = DrugOS.class.getResourceAsStream("/startupMsg.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            Utils.printa(sb.toString());
            System.out.println("\n");
        } catch (IOException e) {
            System.err.println(ansi().a("\u001B[31mError: " + e.getMessage() + "\u001B[0m"));
            System.exit(1);
        }
    }
}
