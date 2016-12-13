package cn.xinhuo.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import cn.xinhuo.interf.Houtai;

public class ServiceGate {


    
    
    

    private Houtai houtai;

    public Houtai getHoutai() {
        return houtai;
    }

    public void setDaikuanshenqing(Houtai houtai) {
        this.houtai = houtai;
    }

    private RestTemplate restTemplate = new RestTemplate();

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static ServiceGate serviceGate = new ServiceGate();

    private ServiceGate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        houtai = (Houtai) context.getBean("houtai");
        RestTemplate restTemplate = new RestTemplate();
    }

    
    static public ServiceGate getInstance() {
        return serviceGate;
    }

    public static void main(String args[]) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(ServiceGate.getInstance());
            // ServiceGate.getInstance().getHoutai().jilufangwen("aaaa", "bbb", 1, "aaaa", new Date());
        }
    }
}

