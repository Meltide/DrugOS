package sys.bin;

import sys.*;
import static org.fusesource.jansi.Ansi.ansi;

public class Version {
    public Version() {
        System.out.println(ansi().fgBlue().a(Utils.genLogo()).reset());
        System.out.println("DumpOS Version " + DumpOS.version);
    }
}