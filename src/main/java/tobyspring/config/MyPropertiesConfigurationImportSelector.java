package tobyspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;
import tobyspring.config.autoconfig.EnableMyPropertiesConfiguration;

public class MyPropertiesConfigurationImportSelector implements DeferredImportSelector {
    @Override // import 한 class의 이름을 return
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attr = importingClassMetadata.getAllAnnotationAttributes(EnableMyPropertiesConfiguration.class.getName());
        Class propertyClass = (Class) attr.getFirst("value"); // get만 하면 List로 받아온다.
        return new String[] { propertyClass.getName() };
    }
}
