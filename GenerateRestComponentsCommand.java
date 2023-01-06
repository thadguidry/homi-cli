
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import picocli.CommandLine;

import java.io.ByteArrayInputStream;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "rest", mixinStandardHelpOptions = true, version = "HomiCli 0.2",
        description = "Generate REST API components.")
public class GenerateRestComponentsCommand implements Callable<Integer> {

    @CommandLine.Option(names = { "--recipe" , "-r" } , description = "Recipe file", required = true)
    private String configFile;

    @CommandLine.Option(names = { "--user" , "-u" } , description = "Database Username", required = true)
    private String user;

    @CommandLine.Option(names = { "--password" , "-p" } , description = "Database Password", required = true)
    private String password;

    @CommandLine.Option(names = { "--jdbcUrl" , "-j" } , description = "JDBC URL", required = true)
    private String jdbcUrl;

    private final String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<!DOCTYPE hibernate-reverse-engineering PUBLIC\n" +
            "        \"-//Hibernate/Hibernate Reverse Engineering DTD 3.0//EN\"\n" +
            "        \"http://hibernate.org/dtd/hibernate-reverse-engineering-3.0.dtd\" >";

    @Override
    public Integer call() throws Exception {

        RecipeLoader recipeLoader = new RecipeLoader();
        Recipe recipe = recipeLoader.get(configFile);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String xml = HEADER +
                xmlMapper.writeValueAsString(recipe.getDbConfig());
        System.out.println(

                xml);



        JpaGenerator jpaGenerator = new JpaGenerator(recipe, new ByteArrayInputStream(xml.getBytes()));
        jpaGenerator.execute(user, password, jdbcUrl);

        return 0;
    }




}
