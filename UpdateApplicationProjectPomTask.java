import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class UpdateApplicationProjectPomTask {

    public void execute(Recipe recipe) {
        try {
            MavenXpp3Reader applicationReader = new MavenXpp3Reader();
            FileInputStream appFis = new FileInputStream(recipe.getApp().getArtifactId() + "/application/pom.xml");
            Model applicationModel = applicationReader.read(appFis);
            applicationModel.setGroupId(recipe.getApp().getGroupId());
            applicationModel.getBuild().setFinalName(recipe.getApp().getArtifactId());

            //3.1 add dependency - custom, repository
            Dependency dependencyCustom = new Dependency();
            dependencyCustom.setGroupId(recipe.getApp().getGroupId());
            dependencyCustom.setArtifactId("custom");
            dependencyCustom.setVersion("${project.version}");

            Dependency dependencyRepository = new Dependency();
            dependencyRepository.setGroupId(recipe.getApp().getGroupId());
            dependencyRepository.setArtifactId("repository");
            dependencyRepository.setVersion("${project.version}");



            applicationModel.getDependencies().removeIf(i -> i.getGroupId().equals("com.homihq")
                    && i.getArtifactId().equals("custom"));
            applicationModel.getDependencies().removeIf(i -> i.getGroupId().equals("com.homihq")
                    && i.getArtifactId().equals("repository"));

            applicationModel.getDependencies().add(dependencyCustom);
            applicationModel.getDependencies().add(dependencyRepository);

            if("pg".equals(recipe.getApp().getDb())) {
                Dependency db = new Dependency();
                db.setGroupId("org.postgresql");
                db.setArtifactId("postgresql");
                db.setScope("runtime");
                applicationModel.getDependencies().add(db);
            }

            MavenXpp3Writer applicationWriter = new MavenXpp3Writer();
            FileOutputStream appFos = new FileOutputStream(recipe.getApp().getArtifactId() + "/application/pom.xml");
            applicationWriter.write(appFos, applicationModel);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to update application module pom.");
        }
    }
}
