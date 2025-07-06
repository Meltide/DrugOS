package sys.bin;

public class UserMan {
    public UserMan(String[] args) {
        if (args.length < 1)
            throw new IllegalArgumentException("Unknown argument.");


    }
}
