import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


public class ConfigLoader {

    private Recipe recipe;
    private List<Map<String, String>> dbMap;

    public Recipe getRecipe() {
        return recipe;
    }

    public List<Map<String, String>> getDbMap() {
        return dbMap;
    }

    public ConfigLoader(String configFile) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        InputStream inputStream =
        this.getClass().getClassLoader().getResourceAsStream("db.yml");

        this.dbMap = mapper.readValue(inputStream, new TypeReference<List<Map<String, String>>>(){});

        recipe =
                mapper.readValue(new FileInputStream(new File(configFile)), Recipe.class);
    }


}
