package qroktask.support;

/**
 * Created by Lenovo on 22.05.2017.
 */
public class JacksonMappingMarker {
    public static class Lower {};

    public static class Middle extends Lower {};

    public static class Higher extends Middle {};

    public static class BookHigher extends Middle {};
}
