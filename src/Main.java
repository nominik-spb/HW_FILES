import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static StringBuilder logs = new StringBuilder();

    public static void main(String[] args) throws IOException {

        ArrayList<String> listDir = new ArrayList<>();
        listDir.add("D://Games/src");
        listDir.add("D://Games/res");
        listDir.add("D://Games/savegames");
        listDir.add("D://Games/temp");
        listDir.add("D://Games/src/main");
        listDir.add("D://Games/src/test");
        listDir.add("D://Games/res/drawables");
        listDir.add("D://Games/res/vectors");
        listDir.add("D://Games/res/icons");

        ArrayList<String> listFiles = new ArrayList<>();
        listFiles.add("D://Games/src/main/Main.java");
        listFiles.add("D://Games/src/main/Utils.java");
        listFiles.add("D://Games/temp/Temp.txt");

        for (int i = 0; i < listDir.size(); i++) {
            CreateDir(listDir.get(i));
        }

        for (int i = 0; i < listFiles.size(); i++) {
            CreateFile(listFiles.get(i));
        }

        CreateLog("D://Games/temp/Temp.txt", logs);

        GameProgress progress1 = new GameProgress(50, 32, 1, 325.52);
        GameProgress progress2 = new GameProgress(20, 82, 3, 526.5);
        GameProgress progress3 = new GameProgress(10, 102, 4, 744.3);

        ArrayList<String> listProgress = new ArrayList<>();
        File savegames = new File("D://Games/savegames");
        if (savegames.canWrite()) {
            saveGame("D://Games/savegames/save0.dat", progress1);
            saveGame("D://Games/savegames/save1.dat", progress2);
            saveGame("D://Games/savegames/save2.dat", progress3);
            listProgress.add("D://Games/savegames/save0.dat");
            listProgress.add("D://Games/savegames/save1.dat");
            listProgress.add("D://Games/savegames/save2.dat");
        }
        zipFiles("D://Games/savegames/zip.zip", listProgress);
    }

    public static void CreateDir(String pathDir) {
        File dir = new File(pathDir);
        if (dir.mkdir()) {
            System.out.println("Каталог " + pathDir + " создан");
            logs.append(dir.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
        } else {
            System.out.println("Каталог " + pathDir + " не создан");
        }
    }

    public static void CreateFile(String pathFile) {
        File file = new File(pathFile);
        try {
            if (file.createNewFile()) {
                System.out.println("Файл " + pathFile + " создан");
                logs.append(file.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
            } else {
                System.out.println("Файл " + pathFile + " не создан");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void CreateLog(String pathFile, StringBuilder logs) {
        try {
            FileWriter writer = new FileWriter(pathFile);
            writer.write(String.valueOf(logs));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveGame(String filePath, GameProgress progress) {

        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(progress);
            System.out.println("Файл сохранения создан");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void zipFiles(String zipFilePath, ArrayList<String> listProgress) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (int i = 0; i < listProgress.size(); i++) {
                String zipFileName = "packed_progress" + i + ".dat";
                FileInputStream fis = new FileInputStream(listProgress.get(i));
                ZipEntry entry = new ZipEntry(zipFileName);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
