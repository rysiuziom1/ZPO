import java.util.Map;

public class DictionaryUpdater {
    public static void updateByContainsKey(Map<String, Integer> map, String word) {
        int count = map.containsKey(word) ? map.get(word) : 0;
        map.put(word, count + 1);
    }

    public static void updateByGet(Map<String, Integer> map, String word) {
        Integer count = map.get(word);
        if (count == null)
            map.put(word, 1);
        else
            map.put(word, count + 1);
    }

    public static void updateByGetOrDefault(Map<String, Integer> map, String word) {
        Integer count = map.getOrDefault(word, -1);
        if(count == -1)
            map.put(word, 1);
        else
            map.put(word, count + 1);
    }

    public static void updateByPutIfAbsent(Map<String, Integer> map, String word) {
        Integer count = map.putIfAbsent(word, 1);
        if(count != null)
            map.put(word, count + 1);
    }
}
