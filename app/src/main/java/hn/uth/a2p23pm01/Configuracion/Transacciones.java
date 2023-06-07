package hn.uth.a2p23pm01.Configuracion;

public class Transacciones {
    public static final String NameDataBase="PM01";
    public static final String TablaPersonas="personas";
    public static final String id="id";
    public static final String nombres="nombres";
    public static final String apellidos="apellidos";
    public static final String edad="edad";
    public static final String correo="correo";
    public static final String CreateTablePersona="CREATE TABLE PERSONAS"+
            "(id INTEGER PRIMARY KEY AUTOINCREMENT,nombres TEXT , apellidos TEXT,edad INTEGER,"+
            "correo TEXT)";
        public static final String DROPTablePersonas="DROP TABLE IF EXISTS personas";

        public static  final  String SelectTablePersona="SELECT * FROM "+ Transacciones.TablaPersonas;
}
