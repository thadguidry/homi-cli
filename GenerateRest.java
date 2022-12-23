
import kong.unirest.Unirest;
import net.lingala.zip4j.ZipFile;
import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;



@CommandLine.Command(name = "rest", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "Generate secured REST API from your database.")
public class GenerateRest implements Callable<Integer> {

    //@CommandLine.Option(names = { "--config" , "-c" } , description = "Configuration file", required = true)
    //private String configFile;

    @CommandLine.Option(names = { "--name" , "-n" } , description = "Project name", required = true)
    private String name;

    @CommandLine.Option(names = { "--groupId" , "-g" } , description = "Project group Id", required = true)
    private String groupId;

    @CommandLine.Option(names = { "--description" , "-d" } , description = "Describe the project", required = false)
    private String description;

    @CommandLine.Option(names = { "--packageName" , "-p" } , description = "Package name", required = true)
    private String packageName;

    @CommandLine.Option(names = { "--database" , "-db" } , description = "Database type - allowed values : postgresql,mysql", required = true)
    private String database;
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


        String url = this.nonReactiveAPIUrl.replace("#groupId#", groupId)
                .replace("#packageName#", packageName)
                .replace("#description#", description)
                .replace("#database#", database)
                .replace("#name#", name)
                ;

        File result = Unirest.get(url)
                .asFile(name + ".zip")
                .getBody();


        new ZipFile(result).extractAll("./");

        return 0;
    }




}
