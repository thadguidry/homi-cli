
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tool.api.metadata.MetadataDescriptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Generate Java JPA Entities from an existing database.
 *
 */
public class JpaGenerator extends AbstractJpaGenerator {

    /** The directory into which the JPA entities will be generated. */
    private File outputDirectory = new File("generated-sources/");


    /** A path used for looking up user-edited templates. */
    private String templatePath = null;

    protected void executeExporter(MetadataDescriptor metadataDescriptor) {
        LombokPojoExporter pojoExporter = new LombokPojoExporter();
        pojoExporter.setMetadataDescriptor(metadataDescriptor);
        pojoExporter.setOutputDirectory(outputDirectory);
        if (templatePath != null) {
            System.out.println("Setting template path to: " + templatePath);
            pojoExporter.setTemplatePath(new String[]{templatePath});
        }
        pojoExporter.getProperties().setProperty("ejb3", "true");
        pojoExporter.getProperties().setProperty("jdk5", "true");

        pojoExporter.start();

        List<PersistentClass> pojos = new ArrayList<>(
        pojoExporter.getMetadata().getEntityBindings());


        for(PersistentClass pojo : pojos) {
            System.out.println("pojo - {}" +  pojo);
        }

    }


}
