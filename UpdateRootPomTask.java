import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
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
