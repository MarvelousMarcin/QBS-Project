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
        throw new AssertionError();
    }

    /**
     * Methods which create simple mouse animation
     *
     * @param button which need to be animated during mouse movement

     * @return EventHandler<MouseEvent> - handler which will provide animation
     */
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

    /**
     *
     * @param string with bytes(between -127 ana 127) divided by space
     * @return byte[] array with every byte in different cell
     */
    public static byte[] stringToIntArray(String string){
        String[] array = string.split(" ");
        byte[] ints = new byte[array.length];
        for(int i =0;i<array.length;i++){
            ints[i] = Byte.parseByte(array[i]);
        }
        return ints;
    }

    /**
     *
     * @param source  byte string of the file for instance .txt file with byte string [122, 33, 44, 22]
     * @param arrayToChange  part of the file string which we want to replace
     * @param arrayChangeWith  array with new string
     * @param fp  class which implements counter to see how many changes in file string we will make
     * @return new file byte string with new replaced party
     */
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
                sourceArray.subList(i,i+toChangeArray.size()).clear();
                sourceArray.addAll(i,changeWithArray);
                //If we add new byte string we have to skip through it
                i += changeWithArray.size()-1;
            }
        }
        System.out.println("After: "+sourceArray);
        byte[] newSource = new byte[sourceArray.size()];
        for(int i=0;i<newSource.length;i++){
            newSource[i] = sourceArray.get(i);
        }

        return newSource;
    }

    /**
     * @param byteString string to be checked
     * @return true or false - check if given string is a byte string(every number between -127 and 127, no letters or
     * other symbols, every letter divided with one space)
     */
    public static boolean checkByteString(String byteString){
        //Not a double
        if(byteString.contains(".")
                || byteString.contains("/")
                || byteString.contains("*")
                || byteString.contains("+")
                || byteString.contains("-")
                || byteString.contains("#")
                || byteString.contains("&")
        ){
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
