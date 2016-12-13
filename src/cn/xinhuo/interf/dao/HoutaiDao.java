package cn.xinhuo.interf.dao;

import java.util.Date;
import java.util.List;

import cn.xinhuo.entity.Daikuanjilu;
import cn.xinhuo.entity.Dianmian;
import cn.xinhuo.entity.Dianmianliushui;
import cn.xinhuo.entity.Fangwenjilu;
import cn.xinhuo.entity.Pre;
import cn.xinhuo.entity.Yonghu;
import cn.xinhuo.entity.Zichanxinxi;

public interface HoutaiDao {

    // ��ȡ���ʼ�¼
    public List<Fangwenjilu> getFangwenjilulist(int pageNo, int pageSize);

    // ��ȡ��������
    public int fwCount();

    // ��ȡ��������¼
    public List<Daikuanjilu> getDaikuanjilu(int pageNo, int style);

    public List<Daikuanjilu> getDaikuanjilu(int pageNo, int style, String dianmianId);

    // ��ȡ������ˮ
    public List<Dianmianliushui> getDianmianliushui(int pageNo, String dianmianId);

    // ��ȡ�û���Ϣ
    public Yonghu getYonghu(int id);

    // ��ȡ�û��ʲ�
    public Zichanxinxi getZichan(int id);

    // ��ȡ�ŵ���Ϣ
    public Dianmian getDianmian(int yonghuid);

    // �����û���Ϣ
    public void saveYonghu(Yonghu yh);

    // ���������Ϣ
    public void saveDianmian(Dianmian dm);

    // �����ʲ���Ϣ
    public void saveZichanxinxi(Zichanxinxi zc);

    // �������¼
    public void saveDaikuanjilu(Daikuanjilu dk);

    public int getDianmianliushuicishu(String dianmianid);

    public void saveliushui(List<Dianmianliushui> dls);

    // ������ʼ�¼
    public void saveJilufangwen(Fangwenjilu fwjl);

    // ����ŵ�id��ȡ����¼��Ϣ
    public int getDaikuanjilu(String mendianid);

    // ״̬���
    public void updateStatus(int id, int status);

    // 存储预申请
    public boolean savepre(String dianmianId, String dmmc, int saasid, String saasmc, Date shijian, String man,
            String phone, Integer money);

    List<Pre> getPre(int pageNo);

    Pre getP(int id);

    public int countPre();

    public int countdk();
}
