import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import java.io.FileInputStream;
import java.io.FileOutputStream;

@Slf4j
public class UpdateDomainProjectPomTask {

    public void execute(Recipe recipe) {


        try {
            MavenXpp3Reader applicationReader = new MavenXpp3Reader();

            FileInputStream appFis = new FileInputStream(recipe.getApp().getArtifactId() + "/domain/pom.xml");
            Model repositoryModel = applicationReader.read(appFis);
            repositoryModel.setGroupId(recipe.getApp().getGroupId());


            MavenXpp3Writer applicationWriter = new MavenXpp3Writer();
            FileOutputStream appFos = new FileOutputStream(recipe.getApp().getArtifactId() + "/domain/pom.xml");
            applicationWriter.write(appFos, repositoryModel);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update application module pom.");
        }
    }
}
