import java.sql.*;
// loescht Tabelle Coffee

public class DropCoffees {

  public static void main(String args[]) {
	Connection con =  null;
	Statement stmt = null;
    String createString = "drop table COFFEES ";
    try {
      con =  ConnectionManager.getConnection();
      stmt = con.createStatement();
      stmt.executeUpdate(createString);
      System.out.println("Tabelle Coffees geloescht");
    }
    catch(SQLException e) {
      e.printStackTrace();
    }
    finally{
          try {
            stmt.close();
            con.close();
            System.out.println("aufgeraeumt");
          }
          catch (Exception e) {e.printStackTrace();}
    }
  }
}


