package cn.xinhuo.interf.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.xinhuo.entity.Dianmianliushui;
import cn.xinhuo.entity.Item;
import cn.xinhuo.entity.liushui;
import cn.xinhuo.interf.dao.Demoiter;
import cn.xinhuo.service.YingyeClient;

public class Demo extends Thread implements Demoiter {
    String dmmc;
    String sid;
  
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    Demo(String dmmc1){
        dmmc = dmmc1;
        // sid=id;
    }

    public void run() {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            ObjectMapper objectMapper = new ObjectMapper();
            for (int i = 0; i < 7; i++) {
                String r = YingyeClient.getInstance().takeliushui(dmmc, sdf.format(calendar.getTime()));
                // List<LinkedHashMap<String, Dianmianliushui>> list = objectMapper.readValue(r, List.class);
                liushui l = objectMapper.readValue(r, liushui.class);
                for (Item ii : l.getModel().getItems()) {
                    Dianmianliushui dl = new Dianmianliushui();
                    dl.setDianmianId(l.getModel().getEntityId());
                    dl.setYinyejine(ii.getSumFee());
                    dl.setRiqi(ii.getCurrDate());
                    dl.setKeliurenshu(ii.getPeopleCount());
                    dl.setYinyedanshu(ii.getSaleCount());
                    dl.setDianmianmingchen(dmmc);
                    dl.setId(ii.getCurrDate() + l.getModel().getEntityId());
                    Session sess = sessionFactory.getCurrentSession();
                    sess.saveOrUpdate(dl);
                }
                // for (Dianmianliushui l : list) {
                // l.setDianmianId(saasid+l.getDianmianId());
                // houtaidao.saveliushui( l);
                // }
                // if(list!=null){
                // for (int j = 0; j < list.size(); j++) {
                //// Map<String, Dianmianliushui> map = list.get(i);
                //// Set<String> set = map.keySet();
                //// for (Iterator<String> it = set.iterator();it.hasNext();) {
                //// String key = it.next();
                // houtaidao.saveliushui( list.get(j));
                //// }
                // }
                // }
                calendar.add(Calendar.MONTH, -1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }
 }
