import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import oracle.jdbc.pool.OracleDataSource;

public class NEW_ConnectionManager {

	private static String dburl;

	/**
	 * Dient dem Connection-Pooling
	 */
	private static volatile Vector<Connection> connectionPool = new Vector<Connection>(5);

	static {
		// Die Initialisierung der Connect-URL findet nur einmal statt.
		final String uid = "user";
		final String sid = "ORA11G";
		final String pwd = "pwd";
		final String driver = "thin";
		final String server = "dellserver01.inform.fh-hannover.de";
		dburl = "jdbc:oracle:"+driver+":" + uid + "/" + pwd	+ "@" + server + ":1521:"+sid;
		System.out.println("dburl:  " + dburl);
	}

	// TEST
	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			final Connection connection = getConnection();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ###########################################################################
	/**
	 * Liefert eine vorhandene Connection aus dem Pool (erzeugt wenn n�tig daf�r
	 * eine neue)
	 *
	 * @return Connection
	 */
	public static synchronized Connection getConnection() throws SQLException {
		if (connectionPool.size() == 0)
			connectionPool.addElement(newConnection());
		Connection conn = connectionPool.remove(0);
		return conn;
	}

	// ###########################################################################
	/**
	 * Gibt eine Connection wieder frei und damit zur�ck in den Pool
	 *
	 * @param Connection
	 */
	public static void release(Connection conn) {
		if (!connectionPool.contains(conn))
			connectionPool.add(conn);
	}

	// ###########################################################################
	/**
	 * Beendet alle Datenbank-Connections und l�scht sie aus dem
	 * Connection-Pool.
	 */
	public static void close() throws SQLException {
		for (final Connection c : connectionPool) {
			c.commit();
			c.close();
		}
		connectionPool.removeAllElements();
	}

	// ###########################################################################
	/**
	 * Erzeuge neue Connection. Autocommit wird nicht explizit gesetzt, ist also
	 * per defaul aktiv.
	 *
	 * @return Connection
	 */
	private static Connection newConnection() throws SQLException {
		OracleDataSource ods = new OracleDataSource();
		ods.setURL(dburl);
		Connection conn = ods.getConnection();
		return conn;
	}
}

