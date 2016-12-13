package cn.xinhuo.entity;

import java.util.Date;

/**
 * Pre entity. @author MyEclipse Persistence Tools
 */

public class Pre implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 1702040951719346619L;
    private Integer id;
    private String dianmianId;
    private String dmmc;
    private Integer saasid;
    private String saasmc;
    private Date shijian;
    private String man;
    private String phone;
    private Integer money;
    // Constructors

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    /** default constructor */
    public Pre() {
    }

    /** full constructor */
    public Pre(String dianmianId, String dmmc, Integer saasid, String saasmc, Date shijian, String man, String phone,
            Integer money) {
        this.dianmianId = dianmianId;
        this.dmmc = dmmc;
        this.saasid = saasid;
        this.saasmc = saasmc;
        this.shijian = shijian;
        this.man = man;
        this.phone = phone;
        this.money = money;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDianmianId() {
        return this.dianmianId;
    }

    public void setDianmianId(String dianmianId) {
        this.dianmianId = dianmianId;
    }

    public String getDmmc() {
        return this.dmmc;
    }

    public void setDmmc(String dmmc) {
        this.dmmc = dmmc;
    }

    public Integer getSaasid() {
        return this.saasid;
    }

    public void setSaasid(Integer saasid) {
        this.saasid = saasid;
    }

    public String getSaasmc() {
        return this.saasmc;
    }

    public void setSaasmc(String saasmc) {
        this.saasmc = saasmc;
    }

    public Date getShijian() {
        return this.shijian;
    }

    public void setShijian(Date shijian) {
        this.shijian = shijian;
    }

    public String getMan() {
        return this.man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}