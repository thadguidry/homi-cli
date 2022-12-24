import java.io.File;

public class GithubHelper {

    public void init(String folder) {
        String gitFolder = folder + "/.github/workflows";
        System.out.println("gitFolder : " + gitFolder);
        File file = new File(gitFolder);
        file.mkdirs();
    }
}
