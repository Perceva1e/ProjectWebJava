package bsuir.group.projectweb.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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
}