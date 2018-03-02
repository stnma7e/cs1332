import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Your implementation of HashMap.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(HashMapInterface.INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = new MapEntry[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }

        if ((float) (size + 1) / (float) table.length
                > HashMapInterface.MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }

        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            table[index] = new MapEntry(key, value, null);

            size += 1;
        } else {
            MapEntry<K, V> node = table[index];
            while (node != null) {
                if (node.getKey().equals(key)) {
                    // the key is already in the map, so update value
                    V returnValue = node.getValue();
                    node.setValue(value);

                    return returnValue;
                } else {
                    // there is already a value at that index
                    // must add new value to chain
                    if (node.getNext() == null) {
                        table[index] = new MapEntry(key, value, table[index]);

                        size += 1;
                        return null;
                    } else {
                        node = node.getNext();
                    }
                }
            }
        }

        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        int index = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> prevNode = null;
        MapEntry<K, V> node = table[index];
        while (node != null) {
            if (node.getKey().equals(key)) {
                if (prevNode == null) {
                    // we are at the first node in the bucket
                    table[index] = node.getNext();
                } else {
                    prevNode.setNext(node.getNext());
                }

                size -= 1;
                return node.getValue();
            } else {
                prevNode = node;
                node = node.getNext();
            }
        }

        throw new java.util.NoSuchElementException("key was not found in map");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }

        int index = Math.abs(key.hashCode() % table.length);
        MapEntry<K, V> node = table[index];
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            } else {
                node = node.getNext();
            }
        }

        throw new java.util.NoSuchElementException("key was not found in map");
    }

    @Override
    public boolean containsKey(K key) {
        try {
            get(key);
            return true;
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public void clear() {
        table = new MapEntry[HashMapInterface.INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> returnSet = new HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> entry = table[i];
            while (entry != null) {
                returnSet.add(entry.getKey());
                entry = entry.getNext();
            }
        }

        return returnSet;
    }

    @Override
    public List<V> values() {
        List<V> returnSet = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> entry = table[i];
            while (entry != null) {
                returnSet.add(entry.getValue());
                entry = entry.getNext();
            }
        }

        return returnSet;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length < 1 || length < size) {
            throw new IllegalArgumentException(
                "resize length cannot be 0 or smaller than the size of map");
        }

        HashMap<K, V> tmp = new HashMap(length);

        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> entry = table[i];
            while (entry != null) {
                tmp.put(entry.getKey(), entry.getValue());
                entry = entry.getNext();
            }
        }

        this.table = tmp.table;
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
