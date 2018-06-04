package cn.kaixuan.data;

import java.util.Date;

/**
 * 人物模型，包含选定的人物属性信息
 */
public class Character {
    private Date birth; // 出生日期
    private String location;    // 位置
    private String org; // 组织机构
    private String occu;    // 职业
    private String email; // email地址
    private String tel; // 电话

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getOccu() {
        return occu;
    }

    public void setOccu(String occu) {
        this.occu = occu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
