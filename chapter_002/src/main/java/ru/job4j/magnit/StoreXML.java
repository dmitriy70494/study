package ru.job4j.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Генерация XML из данных базы. Описывается класс StoreXML
 */
public class StoreXML {

    private final static Logger LOGGER = LoggerFactory.getLogger(StoreXML.class);

    private final File target;

    /**
     * target - Файл куда будет сохраняться данные.
     * @param target
     */
    StoreXML(File target) {
        this.target = target;
    }

    /**
     * сохраняет данные из list в файл target.
     * Данные нужно сохранить в виде XML.
     *
     * @param list
     */
    public void save(List<Field> list) {
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller  = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Entries(list), target);
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
