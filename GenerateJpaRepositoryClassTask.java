import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.hibernate.boot.Metadata;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

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

            String serviceFolder = recipe.getApp().getArtifactId() + "/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/service";

            new File(serviceFolder).mkdirs();

            String dtoFolder = recipe.getApp().getArtifactId() + "/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/dto";

            new File(dtoFolder).mkdirs();

            String controllerFolder = recipe.getApp().getArtifactId() + "/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/controller";

            new File(controllerFolder).mkdirs();

            List<PersistentClass> pojos = new ArrayList<>(
                    metadata.getEntityBindings());
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("exceptionPackage", recipe.getApp().getPackageName() + ".exception");
            data.put("controllerPackage", recipe.getApp().getPackageName() + ".controller");

            for(PersistentClass pojo : pojos) {
                data.put("servicePackage", recipe.getApp().getPackageName() + ".service");
                data.put("domainPackage", recipe.getApp().getPackageName() + ".domain");
                data.put("dtoPackage", recipe.getApp().getPackageName() + ".dto");
                data.put("entity", pojo.getJpaEntityName());
                data.put("pkg", recipe.getApp().getPackageName() + ".repository");
                data.put("keyJavaType", pojo.getIdentifier().getType().getReturnedClass().getSimpleName());

                Template template = cfg.getTemplate("RestRepository.ftl");
                Writer fileWriter = new FileWriter(new File(appFolder + "/" + pojo.getJpaEntityName() +"Repository.java"));
                template.process(data, fileWriter);
                fileWriter.close();

                writeDto(cfg, pojo, dtoFolder, data);

                Template serviceTemplate = cfg.getTemplate("Service.ftl");
                Writer serviceFileWriter = new FileWriter(new File(serviceFolder + "/" + pojo.getJpaEntityName() +"Service.java"));
                serviceTemplate.process(data, serviceFileWriter);
                serviceFileWriter.close();


                Template controllerTemplate = cfg.getTemplate("Controller.ftl");
                Writer controllerFileWriter = new FileWriter(new File(controllerFolder + "/" + pojo.getJpaEntityName() +"Controller.java"));
                controllerTemplate.process(data, controllerFileWriter);
                controllerFileWriter.close();



            }



            String exceptionFolder = recipe.getApp().getArtifactId() + "/src/main/java/" +
                    recipe.getApp().getPackageName().replace(".", "/") + "/exception";

            new File(exceptionFolder).mkdirs();

            Template exceptionTemplate = cfg.getTemplate("/error/nodatafound.ftl");
            Writer exceptionFileWriter = new FileWriter(new File(exceptionFolder + "/" + "NoDataFoundProblem.java"));
            exceptionTemplate.process(data, exceptionFileWriter);
            exceptionFileWriter.close();

        }
        catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load templates or write output.");
        }
    }

    private void writeDto(Configuration cfg, PersistentClass pojo, String dtoFolder, Map<String, Object> data) throws Exception{
        Iterator it =
        pojo.getPropertyIterator();

        List<DtoItem> items = new ArrayList<>();

        //TODO handle composite


        items.add(new DtoItem(pojo.getIdentifierProperty().getType().getReturnedClass().getSimpleName(), pojo.getIdentifierProperty().getName()));

        while(it.hasNext()) {
            Property property = (Property) it.next();

            if(!property.getType().isAssociationType())
            items.add(new DtoItem(property.getType().getReturnedClass().getSimpleName(), property.getName()));
        }

        data.put("items", items);

        Template serviceTemplate = cfg.getTemplate("Dto.ftl");
        Writer serviceFileWriter = new FileWriter(new File(dtoFolder + "/" + pojo.getJpaEntityName() +"Dto.java"));
        serviceTemplate.process(data, serviceFileWriter);
        serviceFileWriter.close();
    }


}
