package controllers;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilter implements FilenameFilter {

    private final String extension;


    public FileFilter(String extension)
    {
        this.extension = extension;
    }

   @Override
   public boolean accept(File dir, String name)
   {
       return name.endsWith("."+extension) || !name.contains(".");

   }
}
