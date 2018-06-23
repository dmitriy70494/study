package ru.job4j.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.bufferedfilereader.BufferedFileReader;

import java.io.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Преобразовать полученный файл из пункта 3 в файл другого XML формата через XSTL.
 * Этот процесс будет описываться классом - ConvertXSQT
 */
public class ConvertXSQT {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConvertXSQT.class);

    /**
     * читает файл source и преобразовывает его в файл dest за счет XSTL схемы scheme.
     * Исходный файл должен выглядеть следующим образом.
     * @param source файл с преобразуемыми данными в формате xml
     * @param dest в этот файл идет запись, расширение xml
     * @param scheme схема преобразования файла source в dest
     */
    public void convert(File source, File dest, File scheme) {
        try {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(
                        BufferedFileReader.instanceWithLogging(scheme, "UTF-8", LOGGER))
        );
            transformer.transform(new StreamSource(
                            BufferedFileReader.instanceWithLogging(source, "UTF-8", LOGGER)),
                    new StreamResult(new BufferedOutputStream(new FileOutputStream(dest)))
            );
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
