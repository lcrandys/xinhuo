package cn.xinhuo.service;

import java.util.Calendar;

public class YEliushuiThread extends Thread {

    int month;

    @Override
    public void run() {
        Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH) + 1;
        while (true) {
            try {
                sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int temp = cal.get(Calendar.MONTH) + 1;
            if (temp != month) {
                System.out.print("moth is changed");
            }
        }
    }
}
