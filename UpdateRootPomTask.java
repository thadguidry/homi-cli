import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class UpdateRootPomTask {
    private List<Map<String, String>> dbMap;
    public UpdateRootPomTask(List<Map<String, String>> dbMap) {
        this.dbMap = dbMap;
    }

    public void execute(Recipe recipe) {
        try {
            MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
            FileInputStream fileInputStream = new FileInputStream(recipe.getApp().getArtifactId() + "/pom.xml");
            Model model = mavenXpp3Reader.read(fileInputStream);
            model.setArtifactId(recipe.getApp().getArtifactId());
            model.setGroupId(recipe.getApp().getGroupId());
            model.setDescription(recipe.getApp().getDescription());
            model.setName(recipe.getApp().getArtifactId());

            Optional<Map<String,String>> db =
            dbMap.stream().filter(i -> i.get("key").equals(recipe.getApp().getDb())).findFirst();

            if(db.isPresent()) {

                Dependency dbDependency = new Dependency();
                dbDependency.setGroupId(db.get().get("groupId"));
                dbDependency.setArtifactId(db.get().get("artifactId"));
                dbDependency.setScope("runtime");
                model.getDependencies().add(dbDependency);

            }
            else{
                System.out.println("!!! No matching DB found");
            }

            if(Objects.nonNull(recipe.getApp().getDbVersionManager())) {
                if(recipe.getApp().getDbVersionManager().equalsIgnoreCase("liquibase")) {
                    //create folder
                    File lbFolder = new File(recipe.getApp().getArtifactId() + "/src/main/resources/db/changelog");
                    lbFolder.mkdirs();
                    //add dependency

                    Dependency lbDependency = new Dependency();
                    lbDependency.setGroupId("org.liquibase");
                    lbDependency.setArtifactId("liquibase-core");
                    model.getDependencies().add(lbDependency);
                }
                else if(recipe.getApp().getDbVersionManager().equalsIgnoreCase("flyway")) {
                    //create folder
                    File lbFolder = new File(recipe.getApp().getArtifactId() + "/src/main/resources/db/migration");
                    lbFolder.mkdirs();


                    Dependency fwDependency = new Dependency();
                    fwDependency.setGroupId("org.flywaydb");
                    fwDependency.setArtifactId("flyway-core");
                    model.getDependencies().add(fwDependency);

                    if(recipe.getApp().getDb().equalsIgnoreCase("mysql")) {
                        Dependency fwMySqlDependency = new Dependency();
                        fwMySqlDependency.setGroupId("org.flywaydb");
                        fwMySqlDependency.setArtifactId("flyway-mysql");
                        model.getDependencies().add(fwMySqlDependency);
                    }
                    else if(recipe.getApp().getDb().equalsIgnoreCase("sqlserver")) {
                        Dependency fwMySqlDependency = new Dependency();
                        fwMySqlDependency.setGroupId("org.flywaydb");
                        fwMySqlDependency.setArtifactId("flyway-sqlserver");
                        model.getDependencies().add(fwMySqlDependency);
                    }
                }
            }


            MavenXpp3Writer mavenXpp3Writer = new MavenXpp3Writer();
            FileOutputStream fileOutputStream = new FileOutputStream(recipe.getApp().getArtifactId() + "/pom.xml");
            mavenXpp3Writer.write(fileOutputStream, model);
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update parent pom.");
        }

    }
}
