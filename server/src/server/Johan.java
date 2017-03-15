package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by hein on 8-3-17.
 */
public class Johan
{
    public Johan(String targetUrl)
    {

    }

    public void registerNode() throws IOException
    {
        String targetUrl = "";

        HttpURLConnection connection = null;

        URL url = new URL(targetUrl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type",
                "application/json");

        String body = "{ \"ip\": \"192.168.0.1\" }";
        System.out.println("Body: " + body);

        connection.setRequestProperty("Content-Length",
                Integer.toString(body.length() + 10));
        connection.setRequestProperty("Content-Language", "en-US");

        connection.setUseCaches(false);
        connection.setDoOutput(true);

        //Send request
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes("abcde");
        wr.close();

        connection.disconnect();
    }
}
