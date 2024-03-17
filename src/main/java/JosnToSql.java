import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import sql.BaseSql;
import sql.CreateTableSql;
import sql.InsertSql;

import java.io.*;

public class JosnToSql {

    public void transfer(String fromPath, String toPath,BaseSql sql) throws IOException {
        JSONObject jsonObject = JSON.parseObject(readFile(fromPath));
        System.out.println("read json file success");
        sql.init(jsonObject);
        System.out.println("sql init success");
        File sqlFile = new File(toPath);
        Writer writer = new FileWriter(sqlFile);
        writer.write(sql.produceSql());
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
        // josnToSql.transfer("E:\\javaLearning\\sqljson.json","E:\\javaLearning\\sql.sql",new CreateTableSql());
        josnToSql.transfer("E:\\javaLearning\\insertsqljson.json","E:\\javaLearning\\insertsql.sql",new InsertSql());
    }
}
