import java.util.HashMap;
import java.util.Map;

public class JdbcPropertyRule {

    Map<String,String> jdbcMap = new HashMap<>();

    public static String getUrl(String db){
        switch (db) {
            case "pg" :
                return "jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}?currentSchema=${DB_CURRENT_SCHEMA}";
            default:
                return "";
        }
    }
}
