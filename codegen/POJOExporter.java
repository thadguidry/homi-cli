
package codegen;

public class POJOExporter extends GenericExporter {

	private static final String POJO_JAVACLASS_FTL = "pojo/Pojo.ftl";

	protected void init() {
		setTemplateName(POJO_JAVACLASS_FTL);
    	setFilePattern("{package-name}/{class-name}.java");    	
	}

	public POJOExporter() {
		init();		
	}
    
	public String getName() {
		return "jpagenerator";
	}
	
	protected void setupContext() {
		getProperties().put("ejb3", "true");
		getProperties().put("jdk5", "true");

		super.setupContext();
	}
}
