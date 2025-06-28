package sys;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
}
