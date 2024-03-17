package sql;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DelSql implements BaseSql{

    private String tableName;

    private List<Field> fields;

    public DelSql() {
        fields = new ArrayList<>();
    }

    @Override
    public void init(JSONObject jsonObject) {
        // init table name
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            this.tableName = entry.getKey();
        }
        System.out.println("init table name success");
        // init fields
        JSONObject fieldJsons = jsonObject.getJSONObject(tableName);
        for (Map.Entry<String, Object> entry : fieldJsons.entrySet()) {
            Field field = Field.builder()
                    .name(entry.getKey())
                    .value((String) entry.getValue())
                    .build();
            this.fields.add(field);
        }
    }

    @Override
    public String produceSql() {
        StringBuilder condition = new StringBuilder();
        // 字段名创造
        for (Field field : fields) {
            if (condition.length() == 0) {
                condition.append("where ");
            } else {
                condition.append("and ");
            }
            condition.append(String.format("%s = '%s' ",field.getName(),field.getValue()));
        }
        return String.format("delete table %s %s;", tableName,condition);
    }
}
