import picocli.CommandLine;

import java.util.concurrent.Callable;
import picocli.CommandLine.Option;

@CommandLine.Command(name = "register", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "Register a gateway using CLI.")
public class RegisterGateway implements Callable<Integer> {

    @Option(names = { "--apiKey" } , description = "API Key")
    private String apiKey;

    @Override
    public Integer call() throws Exception {
        return null;
    }
}

