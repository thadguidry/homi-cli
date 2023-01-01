import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpdateRepositoryProjectFolderTask {

    public void execute(Recipe recipe) {
        try {
            String javaFolder = recipe.getApp().getArtifactId() + "/repository/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/repository";

            new File(javaFolder).mkdirs();

            String testFolder = recipe.getApp().getArtifactId() + "/repository/src/test/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/repository";

            new File(testFolder).mkdirs();

        }
        catch (Exception e) {
            throw new RuntimeException("Failed to update application module folders.");
        }
    }
}
