//SOURCES codegen/*.java

import codegen.AbstractJpaGenerator;
import codegen.POJOExporter;
import org.hibernate.tool.api.metadata.MetadataDescriptor;

import java.io.File;



/**
 * Generate Java JPA Entities from an existing database.
 */
public class JpaGenerator extends AbstractJpaGenerator {

    /** The directory into which the JPA entities will be generated. */
    private File outputDirectory = new File("./generated");


    protected void executeExporter(MetadataDescriptor metadataDescriptor) {
        POJOExporter pojoExporter = new POJOExporter();
        pojoExporter.setMetadataDescriptor(metadataDescriptor);
        pojoExporter.setOutputDirectory(outputDirectory);

        pojoExporter.getProperties().setProperty("ejb3", "true");
        pojoExporter.getProperties().setProperty("jdk5", "true");
        System.out.println("Starting POJO export to directory: " + outputDirectory + "...");
        pojoExporter.start();
        System.out.println("Metadata: " + pojoExporter.getMetadata() );

    }


}
