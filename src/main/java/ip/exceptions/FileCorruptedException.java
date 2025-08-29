package ip.exceptions;

public class FileCorruptedException extends RuntimeException {
    public FileCorruptedException(String message) {
        super(message);
    }
}
