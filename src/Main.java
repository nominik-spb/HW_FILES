import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        StringBuilder logs = new StringBuilder();

        File src = new File("D://Games/src");
        if (src.mkdir()) {
            System.out.println("Каталог создан");
            logs.append(src.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
        } else {
            System.out.println("Каталог не создан");
        }

        File res = new File("D://Games/res");
        if (res.mkdir()) {
            System.out.println("Каталог создан");
            logs.append(res.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
        } else {
            System.out.println("Каталог не создан");
        }

        File savegames = new File("D://Games/savegames");
        if (savegames.mkdir()) {
            System.out.println("Каталог создан");
            logs.append(savegames.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
        } else {
            System.out.println("Каталог не создан");
        }

        File temp = new File("D://Games/temp");
        if (temp.mkdir()) {
            System.out.println("Каталог создан");
            logs.append(temp.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
        } else {
            System.out.println("Каталог не создан");
        }

        File srcMain = new File("D://Games/src/main");
        if (srcMain.mkdir()) {
            System.out.println("Каталог создан");
            logs.append(srcMain.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
        } else {
            System.out.println("Каталог не создан");
        }

        File srcTest = new File("D://Games/src/test");
        if (srcTest.mkdir()) {
            System.out.println("Каталог создан");
            logs.append(srcTest.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
        } else {
            System.out.println("Каталог не создан");
        }

        File srcMainJava = new File("D://Games/src/main/Main.java");
        try {
            if (srcMainJava.createNewFile()) {
                System.out.println("Файл создан");
                logs.append(srcMainJava.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
            } else {
                System.out.println("Файл не создан");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File srcUtilsJava = new File("D://Games/src/main/Utils.java");
        try {
            if (srcUtilsJava.createNewFile()) {
                System.out.println("Файл создан");
                logs.append(srcUtilsJava.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
            } else {
                System.out.println("Файл не создан");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File resDrawables = new File("D://Games/res/drawables");
        if (resDrawables.mkdir()) {
            System.out.println("Каталог создан");
            logs.append(resDrawables.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
        } else {
            System.out.println("Каталог не создан");
        }

        File resVectors = new File("D://Games/res/vectors");
        if (resVectors.mkdir()) {
            System.out.println("Каталог создан");
            logs.append(resVectors.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
        } else {
            System.out.println("Каталог не создан\n");
        }

        File resIcons = new File("D://Games/res/icons");
        if (resIcons.mkdir()) {
            System.out.println("Каталог создан");
            logs.append(resIcons.getAbsolutePath()).append(" - создан успешно").append(System.lineSeparator());
        } else {
            System.out.println("Каталог не создан");
        }

        File tempTemp = new File("D://Games/temp/Temp.txt");
        try {
            if (tempTemp.createNewFile()) {
                System.out.println("Файл создан");
                FileWriter writer = new FileWriter("D://Games/temp/Temp.txt");
                writer.write(String.valueOf(logs));
                writer.close();
            } else {
                System.out.println("Файл не создан");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GameProgress progress1 = new GameProgress(50, 32, 1, 325.52);
        GameProgress progress2 = new GameProgress(20, 82, 3, 526.5);
        GameProgress progress3 = new GameProgress(10, 102, 4, 744.3);

        ArrayList<String> listProgress = new ArrayList<>();
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
