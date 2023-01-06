import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;

public class SimpleReverseEngineeringStrategy extends DelegatingReverseEngineeringStrategy {
    public SimpleReverseEngineeringStrategy(ReverseEngineeringStrategy delegate) {
        super(delegate);
    }


}
