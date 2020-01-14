package net.codejava;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class BooksManager {

    static EntityManagerFactory factory;
    static EntityManager entityManager;

    public static void main(String[] args) {
        begin();
       // create();
       // update();
       // find();
       // query();
        remove();
        end();
    }

    private static void begin() {
        factory = Persistence.createEntityManagerFactory("BookUnit");
        entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    private static void remove() {
        Integer primaryKey = 3;
        Book reference = entityManager.getReference(Book.class, primaryKey);
        entityManager.remove(reference);
    }

    private static void query() {
        String jpql = "Select b From Book b Where b.price < 30";
        Query query = entityManager.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<Book> listBooks = query.getResultList();
        listBooks.forEach(book -> System.out.println(book.getTitle() + " " + book.getAuthor() + " " + book.getPrice()));
    }

    private static void find() {
        Integer primaryKey = 1;
        Book book = entityManager.find(Book.class, primaryKey);
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor());
        System.out.println(book.getPrice());
    }

    private static void create() {
        Book newBook = new Book();
        newBook.setTitle("Thinking in Java");
        newBook.setAuthor("Bruce Eckel");
        newBook.setPrice(45);
        entityManager.persist(newBook);
    }

    private static void update() {
        Book existBook = new Book();
        existBook.setBookId(1);
        existBook.setTitle("Effective Java (2nd edition)");
        existBook.setAuthor("Joshua Bloch");
        existBook.setPrice(19);
        entityManager.merge(existBook);
    }

    private static void end() {
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }
}
