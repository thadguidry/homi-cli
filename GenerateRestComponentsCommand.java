
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import picocli.CommandLine;

import java.util.concurrent.Callable;


@CommandLine.Command(name = "rest", mixinStandardHelpOptions = true, version = "HomiCli 0.2",
        description = "Generate REST API components.")
public class GenerateRestComponentsCommand implements Callable<Integer> {

    @CommandLine.Option(names = { "--recipe" , "-r" } , description = "Recipe file", required = true)
    private String configFile;


    @Override
    public Integer call() throws Exception {

        RecipeLoader recipeLoader = new RecipeLoader();
        Recipe recipe = recipeLoader.get(configFile);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String xml = xmlMapper.writeValueAsString(recipe.getDbConfig());
        System.out.println(xml);



        return 0;
    }




}
