package sys.bin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.fusesource.jansi.Ansi.*;

public class Ls {
    String dir;
    List<String> directories = new ArrayList<>();
    List<String> files = new ArrayList<>();

    public Ls(String[] args) {
        if (args.length < 1) dir = ".";
        else dir = args[0];

        File file = new File(dir);
        File[] fileList = file.listFiles();

        if (fileList == null) {
            System.out.println(ansi().fgYellow().a("No items in this directory.").reset());
            return;
        }

        for (File fl : fileList) {
            if (fl.isDirectory())
                directories.add(fl.getName());
            else files.add(fl.getName());
        }
        Collections.sort(directories);
        Collections.sort(files);

        for (String dir : directories)
            System.out.println(ansi().fgBlue().a(dir).reset().a("/"));
        for (String fl : files)
            System.out.println(fl);
    }
}
