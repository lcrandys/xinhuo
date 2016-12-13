package cn.xinhuo.interf;

import java.util.Date;
import java.util.List;

import cn.xinhuo.entity.Daikuanjilu;
import cn.xinhuo.entity.Dianmian;
import cn.xinhuo.entity.Dianmianliushui;
import cn.xinhuo.entity.Fangwenjilu;
import cn.xinhuo.entity.Pre;
import cn.xinhuo.entity.SaaS;
import cn.xinhuo.entity.Yonghu;
import cn.xinhuo.entity.Zichanxinxi;

public interface Houtai {

    // ���ʼ�¼ �б�
    // ���� int time int pageNo
    void savetest(Yonghu yh);

    List<Fangwenjilu> getFangwenjilulist(int pageNo);

    int getFangwenjilucishu();

    // �����¼�б�
    // ����1��״̬2���������3�����������4��������ֻ�ţ�5�������ID������Ϊnull��0ʱ���Դ˲���,int pageNo
    // ״̬ ����=1��ͬ��=2��������=3������=4,��ֹ=5
    List<Daikuanjilu> getDaikuanshenqinglist(int status, int pageNo, String dianmianid);

    List<Daikuanjilu> getDaikuanshenqinglist(int status, int pageNo);

    // ��Ӫ��ˮ�б�
    // ���� 1����ʼ����2����������3����СӪҵ��4�����Ӫҵ��5��������� ������Ϊ0ʱ���Դ˲���int pageNo
    List<Dianmianliushui> getDianmianliushui(String dianmianId, int pageNo);

    // ������Ϣ

    // ���� �û�ID
    Yonghu getYonghu(int id);
    // ������Ϣ

    // �����û�ID
    Dianmian getDianmian(int id);
    // �ʲ���Ϣ

    // ���� �û�ID
    Zichanxinxi getZichan(int id);

    // SaaS�Ǽ�
    void SaaSdengji(String liushuijiekou, String mingcheng);

    // SaaS�б�
    List<SaaS> Saasliebiao();

    // ȡ�������ȡ��ˮʱ��
    Date getHuoqu(int dianmianId);
public int getDaikuancishu();

    // ǰ̨��Ҫ
    // ���ʼ�¼
    void jilufangwen(String dianmianId, String dmmc, int saasid, String saasmc, Date shijian);

    // �������¼
    void shenqingdaikuan(Yonghu yh, Dianmian dm, Zichanxinxi zc, Date shijian, String fenqi, String jine,
            String saasmc);

    // ��ˮ��¼
    // 1���洢��ˮ
    // 2���������ȡ���ȥ��ˮʱ��Ϊ����
    // void liushuijilu(List<Dianmianliushui> liushui);
    // �жϸõ����Ƿ�������������򷵻ش���¼id,���򷵻�0,��ѯ����ID=saasid+outid(�ַ����)�Ƿ���ڣ�����״̬��Ϊ4
    int ifdaikuan(int saasId, String outId);

    // ���״̬���
    String updateStatus(int id, String mendianid, int status, String fee, int daikuanid);

    String savepre(String dianmianId, String dmmc, int saasid, String saasmc, Date shijian, String man, String phone,
            Integer jine);

    List<Pre> getPre(int pageNo);

    int getPrecishu();

    void saveliushui(List<Dianmianliushui> dls);

    int getDianmianliushuicishu(String dianmianId);

    String getliushui(int preId);
}
