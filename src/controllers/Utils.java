package controllers;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.Arrays;


public class Utils {

    private Utils(){
        throw  new AssertionError();
    }


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
        for(int i=0;i<=source.length-arrayToChange.length;i++){
            byte[] subArray = Arrays.copyOfRange(source , i, i+arrayChangeWith.length);
            if(Arrays.equals(arrayToChange, subArray)){
                fp.addChange();
                System.out.println("Changing" + Arrays.toString(subArray));
                System.out.println(Arrays.toString(subArray));
                System.arraycopy(arrayChangeWith, 0, source, i, i + arrayChangeWith.length - i);
            }
        }
        return source;
    }

    public static boolean checkByteString(String byteString){

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
            if(array[i].length() > 3){
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
