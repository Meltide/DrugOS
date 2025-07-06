package sys;

import sys.except.UnknownCommandException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;

public class ErrorManager {
    private static final Map<Class<? extends Exception>, Integer> errCodes = new HashMap<>() {{
        put(Exception.class, 100);
        put(RuntimeException.class, 101);
        put(NullPointerException.class, 102);
        put(UnknownCommandException.class, 200);
        put(IllegalArgumentException.class, 201);
        put(IOException.class, 300);
        put(UncheckedIOException.class, 301);
    }};

    public static int getErrorCode(Exception e) {
        if (errCodes.containsKey(e.getClass()))
            return errCodes.get(e.getClass());
        return 0;
    }
}
