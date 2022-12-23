
import kong.unirest.Unirest;
import net.lingala.zip4j.ZipFile;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.Callable;



@CommandLine.Command(name = "rest", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "Generate secured REST API from your database.")
public class GenerateRest implements Callable<Integer> {

    @CommandLine.Option(names = { "--config" , "-c" } , description = "Configuration file", required = true)
    private String configFile;


    private String nonReactiveAPIUrl =

            "https://start.spring.io/starter.zip?" +
                    "type=maven-project" +
                    "&language=java" +
                    "&bootVersion=2.7.6" +
                    "&baseDir=#name#" +
                    "&groupId=#groupId#" +
                    "&artifactId=#name#" +
                    "&name=#name#" +
                    "&description=#description#" +
                    "&packageName=#packageName#" +
                    "&packaging=jar" +
                    "&javaVersion=17" +
                    "&dependencies=web,data-rest,oauth2-resource-server,#database#";

    @Override
    public Integer call() throws Exception {
        System.out.println("Hello rest");

        Yaml yaml = new Yaml();
        GeneratorConfig generator =
                yaml.loadAs(new FileInputStream(new File(configFile)), GeneratorConfig.class);

        String url = this.nonReactiveAPIUrl.replace("#groupId#", generator.getGroupId())
                .replace("#packageName#", generator.getPackageName())
                .replace("#description#", generator.getDescription())
                .replace("#database#", generator.getDatabase())
                .replace("#name#", generator.getName())
                ;

        File result = Unirest.get(url)
                .asFile(generator.getName() + ".zip")
                .getBody();

        if(result.exists()) {
            new ZipFile(result).extractAll("/Users/dhrubo/Downloads/generator");
        }
        else{
            System.out.println("Unable to unzip the file.");
        }

        return 0;
    }




}
