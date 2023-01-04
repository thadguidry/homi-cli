import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class PrimaryKey {

    @JacksonXmlProperty(isAttribute = false, localName = "generator")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Generator generator;

    @JacksonXmlProperty(isAttribute = false, localName = "key-column")
    @JacksonXmlElementWrapper(useWrapping = false)
    private KeyColumn [] keyColumn;

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public KeyColumn[] getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(KeyColumn[] keyColumn) {
        this.keyColumn = keyColumn;
    }

    private static class KeyColumn{
        @JacksonXmlProperty(isAttribute = true, localName = "name")
        private String name;
        @JacksonXmlProperty(isAttribute = true, localName = "jdbc-type")
        private String jdbcType;
        @JacksonXmlProperty(isAttribute = true, localName = "type")
        private String type;
        @JacksonXmlProperty(isAttribute = true, localName = "property")
        private String property;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJdbcType() {
            return jdbcType;
        }

        public void setJdbcType(String jdbcType) {
            this.jdbcType = jdbcType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }

    private static class Generator{

        @JacksonXmlProperty(isAttribute = true, localName = "class")
        private String className;

        @JacksonXmlProperty(isAttribute = false, localName = "param")
        @JacksonXmlElementWrapper(useWrapping = false)
        private Parameter param;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public Parameter getParam() {
            return param;
        }

        public void setParam(Parameter param) {
            this.param = param;
        }


        private static class Parameter {
            @JacksonXmlProperty(isAttribute = true, localName = "name")
            private String name;

            @JacksonXmlText
            private String value;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }


}
