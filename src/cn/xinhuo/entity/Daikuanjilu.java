package cn.xinhuo.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Daikuanjilu entity. @author MyEclipse Persistence Tools
 */

public class Daikuanjilu implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 1253646607226448303L;
    private Integer id;
    private Integer zhuangtai;
    private Date daikuanshijian;
    private String jine;
    private Integer yonghuId;
    private String fenqi;
    private String mendianId;
    private String yonghumingchen;
    private String dianmianmingchen;
    private String saasmc;

    // Constructors

    public String getSaasmc() {
        return saasmc;
    }

    public void setSaasmc(String saasmc) {
        this.saasmc = saasmc;
    }

    /** default constructor */
    public Daikuanjilu() {
    }

    /** full constructor */
    public Daikuanjilu(Integer zhuangtai, Date daikuanshijian, String jine, Integer yonghuId, String fenqi,
            String mendianId, String yonghumingchen, String dianmianmingchen) {
        this.zhuangtai = zhuangtai;
        this.daikuanshijian = daikuanshijian;
        this.jine = jine;
        this.yonghuId = yonghuId;
        this.fenqi = fenqi;
        this.mendianId = mendianId;
        this.yonghumingchen = yonghumingchen;
        this.dianmianmingchen = dianmianmingchen;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZhuangtai() {
        return this.zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public Date getDaikuanshijian() {
        return this.daikuanshijian;
    }

    public String getDaikuanshijian1() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(this.daikuanshijian);
    }

    public void setDaikuanshijian(Date daikuanshijian) {
        this.daikuanshijian = daikuanshijian;
    }

    public String getJine() {
        return this.jine;
    }

    public void setJine(String jine) {
        this.jine = jine;
    }

    public Integer getYonghuId() {
        return this.yonghuId;
    }

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }

    public String getFenqi() {
        return this.fenqi;
    }

    public void setFenqi(String fenqi) {
        this.fenqi = fenqi;
    }

    public String getMendianId() {
        return this.mendianId;
    }

    public void setMendianId(String mendianId) {
        this.mendianId = mendianId;
    }

    public String getYonghumingchen() {
        return this.yonghumingchen;
    }

    public void setYonghumingchen(String yonghumingchen) {
        this.yonghumingchen = yonghumingchen;
    }

    public String getDianmianmingchen() {
        return this.dianmianmingchen;
    }

    public void setDianmianmingchen(String dianmianmingchen) {
        this.dianmianmingchen = dianmianmingchen;
    }

}