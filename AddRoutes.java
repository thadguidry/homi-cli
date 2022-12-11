import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "addRoutes", mixinStandardHelpOptions = true, version = "HomiCli 0.2",
        description = "Add Routes to a gateway using CLI.")
public class AddRoutes implements Callable<Integer> {



    @Override
    public Integer call() throws Exception {
        System.out.println("addRoutes");
        return 0;
    }
}

