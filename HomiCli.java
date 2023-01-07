///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.6.3
//DEPS com.konghq:unirest-java:3.14.1
//DEPS com.konghq:unirest-objectmapper-jackson:3.14.1
//DEPS org.apache.maven:maven-model:3.8.6
//DEPS org.eclipse.jgit:org.eclipse.jgit:6.4.0.202211300538-r
//DEPS org.freemarker:freemarker:2.3.31
//DEPS com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.1
//DEPS com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.1
//DEPS org.hibernate:hibernate-tools:5.6.14.Final
//DEPS org.postgresql:postgresql:42.5.1

//SOURCES Recipe.java
//SOURCES App.java
//SOURCES GenerateRestComponentsCommand.java
//SOURCES InitProjectCommand.java
//SOURCES ConfigLoader.java
//SOURCES RenameProjectTask.java
//SOURCES CreateProjectFolderTask.java
//SOURCES UpdateRootPomTask.java
//SOURCES GenerateMainClassTask.java
//SOURCES GenerateAppPropertiesTask.java
//SOURCES GenerateRestRepositoryClassTask.java

//SOURCES PrimaryKey.java
//SOURCES JpaGenerator.java
//SOURCES LombokPojoExporter.java
//SOURCES AbstractJpaGenerator.java
//SOURCES SimpleReverseEngineeringStrategy.java

//SOURCES DbConfig.java
//SOURCES SchemaSelection.java
//SOURCES TableFilter.java
//SOURCES Table.java

//FILES sbmain.ftl
//FILES sbmaintest.ftl
//FILES db.yml

//FILES pojo2/Pojo2.ftl=pojo2/Pojo2.ftl
//FILES pojo2/PojoTypeDeclaration.ftl=pojo2/PojoTypeDeclaration.ftl
//FILES pojo2/Ejb3TypeDeclaration.ftl=pojo2/Ejb3TypeDeclaration.ftl
//FILES pojo2/PojoFields.ftl=pojo2/PojoFields.ftl
//FILES pojo2/PojoConstructors.ftl=pojo2/PojoConstructors.ftl
//FILES pojo2/PojoPropertyAccessors.ftl=pojo2/PojoPropertyAccessors.ftl
//FILES pojo2/GetPropertyAnnotation.ftl=pojo2/GetPropertyAnnotation.ftl
//FILES pojo2/Ejb3PropertyGetAnnotation.ftl=pojo2/Ejb3PropertyGetAnnotation.ftl
//FILES pojo2/PojoToString.ftl=pojo2/PojoToString.ftl
//FILES pojo2/PojoEqualsHashcode.ftl=pojo2/PojoEqualsHashcode.ftl
//FILES pojo2/PojoExtraClassCode.ftl=pojo2/PojoExtraClassCode.ftl

//FILES repo/RestRepository.ftl=repo/RestRepository.ftl


import picocli.CommandLine;
import picocli.CommandLine.Command;


import java.util.concurrent.Callable;

@Command(name = "homi", mixinStandardHelpOptions = true, version = "HomiCli 0.1.1",
        description = "HomiCli made with jbang",
        subcommands = {GenerateRestComponentsCommand.class, InitProjectCommand.class}
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
