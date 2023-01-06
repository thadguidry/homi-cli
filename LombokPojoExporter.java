import org.hibernate.tool.hbm2x.POJOExporter;

public class LombokPojoExporter extends POJOExporter {

    @Override
    protected void init() {
        this.setTemplateName("pojo2/Pojo2.ftl");
        this.setFilePattern("{package-name}/{class-name}.java");
    }
}
