
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.Callable;



@CommandLine.Command(name = "mergeSpec", mixinStandardHelpOptions = true, version = "HomiCli 0.1",
        description = "Merges two DigitalOcean App Specification file.")
public class MergeYaml implements Callable<Integer> {

    @CommandLine.Option(names = { "--source" } , description = "Source file path")
    private String source;

    @CommandLine.Option(names = { "--override" } , description = "Override file path")
    private String override;

    @Override
    public Integer call() throws Exception {

        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setPrettyFlow(true);
        //dumperOptions.setCanonical(true);
        dumperOptions.setTimeZone(TimeZone.getTimeZone("UTC"));
        Yaml snakeYaml = new Yaml(dumperOptions);


        FileInputStream sourceFile = new FileInputStream(source);
        Map<String,Object> sourceAppSpec = snakeYaml.load(sourceFile);

        System.out.println(sourceAppSpec);

        System.out.println("===============================================");
        FileInputStream overrideFile = new FileInputStream(override);
        Map<String,Object> overrideAppSpec = snakeYaml.load(overrideFile);

        System.out.println(overrideAppSpec);

        Map<String,Object> mergedSpec = new LinkedHashMap<>();

        //merge envs
        mergeEnvs(mergedSpec, sourceAppSpec, overrideAppSpec);

        System.out.println("***************************************");
        System.out.println(snakeYaml.dump(mergedSpec));

        return 0;
    }

    private void mergeEnvs(Map<String, Object> mergedSpec, Map<String, Object> sourceAppSpec, Map<String, Object> overrideAppSpec) {
        List<Map<String,String>> sourceEnvs = (List<Map<String, String>>) sourceAppSpec.get("envs");
        System.out.println("sourceEnvs = " + sourceEnvs);

        List<Map<String,String>> overrideEnvs = (List<Map<String, String>>) overrideAppSpec.get("envs");
        System.out.println("overrideEnvs = " + overrideEnvs);

        if(Objects.nonNull(overrideEnvs) && !overrideEnvs.isEmpty()) {
            if(Objects.nonNull(sourceEnvs) && !sourceEnvs.isEmpty()) { //both are present

                for(Map<String, String> sourceMap : sourceEnvs) {
                    String key = sourceMap.get("key");
                    String scope = sourceMap.get("scope");


                }

            }
            else{ //dump override
                mergedSpec.put("envs", overrideEnvs);
            }
        }
        else { //dump source if not empty
            if(Objects.nonNull(sourceEnvs) && !sourceEnvs.isEmpty()) {
                mergedSpec.put("envs", sourceEnvs);
            }
        }
    }


}
