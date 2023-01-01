

import picocli.CommandLine;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "rest", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "Generate secured REST API.")
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
                    "&dependencies=web,data-rest,#database#";

    @Override
    public Integer call() throws Exception {
        System.out.println("Hello rest");




        return 0;
    }




}
