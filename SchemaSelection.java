
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SchemaSelection {

    @JacksonXmlProperty(isAttribute = true, localName = "match-catalog")
    private String matchCatalog;

    @JacksonXmlProperty(isAttribute = true, localName = "match-schema")
    private String matchSchema;

    @JacksonXmlProperty(isAttribute = true, localName = "match-table")
    private String matchTable;

    public String getMatchCatalog() {
        return matchCatalog;
    }

    public void setMatchCatalog(String matchCatalog) {
        this.matchCatalog = matchCatalog;
    }

    public String getMatchSchema() {
        return matchSchema;
    }

    public void setMatchSchema(String matchSchema) {
        this.matchSchema = matchSchema;
    }

    public String getMatchTable() {
        return matchTable;
    }

    public void setMatchTable(String matchTable) {
        this.matchTable = matchTable;
    }
}
