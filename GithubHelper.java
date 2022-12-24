import java.io.File;

public class GithubHelper {

    public void init(String folder) {
        File file = new File(folder + "/.github/workflows");
        file.mkdirs();
    }
}
