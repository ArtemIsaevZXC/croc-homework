package ru.croc.javaschool.homework7.xmltools;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;

/**
 * Сериализация и десериализация в/из xml.
 *
 * @author Alexander Golovin
 */
public class XmlConverter {
    /**
     * Маппер.
     */
    private XmlMapper xmlMapper;

    /**
     * Десериализация xml в объект.
     *
     * @param xml  xml
     * @param type тип объекта
     * @param <T>  ожидаемый тип объекта
     * @return объект
     */
    public <T> T fromXml(String xml, Class<T> type) throws IOException {
        return getMapper().readValue(xml, type);
    }

    /**
     * Инициализация маппера.
     *
     * @return маппер.
     */
    private XmlMapper getMapper() {
        if (xmlMapper == null) {
            xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JaxbAnnotationModule());
        }
        xmlMapper.setDefaultUseWrapper(false);
        return xmlMapper;
    }
}
