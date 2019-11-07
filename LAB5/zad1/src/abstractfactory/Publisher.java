package abstractfactory;

import books.Book;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Publisher {
    private static Map<String, Class> authorToPublisher = Stream.of(new Object[][]{
            {"Józef Ignacy Kraszewski", PublisherHistoricalNovel.class},
            {"Adam Mickiewicz", PublisherPoem.class},
            {"Marcin Wroński", PublisherThriller.class},
            {"Ryszard Ćwirlej", PublisherThriller.class},
            {"Juliusz Słowacki", PublisherPoem.class},
            {"Henryk Sienkiewicz", PublisherHistoricalNovel.class}
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Class) data[1]));

    public static Publisher getInstance(String name) throws ReflectiveOperationException {
        Class<?> myClassType = authorToPublisher.get(name);
        if (myClassType == null)
            throw new NoSuchFieldException("Author " + name + " doesn't appear on any publisher");
        Class<?>[] types = new Class[]{String.class};
        Constructor<?> cons = myClassType.getConstructor(types);
        return (Publisher) cons.newInstance(name);
    }

    public abstract Book createBook(String title, int pages);
}
