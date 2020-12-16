import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class FicheroCiudadMysql {

	public static final String HOSTDB = "localhost";	
	public static final String NOMBREDB = "ciudadesdb";
	public static final String USUARIODB = "root";
	public static final String CLAVEDB = "Raulcury16920000-";
	private static final String OPCIONESHORA = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + "/" + NOMBREDB + OPCIONESHORA;
	public static final String PUERTOMYSQL = "3306";
	//----------------------------------------------------------....
	public static Connection conectarConBaseDeDatos() {
		try {
			Connection conexion = DriverManager.getConnection(URLMYSQL, USUARIODB, CLAVEDB);
			return conexion;
		} catch (SQLException e) {
			System.out.println("no se pudo establecer la conexion con la base de datos");
			return null;
		}		
	}	
	//-----------------------------------------------------------	
	public static ArrayList<Ciudad> obtenerCiudades()
	{
		Connection conexion = conectarConBaseDeDatos();
		if(conexion == null)
		{
			return null;
		}
		ArrayList<Ciudad> ciudadesDevueltas = new ArrayList<Ciudad>();
		try {
			Statement sentencia = conexion.createStatement();
			String ordenSQL = "select * from ciudades"; 
			ResultSet resultado = sentencia.executeQuery(ordenSQL);
			while(resultado.next())
			{
				int idciudad = resultado.getInt("idciudad");
				String nombre = resultado.getString("nombre");
				int habitantes = resultado.getInt("habitantes");
				int idprovincia = resultado.getInt("idprovincia");
				Ciudad c = new Ciudad(idciudad, nombre, habitantes, idprovincia);
				ciudadesDevueltas.add(c);
			}
			resultado.close();
			sentencia.close();
			conexion.close();
			return ciudadesDevueltas;
		} catch (SQLException e) {
			return null;
		}
	}
	//-------------------------------------------------------
	public static boolean insertarCiudadTabla(Ciudad c)
	{
		Connection conexion = conectarConBaseDeDatos();
		if(conexion == null)
		{
			return false;
		}
		//----------------------------
		try {
			Statement st = conexion.createStatement();
		//	String ordensql = "insert into ciudades (idciudad, nombre, habitantes, idprovincia) values  (" + c.getIdciudad()+ ",'"+ c.getNombre()+ "',"+ c.getHabitantes()+","+ c.getIdprovincia() + ")";
			String ordensql = String.format("INSERT INTO `ciudadesdb`.`ciudades` (`nombre`, `habitantes`, `idprovincia`) VALUES ('%s', '%s', '%s');",c.getNombre(),c.getHabitantes(),c.getIdprovincia());
			int filasAfectadas = st.executeUpdate(ordensql);
			st.close();
			conexion.close();
			if(filasAfectadas > 0)
			{
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	//-----------------------------------------------------------
	public static boolean borrarCiudadTabla(Ciudad c)
	{
		Connection conexion = conectarConBaseDeDatos();
		if(conexion == null)
		{
			return false;
		}
		//----------------------------
		try {
			Statement st = conexion.createStatement();
			String ordensql = String.format("DELETE FROM `ciudadesdb`.`ciudades` WHERE `idciudad` = '%s';",c.getIdciudad());
			int filasAfectadas = st.executeUpdate(ordensql);
			st.close();
			conexion.close();
			if(filasAfectadas > 0)
			{
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	//---------------------------------------------------------------
	public static boolean actualizarCiudadTabla(Ciudad c)
	{
		Connection conexion = conectarConBaseDeDatos();
		if(conexion == null)
		{
			return false;
		}
		//----------------------------
		try {
			Statement st = conexion.createStatement();
			String ordensql = String.format("UPDATE `ciudadesdb`.`ciudades` SET `nombre` = '%s', `habitantes` = '%s', `idprovincia` = '%s' WHERE (`idciudad` = '%s');",c.getNombre(), c.getHabitantes(), c.getIdprovincia(), c.getIdciudad());
			int filasAfectadas = st.executeUpdate(ordensql);
			st.close();
			conexion.close();
			if(filasAfectadas > 0)
			{
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	//--------------------------------------------------------------
	public static Ciudad buscarCiudadTabla(String nombre)
	{
		Connection conexion = conectarConBaseDeDatos();
		if(conexion == null)
		{
			return null;
		}
		//---------------------------------
		Ciudad ciudadEncontrada = null;
		try {
			Statement sentencia = conexion.createStatement();
			String ordensql = String.format("select * from ciudades where nombre like '%s'", nombre);
			ResultSet resultadosql = sentencia.executeQuery(ordensql);
			//------------------------------------------------
			while(resultadosql.next())
			{
				int idciudad = resultadosql.getInt("idciudad");
				String nombreciudad = resultadosql.getString("nombre");
				int habitantes = resultadosql.getInt("habitantes");
				int idprovincia = resultadosql.getInt("idprovincia");
				ciudadEncontrada = new Ciudad(idciudad,nombreciudad, habitantes, idprovincia);
			}
			resultadosql.close();
			sentencia.close();
			conexion.close();
			return ciudadEncontrada;
		} catch (SQLException e) {
          return null;
		}
	}
//--------------------------------------------------------------

}
