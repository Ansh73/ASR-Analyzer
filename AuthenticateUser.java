package com.kevin.maven.quickstart;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AuthenticateUser {

	public void doAuthentication()
	{
		
        try {
            String urlParameters = "key=AIzaSyDQHPFm_4LBAUE1iCsknU_Bw2OhUvv00HU";
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            String request = "https://language.googleapis.com/v1/documents:analyzeEntities";
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);
            try
            {
                DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
                writer.write(postData);

                /*out = new BufferedOutputStream(conn.getOutputStream());

                BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(out, "UTF-8"));

                writer.write(String.valueOf(postData));*/

                writer.flush();

                writer.close();

                //out.close();

                conn.connect();

            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {

            System.out.println(e.getMessage());


        }
    }
	
}
