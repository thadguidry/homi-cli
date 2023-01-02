import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonRootName("dbConfig")
@JacksonXmlRootElement(localName = "hibernate-reverse-engineering")
public class DbConfig {

    @JacksonXmlProperty(isAttribute = false, localName = "schema-selection")
    @JacksonXmlElementWrapper(useWrapping = false)
    private SchemaSelection [] schemaSelection;

    public SchemaSelection[] getSchemaSelection() {
        return schemaSelection;
    }

    public void setSchemaSelection(SchemaSelection[] schemaSelection) {
        this.schemaSelection = schemaSelection;
    }
}
