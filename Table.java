public class Table {
    private String name;
    private String schema;
    private PrimaryKey [] primaryKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public PrimaryKey[] getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKey[] primaryKey) {
        this.primaryKey = primaryKey;
    }
}
