package ru.croc.javaschool.homework6.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
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
     * Сериалиазция объекта в xml.
     *
     * @param obj объект
     * @return xml
     */
    public String toXml(Object obj) throws JsonProcessingException {
        return getMapper().writeValueAsString(obj);
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
            //xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
        xmlMapper.setDefaultUseWrapper(false);
        return xmlMapper;
    }
}
