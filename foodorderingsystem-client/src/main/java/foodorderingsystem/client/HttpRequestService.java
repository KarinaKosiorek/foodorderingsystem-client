package foodorderingsystem.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

public class HttpRequestService {

  public String sendRequest(String host, String port, Properties properties) throws Exception {
    String url = "";
    try {
      StringBuilder parametersBuilder = new StringBuilder("");
      for (String key : properties.stringPropertyNames()) {
        if (!key.equals(ClientMain.HOST) && !key.equals(ClientMain.PORT)) {
          if (key.startsWith("-")) {
            parametersBuilder.append(key.substring(1));
          } else {
            parametersBuilder.append(key);
          }
          parametersBuilder.append("=");
          String property = properties.getProperty(key);
          if (property.startsWith("-")) {
            parametersBuilder.append(URLEncoder.encode(property.substring(1), "UTF-8"));
          } else {
            parametersBuilder.append(URLEncoder.encode(property, "UTF-8"));
          }
          parametersBuilder.append("&");
        }
      }
      if (parametersBuilder.charAt(parametersBuilder.length() - 1) == '&') {
        parametersBuilder.deleteCharAt(parametersBuilder.length() - 1);
      }

      if (parametersBuilder.toString().length() > 0) {
        url = "http://" + host + ":" + port + "/foodOrderingSystem/?" + parametersBuilder.toString();
      } else {
        url = "http://" + host + ":" + port + "/foodOrderingSystem/";
      }

      System.out.println("\nSending 'GET' request to URL : " + url);

      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();

      con.setRequestMethod("GET");
      con.setRequestProperty("User-Agent", "User Agent");

      int responseCode = con.getResponseCode();
      System.out.println("Response Code : " + responseCode);

      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      return response.toString();
    } catch (Exception e) {
      throw e;
    }
  }
}
