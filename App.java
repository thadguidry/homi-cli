

public class App {

    private String packageName;
    private String groupId;
    private String artifactId;
    private String description;
    private String db;
    private String dbVersionManager;
    private String [] environments;


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDbVersionManager() {
        return dbVersionManager;
    }

    public void setDbVersionManager(String dbVersionManager) {
        this.dbVersionManager = dbVersionManager;
    }

    public String[] getEnvironments() {
        return environments;
    }

    public void setEnvironments(String[] environments) {
        this.environments = environments;
    }
}
