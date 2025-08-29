package ip.main;

import ip.exceptions.FileCorruptedException;
import ip.tasks.Deadline;
import ip.tasks.Event;
import ip.tasks.Task;
import ip.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static ip.main.Squiddy.tasks;

public class FileManager {

    private final File folder;
    private final File data;

    public FileManager(String folderPath, String dataPath) {
        this.folder = new File(folderPath);
        this.data = new File(dataPath);
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

    //Delete and remake file if corrupted
    public void remakeFile() {
        try {
            if (data.delete()) {
                System.out.println("File deleted");
                createFile();
            } else {
                System.out.println("Unable to delete file: "
                        + data.getAbsolutePath());
            }
        } catch (SecurityException e) {
            System.out.println("ERROR ERROR: " + e.getMessage());
        }
    }

    //Load data file into list
    public void loadFile() throws FileNotFoundException {

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
                    task = new Deadline(splitCurr[2].trim(), splitCurr[3].trim());
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

                tasks.add(task);

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FileCorruptedException(e.getMessage());
            }
        }
    }

    //Rewrite data file based on tasks list
    public void rewrite() {
        try {
            FileWriter writer = new FileWriter(data);
            String dataString = "";

            for (Task task : tasks) {
                dataString = dataString + task.toDataString() + "\n";
            }

            writer.write(dataString);
            writer.close();

        } catch (IOException e) {
            throw new FileCorruptedException(e.getMessage());
        }
    }


    //Check and create folder and file if they do not exist
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
