import abstractfactory.Publisher;
import books.Book;
import decorators.BookWithAutographDecorator;
import decorators.BookWithDustCoverDecorator;
import decorators.BookWithHardCoverDecorator;
import decorators.BookWithSoftCoverDecorator;
import interfaces.Publication;

public class Main {
    public static void main(String[] args) {
        Publication k1 = new Book("Adam Mickiewicz", "Pan Tadeusz", 340);
        Publication k2 = new Book("Adam Mickiewicz", "Dziady", 130);

        Publication kk1 = new BookWithSoftCoverDecorator(k1);
        Publication kk2 = new BookWithHardCoverDecorator(k2);

//        Publication fakeBook = new decorators.BookWithDustCoverDecorator(k1);
        Publication kkk2 = new BookWithDustCoverDecorator(kk2);
//        Publication odrzut = new decorators.BookWithDustCoverDecorator(kkk2);
        Publication dziadyZAutografemWieszcza =
                new BookWithAutographDecorator(kkk2, "Drogiej Hani - Adam Mickiewicz");

        System.out.println(dziadyZAutografemWieszcza);

//        Publication dziadyZDwomaAutografami =
//                new BookWithAutographDecorator(dziadyZAutografemWieszcza,
//                        "Haniu, to nieprawda, Dziady napisałem ja, Julek Słowacki!");

        Publisher p = null;
        try {
            p = Publisher.getInstance("Henryk Sienkiewicz");
            Book book = p.createBook("Ogniem i mieczem", 280);
            System.out.println(book);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}
