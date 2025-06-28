package sys;

import bin.Calc;
import bin.Time;
import sys.bin.Ls;

import java.util.Arrays;

public class CmdRunner {
    public static void run(String[] cmd) {
        String[] args = cmd.length >= 2 ? Arrays.copyOfRange(cmd, 1, cmd.length) : new String[] {""};

        try {
            switch (cmd[0]) {
                // 程序
                case "time" -> new Time();
                case "calc" -> new Calc();

                // 系统操作
                case "help" -> HelpManager.show();
                case "ls" -> new Ls(args);
                case "clear" -> Utils.clear();
                case "exit", "shutdown" -> System.exit(0);
                case "restart" -> new DumpOS();
                default -> throw new IllegalArgumentException("Unknown command: " + cmd[0]);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
