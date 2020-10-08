package Model;

/**
 * Created by cuong on 3/20/2017.
 */
public class StringHelper {

    public static String strip(String input) {
        return input.trim();
    }

    public static String normalizeJavaClassPath(String javaClassPath) {
        String className = javaClassPath.substring(javaClassPath.lastIndexOf(".") + 1, javaClassPath.length());
        return javaClassPath.replace(".", "/") + ".java/" + className;
    }
}
