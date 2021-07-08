package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Utils {

    private Utils(){
        throw new AssertionError();
    }

    public static boolean checkIfDirectory(LinkedList<File> files){
        for(File file : files){
            if(file.isDirectory()){
                return true;
            }
        }
        return false;
    }

    public static File[] getFileFromDir(String dir, String ext){
        File directory = new File(dir);
        FileFilter fileFilter = new FileFilter(ext);

        return directory.listFiles(fileFilter);
    }

}
