/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.entidades.Autor;


/**
 *
 * @author sofia
 */
public class DAOautor {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private EntityManager em = emf.createEntityManager();

    public void guardarAutor(Autor autor) throws Exception {
        try {
            if (autor == null) {
                throw new Exception("Debe indicar un autor");
            }

            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }

    }

    public void modificarAutor(Autor autor) throws Exception {
        try {
            if (autor == null) {
                throw new Exception("Debe indicar un autor");
            }

            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }
    }

    public Autor buscarAutorPorID(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar un ID");
            }

            return em.find(Autor.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }
    }
   
    

    
    public Autor buscarAutorPorNombre(String nombre) throws Exception {
        try {
            if (nombre.isEmpty()) {
                throw new Exception("Debe indicar un nombre");
            }
            return (Autor) em.createQuery(
                        "SELECT a FROM Autor a WHERE a.nombre LIKE :nombre" 
                    ).setParameter("nombre", nombre).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void borrarAutor(Autor autor) throws Exception {
        try {
            if (autor == null) {
                throw new Exception("Debe indicar un autor");
            }

            em.getTransaction().begin();
            em.remove(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }
    }
}
