///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.6.3
//DEPS org.yaml:snakeyaml:1.33
//DEPS com.konghq:unirest-java:3.14.1
//DEPS com.konghq:unirest-objectmapper-jackson:3.14.1
//DEPS org.apache.maven:maven-model:3.8.6
//DEPS org.eclipse.jgit:org.eclipse.jgit:6.4.0.202211300538-r
//DEPS org.freemarker:freemarker:2.3.31

//SOURCES Recipe.java
//SOURCES App.java
//SOURCES GeneratorConfig.java
//SOURCES GenerateRest.java
//SOURCES InitProjectCommand.java
//SOURCES RenameProjectTask.java
//SOURCES CreateApplicationProjectFolderTask.java
//SOURCES UpdateApplicationProjectPomTask.java
//SOURCES UpdateRootPomTask.java
//SOURCES UpdateRepositoryProjectPomTask.java
//SOURCES CreateRepositoryProjectFolderTask.java
//SOURCES UpdateDomainProjectPomTask.java
//SOURCES UpdateDomainProjectFolderTask.java
//SOURCES CreateCustomProjectFolderTask.java
//SOURCES UpdateCustomProjectPomTask.java
//SOURCES GenerateMainClassTask.java

//FILES sbmain.ftl
//FILES sbmaintest.ftl

import picocli.CommandLine;
import picocli.CommandLine.Command;


import java.util.concurrent.Callable;

@Command(name = "homi", mixinStandardHelpOptions = true, version = "HomiCli 0.1.1",
        description = "HomiCli made with jbang",
        subcommands = {GenerateRest.class, InitProjectCommand.class}
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
