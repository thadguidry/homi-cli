import java.io.File;

public class CreateProjectFolderTask {

    public void execute(Recipe recipe) {
        try {
            String appFolder = recipe.getApp().getArtifactId() + "/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/");

            File appFolderFile = new File(appFolder);
            appFolderFile.mkdirs();

            //3.3 - create test folder for application
            String appFolderTest = recipe.getApp().getArtifactId() + "/src/test/java/" +
                    recipe.getApp().getPackageName().replace(".", "/");

            File appFolderFileTest = new File(appFolderTest);
            appFolderFileTest.mkdirs();


        }
        catch (Exception e) {
            throw new RuntimeException("Failed to update application module folders.");
        }
    }
}
