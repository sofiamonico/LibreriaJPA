/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.persistencia;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;

/**
 *
 * @author sofia
 */
public class DAOlibro {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private EntityManager em = emf.createEntityManager();

    public void guardarLibro(Libro libro) throws Exception {
        try {
            if (libro == null) {
                throw new Exception("Debe indicar un autor");
            }

            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }

    }

    public void modificarLibro(Libro libro) throws Exception {
        try {
            if (libro == null) {
                throw new Exception("Debe indicar un autor");
            }

            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }
    }

    public Libro buscarLibroPorNombre(String titulo) throws Exception {
        try {
            if (titulo.isEmpty()) {
                throw new Exception("Debe indicar un nombre");
            }
            return (Libro) em.createQuery(
                    "SELECT l FROM Libro l WHERE l.titulo LIKE :titulo"
            ).setParameter("titulo", titulo).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Libro> buscarLibroPorAutor(Autor autor) throws Exception {
        try{
            if (autor == null) {
                throw new Exception("Debe indicar un autor existente");
            }
            
            Collection<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor = :autor"
            ).setParameter("autor", autor).getResultList();
            
            return libros;
            }catch (Exception e){
            e.printStackTrace();
        }
            return null;
        }
    
    public Collection<Libro> buscarLibroPorEditorial(Editorial editorial) throws Exception{
        try{
            if (editorial == null) {
                throw new Exception("Debe indicar una Editorial existente");
            }
            Collection<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.editorial = :editorial"
            ).setParameter("editorial", editorial).getResultList();
            
            return libros;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Libro buscarLibroPorID(Integer id) throws Exception {
        try {
            if (id == null) {
                throw new Exception("Debe indicar un ID");
            }

            return em.find(Libro.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }
    }

    public void borrarAutor(Libro libro) throws Exception {
        try {
            if (libro == null) {
                throw new Exception("Debe indicar un autor");
            }

            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error de sistema");
        }
    }
}
