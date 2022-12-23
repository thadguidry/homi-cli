public class GeneratorConfig {
    private String version;
    private String type;

    private String packageName;
    private String groupId;
    private DbConfig dbConfig;

    public String getVersion() { return version; }
    public void setVersion(String value) { this.version = value; }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public DbConfig getDb() { return dbConfig; }
    public void setDb(DbConfig value) { this.dbConfig = value; }
}
