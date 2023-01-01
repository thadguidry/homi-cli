import java.io.File;

public class RenameProjectTask {

    public void execute(Recipe recipe) {
        File file = new File("homi-starter-rest");
        System.out.println("File exists - " + file.exists());
        file.renameTo(new File(recipe.getApp().getArtifactId()));
    }
}
