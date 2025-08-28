import java.io.File;
import java.io.IOException;

public class FileManager {

    public static boolean checkFolder() {
        File squidFolder = new File("data/");

        return squidFolder.exists();
    }

    public static boolean checkFile() {
        File squidData = new File("data/squid.txt");

        return squidData.exists();
    }

    public static void createFolder() {
        try {
            File squidFolder = new File("data/");

            if (squidFolder.mkdir()) {
                System.out.println("Folder created: " + squidFolder.getName());
            } else {
                System.out.println("Folder already created: "
                        + squidFolder.getAbsolutePath());
            }
        } catch (SecurityException e) {
            System.out.println("ERROR ERROR :");
            e.printStackTrace();
        }
    }

    public static void createFile() {
        try {
            File squidData = new File("data/", "squid.txt");
            if (squidData.createNewFile()) {
                System.out.println("File created: " + squidData.getName());
            } else {
                System.out.println("File already created: "
                        + squidData.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("ERROR ERROR :");
            e.printStackTrace();
        }
    }

    public static void start() {
        if (FileManager.checkFolder()) {
            System.out.println("Folder loaded");
        } else {
            FileManager.createFolder();
        }

        if (FileManager.checkFile()) {
            System.out.println("Data loaded");
        } else {
            FileManager.createFile();
        }
    }

    public static void remakeFile() {
        try {
            File squidData = new File("data/", "squid.txt");
            if (squidData.delete()) {
                System.out.println("File deleted");
                createFile();
            } else {
                System.out.println("Unable to delete file: "
                        + squidData.getAbsolutePath());
            }
        } catch (SecurityException e) {
            System.out.println("ERROR ERROR :");
            e.printStackTrace();
        }
    }
}
