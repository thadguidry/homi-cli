import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TableFilter {
    @JacksonXmlProperty(isAttribute = true, localName = "match-catalog")
    private String matchCatalog;

    @JacksonXmlProperty(isAttribute = true, localName = "match-schema")
    private String matchSchema;

    @JacksonXmlProperty(isAttribute = true, localName = "match-name")
    private String matchName;

    @JacksonXmlProperty(isAttribute = true, localName = "exclude")
    private String exclude;

    @JacksonXmlProperty(isAttribute = true, localName = "package")
    private String pkg;

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

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }
}
