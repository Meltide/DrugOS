package sys;

import bin.*;
import sys.bin.*;
import sys.except.*;

import java.util.Arrays;

import static org.fusesource.jansi.Ansi.ansi;
import static sys.DrugOS.commands;

public class CmdRunner {
    public static void run(String[] cmd) {
        String[] args = cmd.length >= 2 ? Arrays.copyOfRange(cmd, 1, cmd.length) : new String[] {};
        DrugOS.errorCode = 0;

        try {
            if (commands.containsKey(cmd[0])) {
                Class<?> command = commands.get(cmd[0]);
                command.getMethod("execute", String[].class).invoke(command.getConstructor().newInstance(), (Object) args);
            } else {
                throw new UnknownCommandException("Unknown command: " + cmd[0]);
            }
        } catch (Exception e) {
            System.err.println("Error: " + ansi().fgRed().a(e.getMessage()).reset());
            DrugOS.errorCode = ErrorManager.getErrorCode(e);
        }
    }
}
