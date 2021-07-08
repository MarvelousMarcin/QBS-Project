package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.*;

public class Results {

    @FXML
    private Pane resultPane;

    @FXML
    private ListView<String> listView;

    private DataCollector dataCollector;

    public Results(DataCollector dataCollector){
        this.dataCollector = dataCollector;
    }

    public void initialize(){

        LinkedList<File> allFiles = new LinkedList<>();
        Collections.addAll(allFiles,Utils.getFileFromDir(dataCollector.getDirectory(),dataCollector.getExtension()));
        ArrayList<Integer> foldersToRemove = new ArrayList<>();

        while(Utils.checkIfDirectory(allFiles)) {
            int size = allFiles.size();
            for (int i = 0; i < size; i++) {
                if (allFiles.get(i).isDirectory()) {
                    Collections.addAll(allFiles, Utils.getFileFromDir(allFiles.get(i).getAbsolutePath(), dataCollector.getExtension()));
                    foldersToRemove.add(i);
                }
            }
            for(int val : foldersToRemove){
                allFiles.remove(val);
            }
            foldersToRemove.clear();
        }
        for(File file : allFiles){
            listView.getItems().add(file.getAbsolutePath());
        }
    }

}
