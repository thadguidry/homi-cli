
import codegen.Generator;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;
import codegen.JpaGenerator;

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
                    "&baseDir=demo" +
                    "&groupId=#groupId#" +
                    "&artifactId=demo" +
                    "&name=demo" +
                    "&description=Demo project for Spring Boot" +
                    "&packageName=#packageName#" +
                    "&packaging=jar" +
                    "&javaVersion=17" +
                    "&dependencies=web,data-rest,oauth2-resource-server,postgresql";

    @Override
    public Integer call() throws Exception {
        System.out.println("Hello rest");
        /*

        String url = this.nonReactiveAPIUrl.replace("#groupId#", generator.getGroupId())
                .replace("#packageName#", generator.getPackageName());

        File result = Unirest.get(url)
                .asFile("/Users/dhrubo/Downloads/generator/demo.zip")
                .getBody();


        new ZipFile(result).extractAll("/Users/dhrubo/Downloads/generator");
        */

        Yaml yaml = new Yaml();
        Generator generator =
                yaml.loadAs(new FileInputStream(new File(configFile)), Generator.class);

        JpaGenerator jpaGenerator = new JpaGenerator();
        jpaGenerator.execute();

        return 0;
    }




}
