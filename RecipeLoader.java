import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RecipeLoader {

    public Recipe get(String configFile) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return
                mapper.readValue(new FileInputStream(new File(configFile)), Recipe.class);
    }
}
