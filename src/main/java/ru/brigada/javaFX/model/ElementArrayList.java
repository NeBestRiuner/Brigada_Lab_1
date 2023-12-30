package ru.brigada.javaFX.model;
import java.util.LinkedList;
public class ElementArrayList {
    private static int maxValue = 0;
    private static LinkedList<Element> INSTANCE;
    private ElementArrayList(){}
    public static LinkedList<Element> getInstance(){
        if(INSTANCE == null){
            INSTANCE = new LinkedList<Element>();
        }
        return INSTANCE;
    }
    public static void setMaxValue(int max){
        maxValue = max;
    }
    public static int getMaxValue(){
        return maxValue;
    }
}
