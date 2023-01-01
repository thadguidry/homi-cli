import java.io.File;

public class CreateCustomProjectFolderTask {

    public void execute(Recipe recipe) {
        try {
            String javaFolder = recipe.getApp().getArtifactId() + "/custom/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/custom";

            new File(javaFolder).mkdirs();

            String testFolder = recipe.getApp().getArtifactId() + "/custom/src/test/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/custom";

            new File(testFolder).mkdirs();

        }
        catch (Exception e) {
            throw new RuntimeException("Failed to update application module folders.");
        }
    }
}
