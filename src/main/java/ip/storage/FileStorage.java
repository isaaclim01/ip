package ip.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import ip.exceptions.FileCorruptedException;
import ip.tasks.Deadline;
import ip.tasks.Event;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.tasks.ToDo;

/**
 * Class to implement a storage through an external text file
 */
public class FileStorage implements Storage {

    private final File folder;
    private final File data;

    public FileStorage(String folderPath, String dataPath) {
        this.folder = new File(folderPath);
        this.data = new File(dataPath);
    }

    public FileStorage(String dataPath) {
        this.data = new File(dataPath);
        this.folder = new File(dataPath.split("/")[0] + "/");
    }

    public File getData() {
        return data;
    }

    public File getFolder() {
        return folder;
    }

    /**
     * Checks if a folder exists
     * @return True if folder exists, false otherwise
     */
    private boolean checkFolderExists() {
        return folder.exists();
    }

    /**
     * Checks if a file exists
     * @return True if file exists, false otherwise
     */
    private boolean checkFileExists() {
        return data.exists();
    }

    /**
     * Creates new folder if it does not exist
     */
    private void createFolder() {
        try {
            boolean isCreated = folder.mkdir();

            assert isCreated: "Unable to create folder";

        } catch (SecurityException e) {
            e.printStackTrace();
            assert false: "Security exception";
        }
    }

    /**
     * Creates new data file if it does not exist
     */
    private void createFile() {
        try {
            boolean isCreated = data.createNewFile();

            assert isCreated: "Unable to create file";
        } catch (IOException e) {
            e.printStackTrace();
            assert false: "IO exception";
        }
    }

    /**
     * Loads the data file into given TaskList
     * @param tasks TaskList to be input into
     * @throws FileNotFoundException If data file does not exist
     * @throws FileCorruptedException If formatting of data file is wrong
     */
    @Override
    public void loadFile(TaskList tasks) throws FileNotFoundException, FileCorruptedException {

        Scanner s = new Scanner(data);

        int count = 1;

        while (s.hasNext()) {
            try {
                String curr = s.nextLine();
                String[] splitCurr = curr.split("/");
                Task task;

                switch (splitCurr[0].trim()) {
                case "T":
                    task = new ToDo(splitCurr[2].trim());
                    break;
                case "D":
                    task = new Deadline(splitCurr[2].trim(), LocalDate.parse(splitCurr[3].trim()));
                    break;
                case "E":
                    task = new Event(splitCurr[2].trim(),
                            LocalDate.parse(splitCurr[3].trim()), LocalDate.parse(splitCurr[4].trim()));
                    break;
                default:
                    String err = String.format("Task %d does not have valid type", count);
                    throw new FileCorruptedException(err);
                }

                if (splitCurr[1].trim().equals("1")) {
                    task.setDone(true);
                }

                tasks.addTask(task);

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FileCorruptedException(e.getMessage());
            }
        }
    }

    /**
     * Appends task into data file
     * @param task Task to be stored
     * @throws FileCorruptedException If FileWriter is unable to write into file
     */
    @Override
    public void writeToStorage(Task task) throws FileCorruptedException {
        try {
            FileWriter writer = new FileWriter(data, true);
            String dataString = task.toDataString() + "\n";

            writer.write(dataString);
            writer.close();

        } catch (IOException e) {
            throw new FileCorruptedException(e.getMessage());
        }
    }

    /**
     * Rewrites entire data file according to TaskList
     * @param tasks TaskList to be stored
     * @throws FileCorruptedException If FileWriter is unable to write into file
     */
    @Override
    public void rewriteStorage(TaskList tasks) throws FileCorruptedException {
        try {
            FileWriter writer = new FileWriter(data);
            StringBuilder dataString = new StringBuilder();

            for (Task task : tasks) {
                dataString.append(task.toDataString()).append("\n");
            }

            writer.write(dataString.toString());
            writer.close();

        } catch (IOException e) {
            throw new FileCorruptedException(e.getMessage());
        }
    }


    /**
     * Checks and then creates File and Folder if they do not exist
     */
    @Override
    public void start() {
        if (checkFolderExists()) {
            System.out.println("Folder loaded");
        } else {
            createFolder();
        }

        if (checkFileExists()) {
            System.out.println("Data loaded");
        } else {
            createFile();
        }
    }
}
