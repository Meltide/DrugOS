package sys.bin;

import java.io.File;
import java.util.*;

import static org.fusesource.jansi.Ansi.*;

public class Ls {
    public static final Map<String, String> usage = new HashMap<>() {{
        put("[directory]", "Show the specify directory");
    }};

    String dir;
    List<String> directories = new ArrayList<>();
    List<String> files = new ArrayList<>();

    public void execute(String[] args) {
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
