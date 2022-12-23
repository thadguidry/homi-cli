import org.apache.tools.ant.BuildException;
import org.hibernate.cfg.reveng.OverrideRepository;
import org.hibernate.cfg.reveng.ReverseEngineeringSettings;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.tool.api.metadata.MetadataDescriptor;
import org.hibernate.tool.api.metadata.MetadataDescriptorFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractJpaGenerator {

    // For reveng strategy
    /** The default package name to use when mappings for classes are created. */
    private String packageName;

    /** The name of a property file, e.g. hibernate.properties. */
    private File revengFile;

    /** The class name of the reverse engineering strategy to use.
     * Extend the DefaultReverseEngineeringStrategy and override the corresponding methods, e.g.
     * to adapt the generate class names or to provide custom type mappings. */
    private String revengStrategy = "org.hibernate.cfg.reveng.DefaultReverseEngineeringStrategy";

    /** If true, tables which are pure many-to-many link tables will be mapped as such.
     * A pure many-to-many table is one which primary-key contains exactly two foreign-keys pointing
     * to other entity tables and has no other columns. */
    private boolean detectManyToMany = true;

    /** If true, a one-to-one association will be created for each foreignkey found. */
    private boolean detectOneToOne = true;

    /** If true, columns named VERSION or TIMESTAMP with appropriate types will be mapped with the appropriate
     * optimistic locking corresponding to &lt;version&gt; or &lt;timestamp&gt;. */
    private boolean detectOptimisticLock = true;

    /** If true, a collection will be mapped for each foreignkey. */
    private boolean createCollectionForForeignKey = true;

    /** If true, a many-to-one association will be created for each foreignkey found. */
    private boolean createManyToOneForForeignKey = true;

    // For configuration
    /** The name of a property file, e.g. hibernate.properties. */
    private File propertyFile = new File("/Users/dhrubo/Downloads/generator/hibernate2.properties");

    // Not exposed for now
    private boolean preferBasicCompositeIds = true;

    public void execute() {
        System.out.println("1. Starting " + this.getClass().getSimpleName() + "...");
        ReverseEngineeringStrategy strategy = setupReverseEngineeringStrategy();
        if (propertyFile.exists()) {
        	executeExporter(createJdbcDescriptor(strategy, loadPropertiesFile()));
        } else {
            System.out.println("Property file '" + propertyFile + "' cannot be found, aborting...");
        }
        System.out.println("Finished " + this.getClass().getSimpleName() + "!");
    }

    private ReverseEngineeringStrategy setupReverseEngineeringStrategy() {
        ReverseEngineeringStrategy strategy;
        try {
            strategy = ReverseEngineeringStrategy.class.cast(Class.forName(revengStrategy).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | ClassCastException e) {
            throw new BuildException(revengStrategy + " not instanced.", e);
        }

        if (revengFile != null) {
            OverrideRepository override = new OverrideRepository();
            override.addFile(revengFile);
            strategy = override.getReverseEngineeringStrategy(strategy);
        }

        ReverseEngineeringSettings settings =
                new ReverseEngineeringSettings(strategy)
                        .setDefaultPackageName(packageName)
                        .setDetectManyToMany(detectManyToMany)
                        .setDetectOneToOne(detectOneToOne)
                        .setDetectOptimisticLock(detectOptimisticLock)
                        .setCreateCollectionForForeignKey(createCollectionForForeignKey)
                        .setCreateManyToOneForForeignKey(createManyToOneForForeignKey);

        strategy.setSettings(settings);
        return strategy;
    }

    private Properties loadPropertiesFile() {
        try (FileInputStream is = new FileInputStream(propertyFile)) {
            Properties result = new Properties();
            result.load(is);
            return result;
        } catch (FileNotFoundException e) {
            throw new BuildException(propertyFile + " not found.", e);
        } catch (IOException e) {
            throw new BuildException("Problem while loading " + propertyFile, e);
        }
    }

    private MetadataDescriptor createJdbcDescriptor(ReverseEngineeringStrategy strategy, Properties properties) {
        return MetadataDescriptorFactory
                .createJdbcDescriptor(
                        strategy,
                        properties,
                        preferBasicCompositeIds);
    }

    protected abstract void executeExporter(MetadataDescriptor metadataDescriptor);
}
