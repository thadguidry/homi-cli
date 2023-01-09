public class DtoItem {

        @Override
        public String toString() {
            return "DtoItem{" +
                    "javaType='" + javaType + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }

        public DtoItem(String javaType, String name) {
            this.javaType = javaType;
            this.name = name;
        }
        private String javaType;
        private String name;

        public String getJavaType() {
            return javaType;
        }

        public void setJavaType(String javaType) {
            this.javaType = javaType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
