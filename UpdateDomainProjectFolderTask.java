import java.io.File;

public class UpdateDomainProjectFolderTask {

    public void execute(Recipe recipe) {
        try {
            String javaFolder = recipe.getApp().getArtifactId() + "/domain/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/domain";

            new File(javaFolder).mkdirs();

            String testFolder = recipe.getApp().getArtifactId() + "/domain/src/test/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/domain";

            new File(testFolder).mkdirs();

        }
        catch (Exception e) {
            throw new RuntimeException("Failed to update application module folders.");
        }
    }
}
