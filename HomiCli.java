///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.6.3
//DEPS org.yaml:snakeyaml:1.33
//DEPS com.konghq:unirest-java:3.14.1
//DEPS com.konghq:unirest-objectmapper-jackson:3.14.1
//DEPS net.lingala.zip4j:zip4j:2.11.2
//DEPS org.apache.maven:maven-model:3.8.6
//DEPS org.slf4j:slf4j-api:2.0.6
//DEPS org.projectlombok:lombok:1.18.24
//SOURCES Recipe.java
//SOURCES GeneratorConfig.java
//SOURCES GenerateRest.java
//SOURCES InitProject.java


import picocli.CommandLine;
import picocli.CommandLine.Command;


import java.util.concurrent.Callable;

@Command(name = "homi", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "HomiCli made with jbang",
        subcommands = {GenerateRest.class, InitProject.class}
)
class HomiCli implements Callable<Integer> {


    public static void main(String... args) {
        int exitCode = new CommandLine(new HomiCli()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Hello HomiCli 0.2");
        return 0;
    }


}
