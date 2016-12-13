package cn.xinhuo.entity;

/**
 * Zichanxinxi entity. @author MyEclipse Persistence Tools
 */

public class Zichanxinxi implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 248265367014610163L;
    private Integer id;
    private String fang;
    private String che;
    private String shangpu;
    private String xinyongqingkuang;
    private Integer yonghuId;

    // Constructors

    /** default constructor */
    public Zichanxinxi() {
    }

    /** full constructor */
    public Zichanxinxi(String fang, String che, String shangpu, String xinyongqingkuang, Integer yonghuId) {
        this.fang = fang;
        this.che = che;
        this.shangpu = shangpu;
        this.xinyongqingkuang = xinyongqingkuang;
        this.yonghuId = yonghuId;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFang() {
        return this.fang;
    }

    public void setFang(String fang) {
        this.fang = fang;
    }

    public String getChe() {
        return this.che;
    }

    public void setChe(String che) {
        this.che = che;
    }

    public String getShangpu() {
        return this.shangpu;
    }

    public void setShangpu(String shangpu) {
        this.shangpu = shangpu;
    }

    public String getXinyongqingkuang() {
        return this.xinyongqingkuang;
    }

    public void setXinyongqingkuang(String xinyongqingkuang) {
        this.xinyongqingkuang = xinyongqingkuang;
    }

    public Integer getYonghuId() {
        return this.yonghuId;
    }

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }

}