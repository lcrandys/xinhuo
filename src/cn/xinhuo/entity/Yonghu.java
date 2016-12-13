package cn.xinhuo.entity;

/**
 * Yonghu entity. @author MyEclipse Persistence Tools
 */

public class Yonghu implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 1423642506391336751L;
    /**
     * 
     */
    private Integer id;
    private Integer saaSId;
    private String xingming;
    private String shoujihao;
    private String shengfenzhenghao;
    private String hunyinzhuangkuang;
    private String xueli;
    private String juzhudizhi;
    private String yingyedizhi;

    // Constructors

    /** default constructor */
    public Yonghu() {
    }

    /** full constructor */
    public Yonghu(Integer saaSId, String xingming, String shoujihao, String shengfenzhenghao, String hunyinzhuangkuang,
            String xueli, String juzhudizhi, String yingyedizhi) {
        this.saaSId = saaSId;
        this.xingming = xingming;
        this.shoujihao = shoujihao;
        this.shengfenzhenghao = shengfenzhenghao;
        this.hunyinzhuangkuang = hunyinzhuangkuang;
        this.xueli = xueli;
        this.juzhudizhi = juzhudizhi;
        this.yingyedizhi = yingyedizhi;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSaaSId() {
        return this.saaSId;
    }

    public void setSaaSId(Integer saaSId) {
        this.saaSId = saaSId;
    }

    public String getXingming() {
        return this.xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getShoujihao() {
        return this.shoujihao;
    }

    public void setShoujihao(String shoujihao) {
        this.shoujihao = shoujihao;
    }

    public String getShengfenzhenghao() {
        return this.shengfenzhenghao;
    }

    public void setShengfenzhenghao(String shengfenzhenghao) {
        this.shengfenzhenghao = shengfenzhenghao;
    }

    public String getHunyinzhuangkuang() {
        return this.hunyinzhuangkuang;
    }

    public void setHunyinzhuangkuang(String hunyinzhuangkuang) {
        this.hunyinzhuangkuang = hunyinzhuangkuang;
    }

    public String getXueli() {
        return this.xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public String getJuzhudizhi() {
        return this.juzhudizhi;
    }

    public void setJuzhudizhi(String juzhudizhi) {
        this.juzhudizhi = juzhudizhi;
    }

    public String getYingyedizhi() {
        return yingyedizhi;
    }

    public void setYingyedizhi(String yingyedizhi) {
        this.yingyedizhi = yingyedizhi;
    }
}