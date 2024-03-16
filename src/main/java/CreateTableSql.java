import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTableSql implements BaseSql {

    private String tableName;

    private List<Field> fields;

    private Map<String, String> typeMapping;

    public CreateTableSql(String tableName) {
        this.tableName = tableName;
        this.fields = new ArrayList<>();
        typeMapping = new HashMap<>();
        typeMapping.put("String","varchar(255)");
        typeMapping.put("int","int");
        typeMapping.put("Integer","int");
        typeMapping.put("long","bigint");
        typeMapping.put("Long","bigint");
        typeMapping.put("short","smallint");
        typeMapping.put("Short","smallint");
    }

    public void addField(Field field) {
        fields.add(field);
    }

    @Override
    public String produceSql() {
        StringBuilder sqlFields = new StringBuilder();
        for (Field field : fields) {
            String name = field.getName();
            String type = typeMapping.get(field.getType());
            sqlFields.append(String.format("`%s` %s ,\n", name, type));
        }
        // 把最后一个逗号去掉,留下后面的换行符
        sqlFields.replace(sqlFields.length() - 2, sqlFields.length() - 1, "");
        return String.format("CREATE TABLE `%s` (\n%s)ENGINE=InnoDB", tableName, sqlFields);
    }
}
