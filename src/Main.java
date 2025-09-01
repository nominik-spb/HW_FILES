import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

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

        GameProgress progress1 = new GameProgress(50,32,1,325.52);
        GameProgress progress2 = new GameProgress(20,82,3,526.5);
        GameProgress progress3 = new GameProgress(10,102,4,744.3);


            if (savegames.canWrite()) {
                saveGame (savegames.getAbsolutePath(),progress1);
                saveGame (savegames.getAbsolutePath(),progress2);
                saveGame (savegames.getAbsolutePath(),progress3);
            }

    }

    public static void saveGame(String filePath, GameProgress progressData) {

            LocalDateTime currentDateTime = LocalDateTime.now();
            StringBuilder filePathNew = new StringBuilder().append(filePath).append("/save_").append(currentDateTime.toString()).append(".txt");
            File fileProgress = new File(filePathNew.toString());
        System.out.println(filePathNew.toString());
            //FileOutputStream file = new FileOutputStream().;

    }
}
