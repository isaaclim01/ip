package ip.storage;

import ip.exceptions.FileCorruptedException;
import ip.tasks.Task;
import ip.tasks.TaskList;

import java.io.FileNotFoundException;

public class StorageStub implements Storage {

    @Override
    public void writeToStorage(Task task) throws FileCorruptedException {

    }

    @Override
    public void rewriteStorage(TaskList tasks) throws FileCorruptedException {

    }

    @Override
    public void start() {

    }

    @Override
    public void loadFile(TaskList tasks) throws FileNotFoundException, FileCorruptedException {

    }

    @Override
    public boolean remakeFile() {
        return true;
    }
}
