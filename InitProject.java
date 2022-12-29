

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.eclipse.jgit.api.Git;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "init", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "Initialize Homi project.")
public class InitProject implements Callable<Integer> {

    @CommandLine.Option(names = { "--recipe" , "-r" } , description = "Recipe file", required = true)
    private String configFile;


    @Override
    public Integer call() throws Exception {
        System.out.println("Hello rest");

        Yaml yaml = new Yaml();
        Recipe recipe =
                yaml.loadAs(new FileInputStream(new File(configFile)), Recipe.class);


        Git.cloneRepository()
                .setURI("https://github.com/homihq/homi-starter-rest.git")
                //.setDirectory(new File("/")) //config param
                .call();


        //1. rename project
        File file = new File("homi-starter-rest");
        System.out.println("File exists - " + file.exists());
        file.renameTo(new File(recipe.getApp().getArtifactId()));

        //2. change pom.xml of root
        MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
        FileInputStream fileInputStream = new FileInputStream(  recipe.getApp().getArtifactId() + "/pom.xml");
        Model model = mavenXpp3Reader.read(fileInputStream);
        model.setArtifactId(recipe.getApp().getArtifactId());
        model.setGroupId(recipe.getApp().getGroupId());
        model.setDescription(recipe.getApp().getDescription());

        MavenXpp3Writer mavenXpp3Writer = new MavenXpp3Writer();
        FileOutputStream fileOutputStream = new FileOutputStream(recipe.getApp().getArtifactId()  + "/pom.xml");
        mavenXpp3Writer.write(fileOutputStream, model);


        //3. change application project pom
        MavenXpp3Reader applicationReader = new MavenXpp3Reader();
        FileInputStream appFis = new FileInputStream(  recipe.getApp().getArtifactId()  + "/application/pom.xml");
        Model applicationModel = applicationReader.read(appFis);
        applicationModel.setGroupId(recipe.getApp().getGroupId());
        applicationModel.getBuild().setFinalName(recipe.getApp().getArtifactId());

        //3.1 add dependency - custom, repository
        Dependency dependencyCustom = new Dependency();
        dependencyCustom.setGroupId(recipe.getApp().getGroupId());
        dependencyCustom.setArtifactId("custom");
        dependencyCustom.setVersion("${project.version}");

        Dependency dependencyRepository = new Dependency();
        dependencyRepository.setGroupId(recipe.getApp().getGroupId());
        dependencyRepository.setArtifactId("repository");
        dependencyRepository.setVersion("${project.version}");


        applicationModel.getDependencies().removeIf(i -> i.getGroupId().equals("com.homihq")
                && i.getArtifactId().equals("custom"));
        applicationModel.getDependencies().removeIf(i -> i.getGroupId().equals("com.homihq")
                && i.getArtifactId().equals("repository"));

        applicationModel.getDependencies().add(dependencyCustom);
        applicationModel.getDependencies().add(dependencyRepository);

        MavenXpp3Writer applicationWriter = new MavenXpp3Writer();
        FileOutputStream appFos = new FileOutputStream(recipe.getApp().getArtifactId()  + "/application/pom.xml");
        applicationWriter.write(appFos, applicationModel);

        //3.2 - create folder
        String appFolder = recipe.getApp().getArtifactId() + "/application/src/main/java/" +
                recipe.getApp().getPackageName().replace("." , "/");

        File appFolderFile = new File(appFolder);
        appFolderFile.mkdirs();

        String appFolderDel =  recipe.getApp().getArtifactId() + "/application/src/main/java/" +
                "com/homihq/app";

        Files.move(Paths.get(appFolderDel + "/HomiRestApplication.java"),
                Paths.get(appFolder + "/HomiRestApplication.java"));



        File appFolderDelFile = new File(appFolderDel);
        appFolderDelFile.delete();

        return 0;
    }




}
