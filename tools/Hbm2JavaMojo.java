package tools;


import org.hibernate.tool.api.metadata.MetadataDescriptor;

import java.io.File;


/**
 * Generate Java JPA Entities from an existing database.
 */
public class Hbm2JavaMojo extends AbstractHbm2xMojo {

    /** The directory into which the JPA entities will be generated. */
    private File outputDirectory = new File("./generated");

    /** Code will contain EJB 3 features, e.g. using annotations from javax.persistence
     * and org.hibernate.annotations. */
    private boolean ejb3 = true;

    /** Code will contain JDK 5 constructs such as generics and static imports. */
    private boolean jdk5;

    /** A path used for looking up user-edited templates. */
    private String templatePath;

    protected void executeExporter(MetadataDescriptor metadataDescriptor) {
        POJOExporter pojoExporter = new POJOExporter();
        pojoExporter.setMetadataDescriptor(metadataDescriptor);
        pojoExporter.setOutputDirectory(outputDirectory);
        if (templatePath != null) {
            System.out.println("Setting template path to: " + templatePath);
            pojoExporter.setTemplatePath(new String[]{templatePath});
        }
        pojoExporter.getProperties().setProperty("ejb3", String.valueOf(ejb3));
        pojoExporter.getProperties().setProperty("jdk5", String.valueOf(jdk5));
        System.out.println("Starting POJO export to directory: " + outputDirectory + "...");
        pojoExporter.start();
        System.out.println("Metadata: " + pojoExporter.getMetadata() );

    }


}
