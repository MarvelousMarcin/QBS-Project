package controllers;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class Utils {
    //Providing that we cannot create object
    private Utils(){
        throw  new AssertionError();
    }

    //Animations to buttons
    public static EventHandler<MouseEvent> getAnimationEntry(Button button) {
        return mouseEvent -> {
            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setNode(button);
            scaleTransition.setDuration(Duration.seconds(.4));
            scaleTransition.setCycleCount(1);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.setAutoReverse(false);
            scaleTransition.play();
        };

    }

    public static EventHandler<MouseEvent> getAnimationExit(Button button) {
        return mouseEvent -> {
            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setNode(button);
            scaleTransition.setDuration(Duration.seconds(.4));
            scaleTransition.setCycleCount(1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.setAutoReverse(false);
            scaleTransition.play();
        };

    }


    public static byte[] stringToIntArray(String string){
        String[] array = string.split(" ");
        byte[] ints = new byte[array.length];
        for(int i =0;i<array.length;i++){
            ints[i] = Byte.parseByte(array[i]);
        }
        return ints;
    }

    public static byte[] changerEngine(byte[] source, byte[] arrayToChange, byte[] arrayChangeWith, FinalPresent fp){

        ArrayList<Byte> sourceArray = new ArrayList<>();
        List<Byte> toChangeArray = new ArrayList<>();
        List<Byte> changeWithArray = new ArrayList<>();

        for(byte b : source){
            sourceArray.add(b);
        }
        for(byte b : arrayToChange){
            toChangeArray.add(b);
        }
        for(byte b : arrayChangeWith){
            changeWithArray.add(b);
        }

        System.out.println("Before: "+sourceArray);

        for(int i=0;i<=sourceArray.size()-toChangeArray.size();i++){
            List<Byte> subArray = sourceArray.subList(i,i+toChangeArray.size());
            if(toChangeArray.equals(subArray)){
                //Adding one to change counter
                fp.addChange();
                System.out.println(i + " " + (i+toChangeArray.size()));
                sourceArray.subList(i,i+toChangeArray.size()).clear();
                sourceArray.addAll(i,changeWithArray);
                //If we add new byte string we have to skip through it
                i+= changeWithArray.size()-1;
            }
        }
        System.out.println("After: "+sourceArray);
        byte[] newSource = new byte[sourceArray.size()];
        for(int i=0;i<newSource.length;i++){
            newSource[i] = sourceArray.get(i);
        }

        return newSource;
    }
    //Checking if byte string is correct
    public static boolean checkByteString(String byteString){
        //Not a double
        if(byteString.contains(".") || byteString.contains("/")){
            return true;
        }
        String[] array = byteString.split(" ");
        int[] ints = new int[array.length];
        for(int i =0;i<array.length;i++){
            //Checking if number
            try {
                double d = Double.parseDouble(array[i]);
            } catch (NumberFormatException ex) {
                return true;
            }

            ints[i] = Integer.parseInt(array[i]);
        }

        for(int i : ints){
            if(i < -127 || i > 127){
                return true;
            }
        }
        return false;
    }
}
