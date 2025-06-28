package sys;

public class DumpOS {
    public static final String version = "1.0";

    public static final String hostname = "localhost";
    public static final String defUser = "root";
    public static final String defPasswd = "114514";

    public static String user;
    public static String passwd;

    public DumpOS() {
        Utils.clear();
        new Login();
        new FoxShell();
    }
}
