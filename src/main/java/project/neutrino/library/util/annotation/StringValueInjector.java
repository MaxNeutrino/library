package project.neutrino.library.util.annotation;

import project.neutrino.library.util.XmlReader;

import java.io.InputStream;
import java.lang.reflect.Field;

public class StringValueInjector {

    private InputStream xmlFile;

    private XmlReader xmlReader;

    public StringValueInjector(InputStream xmlFile) {
        this.xmlFile = xmlFile;
    }

    public void injectStringValues(Object instance) throws Exception {
        if (xmlReader == null)
            xmlReader = new XmlReader(xmlFile);

        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {

            if (field.isAnnotationPresent(FromXml.class)) {
                field.setAccessible(true);
                String name = field.getName();

                String value = xmlReader.readUniqueTag(name);
                value.replace("\t", "");
                field.set(instance, value);
            }
        }
    }
}
