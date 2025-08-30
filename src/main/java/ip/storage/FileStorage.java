package ip.storage;

import ip.exceptions.FileCorruptedException;
import ip.tasks.Deadline;
import ip.tasks.Event;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

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

    //Check if the folder exists
    public boolean checkFolder() {
        return folder.exists();
    }

    //Check if the file exists
    public boolean checkFile() {
        return data.exists();
    }

    //Create new folder
    public void createFolder() {
        try {
            if (folder.mkdir()) {
                System.out.println("Folder created: " + folder.getName());
            } else {
                System.out.println("Folder already created: "
                        + folder.getAbsolutePath());
            }
        } catch (SecurityException e) {
            System.out.println("ERROR ERROR: " + e.getMessage());
        }
    }

    //Create new data file
    public void createFile() {
        try {
            if (data.createNewFile()) {
                System.out.println("File created: " + data.getName());
            } else {
                System.out.println("File already created: "
                        + data.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("ERROR ERROR: " + e.getMessage());
        }
    }

    //Remakes file if corrupted
    @Override
    public boolean remakeFile() {
        try {
            FileWriter writer = new FileWriter(data);
            String dataString = "";
            writer.write(dataString);
            writer.close();
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    //Load data file into list
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
                    task = new Event(splitCurr[2].trim(), splitCurr[3].trim(), splitCurr[4].trim());
                    break;
                default:
                    String err = String.format("Task %d does not have valid type", count);
                    throw new FileCorruptedException(err);
                }

                if (splitCurr[1].trim().equals("1")) {
                    task.markDone();
                }

                tasks.addTask(task);

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FileCorruptedException(e.getMessage());
            }
        }
    }

    //Appends into data file
    @Override
    public void write(Task task) throws FileCorruptedException {
        try {
            FileWriter writer = new FileWriter(data, true);
            String dataString = task.toDataString() + "\n";

            writer.write(dataString);
            writer.close();

        } catch (IOException e) {
            throw new FileCorruptedException(e.getMessage());
        }
    }

    //Rewrite data file based on tasks list
    @Override
    public void rewrite(TaskList tasks) throws FileCorruptedException {
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


    //Check and create folder and file if they do not exist
    @Override
    public void start() {
        if (checkFolder()) {
            System.out.println("Folder loaded");
        } else {
            createFolder();
        }

        if (checkFile()) {
            System.out.println("Data loaded");
        } else {
            createFile();
        }
    }
}
