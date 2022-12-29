

import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;

import java.io.File;
import java.io.FileInputStream;
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
        Recipe generator =
                yaml.loadAs(new FileInputStream(new File(configFile)), Recipe.class);







        return 0;
    }




}
