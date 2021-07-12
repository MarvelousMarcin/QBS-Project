package controllers;

import java.util.Arrays;


public class Utils {

    private Utils(){
        throw  new AssertionError();
    }

    public static byte[] stringToIntArray(String string){
        String[] array = string.split(" ");
        byte[] ints = new byte[array.length];

        for(int i =0;i<array.length;i++){
            ints[i] = Byte.parseByte(array[i]);
        }

        return ints;
    }

    public static byte[] changerEngine(byte[] source, byte[] arrayToChange, byte[] arrayChangeWith){
        for(int i=0;i<=source.length-arrayToChange.length;i++){
            byte[] subArray = Arrays.copyOfRange(source , i, i+arrayChangeWith.length);
            if(Arrays.equals(arrayToChange, subArray)){
                System.out.println("Changing" + Arrays.toString(subArray));
                System.out.println(Arrays.toString(subArray));
                System.arraycopy(arrayChangeWith, 0, source, i, i + arrayChangeWith.length - i);
            }
        }
        return source;
    }




}
