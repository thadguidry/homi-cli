import java.io.File;

public class RenameProjectTask {

    public void execute(Recipe recipe) {
        File file = new File("spring-rest-jpa-starter");
        file.renameTo(new File(recipe.getApp().getArtifactId()));
    }
}
