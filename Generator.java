public class Generator {

    private String [] schemaFilter;
    private String [] tableFilter;
    private Table[] table;

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

    public Table[] getTable() {
        return table;
    }

    public void setTable(Table[] table) {
        this.table = table;
    }
}
