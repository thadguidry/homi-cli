
import kong.unirest.Unirest;
import net.lingala.zip4j.ZipFile;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.StandardCopyOption;
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
                    "&dependencies=web,data-rest,#database#";

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
                .asFile(generator.getName() + ".zip", StandardCopyOption.REPLACE_EXISTING)
                .getBody();

        String zipFileAbsolutePath = result.getAbsolutePath();
        System.out.println("Zip file location : " + zipFileAbsolutePath);

        if(result.exists()) {
            new ZipFile(result).extractAll("./");
        }
        else{
            System.out.println("Unable to extract the file.");
        }
        String pomFolder = zipFileAbsolutePath.replace(".zip", "");
        System.out.println("POM file location : " + pomFolder);



        MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
        FileInputStream fileInputStream = new FileInputStream(pomFolder + "/pom.xml");
        Model model = mavenXpp3Reader.read(fileInputStream);

        Dependency dependency = new Dependency();
        dependency.setGroupId("org.springframework.boot");
        dependency.setArtifactId("spring-boot-starter-oauth2-resource-server");


        model.getBuild().getPlugins();

        model.getDependencies().add(dependency);
        MavenXpp3Writer mavenXpp3Writer = new MavenXpp3Writer();
        FileOutputStream fileOutputStream = new FileOutputStream(pomFolder + "/pom.xml");
        mavenXpp3Writer.write(fileOutputStream, model);




        return 0;
    }




}
