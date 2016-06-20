package foodorderingsystem.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ClientConfiguration
{

  private static final String CONF_FILE = "config.properties";
  private static final String HOST_PROP = "host";
  private static final String PORT_PROP = "port";

  private String host = "localhost";
  private String port = "8080";

  public String getHost()
  {
    return host;
  }

  public String getPort()
  {
    return port;
  }

  public void loadConfiguration()
  {
    String pathToFile = "";
    try
    {
      Properties properties = new Properties();
      pathToFile = getJarExecutionDirectory() + File.separator + CONF_FILE;

      if (isFileExist(pathToFile) && isFileAccessible(pathToFile))
      {
        File configFile = new File(pathToFile);
        InputStream inputStream = new FileInputStream(configFile);

        if (inputStream != null)
        {
          properties.load(inputStream);
          host = properties.getProperty(HOST_PROP);
          port = properties.getProperty(PORT_PROP);
          inputStream.close();
        }
      }
    } catch (Exception e)
    {
      System.out.println("Error opening configuration file: " + pathToFile);
    }
  }

  private static String getJarExecutionDirectory()
  {
    return System.getProperty("user.dir");
  }

  private static boolean isFileExist(String pathToFile)
  {
    return pathToFile == null ? false : new File(pathToFile).exists() && new File(pathToFile).isFile();
  }

  private static boolean isFileAccessible(String pathToFile)
  {
    return pathToFile == null ? false
        : new File(pathToFile).exists() && new File(pathToFile).isFile() && new File(pathToFile).canRead()
            && new File(pathToFile).canWrite();
  }
}
