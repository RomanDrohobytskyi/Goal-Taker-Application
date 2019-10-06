package application.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This is Map Utils class.<br>
 * @author Roman
 */
public class MapUtils {

    /**
     * Use this method to create one element HashMap.
     * @param key HashMap key
     * @param value HashMap value
     * @param <T> generic type
     * @return one element HashMap
     */
    public static <T> HashMap<T,T>  oneElementHashMap (T key, T value){
        HashMap<T,T> oneElementMap = new  HashMap<T,T>();
        oneElementMap.put(key, value);
        return oneElementMap;
    }

}
