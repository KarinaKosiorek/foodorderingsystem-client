package foodorderingsystem.client;

import java.util.Properties;

/**
 *
 * @author karkos
 *
 */

public class ClientMain
{
  public static final String HOST = "-host";
  public static final String PORT = "-port";

  public static void main(String[] args)
  {
    try
    {
      HttpRequestService httpRequestService = new HttpRequestService();
      Properties commandLineProperties = new Properties();

      ClientConfiguration clientConfiguration = new ClientConfiguration();
      clientConfiguration.loadConfiguration();
      String host = clientConfiguration.getHost();
      String port = clientConfiguration.getPort();

      saveProperties(args, commandLineProperties);

      // for (String key : commandLineProperties.stringPropertyNames())
      // {
      // System.out.println(key + " " + commandLineProperties.getProperty(key));
      // }

      if (commandLineProperties.getProperty(HOST) != null)
      {
        host = commandLineProperties.getProperty(HOST);
      }
      if (commandLineProperties.getProperty(PORT) != null)
      {
        port = commandLineProperties.getProperty(PORT);
      }
      String response = httpRequestService.sendRequest(host, port, commandLineProperties);
      System.out.println(response);

    } catch (Exception e)
    {
      System.out.println("Error with food ordering service: " + e.getMessage());
    }
  }

  private static void saveProperties(String[] args, Properties commandLineProperties)
  {
    for (int i = 0; i < args.length; i++)
    {
      if (i + 1 < args.length)
      {
        if (args[i + 1].startsWith("-"))
        {
          commandLineProperties.put(args[i], args[i]);
        } else
        {
          commandLineProperties.put(args[i], args[i + 1]);
          i++;
        }
      } else
      {
        commandLineProperties.put(args[i], args[i]);
      }
    }
  }
}
