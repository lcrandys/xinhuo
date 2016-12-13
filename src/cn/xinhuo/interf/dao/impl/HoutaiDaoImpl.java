package cn.xinhuo.interf.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import cn.xinhuo.entity.Daikuanjilu;
import cn.xinhuo.entity.Dianmian;
import cn.xinhuo.entity.Dianmianliushui;
import cn.xinhuo.entity.Fangwenjilu;
import cn.xinhuo.entity.Pre;
import cn.xinhuo.entity.Yonghu;
import cn.xinhuo.entity.Zichanxinxi;
import cn.xinhuo.interf.dao.HoutaiDao;

public class HoutaiDaoImpl implements HoutaiDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Fangwenjilu> getFangwenjilulist(int pageNo, int pageSize) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        List<Fangwenjilu> fwlist = session.createCriteria(Fangwenjilu.class).addOrder(Order.desc("shijian"))
                .setFirstResult(pageSize * (pageNo - 1)).setMaxResults(pageSize).list();
        return fwlist;
    }

    public int fwCount() {
        Session session = sessionFactory.getCurrentSession();
        int count = 0;
        String hql = "select count(*) from Fangwenjilu";
        Query query = session.createQuery(hql);
        count = (Integer.parseInt(query.uniqueResult().toString()));
        return count;
    }

    public void saveYonghu(Yonghu yh) {
        Session session = sessionFactory.getCurrentSession();
        session.save(yh);
    }

    public Pre getP(int id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria dkjl = session.createCriteria(Pre.class);
        dkjl.add(Restrictions.eq("id", id));
        List<Pre> p = dkjl.list();
        System.out.println(p.size() + id);
        if (p.size() == 0) {
            return null;
        } else {
            return ((Pre) p.get(0));
        }
    }

    public void saveDianmian(Dianmian dm) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        session.save(dm);
    }

    public void saveZichanxinxi(Zichanxinxi zc) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        session.save(zc);
    }

    public void saveDaikuanjilu(Daikuanjilu dk) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        session.save(dk);
    }

    public void saveJilufangwen(Fangwenjilu fwjl) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        session.save(fwjl);
    }

    public int getDaikuanjilu(String mendianid) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Criteria dkjl = session.createCriteria(Daikuanjilu.class);
        dkjl.add(Restrictions.eq("mendianId", mendianid));
        dkjl.addOrder(Order.desc("daikuanshijian"));
        if (dkjl.list().size() == 0) {
            return 0;
        } else {
            return ((Daikuanjilu) dkjl.list().get(0)).getZhuangtai();
        }
    }

    public List<Daikuanjilu> getDaikuanjilu(int pageNo, int style) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Criteria dkjl = session.createCriteria(Daikuanjilu.class);
        if (style == -1) {
            dkjl.setFirstResult(20 * (pageNo - 1)).setMaxResults(20);
            dkjl.addOrder(Order.desc("daikuanshijian"));
            return dkjl.list();
        }
        dkjl.setFirstResult(20 * (pageNo - 1)).setMaxResults(20);
        dkjl.addOrder(Order.desc("daikuanshijian"));
        return dkjl.list();
    }

    public List<Daikuanjilu> getDaikuanjilu(int pageNo, int style, String dianmianid) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Criteria dkjl = session.createCriteria(Daikuanjilu.class);
        if (style == -1) {
            dkjl.setFirstResult(20 * (pageNo - 1)).setMaxResults(20);
            dkjl.add(Restrictions.eq("mendianId", dianmianid));
            dkjl.addOrder(Order.desc("daikuanshijian"));
            return dkjl.list();
        }
        dkjl.add(Restrictions.eq("mendianId", dianmianid));
        dkjl.add(Restrictions.eq("zhuangtai", style)).setFirstResult(20 * (pageNo - 1)).setMaxResults(20);
        dkjl.addOrder(Order.desc("daikuanshijian"));
        return dkjl.list();
    }

    public List<Dianmianliushui> getDianmianliushui(int pageNo, String dianmianId) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Criteria dkjl = session.createCriteria(Dianmianliushui.class);
        dkjl.add(Restrictions.eq("dianmianId", dianmianId)).setFirstResult(20 * (pageNo - 1)).setMaxResults(20);
        dkjl.addOrder(Order.desc("id"));
        return dkjl.list();
    }

    public Yonghu getYonghu(int id) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Criteria dkjl = session.createCriteria(Yonghu.class);
        dkjl.add(Restrictions.eq("id", id));
        return (Yonghu) dkjl.uniqueResult();
    }

    public Zichanxinxi getZichan(int id) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Criteria dkjl = session.createCriteria(Zichanxinxi.class);
        dkjl.add(Restrictions.eq("yonghuId", id));
        return (Zichanxinxi) dkjl.uniqueResult();
    }

    public Dianmian getDianmian(int id) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Criteria dkjl = session.createCriteria(Dianmian.class);
        dkjl.add(Restrictions.eq("yonghuId", id));
        return (Dianmian) dkjl.uniqueResult();
    }

    public void updateStatus(int id, int status) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Criteria dkjl = session.createCriteria(Daikuanjilu.class);
        dkjl.add(Restrictions.eq("id", id));
        Daikuanjilu d = (Daikuanjilu) dkjl.uniqueResult();
        d.setZhuangtai(status);
        session.update(d);
    }
    // private List<String> getLiushuilist(int latestliusui){
    // String d=new SimpleDateFormat("yyyyMM").format(Calendar.getInstance().getTime());
    // if(Integer.parseInt(d)>latestliusui){
    // //����Httpclient������ˮ
    // //�洢���õ�����ˮ
    // //
    // }else{
    //
    // }
    // }
    // public List<Dianmianliushui> getDianmianliushui(int id){
    // Dianmian d=getDianmian(id);
    // d.getZuijinliushuishijian()
    // }
    // 当update时为false

    public boolean savepre(String dianmianId, String dmmc, int saasid, String saasmc, Date shijian, String man,
            String phone, Integer money) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Criteria pre = session.createCriteria(Pre.class);
        pre.add(Restrictions.eq("dianmianId", dianmianId));
        pre.add(Restrictions.eq("saasid", saasid));
        Pre p = (Pre) pre.uniqueResult();
        if (p != null) {
            return false;
        } else {
            p = new Pre();
            p.setDianmianId(dianmianId);
            p.setDmmc(dmmc);
            p.setSaasid(saasid);
            p.setSaasmc(saasmc);
            p.setShijian(shijian);
            p.setMan(man);
            p.setPhone(phone);
            p.setMoney(money);
            session.save(p);
            return true;
        }
    }

    @Transactional
    public void saveliushui(List<Dianmianliushui> dls) {
        // TODO Auto-generated method stub
        Session session;
        session = sessionFactory.getCurrentSession();
        // try{
        // session = sessionFactory.getCurrentSession();
        // }catch (Exception e){
        // session = sessionFactory.openSession();
        // }
        // if(session==null){
        // session = sessionFactory.openSession();
        // }
        // ls.setId(ls.getDianmianId()+ls.getRiqi());
        for (Dianmianliushui dl : dls) {
            session.saveOrUpdate(dl);
        }
    }

    public List<Pre> getPre(int pageNo) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        Criteria dkjl = session.createCriteria(Pre.class);
        dkjl.setFirstResult(20 * (pageNo - 1)).setMaxResults(20);
        dkjl.addOrder(Order.desc("id"));
        return dkjl.list();
    }

    public int countPre() {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        int count = 0;
        String hql = "select count(*) from Pre";
        Query query = session.createQuery(hql);
        count = (Integer.parseInt(query.uniqueResult().toString()));
        return count;
    }

    public int countdk() {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        int count = 0;
        String hql = "select count(*) from Daikuanjilu";
        Query query = session.createQuery(hql);
        count = (Integer.parseInt(query.uniqueResult().toString()));
        return count;
    }

    public int getDianmianliushuicishu(String dk) {
        // TODO Auto-generated method stub
        Session session = sessionFactory.getCurrentSession();
        int count = 0;
        String hql = "select count(*) from Dianmianliushui where dianmian_id= " + dk;
        Query query = session.createQuery(hql);
        count = (Integer.parseInt(query.uniqueResult().toString()));
        return count;
    }

}
