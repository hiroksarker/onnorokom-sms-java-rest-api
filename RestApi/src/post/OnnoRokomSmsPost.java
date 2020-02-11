package post;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class OnnoRokomSmsPost {
    public static void main(String[] args){
        try{

            //Base URL
            URL url = new URL("https://api2.onnorokomsms.com/HttpSendSms.ashx");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");

            //API Key
            String apiKeys = "";
            //Recipient Mobile Number
            String recipientMobile = "";
            //Text Message
            String textMessage = "";

            HashMap<String, String> params = new HashMap<>();
            //SMS Send Method
            params.put("op", "NumberSms");
            //API Key (User Panel)
            params.put("apiKey", apiKeys);
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
            String paramString = sbParams.toString();
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(paramString);
            wr.flush();
            wr.close();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
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
