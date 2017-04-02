package shared;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import server.NodeInfo;

import java.io.*;
import java.net.*;

public class RESTController
{
    private String apiEndpoint;

    public RESTController(String ipAddress, int port)
    {
        apiEndpoint = String.format("http://%s:%d/api", ipAddress, port);
        System.out.println("Dashboard API: " + apiEndpoint);
    }

    /**
     * Register a node to the webserver
     * @param node the node to be registered
     * @throws Exception
     */
    public void registerNode(NodeInfo node) throws Exception {
        JsonObject json = new JsonObject();
        json.addProperty("ip", node.getIp());

        Gson gson = new Gson();

        String body = gson.toJson(json);

        sendHttpRequest("/nodes", "POST", gson.toJson(json));
    }

    /**
     * Unregister a node from the webserver
     * @param node the node to be unregistered
     * @throws Exception
     */
    public void unregisterNode(NodeInfo node) throws Exception
    {
        Gson gson = new Gson();

        sendHttpRequest(String.format("/nodes/%s", node.getIp()), "DELETE", "");
    }

    /**
     * Update a node
     * @param node the node to be updated
     * @throws Exception
     */
    public void updateNode(NodeInfo node) throws Exception {
        Gson gson = new Gson();
        // Add list of process info
        String body = gson.toJson(node);

        sendHttpRequest(String.format("/nodes/%s/deviceinfo", node.getIp()), "POST", body);
    }

    /**
     * check if a node is to be shutdown
     * @param node node to be checked
     * @return whether node is to be shutdown
     * @throws Exception
     */
    public boolean checkShutdown(NodeInfo node) throws Exception {
        String response = sendHttpRequest(String.format("/nodes/%s/shutdown", node.getIp()), "POST", "" );
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(response).getAsJsonObject();
        return obj.get("shutdown").getAsBoolean();
    }


    /**
     * Send a HTTP request to the websrever
     * @param endpoint the endpoint where we will send
     * @param method method to be used
     * @param body body of the request
     * @return the HTTP response
     * @throws Exception
     */
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
