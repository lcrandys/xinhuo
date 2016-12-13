package cn.xinhuo.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Fangwenjilu entity. @author MyEclipse Persistence Tools
 */

public class Fangwenjilu implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = -3184059071234533640L;
    private Integer id;
    private String dianmianId;
    private Date shijian;
    private String dianmianmingchen;
    private String saaSmingchen;

    // Constructors

    /** default constructor */
    public Fangwenjilu() {
    }

    /** full constructor */
    public Fangwenjilu(String dianmianId, Date shijian, String dianmianmingchen, String saaSmingchen) {
        this.dianmianId = dianmianId;
        this.shijian = shijian;
        this.dianmianmingchen = dianmianmingchen;
        this.saaSmingchen = saaSmingchen;
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

    public Date getShijian() {
        return this.shijian;
    }

    public String getShijian1() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(this.shijian);
    }

    public void setShijian(Date shijian) {
        this.shijian = shijian;
    }

    public String getDianmianmingchen() {
        return this.dianmianmingchen;
    }

    public void setDianmianmingchen(String dianmianmingchen) {
        this.dianmianmingchen = dianmianmingchen;
    }

    public String getSaaSmingchen() {
        return this.saaSmingchen;
    }

    public void setSaaSmingchen(String saaSmingchen) {
        this.saaSmingchen = saaSmingchen;
    }

}