
public class Field {
    String name;

    String type;

    boolean notNull;

    boolean autoIncrement;

    public Field(String name,String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
