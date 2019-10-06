package application.utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is List Utils class.<br>
 */
public class ListUtils {

    /**
     * Use this method to create one element ArrayList
     * @param value
     * @param <T> generic type
     * @return one element ArrayList
     */
    public static <T> ArrayList<T> oneElementArrayList (T value){
        ArrayList<T> oneElementList = new ArrayList<>();
        oneElementList.add(value);
        return oneElementList;
    }
}
