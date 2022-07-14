/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;


import java.util.Collection;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.DAOlibro;

/**
 *
 * @author sofia
 */
public class LibroService {
    
    private DAOlibro dao = new DAOlibro();
    
    public void crearLibro(String nombre, Integer anio, Integer ejemplares, Autor autor, Editorial editorial){
       try {
            if (anio==null) {
                throw new Exception("Debe indicar el año del libro");
            }
            if(ejemplares==null){
                throw new Exception("Debe indicar el numero de ejemplares");
            }
            if(nombre.isEmpty()){
                throw new Exception("Debe indicar un nombre");
            }
             if(editorial==null){
                 throw new Exception("Debe ingresar una editorial");
             }
            if(dao.buscarLibroPorNombre(nombre) != null){
                throw new Exception("Este autor ya existe");
            }
            if(autor==null){
                throw new Exception("Debe ingresar un autor");
            }
           
         Libro libro= new Libro();
         libro.setTitulo(nombre);
         libro.setAnio(anio);
         libro.setEjemplares(ejemplares);
         libro.setAlta(true);
         libro.setEjemplaresPrestados(0);
         libro.setEjemplaresRestantes(ejemplares);
         libro.setAutor(autor);
         libro.setEditorial(editorial);
         
         dao.guardarLibro(libro);
         
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public Libro buscarLibroPorNombre(String nombre) throws Exception{
        return dao.buscarLibroPorNombre(nombre);
    }
    
    public Libro buscarLibroPorID(int id) throws Exception{
        return dao.buscarLibroPorID(id);
    }
    
    public void modificarNombreLibro(String nombreOriginal, String nombre) throws Exception{
        Libro libro = buscarLibroPorNombre(nombreOriginal);
        libro.setTitulo(nombre);
        dao.modificarLibro(libro);
    }
    
    public void modificarAnioLibro(String nombreOriginal, int anio) throws Exception{
        Libro libro = buscarLibroPorNombre(nombreOriginal);
        libro.setAnio(anio);
        dao.modificarLibro(libro);
    }
    
    public void modificarEjemplaresLibro(String nombreOriginal, int ejemplares) throws Exception{
        Libro libro = buscarLibroPorNombre(nombreOriginal);
        libro.setEjemplares(ejemplares);
        dao.modificarLibro(libro);
    }
    
    public void modificarAutorLibro(String nombreOriginal, Autor autor) throws Exception{
        Libro libro = buscarLibroPorNombre(nombreOriginal);
        libro.setAutor(autor);
        dao.modificarLibro(libro);
    }
    
    public void moficarEditorialLibro(String nombreOriginal, Editorial editorial) throws Exception{
        Libro libro = buscarLibroPorNombre(nombreOriginal);
        libro.setEditorial(editorial);
        dao.modificarLibro(libro);
    }
    
    public Collection<Libro> buscarLibroPorAutor(Autor autor) throws Exception{
        return dao.buscarLibroPorAutor(autor);
    }
    
    public Collection<Libro> buscarLibroPorEditorial(Editorial editorial) throws Exception{
        return dao.buscarLibroPorEditorial(editorial);
    }
    
    public void bajaLibro(String nombre) throws Exception {
        Libro libro = buscarLibroPorNombre(nombre);
        if (libro.isAlta() == false) {
            throw new Exception("El libro ya fue dado de baja");
        }
        libro.setAlta(false);
        dao.modificarLibro(libro);
    }
    
    public void altaLibro(String nombre) throws Exception {
        Libro libro = buscarLibroPorNombre(nombre);
        if (libro.isAlta()) {
            throw new Exception("El libro ya fue dado de alta");
        }
        libro.setAlta(true);
        dao.modificarLibro(libro);
    }
    
    
    
}
