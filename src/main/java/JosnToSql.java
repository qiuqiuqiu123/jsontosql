import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Map;

public class JosnToSql {

    public void transfer(String fromPath, String toPath) throws IOException {
        JSONObject jsonObject = JSON.parseObject(readFile(fromPath));
        System.out.println("read json file success");
        // 获取表名
        String tableName = "";
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            tableName += entry.getKey();
        }
        System.out.println("get table name success");
        JSONObject fieldJosn = jsonObject.getJSONObject(tableName);
        CreateTableSql createTableSql = new CreateTableSql(tableName);
        for (Map.Entry<String, Object> entry : fieldJosn.entrySet()) {
            Field field = new Field(entry.getKey(), (String) entry.getValue());
            createTableSql.addField(field);
        }
        System.out.println("build create sql success");
        File sqlFile = new File(toPath);
        Writer writer = new FileWriter(sqlFile);
        writer.write(createTableSql.produceSql());
        writer.flush();
        System.out.println("sql write success");
    }

    public String readFile(String path) throws IOException {
        File file = new File(path);
        Reader reader = new FileReader(file);
        char[] buf = new char[1000];
        StringBuilder builder = new StringBuilder();
        int len = reader.read(buf);
        while (len != -1) {
            builder.append(buf,0,len);
            len = reader.read(buf);
        }
        return builder.toString();
    }

    public static void main(String[] args) throws IOException {
        JosnToSql josnToSql = new JosnToSql();
        josnToSql.transfer("E:\\javaLearning\\sqljson.json","E:\\javaLearning\\sql.sql");
    }
}
