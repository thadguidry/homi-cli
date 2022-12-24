///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.6.3
//DEPS org.yaml:snakeyaml:1.33
//DEPS com.konghq:unirest-java:3.14.1
//DEPS com.konghq:unirest-objectmapper-jackson:3.14.1
//DEPS org.hibernate:hibernate-core:5.6.14.Final
//DEPS org.hibernate:hibernate-tools:5.6.14.Final
//DEPS org.postgresql:postgresql:42.5.1
//DEPS net.lingala.zip4j:zip4j:2.11.2
//DEPS org.apache.maven:maven-model:3.8.6

//SOURCES GenerateRest.java
//SOURCES GeneratorConfig.java
//SOURCES JpaGenerator.java
//SOURCES AbstractJpaGenerator.java
//SOURCES AbstractExporter.java
//SOURCES GenericExporter.java
//SOURCES POJOExporter.java
//SOURCES GithubHelper.java


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
    public Integer call() throws Exception {
        System.out.println("Hello HomiCli 0.2");
        return 0;
    }


}
