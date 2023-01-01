import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UpdateApplicationProjectFolderTask {

    public void execute(Recipe recipe) {
        try {
            String appFolder = recipe.getApp().getArtifactId() + "/application/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/");

            File appFolderFile = new File(appFolder);
            appFolderFile.mkdirs();

            String appFolderDel = recipe.getApp().getArtifactId() + "/application/src/main/java/" +
                    "com/homihq/app";

            Files.move(Paths.get(appFolderDel + "/HomiRestApplication.java"),
                    Paths.get(appFolder + "/HomiRestApplication.java"));

            File appFolderDelFile = new File(appFolderDel);
            appFolderDelFile.delete();

            //3.3 - create test folder for application
            String appFolderTest = recipe.getApp().getArtifactId() + "/application/src/test/java/" +
                    recipe.getApp().getPackageName().replace(".", "/");

            File appFolderFileTest = new File(appFolderTest);
            appFolderFileTest.mkdirs();

            String appFolderDelTest = recipe.getApp().getArtifactId() + "/application/src/test/java/" +
                    "com/homihq/app";

            Files.move(Paths.get(appFolderDelTest + "/HomiRestApplicationTests.java"),
                    Paths.get(appFolderTest + "/HomiRestApplicationTests.java"));

            File appFolderDelFileTest = new File(appFolderDelTest);
            appFolderDelFileTest.delete();
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to update application module folders.");
        }
    }
}
