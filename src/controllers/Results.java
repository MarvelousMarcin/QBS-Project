package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Results {

    @FXML
    private ListView<String> listView;

    @FXML
    private Button acceptBut;

    private final DataCollector dataCollector;

    public Results(DataCollector dataCollector){
        this.dataCollector = dataCollector;
    }

    public void initialize(){

        LinkedList<File> files = new LinkedList<>();

        try (Stream<Path> stream = Files.find(Paths.get(dataCollector.getDirectory()), 10,
                (path, attr) -> path.getFileName().toString().endsWith("."+dataCollector.getExtension()) ))
        {
            stream.forEach(e -> files.add(e.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(File file : files){
            listView.getItems().add(file.toString());
        }


        acceptBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Animations.QUESTION_BUT.animateEntry(acceptBut));
        acceptBut.addEventHandler(MouseEvent.MOUSE_EXITED, Animations.QUESTION_BUT.animateExit(acceptBut));

//  First Version -> Doesn't work and too long

//        LinkedList<File> allFiles = new LinkedList<>();
//        Collections.addAll(allFiles,Utils.getFileFromDir(dataCollector.getDirectory(),dataCollector.getExtension()));
//        ArrayList<Integer> foldersToRemove = new ArrayList<>();
//
//        while(Utils.checkIfDirectory(allFiles)) {
//            int size = allFiles.size();
//            for (int i = 0; i < size; i++) {
//                if (allFiles.get(i).isDirectory()) {
//                    Collections.addAll(allFiles, Utils.getFileFromDir(allFiles.get(i).getAbsolutePath(), dataCollector.getExtension()));
//                    foldersToRemove.add(i);
//                }
//            }
//            for(int val : foldersToRemove){
//                allFiles.remove(val);
//            }
//            foldersToRemove.clear();
//        }
//        for(File file : allFiles){
//            listView.getItems().add(file.getAbsolutePath());
//        }

    }

}
