package net.deddybones.techplusplus.util;

public class ObjectNumbered<T> {
    private final T obj;
    private final int count;

    public ObjectNumbered(T obj) {
        this(obj, 1);
    }

    public ObjectNumbered(T obj, int count) {
        this.obj = obj;
        this.count = count;
    }

    public static <T> ObjectNumbered<T> ObN(T obj) {
        return new ObjectNumbered<>(obj);
    }

    public static <T> ObjectNumbered<T> ObN(T obj, int count) {
        return new ObjectNumbered<>(obj, count);
    }

    public T getObject() {
        return this.obj;
    }

    public int getCount() {
        return this.count;
    }
}
