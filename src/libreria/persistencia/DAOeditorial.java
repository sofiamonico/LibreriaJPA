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
import libreria.entidades.Editorial;

/**
 *
 * @author sofia
 */
public class DAOeditorial {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private EntityManager em = emf.createEntityManager();

    public void guardarEditorial(Editorial editorial) throws Exception {
        try {
            if (editorial == null) {
                throw new Exception("Debe indicar un autor");
            }

            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }

    }

    public Editorial buscarEditorialPorNombre(String nombre) throws Exception {
        try {
            if (nombre.isEmpty()) {
                throw new Exception("Debe indicar un nombre");
            }
            return (Editorial) em.createQuery(
                        "SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre" 
                    ).setParameter("nombre", nombre).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void modificarEditorial(Editorial editorial) throws Exception {
        try {
            if (editorial == null) {
                throw new Exception("Debe indicar un autor");
            }

            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }
    }

    public Editorial buscarEditorialPorID(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar un ID");
            }

            return em.find(Editorial.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }
    }

    public void borrarEditorial(Editorial editorial) throws Exception {
        try {
            if (editorial == null) {
                throw new Exception("Debe indicar un autor");
            }

            em.getTransaction().begin();
            em.remove(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }
    }

}
