package ru.job4j.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.bufferedfilereader.BufferedFileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Приложение парсит выходной файл из пункта 4 и выводит арифметическую сумму значений всех атрибутов
 * field в консоль.
 *
 */
public class ParseXSQT {

    private final static Logger LOGGER = LoggerFactory.getLogger(ParseXSQT.class);

    public Queue<String> parse(File file) {
        try {
            Queue<String> results = new LinkedList<>();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader parser = factory.createXMLStreamReader(BufferedFileReader.instanceWithLogging(file, "UTF-8", LOGGER));
            while (parser.hasNext()) {
                int event = parser.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (parser.getLocalName().equals("entry")) {
                        String href = parser.getAttributeValue(null, "href");
                       results.offer(href);
                    }
                }
            }
            return results;
        } catch (XMLStreamException e) {
            LOGGER.error(e.getMessage(), e);
        }
        throw new IllegalArgumentException("Mistake in ParseXSQT.parse, That's possible uncorrect file or XMLStreamException, look LOGGER");
    }
}
