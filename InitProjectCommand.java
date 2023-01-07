
import org.eclipse.jgit.api.Git;
import picocli.CommandLine;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "init", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "Initialize Homi project.")
public class InitProjectCommand implements Callable<Integer> {

    @CommandLine.Option(names = { "--recipe" , "-r" } , description = "Recipe file", required = true)
    private String recipeFile;


    @Override
    public Integer call() throws Exception {

        ConfigLoader configLoader = new ConfigLoader(recipeFile);
        Recipe recipe = configLoader.getRecipe();

        Git.cloneRepository()
                .setURI("https://github.com/homihq/spring-rest-jpa-starter.git")
                //.setDirectory(new File("/")) //config param
                .call();


        //1. rename project
        RenameProjectTask renameProjectTask = new RenameProjectTask();
        renameProjectTask.execute(recipe);

        //2. change pom.xml of root
        UpdateRootPomTask updateRootPomTask = new UpdateRootPomTask(configLoader.getDbMap());
        updateRootPomTask.execute(recipe);


        //3 - create project root folders
        CreateProjectFolderTask createProjectFolderTask = new CreateProjectFolderTask();
        createProjectFolderTask.execute(recipe);


        //7. Generate class
        GenerateMainClassTask generateMainClassTask = new GenerateMainClassTask();
        generateMainClassTask.execute(recipe);

        //8. Generate application properties
        GenerateAppPropertiesTask generateAppPropertiesTask = new GenerateAppPropertiesTask(configLoader.getDbMap());
        generateAppPropertiesTask.execute(recipe);


        return 0;
    }




}
