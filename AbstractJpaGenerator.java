
import org.hibernate.cfg.reveng.DefaultReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.OverrideRepository;
import org.hibernate.cfg.reveng.ReverseEngineeringSettings;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.tool.api.metadata.MetadataDescriptor;
import org.hibernate.tool.api.metadata.MetadataDescriptorFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;


public abstract class AbstractJpaGenerator {

    // For reveng strategy
    /** The default package name to use when mappings for classes are created. */
    private String packageName;

    private InputStream reverseEnggFile;


    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setReverseEnggFile(InputStream reverseEnggFile) {
        this.reverseEnggFile = reverseEnggFile;
    }

    public void execute(String user, String password, String jdbcUrl) {

        ReverseEngineeringStrategy strategy = setupReverseEngineeringStrategy();

        executeExporter(createJdbcDescriptor(strategy,
                loadProperties(user, password, jdbcUrl)));

    }

    private ReverseEngineeringStrategy setupReverseEngineeringStrategy() {

        OverrideRepository override = new OverrideRepository();
        override.addInputStream(reverseEnggFile);
        ReverseEngineeringStrategy strategy = override.getReverseEngineeringStrategy(new SimpleReverseEngineeringStrategy(new DefaultReverseEngineeringStrategy()));


        ReverseEngineeringSettings settings =
                new ReverseEngineeringSettings(strategy)
                        .setDefaultPackageName(packageName)
                        .setDetectManyToMany(true)
                        .setDetectOneToOne(true)
                        .setDetectOptimisticLock(true)
                        .setCreateCollectionForForeignKey(true)
                        .setCreateManyToOneForForeignKey(true);


        strategy.setSettings(settings);
        return strategy;
    }

    private Properties loadProperties(String user, String password, String jdbcUrl) {


        Properties result = new Properties();
        result.setProperty("hibernate.connection.url", jdbcUrl);
        //result.setProperty("hibernate.connection.driver_class", System.getenv().get("JDBC_DRIVER_CLASS"));
        result.setProperty("hibernate.connection.username", user);
        result.setProperty("hibernate.connection.password", password);

        //result.setProperty(" hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        return result;

    }

    private MetadataDescriptor createJdbcDescriptor(ReverseEngineeringStrategy strategy, Properties properties) {
        return MetadataDescriptorFactory
                .createJdbcDescriptor(
                        strategy,
                        properties,
                        true);
    }

    protected abstract void executeExporter(MetadataDescriptor metadataDescriptor);
}
