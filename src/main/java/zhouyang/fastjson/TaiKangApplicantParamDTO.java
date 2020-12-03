package zhouyang.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//在此将sex排除不进行序列化
@Data
@JSONType(ignores ={"sex"})
public class TaiKangApplicantParamDTO {

    @JSONField(ordinal = 0)
    private String type;

    @JSONField(ordinal = 1)
    private Integer id;

    @JSONField(ordinal = 2)
    private Boolean success;

    private String name;

    private String certi_type;

    private String certi_no;

    private String birthday;

    private String sex;

    private String phone;

    private String email;

    private String postCode;

    @JSONField(ordinal = 3)
    private String address;


    //这个无法全局的
    //@JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime localDateTime;

    private Integer age;

    private Date currentDate;

    private List<TaiKangKindParamDTO> taiKangKindParamDTOS;

    public TaiKangApplicantParamDTO(String type, String name, String certi_type, String certi_no, String birthday, String sex, String phone, String email, String postCode, String address, LocalDateTime localDateTime, Integer age) {
        this.type = type;
        this.name = name;
        this.certi_type = certi_type;
        this.certi_no = certi_no;
        this.birthday = birthday;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.postCode = postCode;
        this.address = address;
        this.localDateTime = localDateTime;
        this.age = age;
    }

    public TaiKangApplicantParamDTO() {
    }

}
