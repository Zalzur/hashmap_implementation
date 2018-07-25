import java.util.LinkedList;

class HashMap<String, Integer> {

    class KeyValue {
        public String key;
        public Integer value;

        public KeyValue(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private int bucketSize = 16;
    private LinkedList<KeyValue>[] elements = new LinkedList[bucketSize];

    public void add(String key, Integer value) {
        int position = getHash(key);
        LinkedList list = elements[position];

        if (list == null) {
            list = new LinkedList();
        }

        // If the key already exists throw an error.
        for (Object map : list) {
            KeyValue keyValue = (KeyValue) map;
            if (keyValue.key.equals(key)) {
                throw new IllegalArgumentException();
            }
        }

        // Make a new instance of the KeyValue class, fill it with the key, value parameters, then add it to the list.
        KeyValue newData = new KeyValue(key, value);
        list.add(newData);
        elements[position] = list;
    }

    public Integer getValue(String key) {
        // 1. Calculate the hash of the key. This defines which element to get from the "elements" array
        int position = getHash(key);
        LinkedList list = elements[position];
        // 2. Find in the List in this position the KeyValue element that has this key, then return its value.
        //    If none of the items in the list has this key throw error.
        if (list == null) {
            throw new NullPointerException("There is no such key in the map.");
        }

        for (Object map : list) {
            KeyValue keyValue = (KeyValue) map;
            if (keyValue.key.equals(key)) {
                return keyValue.value;
            }
        }
        throw new NullPointerException("There is no such key in the map.");
    }

    public void remove(String key) {
        int position = getHash(key);
        LinkedList list = elements[position];
        boolean isRemoved = false;

        if (list == null) {
            throw new NullPointerException("There is no such key in the map.");
        }

        for (Object map : list) {
            KeyValue keyValue = (KeyValue) map;
            if (keyValue.key.equals(key)) {
                list.remove(map);
                isRemoved = true;
            }
        }
        if (!isRemoved) {
            throw new NullPointerException("There is no such key in the map.");
        }
    }

    public void clearAll() {
        elements = new LinkedList[bucketSize];
    }

    private int getHash(String key) {
        return Math.abs(key.hashCode() % bucketSize);
    }
}
