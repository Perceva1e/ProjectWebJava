package bsuir.group.projectweb.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ExtendWith(MockitoExtension.class)
class TextDataCacheTest {
    private final TextDataCache textCache = new TextDataCache();
    @Test
    void putText() {
        String key = "key";
        Object value = "value";
        textCache.putText(key, value);
        Assertions.assertEquals(value, textCache.getText(key));
    }

    @Test
    void getText() {
        String key = "key";
        Object value = "value";
        textCache.putText(key, value);
        Object result = textCache.getText(key);
        Assertions.assertEquals(value, result);
    }

    @Test
    void clearText() {
        String key = "key";
        Object value = "value";
        textCache.putText(key, value);
        textCache.clearText();
        Assertions.assertNull(textCache.getText(key));
    }
    @Test
    public void testGetterAndSetter() {
        TextDataCache cache = new TextDataCache();
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("key", "value");
        cache.setHashMap(map);

        Assertions.assertEquals(map, cache.getHashMap());
    }

    @Test
    public void testEqualsAndHashCode() {
        TextDataCache cache1 = new TextDataCache();
        Map<String, Object> map1 = new ConcurrentHashMap<>();
        map1.put("key", "value");
        cache1.setHashMap(map1);

        TextDataCache cache2 = new TextDataCache();
        Map<String, Object> map2 = new ConcurrentHashMap<>();
        map2.put("key", "value");
        cache2.setHashMap(map2);

        Assertions.assertEquals(cache1, cache2);
        Assertions.assertEquals(cache1.hashCode(), cache2.hashCode());
    }
}