package bsuir.group.projectweb.cache;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class TextDataCache {
    private Map<String, Object> hashMap = new ConcurrentHashMap<>();

    public void put(String key, Object value) {
        hashMap.put(key, value);
    }

    public Object get(String key) {
        return hashMap.get(key);
    }

    public void clear() {
        hashMap.clear();
    }
}