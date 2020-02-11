package get;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class OnnoRokomSmsGet {
    public static void main(String[] args){
        try{
            //BASE URL
            String BASE_URL = "https://api2.onnorokomsms.com/HttpSendSms.ashx";
            //User Auth Info
            String username = "";
            String password = "";
            //Recipient Mobile Number
            String recipientMobile = "";
            //Text Message
            String textMessage = "";


            HashMap<String, String> params = new HashMap<>();
            //SMS Send Method
            params.put("op", "OneToMany");
            //User Info
            params.put("username", username);
            params.put("password", password);
            params.put("type", "TEXT");
            //Mobile Number
            params.put("mobile", recipientMobile);
            //SMS Text
            params.put("smsText", textMessage);
            params.put("maskName", "");
            params.put("campaignName", "");

            //Add Parameters
            StringBuilder sbParams = new StringBuilder();
            int i = 0;
            for (String key : params.keySet()) {
                if (i != 0){
                    sbParams.append("&");
                }
                sbParams.append(key).append("=")
                        .append(URLEncoder.encode(params.get(key), StandardCharsets.UTF_8));
                i++;
            }

            //Full Params toString()
            String fullParams = sbParams.toString();
            //Create Full Url
            URL url = new URL(BASE_URL + "?"+ fullParams);
            //HttpUrlConnection Initialize
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
