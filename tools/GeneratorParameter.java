/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**s
 *
 * @author darryl
 */
public class GeneratorParameter {
    
    private String destDir = "output";
    private String hibernatePropertiesFilePath = "./hibernate.properties";
    private String hibernateRevengXmlFilePath;
    private String generatedSourcesPackageName = "entity.generated";
    private String customReverseEngineeringStrategyClassName;

    public GeneratorParameter() {
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public void setHibernatePropertiesFilePath(String hibernatePropertiesFilePath) {
        this.hibernatePropertiesFilePath = hibernatePropertiesFilePath;
    }

    public void setHibernateRevengXmlFilePath(String hibernateRevengXmlFilePath) {
        this.hibernateRevengXmlFilePath = hibernateRevengXmlFilePath;
    }

    public void setGeneratedSourcesPackageName(String generatedSourcesPackageName) {
        this.generatedSourcesPackageName = generatedSourcesPackageName;
    }

    public void setCustomReverseEngineeringStrategyClassName(String customReverseEngineeringStrategyClassName) {
        this.customReverseEngineeringStrategyClassName = customReverseEngineeringStrategyClassName;
    }

    public String getCustomReverseEngineeringStrategyClassName() {
        return customReverseEngineeringStrategyClassName;
    }

    public String getGeneratedSourcesPackageName() {
        return generatedSourcesPackageName;
    }

    public String getDestDir() {
        return destDir;
    }

    public String getHibernatePropertiesFilePath() {
        return hibernatePropertiesFilePath;
    }

    public String getHibernateRevengXmlFilePath() {
        return hibernateRevengXmlFilePath;
    }

    @Override
    public String toString() {
        return "GeneratorParameter{" + "destDir=" + destDir + ", hibernatePropertiesFilePath=" + hibernatePropertiesFilePath + ", hibernateRevengXmlFilePath=" + hibernateRevengXmlFilePath + ", generatedSourcesPackageName=" + generatedSourcesPackageName + ", customReverseEngineeringStrategyClassName=" + customReverseEngineeringStrategyClassName + '}';
    }
    
}
