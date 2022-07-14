/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.persistencia.DAOautor;

/**
 *
 * @author sofia
 */
public class AutorService {

    private DAOautor dao = new DAOautor();

    public void crearAutor(String nombre) {

        try {
            if (nombre.isEmpty()) {
                throw new Exception("Debe indicar un nombre");
            }
            validarDisponibilidadNombre(nombre);
            Autor autor = new Autor();
            autor.setNombre(nombre);
            autor.setAlta(true);
            dao.guardarAutor(autor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Autor buscarAutorPorNombre(String nombre) throws Exception {
        return dao.buscarAutorPorNombre(nombre);
    }

    public Autor buscarAutorPorID(int id) throws Exception {
        return dao.buscarAutorPorID(id);
    }

    private void validarDisponibilidadNombre(String nombre) throws Exception {
        if (buscarAutorPorNombre(nombre) != null) {
            throw new Exception("Ya existe un autor con el nombre brindado");
        };
    }

    public void modificarNombreAutor(String nombreOrig, String newNombre) throws Exception {
        Autor autor = buscarAutorPorNombre(nombreOrig);
        validarDisponibilidadNombre(newNombre);
        autor.setNombre(newNombre);
        dao.modificarAutor(autor);
    }
    
    //public int devolverIDAutor(String nombre) throws Exception{
      //  Autor autor = buscarAutorPorNombre(nombre);
        //return dao.autor.getId();
    //}

    public void bajaAutor(String nombre) throws Exception {
        Autor autor = buscarAutorPorNombre(nombre);
        if (autor.isAlta() == false) {
            throw new Exception("El autor ya fue dado de baja");
        }
        autor.setAlta(false);
        dao.modificarAutor(autor);
    }

    public void altaAutor(String nombre) throws Exception {
        Autor autor = buscarAutorPorNombre(nombre);
        if (autor.isAlta()) {
            throw new Exception("El autor ya fue dado de alta");
        }
        autor.setAlta(true);
        dao.modificarAutor(autor);
    }

}
