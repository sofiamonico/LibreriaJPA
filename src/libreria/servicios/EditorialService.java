/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.util.Scanner;
import java.util.UUID;
import libreria.entidades.Editorial;
import libreria.persistencia.DAOeditorial;

/**
 *
 * @author sofia
 */
public class EditorialService {
    private DAOeditorial dao= new DAOeditorial();
    
    public void crearEditorial() throws Exception{
        Scanner leer = new Scanner (System.in);
        System.out.println("INGRESE EL NOMBRE DE LA EDITORIAL");
        String nombre= leer.next();
        try {
            if(nombre.isEmpty()){
                throw new Exception("Debe indicar un nombre");
            }
            validarDisponibilidadNombre(nombre);
         Editorial editorial= new Editorial();
         editorial.setNombre(nombre);
         editorial.setAlta(true);
         dao.guardarEditorial(editorial);
        } catch (Exception e) {
            //e.printStackTrace();
            throw e;
        }
    }
    
    private void validarDisponibilidadNombre(String nombre) throws Exception {
        if (buscarEditorialPorNombre(nombre) != null) {
            throw new Exception("Ya existe una editorial con el nombre brindado");
        };
    }
    
    public Editorial buscarEditorialPorNombre(String nombre) throws Exception{
        return dao.buscarEditorialPorNombre(nombre);
    }
    
    public void modificarNombreEditorial(String nombreOriginal, String nombreNuevo) throws Exception{
        Editorial editorial = buscarEditorialPorNombre(nombreOriginal);
        validarDisponibilidadNombre(nombreNuevo);
        editorial.setNombre(nombreNuevo);
        dao.modificarEditorial(editorial);
    }
    
    public void bajaAutor(String nombre) throws Exception {
        Editorial editorial = buscarEditorialPorNombre(nombre);
        if (editorial.isAlta() == false) {
            throw new Exception("El autor ya fue dado de baja");
        }
        editorial.setAlta(false);
        dao.modificarEditorial(editorial);
    }

    public void altaAutor(String nombre) throws Exception {
        Editorial editorial = buscarEditorialPorNombre(nombre);
        if (editorial.isAlta()) {
            throw new Exception("El autor ya fue dado de alta");
        }
        editorial.setAlta(true);
        dao.modificarEditorial(editorial);
    }
    
    
}
