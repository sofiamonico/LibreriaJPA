/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import java.util.Collection;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.AutorService;
import libreria.servicios.EditorialService;
import libreria.servicios.LibroService;

public class Libreria {

    public static void main(String[] args) throws Exception {
        try{
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        EditorialService es = new EditorialService();
        AutorService as = new AutorService();
        LibroService ls= new LibroService();
        //guardarLibro(leer,ls,as,es);
        //guardarAutor(leer,as);
        //modificarAutor(leer,as);
        //modificarLibro(leer,ls,as,es);
        //modificarEditorial(leer,es);
        //System.out.println("ACA TENES LOS LIBROS: \n" + buscarLibroPorAutor(leer,as,ls));
            System.out.println("ACA TENES LOS LIBROS: \n" + buscarLibroPorEditorial(leer,ls,es));
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public static void guardarLibro(Scanner leer, LibroService ls, AutorService as, EditorialService es) throws Exception{
        System.out.println("INGRESE EL NOMBRE DEl LIBRO");
        String nombre= leer.next();
        System.out.println("INGRESE EL AÑO DEL LIBRO");
        int anio = leer.nextInt();
        System.out.println("INGRESE EL NUMERO DE EJEMPLARES");
        int ejemplares= leer.nextInt();
        System.out.println("INGRESE EL NOMBRE DEL AUTOR");
        String nombreAutor= leer.next();
        System.out.println("INGRESE EL NOMBRE DE LA EDITORIAL");
        String nombreEditorial= leer.next();
        Autor autor = as.buscarAutorPorNombre(nombreAutor);
        Editorial editorial= es.buscarEditorialPorNombre(nombreEditorial);
        ls.crearLibro(nombre, anio, ejemplares, autor, editorial);
    }
    
    public static void modificarLibro(Scanner leer,LibroService ls, AutorService as, EditorialService es ) throws Exception{
        System.out.println("INGRESE EL NOMBRE DEL LIBRO QUE DESEA MODIFICAR");
        String nombreOrig = leer.next();
        System.out.println("INGRESE LA OPCION DE LO QUE DESEA MODIFICAR \n"+
                "1- NOMBRE \n"+
                "2-AÑO \n"+
                "3- N° DE EJEMPLARES \n"+
                "4-AUTOR \n" +
                "5-EDITORIAL \n"+
                "6-DAR DE BAJA \n" +
                "7- DAR DE ALTA \n");
        int opcion = leer.nextInt();
        switch(opcion){
            case 1:
               System.out.println("INGRESE EL NUEVO NOMBRE");
               String nombreNuevo=leer.next();
               ls.modificarNombreLibro(nombreOrig, nombreNuevo);
               break;
            case 2: 
                System.out.println("INGRESE EL NUEVO AÑO DEL LIBRO");
                int anio= leer.nextInt();
                ls.modificarAnioLibro(nombreOrig, anio);
                break;
            case 3:
                System.out.println("INGRESE EL NUEVO NUMERO DE EJEMPLARES");
                int ejemplares= leer.nextInt();
                ls.modificarEjemplaresLibro(nombreOrig, ejemplares);
                break;
            case 4:
                System.out.println("INGRESE EL NOMBRE DEL NUEVO AUTOR");
                String nombreAutor= leer.next();
                if(as.buscarAutorPorNombre(nombreAutor)== null){
                    throw new Exception("El autor que ingreso no existe");
                }
                Autor autor = as.buscarAutorPorNombre(nombreAutor);
                ls.modificarAutorLibro(nombreOrig, autor);
                break;
            case 5:
                System.out.println("INGRESE EL NOMBRE DE LA NUEVA EDITORIAL");
                String nombreEditorial= leer.next();
                if(es.buscarEditorialPorNombre(nombreEditorial)==null){
                    throw new  Exception("La editorial que ingreso no existe");
                }
                Editorial editorial= es.buscarEditorialPorNombre(nombreEditorial);
                ls.moficarEditorialLibro(nombreOrig, editorial);
                break;
            case 6:
                ls.bajaLibro(nombreOrig);
                break;
            case 7:
                ls.altaLibro(nombreOrig);
                break;
        }
                
    }
    
    public static Collection<Libro> buscarLibroPorAutor(Scanner leer, AutorService as, LibroService ls) throws Exception{
        System.out.println("INGRESE EL NOMBRE DEL AUTOR PARA VER LOS LIBROS QUE TIENE");
        String nombre=leer.next();
        Autor autor = as.buscarAutorPorNombre(nombre);
        return ls.buscarLibroPorAutor(autor);
        
    }
    
    public static Collection<Libro> buscarLibroPorEditorial(Scanner leer,LibroService ls, EditorialService es) throws Exception{
        System.out.println("INGRESE EL NOMBRE DE LA EDITORIAL QUE DESEA VER LOS LIBROS");
        String nombre=leer.next();
        Editorial editorial= es.buscarEditorialPorNombre(nombre);
        return ls.buscarLibroPorEditorial(editorial);
    }
    
    public static void guardarAutor(Scanner leer, AutorService as){
        
        System.out.println("INGRESE EL NOMBRE DEl AUTOR");
        String nombre= leer.next();
        as.crearAutor(nombre);
    }
    
    
    public static void modificarAutor(Scanner leer, AutorService as) throws Exception{
        System.out.println("INGRESE EL NOMBRE DEL AUTOR QUE DESEA MODIFICAR");
        String nombreOriginal=leer.next();
        System.out.println("INGRESE LA OPCION DE LO QUE DESEA MODIFICAR \n"+
                "1- NOMBRE \n"+
                "2-DAR DE BAJA \n"+
                "3- DAR DE ALTA");
       String orden = leer.next();
       switch(orden){
           case "1":
               System.out.println("INGRESE EL NUEVO NOMBRE");
               String nombreNuevo=leer.next();
               as.modificarNombreAutor(nombreOriginal, nombreNuevo);
               break;
           case "2":
               as.altaAutor(nombreOriginal);
               break;
           case "3":
               as.bajaAutor(nombreOriginal);
               break;           
       }
    }
    
    public static void modificarEditorial(Scanner leer, EditorialService es) throws Exception{
        System.out.println("INGRESE EL NOMBRE DE LA EDITORIAL QUE DESEA MODIFICAR");
        String nombreOriginal= leer.next();
        System.out.println("INGRESE LA OPCION DE LO QUE DESEA MODIFICAR \n"+
                "1- NOMBRE \n"+
                "2-DAR DE BAJA \n"+
                "3- DAR DE ALTA");
        String orden= leer.next();
        switch(orden){
            case "1":
                System.out.println("INGRESE EL NUEVO NOMBRE");
               String nombreNuevo=leer.next();
               es.modificarNombreEditorial(nombreOriginal, nombreNuevo);
               break;
            case "2":
                es.bajaAutor(nombreOriginal);
                break;
            case "3":
                es.altaAutor(nombreOriginal);
                break;
        }
    }
   
    
}
