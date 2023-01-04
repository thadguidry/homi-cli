
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.eclipse.jgit.api.Git;
import picocli.CommandLine;
import java.util.concurrent.Callable;


@CommandLine.Command(name = "init", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "Initialize Homi project.")
public class InitProjectCommand implements Callable<Integer> {

    @CommandLine.Option(names = { "--recipe" , "-r" } , description = "Recipe file", required = true)
    private String configFile;


    @Override
    public Integer call() throws Exception {

        RecipeLoader recipeLoader = new RecipeLoader();
        Recipe recipe = recipeLoader.get(configFile);
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);


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
        CreateApplicationProjectFolderTask createApplicationProjectFolderTask = new CreateApplicationProjectFolderTask();
        createApplicationProjectFolderTask.execute(recipe);

        //4. change repository project pom
        UpdateRepositoryProjectPomTask updateRepositoryProjectPomTask = new UpdateRepositoryProjectPomTask();
        updateRepositoryProjectPomTask.execute(recipe);

        //4.1 update repositry project folder
        CreateRepositoryProjectFolderTask createRepositoryProjectFolderTask = new CreateRepositoryProjectFolderTask();
        createRepositoryProjectFolderTask.execute(recipe);

        //5. change domain project pom
        UpdateDomainProjectPomTask updateDomainProjectPomTask = new UpdateDomainProjectPomTask();
        updateDomainProjectPomTask.execute(recipe);

        //5.1 update folders
        UpdateDomainProjectFolderTask updateDomainProjectFolderTask = new UpdateDomainProjectFolderTask();
        updateDomainProjectFolderTask.execute(recipe);

        //6 Change custom oroject pom
        UpdateCustomProjectPomTask updateCustomProjectPomTask = new UpdateCustomProjectPomTask();
        updateCustomProjectPomTask.execute(recipe);

        //6.1 update folders
        CreateCustomProjectFolderTask createCustomProjectFolderTask = new CreateCustomProjectFolderTask();
        createCustomProjectFolderTask.execute(recipe);

        //7. Generate class
        GenerateMainClassTask generateMainClassTask = new GenerateMainClassTask();
        generateMainClassTask.execute(recipe);

        //8. Generate application properties
        GenerateAppPropertiesTask generateAppPropertiesTask = new GenerateAppPropertiesTask();
        generateAppPropertiesTask.execute(recipe);


        return 0;
    }




}
