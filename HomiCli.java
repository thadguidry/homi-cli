///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.6.3
//DEPS org.yaml:snakeyaml:1.33
//SOURCES RegisterGateway.java
//SOURCES MergeYaml.java


import picocli.CommandLine;
import picocli.CommandLine.Command;


import java.util.concurrent.Callable;

@Command(name = "homi", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "HomiCli made with jbang",
        subcommands = {RegisterGateway.class,}
)
class HomiCli implements Callable<Integer> {


    public static void main(String... args) {
        int exitCode = new CommandLine(new HomiCli()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        System.out.println("Hello HomiCli 0.1");
        return 0;
    }
}
