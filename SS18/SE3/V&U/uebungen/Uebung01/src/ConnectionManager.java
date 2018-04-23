import java.sql.*;
import oracle.jdbc.*;
import java.util.ResourceBundle;

// liefert Connections zur Datenbank
// die Connection-Parameter müssen in der Property-Datei
// connect.properties festgelegt werden

public class ConnectionManager {

  private static ResourceBundle b;
  private static String uid;
  private static String pwd;
  private static String driver;
  private static String dburl;

  static {
      // die Initialisierung von Treiber und Connect-Parameter findet
      // nur einmal statt
      // Laden der Connection-Parameter aus der Property-Datei
      b = ResourceBundle.getBundle("connect");
      uid    = b.getString("uid");
      pwd    = b.getString("pwd");

      driver = b.getString("driver");
      dburl  = b.getString("dburl");

      System.out.println("Account: " + uid +  "\nTreiber:  "  +  driver + "\ndburl:  " + dburl);

      try{
        // Initialisieren der Treiber-Klasse
        Class driverClass = Class.forName(driver);

      }catch (Exception e){
        System.out.println("Treiber konnte nicht initialisiert werden ....");
      }
  }

  // es können mehrere Connections angefordert werden

  static public Connection getConnection()  {

    Connection conn = null;
    try {
      conn = DriverManager.getConnection (dburl, uid, pwd);
      System.out.println("Connect durchgefuehrt ....");
    } catch (Exception e) {
      System.err.println( "Could not establish the JDBC connection:") ;
      System.err.println(e);  System.exit(1);
    }
    return conn;
  }

}
