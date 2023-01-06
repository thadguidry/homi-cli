
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tool.api.metadata.MetadataDescriptor;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Generate Java JPA Entities from an existing database.
 *
 */
public class JpaGenerator extends AbstractJpaGenerator {

    /** The directory into which the JPA entities will be generated. */
    private File outputDirectory;

    private Recipe recipe;

    public JpaGenerator(Recipe recipe, InputStream inputStream) {
        this.recipe = recipe;
        String domainFolder = recipe.getApp().getArtifactId() + "/domain/src/main/java" ;

        this.outputDirectory = new File(domainFolder);
        this.setPackageName(recipe.getApp().getPackageName()+".domain");

        this.setReverseEnggFile(inputStream);
    }


    protected void executeExporter(MetadataDescriptor metadataDescriptor) {
        LombokPojoExporter pojoExporter = new LombokPojoExporter();
        pojoExporter.setMetadataDescriptor(metadataDescriptor);
        pojoExporter.setOutputDirectory(outputDirectory);

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
