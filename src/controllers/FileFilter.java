package controllers;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileFilter implements FilenameFilter {

    private final String extension;

    public FileFilter(String extension)
    {
        this.extension = extension;
    }

   @Override
   public boolean accept(File dir, String name)
   {
       return name.endsWith("." + extension) || dir.isDirectory();

   }
}
