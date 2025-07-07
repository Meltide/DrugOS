package sys;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Utils {
    public static void sleepFor(double time) {
        try {
            Thread.sleep((long) time * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public static<T> T choice(T[] in) {
        return in[randInt(0, in.length - 1)];
    }

    public static<T> T choice(ArrayList<T> in) {
        return in.get(randInt(0, in.size() - 1));
    }

    public static String getHiddenPasswd() {
        Console console = System.console();
        if (console == null) {
            return new Scanner(System.in).nextLine();
        }
        return new String(console.readPassword());
    }
    
    public static void clear() {
        try {
            final String os = System.getProperty("os.name");
            ProcessBuilder pb = os.contains("Windows")
                ? new ProcessBuilder("cmd", "/c", "cls")
                : new ProcessBuilder("clear");
            pb.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static void printa(String in) {
        String[] printList = in.split("\n");
        for (String s : printList) {
            System.out.println(s);
            System.out.flush();
            sleepFor(0.1);
        }
    }

    public static String readTxt(String path) throws IOException {
        try (InputStream is = DrugOS.class.getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
                sb.append(line).append("\n");
            return sb.toString();
        }
    }

    public static String genLogo() {
        return """
                  ___               _____              \s
                 |   \\ _ _ _  _ __ |_   _|__ __ _ _ __ \s
                 | |) | '_| || / _` || |/ -_) _` | '  \\\s
                 |___/|_|  \\_,_\\__, ||_|\\___\\__,_|_|_|_|
                               |___/                   \s
                """;
    }
}
