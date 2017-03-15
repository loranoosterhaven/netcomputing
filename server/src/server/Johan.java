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
    String targetUrl;

    public Johan(String targetUrl)
    {
        this.targetUrl = targetUrl;
    }

    public void registerNode() throws IOException {
        HttpURLConnection connection = null;

        URL url = new URL(targetUrl);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        connection.setRequestProperty("Content-Length",
                "5");
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
