package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

public class FinalPresent {

    private final LinkedList<File> files;
    private final DataCollector dataCollector;

    public FinalPresent(LinkedList<File> files, DataCollector dataCollector){
        this.files = files;
        this.dataCollector = dataCollector;
    }


    public void initialize(){
        for(File file : files){
            System.out.println(file);
            try {
                Files.write(file.toPath(), Utils.changerEngine(Files.readAllBytes(file.toPath()),
                        Utils.stringToIntArray(dataCollector.getByteStringTo()),
                        Utils.stringToIntArray(dataCollector.getByteStringWith()))
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(dataCollector.getByteStringTo());
        System.out.println(dataCollector.getByteStringWith());
    }


}
