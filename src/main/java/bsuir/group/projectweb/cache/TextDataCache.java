package bsuir.group.projectweb.cache;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class TextDataCache {
    /**
     * This initializes hash map.
     */
    private Map<String, Object> hashMap = new ConcurrentHashMap<>();
    private static final int MAXSIZE = 20;

    /**
     * This method save object in hashmap.
     *
     * @param key   is a key word for save
     * @param value is an object which have to save
     */
    public void putText(final String key, final Object value) {
        hashMap.put(key, value);
        if (hashMap.size() >= MAXSIZE) {
            hashMap.clear();
        }
    }
    /**
     * This method get object from hashmap.
     *
     * @param key is a key word for get
     * @return object by a key word
     */
    public Object getText(final String key) {
        return hashMap.get(key);
    }

    /**
     * This method clear hashmap.
     */
    public void clearText() {
        hashMap.clear();
    }
}
