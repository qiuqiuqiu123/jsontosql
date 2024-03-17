package sql;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InsertSql implements BaseSql {

    private String tableName;

    private List<Field> fields;

    public InsertSql() {
        fields = new ArrayList<>();
    }

    public void addField(Field field) {
        fields.add(field);
    }

    @Override
    public void init(JSONObject jsonObject) {
        // init table name
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            this.tableName = entry.getKey();
        }
        System.out.println("init table name success");
        // init fields
        JSONObject fields = jsonObject.getJSONObject(tableName);
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            Field field = Field.builder()
                    .name(entry.getKey())
                    .value((String) entry.getValue())
                    .build();
            addField(field);
        }
    }

    @Override
    public String produceSql() {
        StringBuilder keys = new StringBuilder();
        StringBuilder values = new StringBuilder();
        // 字段名创造
        for (Field field : fields) {
            keys.append(String.format("%s,",field.getName()));
            values.append(String.format("'%s',",field.getValue()));
        }
        // 去掉最后一个逗号
        keys.replace(keys.length() - 1, keys.length(), "");
        values.replace(values.length() - 1, values.length(), "");
        return String.format("insert into %s (%s) values(%s);", tableName, keys, values);
    }
}
