package sk.myProject.gamestudio.server;

public class JSON {

    private String value = "";
    private boolean arr = false;

    private JSON() {
    }

    public JSON(boolean arr) {
        this.arr = arr;
    }

    public static JSON array() {
        return new JSON(true);
    }

    public static JSON start(String key, String value) {
        var json = new JSON();
        return json.parse(key, value);
    }

    public static JSON start(String key, Integer value) {
        var json = new JSON();
        return json.parse(key, value);
    }

    public static JSON start(String key, JSON value) {
        var json = new JSON();
        return json.parse(key, value);
    }

    public static JSON start(String key, Boolean value) {
        var json = new JSON();
        return json.parse(key, value);
    }

    public JSON parse(String key, String v) {
        value += "\"" + key + "\"" + ": " + "\"" + v + "\",";
        return this;
    }

    public JSON parse(String key, Integer v) {
        value += "\"" + key + "\"" + ": " + +v + ",";
        return this;
    }

    public JSON parse(String key, JSON v) {
        value += "\"" + key + "\"" + ": ";
        value += v.toString();
        value += ",";
        return this;
    }

    public JSON parse(String key, Boolean v) {
        value += "\"" + key + "\"" + ": ";
        value += v.toString();
        value += ",";
        return this;
    }

    public JSON parse(JSON v) {
        value += v.toString();
        value += ",";
        return this;
    }

    @Override
    public String toString() {
        return arr ? "[" + (value).replaceFirst(".$", "") + "]" : "{" + (value).replaceFirst(".$", "") + "}";
    }
}
