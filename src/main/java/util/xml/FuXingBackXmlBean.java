package util.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 复星的投保接口
 * @author dzd
 */
@Data
@XmlRootElement(name = "EbizRes")
@XmlAccessorType(XmlAccessType.FIELD)
public class FuXingBackXmlBean {

    @XmlElement(name = "ResHead")
    private FuXingBackXmlBeanHead resHead;


    @XmlElement(name = "ResultInfo")
    private FuXingBackXmlBeanInfo resultInfo;
}