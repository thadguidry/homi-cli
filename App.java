import lombok.Data;

@Data
public class App {

    private String packageName;
    private String groupId;
    private String artifactId;
    private String description;
    private String db;
    private String dbVersionManager;
    private String [] environments;
}
