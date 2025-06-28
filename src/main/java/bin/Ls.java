package bin;

import java.io.File;

public class Ls {
    String dir;

    public Ls(String[] args) {
        if (args[0].isEmpty()) dir = ".";
        else dir = args[0];

        File file = new File(dir);
        File[] files = file.listFiles();

        if (files == null) {
            System.out.println("No items in this directory.");
        } else {
            for (File fl : files) {
                System.out.println(fl.getName());
            }
        }
    }
}
