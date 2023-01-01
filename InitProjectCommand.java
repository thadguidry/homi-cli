
import org.eclipse.jgit.api.Git;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "init", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "Initialize Homi project.")
public class InitProjectCommand implements Callable<Integer> {

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
        RenameProjectTask renameProjectTask = new RenameProjectTask();
        renameProjectTask.execute(recipe);

        //2. change pom.xml of root
        UpdateRootPomTask updateRootPomTask = new UpdateRootPomTask();
        updateRootPomTask.execute(recipe);


        //3. change application project pom
        UpdateApplicationProjectPomTask updateApplicationProjectPomTask = new UpdateApplicationProjectPomTask();
        updateApplicationProjectPomTask.execute(recipe);

        //3.2 - create main folder for application
        UpdateApplicationProjectFolderTask updateApplicationProjectFolderTask = new UpdateApplicationProjectFolderTask();
        updateApplicationProjectFolderTask.execute(recipe);

        //4. change repository project pom
        UpdateRepositoryProjectPomTask updateRepositoryProjectPomTask = new UpdateRepositoryProjectPomTask();
        updateRepositoryProjectPomTask.execute(recipe);

        //4.1 update repositry project folder
        UpdateRepositoryProjectFolderTask updateRepositoryProjectFolderTask = new UpdateRepositoryProjectFolderTask();
        updateRepositoryProjectFolderTask.execute(recipe);

        //5. change domain project pom
        UpdateDomainProjectPomTask updateDomainProjectPomTask = new UpdateDomainProjectPomTask();
        updateDomainProjectPomTask.execute(recipe);

        //5.1 update folders
        UpdateDomainProjectFolderTask updateDomainProjectFolderTask = new UpdateDomainProjectFolderTask();
        updateDomainProjectFolderTask.execute(recipe);

        return 0;
    }




}
