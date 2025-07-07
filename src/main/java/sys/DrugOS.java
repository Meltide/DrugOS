package sys;

import bin.Calc;
import bin.Time;
import sys.bin.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.fusesource.jansi.Ansi.*;

public class DrugOS {
    public static String version;
    public static String coreVersion;
    public static String shellVersion;

    public static String hostname;
    public static String defUser;
    public static String defPasswd;

    public static String user;
    public static String passwd;

    public static int errorCode = 0;

    public static final Map<String, Class<?>> commands = new HashMap<>() {{
        // Tools
        put("time", Time.class);
        put("calc", Calc.class);

        // System
        put("help", Help.class);
        put("ls", Ls.class);
        put("userman", UserMan.class);
        put("version", Version.class);
        put("clear", Clear.class);

        // Power
        put("exit", Exit.class);
        put("shutdown", Shutdown.class);
        put("restart", Restart.class);
    }};

    public DrugOS() {
        Utils.clear();
        readConfig();

        new Login();
        new FoxShell();
    }

    public static void readConfig() {
        Properties prop = new Properties();
        Properties config = new Properties();

        try {
            try (InputStream is = DrugOS.class.getResourceAsStream("/sys/build.properties")) {
                prop.load(is);
                version = prop.getProperty("version");
                coreVersion = prop.getProperty("coreVersion");
                shellVersion = prop.getProperty("shellVersion");
            }

            Path configPath = Path.of("DrugOS", "config.properties");
            if (!Files.exists(Path.of("DrugOS")))
                Files.createDirectory(Path.of("DrugOS"));
            if (!Files.exists(configPath)) {
                Files.createFile(configPath);
                config.setProperty("hostname", "localhost");
                config.setProperty("username", "root");
                config.setProperty("passwd", Base64.getEncoder().encodeToString("114514".getBytes(StandardCharsets.UTF_8)));

                try (BufferedWriter bw = Files.newBufferedWriter(configPath, StandardCharsets.UTF_8)) {
                    bw.write("# This is a config properties" + System.lineSeparator() + System.lineSeparator());
                    config.forEach((k, v) -> {
                        try {
                            bw.write(k + "=" + v + System.lineSeparator());
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });

                }
            }

            try (InputStream is = Files.newInputStream(configPath)) {
                config.load(is);
                hostname = config.getProperty("hostname");
                defUser = config.getProperty("username");
                defPasswd = new String(Base64.getDecoder().decode(config.getProperty("passwd")), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            System.err.println(ansi().a("Error: \u001B[31m" + e.getMessage() + "\u001B[0m"));
            System.exit(1);
        }
    }
}
