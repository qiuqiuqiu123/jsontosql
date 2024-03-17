package sql;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Field {
    String name;

    String type;

    String value;

    boolean notNull;

    boolean autoIncrement;

}
