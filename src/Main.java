import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        File savegames = new File("/Users/maximkorchagin/Documents/Games/savegames");

        ArrayList<String> savelist = new ArrayList<>();

        GameProgress gameProgress1 = new GameProgress(100, 3, 10, 40);
        GameProgress gameProgress2 = new GameProgress(90, 2, 20, 60);
        GameProgress gameProgress3 = new GameProgress(70, 6, 25, 120);

        gameProgress1.saveGame("/Users/maximkorchagin/Documents/Games/savegames/save1.dat", gameProgress1);
        savelist.add("/Users/maximkorchagin/Documents/Games/savegames/save1.dat");
        gameProgress2.saveGame("/Users/maximkorchagin/Documents/Games/savegames/save2.dat", gameProgress2);
        savelist.add("/Users/maximkorchagin/Documents/Games/savegames/save2.dat");
        gameProgress3.saveGame("/Users/maximkorchagin/Documents/Games/savegames/save3.dat", gameProgress3);
        savelist.add("/Users/maximkorchagin/Documents/Games/savegames/save3.dat");

        System.out.println(savelist);

        zipFiles("/Users/maximkorchagin/Documents/Games/savegames/savegames.zip", savelist);

        for (File item : savegames.listFiles()) {
            if (item.getName().contains(".dat")) {
                item.delete();
            }
        }

    }

    public static void zipFiles(String path, ArrayList<String> list) {
        try (ZipOutputStream zSave = new ZipOutputStream(new FileOutputStream(path))) {
            for (String saves : list) {
                if (saves.contains(".dat")) {
                    FileInputStream savePlayerRead = new FileInputStream(saves);
                    zSave.putNextEntry(new ZipEntry(saves));
                    byte[] buffer = new byte[savePlayerRead.available()];
                    savePlayerRead.read(buffer);
                    zSave.write(buffer);
                    savePlayerRead.close();
                    zSave.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
