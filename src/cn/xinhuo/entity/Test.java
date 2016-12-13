package cn.xinhuo.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class Test implements MyClass {
private String string1;
private String int1;
@Autowired
private org.springframework.jdbc.datasource.DriverManagerDataSource dataSource;


    public String getString1() {
        return string1;
}

public void setString1(String string1) {
        this.string1 = string1;
}

public String getInt1() {
        return int1;
}

public void setInt1(String int1) {
        this.int1 = int1;
}

    public Object exe() {
        // TODO Auto-generated method stub
        System.out.println(dataSource);
        return int1 + string1;
    }

}
