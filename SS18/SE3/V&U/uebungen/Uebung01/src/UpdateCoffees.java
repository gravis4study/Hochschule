import java.sql.*;

public class UpdateCoffees {
	public static void main(String args[]) {

    Connection con =  null;
	Statement stmt = null;

	try {
	  con =  ConnectionManager.getConnection();
	  stmt = con.createStatement();
      int verkauf = 12;
      String update =  "update coffees set sales = " + verkauf +
                                      " , total = total + " + verkauf
                      + " where cof_name like 'Colombian%'";
      int anzahl = stmt.executeUpdate(update);

      System.out.println(anzahl + " Datensaetze in Tabelle COFFEES geaendert");
      stmt.close();
			con.close();
		}
    catch(SQLException ex) {ex.printStackTrace();}
    finally{
          try {
            stmt.close();   con.close();
            System.out.println("aufgeraeumt");
          }
          catch (Exception e) {e.printStackTrace();}
    }
	}
}
