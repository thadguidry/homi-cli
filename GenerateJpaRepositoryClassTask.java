import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.hibernate.boot.Metadata;
import org.hibernate.mapping.PersistentClass;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateJpaRepositoryClassTask {
    private final Metadata metadata;

    public GenerateJpaRepositoryClassTask(Metadata metadata){
        this.metadata = metadata;
    }

    public void execute(Recipe recipe) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setTemplateLoader(new ClassTemplateLoader(getClass(), "/repo"));
            String appFolder = recipe.getApp().getArtifactId() + "/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/repository";

            new File(appFolder).mkdirs();



            List<PersistentClass> pojos = new ArrayList<>(
                    metadata.getEntityBindings());
            Map<String, Object> data = new HashMap<String, Object>();
            for(PersistentClass pojo : pojos) {
                System.out.println("pojo - {}" +  pojo);
                System.out.println("IDENTIFIER - {}" +  pojo.getIdentifier().getType().getReturnedClass().getSimpleName());
                System.out.println("IDENTIFIER - {}" +  pojo.getIdentifierProperty());

                data.put("domainPackage", recipe.getApp().getPackageName() + ".domain");
                data.put("entity", pojo.getJpaEntityName());
                data.put("pkg", recipe.getApp().getPackageName() + ".repository");
                data.put("keyJavaType", pojo.getIdentifier().getType().getReturnedClass().getSimpleName());

                Template template = cfg.getTemplate("RestRepository.ftl");
                Writer fileWriter = new FileWriter(new File(appFolder + "/" + pojo.getJpaEntityName() +"Repository.java"));
                template.process(data, fileWriter);
                fileWriter.close();
            }



        }
        catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load templates or write output.");
        }
    }
}
