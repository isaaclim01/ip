package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;

import java.io.FileNotFoundException;

public interface Command {
    void execute(String input) throws UnknownInputException, FileNotFoundException, FileCorruptedException;
}
