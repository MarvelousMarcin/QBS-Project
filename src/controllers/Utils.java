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
