package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ManejadorDB {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("miUnidad");

    public void guardarCambios(Object objeto) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al guardar en la base de datos.");
        } finally {
            em.close();
        }
    }

    public Pou cargarPou(Long id) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.find(Pou.class, id);
        } finally {
            em.close();
        }
    }

    public List<Alimento> listaComidas() {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("FROM Alimento", Alimento.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void desconectar() {
        if (factory != null) {
            factory.close();
        }
    }
}