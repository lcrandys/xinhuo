package cn.xinhuo.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.router.property.PropertiesFactory;

import cn.xinhuo.entity.Dianmianliushui;
import cn.xinhuo.entity.Test;
import cn.xinhuo.util.RopUtils;
  
public class YingyeClient  
{  

    private static YingyeClient client = new YingyeClient();

    public static YingyeClient getInstance() {
        return client;
    }

    public String APP_KEY = PropertiesFactory.getInstance().getValue("APP_KEY");
    public  String      APP_SECRET = PropertiesFactory.getInstance().getValue("APP_SECRET");
    private  String      USER_NAME  =  PropertiesFactory.getInstance().getValue("USER_NAME");
    private  String      PASSWORD  =  PropertiesFactory.getInstance().getValue("PASSWORD");
    public  String      SERVER_URL =  PropertiesFactory.getInstance().getValue("SERVER_URL");

    private YingyeClient() {
    }

    public static void main(String args[]) {
        YingyeClient.getInstance().updateStatus("99927090", "3", 1);
    }

    public void test() {
        try {
            JSONObject request = new JSONObject();
            request.put("name", "test");
            request.put("string1", "hello world");
            request.put("int1", "1");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
            System.out.println(new Test().exe());
            System.out.println(ServiceGate.getInstance().getRestTemplate()
                    .exchange("http://localhost:8080/XinHuo/services/testPost", HttpMethod.POST, entity, String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String updateStatus(String id, String status, int fee) {
        System.out.println("状态修改================：" + id);
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add("appKey", APP_KEY);
        form.add("v", "1.0");
        // form.add("method", "dfire.users.login");
        // form.add("locale", "zh_CN");
        form.add("method", "dfire.loan.change_status");
        form.add("entityId", String.valueOf(id));
        form.add("actionId", String.valueOf(System.currentTimeMillis()));
        form.add("action", status);
        form.add("timestamp", String.valueOf(System.currentTimeMillis() - 1000 * 60));
        if (fee != 0) {
            form.add("fee", String.valueOf(fee));
        }
        System.out.println("状态修改================：form" + form.toString());
        //
        // form.add("userName", USER_NAME);
        // form.add("password", MD5Util.MD5Encode(PASSWORD, "UTF-8"));
        String sign = RopUtils.sign(form.toSingleValueMap(), APP_SECRET);
        form.add("sign", sign);
        //
        try {
            System.out.println(SERVER_URL);
            String response = ServiceGate.getInstance().getRestTemplate().postForObject(SERVER_URL, form, String.class);
            System.out.println(response);
            if (response.contains("true")) {
                return "success";
            } else {
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "update fail";
        }
    }

    /**
     * @param args
     * @throws IOException
     * @throws ClientProtocolException
     */  
    public  String   takeliushui(String entityId,String date) throws ClientProtocolException, IOException  
    {  
        System.out.println("takeliushui");

        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add("appKey", APP_KEY);
        form.add("v", "1.0");
        form.add("method", "dfire.loan.sale_result");
        form.add("entityId", entityId);
        form.add("period", date);
        form.add("timestamp", String.valueOf(System.currentTimeMillis() - 1000 * 60));
        String sign = RopUtils.sign(form.toSingleValueMap(), APP_SECRET);
        System.out.println(sign);
        form.add("sign", sign);
        //
        String response = ServiceGate.getInstance().getRestTemplate().postForObject(SERVER_URL, form, String.class);
        System.out.println("sa" + response);
         return response;
    }  
    
    @SuppressWarnings({ "unused", "unchecked" })
    public   List<LinkedHashMap<String, Dianmianliushui>>  readJson2List(String in  ) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
                    "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
        try {
            List<LinkedHashMap<String, Dianmianliushui>> list = objectMapper.readValue(in, List.class);
           return list;
            
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}  
