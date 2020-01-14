package net.codejava;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BooksManager {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookUnit");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        Book newBook = new Book();
//        newBook.setTitle("Effective Java");
//        newBook.setAuthor("Joshua Bloch");
//        newBook.setPrice(39);
        newBook.setTitle("Book");
        newBook.setAuthor("Author");
        newBook.setPrice(99);
        entityManager.persist(newBook);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }
}
