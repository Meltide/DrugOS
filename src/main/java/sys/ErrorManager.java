package sys;

import net.objecthunter.exp4j.tokenizer.UnknownFunctionOrVariableException;
import sys.except.UnknownCommandException;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

public class ErrorManager {
    private static final Map<Class<? extends Exception>, Integer> errCodes = new HashMap<>() {{
        // 基础异常
        put(Exception.class, 100);
        put(RuntimeException.class, 101);
        put(NullPointerException.class, 102);
        put(ArrayIndexOutOfBoundsException.class, 103);
        put(ClassCastException.class, 104);
        put(NumberFormatException.class, 105);
        put(UnsupportedOperationException.class, 110);

        // 指令异常
        put(UnknownCommandException.class, 200);
        put(IllegalArgumentException.class, 201);

        // IO异常
        put(IOException.class, 300);
        put(UncheckedIOException.class, 301);

        // 计算器异常
        put(UnknownFunctionOrVariableException.class, 400);
        put(ArithmeticException.class, 401);
        put(EmptyStackException.class, 402);
    }};

    public static int getErrorCode(Exception e) {
        if (errCodes.containsKey(e.getClass()))
            return errCodes.get(e.getClass());
        return 0;
    }
}
