import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class GenerateMainClassTask {

    public void execute(Recipe recipe) {
        try {
            String appFolder = recipe.getApp().getArtifactId() + "/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/");
            String javaClassName = appFolder + "/HomiRestApiApplication.java";
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            //GenerateMainClassTask.class.getClassLoader().getResourceAsStream("templates/sbmain.ftl");
            //cfg.setDirectoryForTemplateLoading(new File("META-INF/resources/"));
            cfg.setTemplateLoader(new ClassTemplateLoader(getClass(), "/"));


            Map<String, Object> data = new HashMap<String, Object>();
            data.put("packageName", recipe.getApp().getPackageName());
            Template template = cfg.getTemplate("sbmain.ftl");
            Writer fileWriter = new FileWriter(new File(javaClassName));
            template.process(data, fileWriter);
            fileWriter.close();

            String testFolder = recipe.getApp().getArtifactId() + "/src/test/java/" +
                    recipe.getApp().getPackageName().replace(".", "/");
            String testClassName = testFolder + "/HomiRestApiApplicationTest.java";

            template = cfg.getTemplate("sbmaintest.ftl");
            fileWriter = new FileWriter(new File(testClassName));
            template.process(data, fileWriter);
            fileWriter.close();

        }
        catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load templates or write output.");
        }
    }
}
