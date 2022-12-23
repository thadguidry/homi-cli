import kong.unirest.Unirest;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Deprecated
@CommandLine.Command(name = "addRoutes", mixinStandardHelpOptions = true, version = "HomiCli 0.2",
        description = "Add Routes to a gateway using CLI.")
public class AddRoutes implements Callable<Integer> {

    @Option(names = { "--gatewayKey" } , description = "Gateway API Key", required = true)
    private String gatewayKey;

    @Option(names = { "--orgId" } , description = "Organization Id", required = true)
    private String orgId;

    @Option(names = { "--routeFile" } , description = "Route File", required = true)
    private String routeFile;

    @Option(names = { "--platformUrl" } , description = "Platform URL", required = true)
    private String platformUrl;

    @Override
    public Integer call() throws Exception {
        System.out.println("addRoutes");

        Unirest.post(platformUrl)
                .header("X-GW-KEY", gatewayKey)
                .header("X-ORG_ID", orgId)
                .field("file", new File(routeFile))
                .asEmpty();


        return 0;
    }


}

