/*
Написать функцию, создающую резервную копию всех файлов в
директории(без поддиректорий) во вновь созданную папку ./backup
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task1 {
    public static void main(String[] args) throws IOException {
        print(new File("."), true);

    }
    static void print(File file, boolean dirFlag) throws IOException {

        if (dirFlag)
            if (Files.exists(Path.of("./Backup"))) {
                System.out.println("BackUp folder already exist");
                dirFlag = false;
            } else {
                File backUpDir = new File("./backUP/");
                backUpDir.mkdir();
                dirFlag = false;
            }

        File[] files = file.listFiles();
        String dirPath="./backUP";
        for (File file1 : files) {
            if (file1.isDirectory() && !file1.getName().equals("backUP") ){
                File newDir = new File(dirPath + "/" + file1.getParentFile() + "/" + file1.getName());
                newDir.mkdir();
                print(new File(String.valueOf(file1)), dirFlag);
            }
            copyFile(file1);
        }
    }

    static void copyFile(File file1) throws IOException {
        if (file1.isFile()){
            try (FileOutputStream fileOutputStream = new FileOutputStream("./backUP/" + file1.getParentFile() + "/" + file1.getName())) {
                int c;
                //reading
                try (FileInputStream fileInputStream = new FileInputStream(file1)) {
                    while ((c = fileInputStream.read()) != -1) {
                        fileOutputStream.write(c);
                    }
                }
            }
        }
    }
}
