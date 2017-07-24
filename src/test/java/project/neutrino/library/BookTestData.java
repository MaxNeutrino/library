package project.neutrino.library;

import project.neutrino.library.model.Book;

public class BookTestData {

    public static int ID_ONE = 1;
    public static int ID_TWO = 2;
    public static int ID_THREE = 3;
    public static int ID_FOUR = 4;

    public static String NAME_ONE = "one";
    public static String NAME_TWO = "two";
    public static String NAME_THREE = "three";
    public static String NAME_FOUR = "threeName";

    public static Book BOOK_ONE = new Book(ID_ONE, NAME_ONE);
    public static Book BOOK_TWO = new Book(ID_TWO, NAME_TWO);
    public static Book BOOK_THREE = new Book(ID_THREE, NAME_THREE);
    public static Book BOOK_FOUR = new Book(ID_FOUR, NAME_FOUR);
}
