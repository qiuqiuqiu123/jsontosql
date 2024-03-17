package sql;

import com.alibaba.fastjson.JSONObject;
import sql.BaseSql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTableSql implements BaseSql {

    private String tableName;

    private final String engine = "InnoDB";

    private final List<Field> fields;


    private static final Map<String, String> typeMapping = new HashMap<>();

    static {
        typeMapping.put("String", "varchar(255)");
        typeMapping.put("int", "int");
        typeMapping.put("Integer", "int");
        typeMapping.put("long", "bigint");
        typeMapping.put("Long", "bigint");
        typeMapping.put("short", "smallint");
        typeMapping.put("Short", "smallint");
        typeMapping.put("boolean", "tinyint");
        typeMapping.put("Boolean", "tinyint");
        typeMapping.put("Date", "datetime");
    }

    public CreateTableSql() {
        this.fields = new ArrayList<>();
    }

    public void addField(Field field) {
        fields.add(field);
    }

    @Override
    public void init(JSONObject jsonObject) {
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            this.tableName = entry.getKey();
        }
        System.out.println("init table name success");
        JSONObject fieldJosn = jsonObject.getJSONObject(tableName);
        for (Map.Entry<String, Object> entry : fieldJosn.entrySet()) {
            Field field = Field.builder()
                    .name(entry.getKey())
                    .type((String) entry.getValue())
                    .build();
            addField(field);
        }
        System.out.println("build create sql success");
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
        return String.format("CREATE TABLE `%s` (\n%s)ENGINE=%s;", tableName, sqlFields, engine);
    }
}
