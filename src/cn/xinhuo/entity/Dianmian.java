package cn.xinhuo.entity;

/**
 * Dianmian entity. @author MyEclipse Persistence Tools
 */

public class Dianmian implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = -7125484954294985388L;
    private Integer id;
    private String danyueyuangonggongzi;
    private String danyueshuidianfei;
    private String danyuefangzufei;
    private String yingyezhizhao;
    private String yuexiaoshoue;
    private String fangzuhetong;
    private String kedanjia;
    private String nianxiaoshoue;
    private String mingchen;
    private Integer yonghuId;
    private Integer zuijinliushuishijian;
    private String mendianId;
    private Integer saaSId;
    private String saaSWaibuId;
    private String percentage;// 食材占比
    private String openYear;// 开业年
    private String openMonth;// 开业月

    // Constructors

    /** default constructor */
    public Dianmian() {
    }

    /** full constructor */
    public Dianmian(String danyueyuangonggongzi, String danyueshuidianfei, String danyuefangzufei, String yingyezhizhao,
            String yuexiaoshoue, String fangzuhetong, String kedanjia, String nianxiaoshoue, String mingchen,
            Integer yonghuId, Integer zuijinliushuishijian, String mendianId, Integer saaSId, String saaSWaibuId,
            String percentage) {
        this.danyueyuangonggongzi = danyueyuangonggongzi;
        this.danyueshuidianfei = danyueshuidianfei;
        this.danyuefangzufei = danyuefangzufei;
        this.yingyezhizhao = yingyezhizhao;
        this.yuexiaoshoue = yuexiaoshoue;
        this.fangzuhetong = fangzuhetong;
        this.kedanjia = kedanjia;
        this.nianxiaoshoue = nianxiaoshoue;
        this.mingchen = mingchen;
        this.yonghuId = yonghuId;
        this.zuijinliushuishijian = zuijinliushuishijian;
        this.mendianId = mendianId;
        this.saaSId = saaSId;
        this.saaSWaibuId = saaSWaibuId;
        this.percentage = percentage;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDanyueyuangonggongzi() {
        return this.danyueyuangonggongzi;
    }

    public void setDanyueyuangonggongzi(String danyueyuangonggongzi) {
        this.danyueyuangonggongzi = danyueyuangonggongzi;
    }

    public String getDanyueshuidianfei() {
        return this.danyueshuidianfei;
    }

    public void setDanyueshuidianfei(String danyueshuidianfei) {
        this.danyueshuidianfei = danyueshuidianfei;
    }

    public String getDanyuefangzufei() {
        return this.danyuefangzufei;
    }

    public void setDanyuefangzufei(String danyuefangzufei) {
        this.danyuefangzufei = danyuefangzufei;
    }

    public String getYingyezhizhao() {
        return this.yingyezhizhao;
    }

    public void setYingyezhizhao(String yingyezhizhao) {
        this.yingyezhizhao = yingyezhizhao;
    }

    public String getYuexiaoshoue() {
        return this.yuexiaoshoue;
    }

    public void setYuexiaoshoue(String yuexiaoshoue) {
        this.yuexiaoshoue = yuexiaoshoue;
    }

    public String getFangzuhetong() {
        return this.fangzuhetong;
    }

    public void setFangzuhetong(String fangzuhetong) {
        this.fangzuhetong = fangzuhetong;
    }

    public String getKedanjia() {
        return this.kedanjia;
    }

    public void setKedanjia(String kedanjia) {
        this.kedanjia = kedanjia;
    }

    public String getNianxiaoshoue() {
        return this.nianxiaoshoue;
    }

    public void setNianxiaoshoue(String nianxiaoshoue) {
        this.nianxiaoshoue = nianxiaoshoue;
    }

    public String getMingchen() {
        return this.mingchen;
    }

    public void setMingchen(String mingchen) {
        this.mingchen = mingchen;
    }

    public Integer getYonghuId() {
        return this.yonghuId;
    }

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }

    public Integer getZuijinliushuishijian() {
        return this.zuijinliushuishijian;
    }

    public void setZuijinliushuishijian(Integer zuijinliushuishijian) {
        this.zuijinliushuishijian = zuijinliushuishijian;
    }

    public String getMendianId() {
        return this.mendianId;
    }

    public void setMendianId(String mendianId) {
        this.mendianId = mendianId;
    }

    public Integer getSaaSId() {
        return this.saaSId;
    }

    public void setSaaSId(Integer saaSId) {
        this.saaSId = saaSId;
    }

    public String getSaaSWaibuId() {
        return this.saaSWaibuId;
    }

    public void setSaaSWaibuId(String saaSWaibuId) {
        this.saaSWaibuId = saaSWaibuId;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getOpenYear() {
        return openYear;
    }

    public void setOpenYear(String openYear) {
        this.openYear = openYear;
    }

    public String getOpenMonth() {
        return openMonth;
    }

    public void setOpenMonth(String openMonth) {
        this.openMonth = openMonth;
    }

}