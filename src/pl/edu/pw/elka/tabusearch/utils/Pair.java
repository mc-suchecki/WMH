/**
 * @author <a href="mailto:jacek.witkowski@gmail.com">Jacek Witkowski</a>
 * Created on 2014-11-2014.
 */
package pl.edu.pw.elka.tabusearch.utils;

/**
 * Class representing a simple key-value pair.
 * @param <KeyType>     type of a key parameter
 * @param <ValueType>   type of a value parameter
 */
public class Pair<KeyType,ValueType> {
    private final KeyType key;
    private final ValueType value;

    public Pair(final KeyType key, final ValueType value) {
        this.key = key;
        this.value = value;
    }

    public KeyType getKey() {
        return key;
    }

    public ValueType getValue() {
        return value;
    }
}
