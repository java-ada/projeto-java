public final class Library {

    private static volatile Library instance;

    public String value;

    private Library(String value) {
        this.value = value;
    }

    public static Library getInstance(String value) {
        Library result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Library.class) {
            if (instance == null) {
                instance = new Library(value);
            }
            return instance;
        }
    }
}
