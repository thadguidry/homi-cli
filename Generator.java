public class Generator {

    private String [] schemaFilter;
    private String [] tableFilter;
    private TableFilter[] tableFilter;

    public String[] getSchemaFilter() {
        return schemaFilter;
    }

    public void setSchemaFilter(String[] schemaFilter) {
        this.schemaFilter = schemaFilter;
    }

    public String[] getTableFilter() {
        return tableFilter;
    }

    public void setTableFilter(String[] tableFilter) {
        this.tableFilter = tableFilter;
    }

    public TableFilter[] getTable() {
        return tableFilter;
    }

    public void setTable(TableFilter[] tableFilter) {
        this.tableFilter = tableFilter;
    }
}
