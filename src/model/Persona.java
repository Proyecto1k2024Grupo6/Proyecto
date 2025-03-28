package model;

public class Persona {
    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;

    //este constructor nos permite leer los datos de la base de datos
    public Persona(int id,String dni, String nombre, String apellido, int edad) {
        this.id=id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
    //este contructor nos permite crear una Persona en la base de datos.
    //el id lo genera automático Mysql
    public Persona(String dni, String nombre, String apellido, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
    //gette y setter
}