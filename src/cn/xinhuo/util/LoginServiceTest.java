package cn.xinhuo.util;
/************************* 鐗堟潈澹版槑 **********************************
 * 鐗堟潈鎵 湁锛欳opyright (c) 2DFIRE Co., Ltd. 2015 
 *
 * 宸ョ▼鍚嶇О锛�open-loan
 * 鍒涘缓鑰咃細 "bingli"
 * 鍒涘缓鏃ユ湡锛�2016骞�鏈�8鏃�
 * 鍒涘缓璁板綍锛�鍒涘缓绫荤粨鏋勩�
 *
 * ************************* 鍙樻洿璁板綍 ********************************
 * 淇敼鑰咃細 
 * 淇敼鏃ユ湡锛�
 * 淇敼璁板綍锛�
 *
 **/

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**     
 * 椤圭洰鍚嶇О锛歰pen-loan  
 * 绫诲悕绉帮細LoginServiceTest  
 * 绫绘弿杩帮細   閫氳繃杩愯杩欎釜test鏉ュ緱鍒皊ession id
 * 鍒涘缓鏃堕棿锛�016骞�鏈�3鏃�涓嬪崍2:51:12  
 * @author bingli  
 * @version 1.0
 */
public class LoginServiceTest {
    public static final String      APP_KEY    = "47656c34bae3e8c9d4ebb7545681209c";
    public static final String      APP_SECRET = "f2b59e829660013f7f09ea6fe23117ad";
    private static final String      USER_NAME  = "loan";
    private static final String      PASSWORD  = "123456";

    public static final String      SERVER_URL = "http://127.0.0.1:8080/router";

    
    public void usersLogin() throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add("appKey", APP_KEY);
        form.add("v", "1.0");
        form.add("method", "dfire.users.login");
        form.add("locale", "zh_CN");
        form.add("timestamp", String.valueOf(System.currentTimeMillis()));
        form.add("userName", USER_NAME);
        form.add("password", MD5Util.MD5Encode(PASSWORD, "UTF-8"));
        //瀵硅姹傚弬鏁板垪琛ㄨ繘琛岀鍚�
        String sign = RopUtils.sign(form.toSingleValueMap(), APP_SECRET);
        form.add("sign", sign);

        String response = restTemplate.postForObject(SERVER_URL, form, String.class);
        System.out.println("response:\n" + response);

    }

}
