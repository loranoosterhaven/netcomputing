package shared;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import server.NodeInfo;

import java.io.*;
import java.net.*;

/**
 * Created by hein on 8-3-17.
 */
public class Johan
{
    private String apiEndpoint;

    public Johan(String ipAddress, int port)
    {
        apiEndpoint = String.format("http://%s:%d/api", ipAddress, port);
        System.out.println("Dashboard API: " + apiEndpoint);
    }

    public void registerNode(NodeInfo node) throws Exception {
        JsonObject json = new JsonObject();
        json.addProperty("ip", node.getIp());

        Gson gson = new Gson();

        String body = gson.toJson(json);

        sendHttpRequest("/nodes", "POST", gson.toJson(json));
    }

    public void unregisterNode(NodeInfo node) throws Exception
    {
        Gson gson = new Gson();

        sendHttpRequest(String.format("/nodes/%s", node.getIp()), "DELETE", "");
    }

    public void updateNode(NodeInfo node) throws Exception {
        Gson gson = new Gson();
        // Add list of process info
        String body = gson.toJson(node);

        sendHttpRequest(String.format("/nodes/%s/deviceinfo", node.getIp()), "POST", body);
    }

    public boolean checkShutdown(NodeInfo node) throws Exception {
        String response = sendHttpRequest(String.format("/nodes/%s/shutdown", node.getIp()), "POST", "" );
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(response).getAsJsonObject();
        return obj.get("shutdown").getAsBoolean();
    }

    public String sendHttpRequest(String endpoint, String method, String body) throws Exception
    {
        String targetUrl = apiEndpoint + endpoint;

        HttpURLConnection connection = null;

        URL url = new URL(targetUrl);
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type",
                "application/json");

        connection.setRequestProperty("Content-Length",
                Integer.toString(body.length() + 1));
        connection.setRequestProperty("Content-Language", "en-US");

        connection.setUseCaches(false);
        connection.setDoOutput(true);

        //Send request
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(body);
        wr.close();

        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();

        System.out.println(response);

        connection.disconnect();

        return response.toString();
    }
}
