package codegen;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;

public class SimpleReverseEngineeringStrategy extends DelegatingReverseEngineeringStrategy {
    public SimpleReverseEngineeringStrategy(ReverseEngineeringStrategy delegate) {
        super(delegate);
    }

    @Override
    public String tableToClassName(TableIdentifier tableIdentifier) {
        return super.tableToClassName(new TableIdentifier(
                tableIdentifier.getCatalog(),
                tableIdentifier.getSchema(),
                tableIdentifier.getName().replace("t_","")));
    }

    @Override
    public boolean excludeTable(TableIdentifier ti) {
        return ti.getName().contains("flyway") || ti.getName().equals("t_verify_job_email") ? true : false;
    }


}
