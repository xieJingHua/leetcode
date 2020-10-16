package subject146;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU算法实现 - LinkedHashMap
 *
 * @author xiejh
 * @Date 2020/10/17 0:13
 **/

class LRUCacheOfficial2 extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCacheOfficial2(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

