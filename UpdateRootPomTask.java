import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class UpdateRootPomTask {

    public void execute(Recipe recipe) {
        try {
            MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
            FileInputStream fileInputStream = new FileInputStream(recipe.getApp().getArtifactId() + "/pom.xml");
            Model model = mavenXpp3Reader.read(fileInputStream);
            model.setArtifactId(recipe.getApp().getArtifactId());
            model.setGroupId(recipe.getApp().getGroupId());
            model.setDescription(recipe.getApp().getDescription());

            MavenXpp3Writer mavenXpp3Writer = new MavenXpp3Writer();
            FileOutputStream fileOutputStream = new FileOutputStream(recipe.getApp().getArtifactId() + "/pom.xml");
            mavenXpp3Writer.write(fileOutputStream, model);
        }
        catch(Exception e) {
            throw new RuntimeException("Failed to update parent pom.");
        }

    }
}
