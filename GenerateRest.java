
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.Callable;



@CommandLine.Command(name = "rest", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "Generate secured REST API from your database.")
public class GenerateRest implements Callable<Integer> {

    @CommandLine.Option(names = { "--config" , "-c" } , description = "Configuration file")
    private String configFile;



    @Override
    public Integer call() throws Exception {



        return 0;
    }




}
