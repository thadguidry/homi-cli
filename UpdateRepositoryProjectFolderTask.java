import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpdateRepositoryProjectFolderTask {

    public void execute(Recipe recipe) {
        try {
            String appFolder = recipe.getApp().getArtifactId() + "/repository/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/repository";

            File repositoryFolderFile = new File(appFolder);
            repositoryFolderFile.mkdirs();


        }
        catch (Exception e) {
            throw new RuntimeException("Failed to update application module folders.");
        }
    }
}
