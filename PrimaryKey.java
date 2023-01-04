import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class PrimaryKey {

    @JacksonXmlProperty(isAttribute = false, localName = "generator")
    @JacksonXmlElementWrapper(useWrapping = false)
    private Generator generator;

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
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
