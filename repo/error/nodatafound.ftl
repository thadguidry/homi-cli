package ${exceptionPackage};

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import java.net.URI;

public class NoDataFoundProblem extends AbstractThrowableProblem {

    private static final URI TYPE = URI.create("https://${exceptionPackage}/not-found");

    public NoDataFoundProblem() {
        super(
            TYPE,
            "Not found",
            Status.NOT_FOUND,
            "No data found for given criteria or key.");
    }

}
