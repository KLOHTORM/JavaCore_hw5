/*
Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3],
и представляют собой, например, состояния ячеек поля для игры в крестикинолики,
где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом
 */

import java.io.File;

public class Task2 {
    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast) {

        File[] myFiles = file.listFiles();


        System.out.print(indent);
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }

        System.out.println(file.getName());
        for (File myFile : myFiles) {
            if (myFile.isFile())
                System.out.println(indent +"+->" + myFile.getName());
        }


        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                subDirTotal++;
            }
        }

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                print(files[i], indent, subDirTotal == ++subDirCounter);
            }
        }

    }
}
