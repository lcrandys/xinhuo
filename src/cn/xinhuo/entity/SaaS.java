package cn.xinhuo.entity;

/**
 * SaaS entity. @author MyEclipse Persistence Tools
 */

public class SaaS implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 8238733856091521843L;
    private Integer saaSId;
    private String liushuijiekou;
    private String mingchen;

    // Constructors

    /** default constructor */
    public SaaS() {
    }

    /** full constructor */
    public SaaS(String liushuijiekou, String mingchen) {
        this.liushuijiekou = liushuijiekou;
        this.mingchen = mingchen;
    }

    // Property accessors

    public Integer getSaaSId() {
        return this.saaSId;
    }

    public void setSaaSId(Integer saaSId) {
        this.saaSId = saaSId;
    }

    public String getLiushuijiekou() {
        return this.liushuijiekou;
    }

    public void setLiushuijiekou(String liushuijiekou) {
        this.liushuijiekou = liushuijiekou;
    }

    public String getMingchen() {
        return this.mingchen;
    }

    public void setMingchen(String mingchen) {
        this.mingchen = mingchen;
    }

}