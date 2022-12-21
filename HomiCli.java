///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.6.3
//DEPS org.yaml:snakeyaml:1.33
//DEPS com.konghq:unirest-java:3.14.1
//DEPS com.konghq:unirest-objectmapper-jackson:3.14.1
//SOURCES AddRoutes.java
//SOURCES GenerateRest.java


import picocli.CommandLine;
import picocli.CommandLine.Command;


import java.util.concurrent.Callable;

@Command(name = "homi", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "HomiCli made with jbang",
        subcommands = {GenerateRest.class}
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
