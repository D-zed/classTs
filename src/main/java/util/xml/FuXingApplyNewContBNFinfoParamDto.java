package util.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * 复星的投保接口
 * @author dzd
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FuXingApplyNewContBNFinfoParamDto {


    @XmlElement(name = "RelationToInsured")
    private String relationToInsured;

    @XmlElement(name = "Name")
    private String name;


    @XmlElement(name = "Gender")
    private String gender;


    @XmlElement(name = "Birthday")
    private String birthday;


    @XmlElement(name = "CardType")
    private String cardType;

    @XmlElement(name = "CardNo")
    private String cardNo;

    @XmlElement(name = "ValStartDate")
    private String valStartDate;

    @XmlElement(name = "ValEndDate")
    private String valEndDate;

    @XmlElement(name = "Mobile")
    private String mobile;


    @XmlElement(name = "BNFRate")
    private BigDecimal bNFRate;

    @XmlElement(name = "BNFOrder")
    private Integer bNFOrder;

    @XmlElement(name = "BelongToInsured")
    private Integer belongToInsured;

}