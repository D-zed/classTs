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
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FuXingBackXmlBeanHead {

    @XmlElement(name = "SerialNo")
    private String serialNo;

    @XmlElement(name = "TransType")
    private String transType;

    @XmlElement(name = "SourceCode")
    private String sourceCode;

    @XmlElement(name = "ResultCode")
    private String resultCode;

    @XmlElement(name = "ResultDesc")
    private String resultDesc;

    @XmlElement(name = "BusinessNo")
    private String businessNo;

}
