package sql;

import com.alibaba.fastjson.JSONObject;

public interface BaseSql {

    void init(JSONObject jsonObject);

    String produceSql();
}
