package cn.xinhuo.interf.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import cn.xinhuo.entity.BaseBean;
import cn.xinhuo.entity.Daikuanjilu;
import cn.xinhuo.entity.Dianmian;
import cn.xinhuo.entity.Dianmianliushui;
import cn.xinhuo.entity.Fangwenjilu;
import cn.xinhuo.entity.Item;
import cn.xinhuo.entity.Page;
import cn.xinhuo.entity.Pre;
import cn.xinhuo.entity.SaaS;
import cn.xinhuo.entity.Yonghu;
import cn.xinhuo.entity.Zichanxinxi;
import cn.xinhuo.entity.liushui;
import cn.xinhuo.interf.Houtai;
import cn.xinhuo.interf.dao.HoutaiDao;
import cn.xinhuo.service.YingyeClient;

public class HoutaiImpl implements Houtai {

    private HoutaiDao houtaidao;
    private Page pages = new Page();

    public Page getPages() {
        return pages;
    }

    public void setPages(Page pages) {
        this.pages = pages;
    }

    public HoutaiDao getHoutaidao() {
        return houtaidao;
    }

    public void setHoutaidao(HoutaiDao houtaidao) {
        this.houtaidao = houtaidao;
    }

    public List<Fangwenjilu> getFangwenjilulist(int pageNo) {
        List<Fangwenjilu> ls = new ArrayList<Fangwenjilu>();
        pages.setCurrentPage(pageNo);
        pages.setPageSize(Integer.parseInt(BaseBean.NUMBER_MAX));
        pages.setTotleCount(houtaidao.fwCount());
        ls = houtaidao.getFangwenjilulist(pages.getCurrentPage(), pages.getPageSize());
        return ls;
    }

    public int getFangwenjilucishu() {
        // TODO Auto-generated method stub
        return houtaidao.fwCount();
    }

    public List<Daikuanjilu> getDaikuanshenqinglist(int status, int pageNo, String dianmianid) {
        // TODO Auto-generated method stub
        return houtaidao.getDaikuanjilu(pageNo, status, dianmianid);
    }

    public List<Daikuanjilu> getDaikuanshenqinglist(int status, int pageNo) {
        // TODO Auto-generated method stub
        return houtaidao.getDaikuanjilu(pageNo, status);
    }

    public List<Dianmianliushui> getDianmianliushui(String dianmianId, int pageNo) {
        // TODO Auto-generated method stub
        return houtaidao.getDianmianliushui(pageNo, dianmianId);
    }

    public Yonghu getYonghu(int id) {
        // TODO Auto-generated method stub
        return houtaidao.getYonghu(id);
    }

    public Dianmian getDianmian(int id) {
        // TODO Auto-generated method stub
        return houtaidao.getDianmian(id);
    }

    public Zichanxinxi getZichan(int id) {
        // TODO Auto-generated method stub
        return houtaidao.getZichan(id);
    }

    public void SaaSdengji(String liushuijiekou, String mingcheng) {
        // TODO Auto-generated method stub
    }

    public List<SaaS> Saasliebiao() {
        // TODO Auto-generated method stub
        return null;
    }

    public Date getHuoqu(int dianmianId) {
        // TODO Auto-generated method stub
        return null;
    }

    // ������
    public void shenqingdaikuan(Yonghu yh, Dianmian dm, Zichanxinxi zc, Date shijian, String fenqi, String jine,
            String saasmc) {
        houtaidao.saveYonghu(yh);
        dm.setYonghuId(yh.getId());
        houtaidao.saveDianmian(dm);
        zc.setYonghuId(yh.getId());
        houtaidao.saveZichanxinxi(zc);
        Daikuanjilu dk = new Daikuanjilu();
        dk.setZhuangtai(1);
        dk.setDaikuanshijian(shijian);
        dk.setJine(jine);
        dk.setYonghuId(yh.getId());
        dk.setFenqi(fenqi);
        dk.setMendianId(dm.getMendianId());
        dk.setYonghumingchen(yh.getXingming());
        dk.setDianmianmingchen(dm.getMingchen());
        dk.setSaasmc(saasmc);
        houtaidao.saveDaikuanjilu(dk);
    }

    public void shenqingdaikuanpre(String mendianid, String mendianname, String man, String saasid, Date shijian,
            String fenqi, String jine) {
        Daikuanjilu dk = new Daikuanjilu();
        dk.setZhuangtai(9);
        dk.setDaikuanshijian(shijian);
        dk.setJine(jine);
        dk.setMendianId(saasid + mendianid);
        dk.setYonghumingchen(man);
        dk.setDianmianmingchen(mendianname);
        dk.setFenqi(fenqi);
        houtaidao.saveDaikuanjilu(dk);
    }

    // ������ʼ�¼
    public void jilufangwen(String dianmianId, String dmmc, int saasid, String saasmc, Date shijian) {
        // TODO Auto-generated method stub
        Fangwenjilu fwjl = new Fangwenjilu();
        fwjl.setDianmianId(dianmianId);
        fwjl.setDianmianmingchen(dmmc);
        fwjl.setShijian(shijian);
        fwjl.setSaaSmingchen(saasmc);
        houtaidao.saveJilufangwen(fwjl);
    }

    public int ifdaikuan(int saasId, String outId) {
        // TODO Auto-generated method stub
        String mendianid = saasId + outId;
        int a = houtaidao.getDaikuanjilu(mendianid);
        return a;
    }

    public void savetest(Yonghu yh) {
        // TODO Auto-generated method stub
        houtaidao.saveYonghu(yh);
    }

    public String updateStatus(int id, String mendianid, int status, String fee, int daikuanid) {
        // TODO Auto-generated method stub
        System.out.println(id + "a" + status + "a" + fee + "a" + daikuanid);
        String s = YingyeClient.getInstance().updateStatus(mendianid + "", status + "", Integer.parseInt(fee));
        if (s.equals("success")) {
            houtaidao.updateStatus(daikuanid, status);
            return s;
        } else {
            return s;
        }
    }

    public String getliushui(int preid) {
        List<Dianmianliushui> dls = new ArrayList();
        Pre p = houtaidao.getP(preid);
        String dianmianId = p.getDianmianId();
        Integer saasid = p.getSaasid();
        String dmmc = p.getDmmc();
        Integer money;
        if (p == null) {
            money = 0;
        }
        money = p.getMoney();
        if (money == null)
            money = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            ObjectMapper objectMapper = new ObjectMapper();
            String r = YingyeClient.getInstance().takeliushui(dianmianId, sdf.format(calendar.getTime()));
            // List<LinkedHashMap<String, Dianmianliushui>> list = objectMapper.readValue(r, List.class);
            liushui l = objectMapper.readValue(r, liushui.class);
            if (l == null) {
                System.out.println("拉取流水：dianmianId：" + dianmianId + ",数据为空");
                return "fail";
            }
            for (Item ii : l.getModel().getItems()) {
                Dianmianliushui dl = new Dianmianliushui();
                dl.setDianmianId(saasid + l.getModel().getEntityId());
                dl.setYinyejine(ii.getSumFee());
                dl.setRiqi(ii.getCurrDate());
                dl.setKeliurenshu(ii.getPeopleCount());
                dl.setYinyedanshu(ii.getSaleCount());
                dl.setDianmianmingchen(dmmc);
                dl.setId(ii.getCurrDate() + l.getModel().getEntityId());
                dls.add(dl);
            }
            this.saveliushui(dls);
            return "success";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "fail";
        }
    }

    public String savepre(String dianmianId, String dmmc, int saasid, String saasmc, Date shijian, String man,
            String phone, Integer money) {
        // TODO Auto-generated method stub
        houtaidao.savepre(dianmianId, dmmc, saasid, saasmc, shijian, man, phone, money);
        try {
            return "success";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "success";
        }
        // if(! houtaidao.savepre(dianmianId,dmmc, saasid, saasmc, shijian,man,phone,money)){
        //
        // YingyeClient.getInstance().updateStatus(dianmianId, "1",money);
        // return "fail";
        // }
        // List<Dianmianliushui> dls=new ArrayList();
        // try {
        // Calendar calendar = Calendar.getInstance();
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        // ObjectMapper objectMapper = new ObjectMapper();
        // if(money==null){
        // money=0;
        // }
        // if( YingyeClient.getInstance().updateStatus(dianmianId, "1", money).equals("success")){
        //// for(int i=0;i<7;i++){
        ////
        //// String r =YingyeClient.getInstance().takeliushui(dianmianId,sdf.format(calendar.getTime()));
        //// // List<LinkedHashMap<String, Dianmianliushui>> list = objectMapper.readValue(r, List.class);
        //// liushui l = objectMapper.readValue(r, liushui.class);
        ////
        //// for(Item ii:l.getModel().getItems()){
        //// Dianmianliushui dl=new Dianmianliushui();
        //// dl.setDianmianId(saasid+l.getModel().getEntityId());
        //// dl.setYinyejine(ii.getSumFee());
        //// dl.setRiqi(ii.getCurrDate());
        //// dl.setKeliurenshu(ii.getPeopleCount());
        //// dl.setYinyedanshu(ii.getSaleCount());
        //// dl.setDianmianmingchen(dmmc);
        //// dl.setId(ii.getCurrDate()+l.getModel().getEntityId());
        ////
        //// dls.add(dl);
        //// }
        ////
        ////
        ////// for (Dianmianliushui l : list) {
        ////// l.setDianmianId(saasid+l.getDianmianId());
        ////// houtaidao.saveliushui( l);
        ////// }
        ////// if(list!=null){
        ////// for (int j = 0; j < list.size(); j++) {
        //////// Map<String, Dianmianliushui> map = list.get(i);
        //////// Set<String> set = map.keySet();
        //////// for (Iterator<String> it = set.iterator();it.hasNext();) {
        //////// String key = it.next();
        ////// houtaidao.saveliushui( list.get(j));
        //////// }
        ////// }
        ////// }
        //// calendar.add(Calendar.MONTH, -1);
        ////
        //// }
        // return "success";
        // }
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // return "success";
        //
        // }
        // return "success";
    }

    public List<Pre> getPre(int pageNo) {
        // TODO Auto-generated method stub
        return houtaidao.getPre(pageNo);
    }

    public int getPrecishu() {
        // TODO Auto-generated method stub
        return houtaidao.countPre();
    }

    public void saveliushui(List<Dianmianliushui> dls) {
        // TODO Auto-generated method stub
        houtaidao.saveliushui(dls);
    }

    public int getDaikuancishu() {
        // TODO Auto-generated method stub
        return houtaidao.countdk();
    }

    public int getDianmianliushuicishu(String dianmianId) {
        // TODO Auto-generated method stub
        return houtaidao.getDianmianliushuicishu(dianmianId);
    }
}
