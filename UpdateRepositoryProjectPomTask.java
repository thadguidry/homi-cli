import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import java.io.FileInputStream;
import java.io.FileOutputStream;

@Slf4j
public class UpdateRepositoryProjectPomTask {

    public void execute(Recipe recipe) {
        System.out.println("=== updating repository pom ===clea");

        try {
            MavenXpp3Reader applicationReader = new MavenXpp3Reader();
            System.out.println("Repo - " + recipe.getApp().getArtifactId() + "/repository/pom.xml");
            FileInputStream appFis = new FileInputStream(recipe.getApp().getArtifactId() + "/repository/pom.xml");
            Model repositoryModel = applicationReader.read(appFis);
            repositoryModel.setGroupId(recipe.getApp().getGroupId());



            Dependency dependencyDomain= new Dependency();
            dependencyDomain.setGroupId(recipe.getApp().getGroupId());
            dependencyDomain.setArtifactId("domain");
            dependencyDomain.setVersion("${project.version}");


            repositoryModel.getDependencies().removeIf(i -> i.getGroupId().equals("com.homihq")
                    && i.getArtifactId().equals("domain"));


            repositoryModel.getDependencies().add(dependencyDomain);


            MavenXpp3Writer applicationWriter = new MavenXpp3Writer();
            FileOutputStream appFos = new FileOutputStream(recipe.getApp().getArtifactId() + "/repository/pom.xml");
            applicationWriter.write(appFos, repositoryModel);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update application module pom.");
        }
    }
}
