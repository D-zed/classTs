package util.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 复星的投保接口
 * @author dzd
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FuXingBackXmlBeanInfo {

    @XmlElement(name = "ProposalNo")
    private String proposalNo;

    @XmlElement(name = "AppDate")
    private String appDate;

    @XmlElement(name = "ContractNo")
    private String contractNo;

    @XmlElement(name = "ReqUrl")
    private String reqUrl;

    @XmlElement(name = "GradedHealthNoticeSerialNo")
    private String gradedHealthNoticeSerialNo;

    @XmlElement(name = "UnderwritingResults")
    private String underwritingResults;

    @XmlElementWrapper(name ="BNFList")
    @XmlElement(name = "BNFinfo")
    private List<FuXingApplyNewContBNFinfoParamDto> shouyiren;

}
