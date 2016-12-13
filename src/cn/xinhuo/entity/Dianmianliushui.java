package cn.xinhuo.entity;

/**
 * Dianmianliushui entity. @author MyEclipse Persistence Tools
 */

public class Dianmianliushui implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6936910122284882817L;
    // Fields

private String id;

    public String getId() {
        return id;
}

public void setId(String id) {
        this.id = id;
}

    private String dianmianId;

    public String getDianmianId() {
        return dianmianId;
    }

    public void setDianmianId(String dianmianId) {
        this.dianmianId = dianmianId;
    }

    private String riqi;

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }

    private String yinyedanshu;
    private String keliurenshu;
    private String yinyejine;
    private String dianmianmingchen;

    // Constructors

    /** default constructor */
    public Dianmianliushui() {
    }

    /** full constructor */
    public Dianmianliushui(String dianmianId, String riqi, String yinyedanshu, String keliurenshu, String yinyejine,
            String dianmianmingchen) {
        this.dianmianId = dianmianId;
        this.riqi = riqi;
        this.yinyedanshu = yinyedanshu;
        this.keliurenshu = keliurenshu;
        this.yinyejine = yinyejine;
        this.dianmianmingchen = dianmianmingchen;
    }






    public String getYinyedanshu() {
        return this.yinyedanshu;
    }

    public void setYinyedanshu(String yinyedanshu) {
        this.yinyedanshu = yinyedanshu;
    }

    public String getKeliurenshu() {
        return this.keliurenshu;
    }

    public void setKeliurenshu(String keliurenshu) {
        this.keliurenshu = keliurenshu;
    }

    public String getYinyejine() {
        return this.yinyejine;
    }

    public void setYinyejine(String yinyejine) {
        this.yinyejine = yinyejine;
    }

    public String getDianmianmingchen() {
        return this.dianmianmingchen;
    }

    public void setDianmianmingchen(String dianmianmingchen) {
        this.dianmianmingchen = dianmianmingchen;
    }

}