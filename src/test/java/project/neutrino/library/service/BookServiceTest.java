package project.neutrino.library.service;

import org.junit.Assert;
import org.junit.Test;
import project.neutrino.library.BookTestData;
import project.neutrino.library.model.Book;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BookServiceTest extends AbstractServiceTest {

    @Test
    public void testFind() {
        Book first = bookService.find(BookTestData.ID_ONE);
        Assert.assertEquals(first, BookTestData.BOOK_ONE);

        Book second = bookService.find(BookTestData.NAME_TWO).get(0);
        Assert.assertEquals(BookTestData.BOOK_TWO, second);
    }

    @Test
    public void testFindByNameContaining() {
        String chars = BookTestData.NAME_THREE.substring(0, 3);
        List<Book> books = bookService.findByNameContaining(chars);
        Assert.assertEquals(Arrays.asList(BookTestData.BOOK_THREE, BookTestData.BOOK_FOUR), books);
    }

    @Test
    public void testFindAll() {
        List<Book> books = bookService.findAll();
        List<Book> booksTestData = Arrays.asList(
                BookTestData.BOOK_ONE,
                BookTestData.BOOK_TWO,
                BookTestData.BOOK_THREE,
                BookTestData.BOOK_FOUR
        );

        booksTestData.sort(Comparator.comparing(Book::getName));
        Assert.assertEquals(booksTestData, books);

    }

    @Test
    public void testCreate() {
        Book toSave = new Book("newBook");
        int id = bookService.save(toSave);
        toSave.setId(id);

        List<Book> books = bookService.findAll();
        List<Book> booksTestData = Arrays.asList(
                BookTestData.BOOK_ONE,
                BookTestData.BOOK_TWO,
                BookTestData.BOOK_THREE,
                BookTestData.BOOK_FOUR,
                toSave
        );

        booksTestData.sort(Comparator.comparing(Book::getName));
        Assert.assertEquals(booksTestData, books);
    }

    @Test
    public void testUpdate() {
        Book toUpdate = BookTestData.BOOK_ONE;
        toUpdate.setName("updated");
        bookService.save(toUpdate);

        Book updated = bookService.find(BookTestData.ID_ONE);
        Assert.assertEquals(toUpdate, updated);
    }

    @Test
    public void testDelete() {
        bookService.delete(BookTestData.ID_FOUR);

        List<Book> books = bookService.findAll();
        List<Book> booksTestData = Arrays.asList(
                BookTestData.BOOK_ONE,
                BookTestData.BOOK_TWO,
                BookTestData.BOOK_THREE
        );

        booksTestData.sort(Comparator.comparing(Book::getName));
        Assert.assertEquals(booksTestData, books);
    }
}
