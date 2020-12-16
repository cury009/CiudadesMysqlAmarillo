import java.util.ArrayList;

public class PruebaCiudades {

	public static void main(String[] args) {
	/*	Connection conexion = FicheroCiudadMysql.conectarConBaseDeDatos();
		if(conexion != null)
		{
			System.out.println("conexion establecida");
		}
		else
		{
			System.out.println("error al conectar");
		}*/
	 ArrayList<Ciudad> ciudadesDevueltas = FicheroCiudadMysql.obtenerCiudades();
	 if(ciudadesDevueltas != null)
	 {
		 for (Ciudad c : ciudadesDevueltas) {
			System.out.println(c.toString());
		}
	 }
	 Ciudad c1 = new Ciudad(0,"bargas", 20000, 1 );
	 boolean insertadoOK = FicheroCiudadMysql.insertarCiudadTabla(c1);
	 if(insertadoOK)
	 {
		 System.out.println("insertado correctamente la ciudad .... ");
		 System.out.println(c1.toString());
	 }
	 else {
		 System.out.println("error al insertar la ciudad .... ");
		 System.out.println(c1.toString());		 
	 }
	 //---------------------------------------------
	 c1.setIdciudad(2);
	 c1.setNombre("ciudad nueva");
	 c1.setHabitantes(32000);
	 c1.setIdprovincia(1);
	 //---------------------------------------------
	 boolean actualizadoOK = FicheroCiudadMysql.actualizarCiudadTabla(c1);
	 if(actualizadoOK)
	 {
		 System.out.println("actualizado correctamente la ciudad .... ");
		 System.out.println(c1.toString());
	 }
	 else {
		 System.out.println("error al actualizar la ciudad .... ");
		 System.out.println(c1.toString());		 
	 }
	 //------------------------------------------------------------------
	 //---------------------------------------------
	 boolean borradoOK = FicheroCiudadMysql.borrarCiudadTabla(c1);
	 if(borradoOK)
	 {
		 System.out.println("borrado correctamente la ciudad .... ");
		 System.out.println(c1.toString());
	 }
	 else {
		 System.out.println("error al borrar la ciudad .... ");
		 System.out.println(c1.toString());		 
	 }
	 
	 //---------------------------------------------
	 Ciudad c3 = FicheroCiudadMysql.buscarCiudadTabla("seseña");
	 if(c3 != null)
	 {
		 System.out.println("encontrada la ciudad .... ");
		 System.out.println(c3.toString());
	 }
	 else {
		 System.out.println("no encontrada la ciudad .... ");	 
	 }
	}

}
