package org.totalbeginner.tutorial;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyLibrary
{

    public String name;
    public ArrayList<Book> books;
    public ArrayList<Person> people;

    public MyLibrary(String name)
    {
        this.name = name;
        books = new ArrayList<>();
        people = new ArrayList<>();
    }

    public static void main(String[] args)
    {
        // create a new MyLibrary instance
        MyLibrary testLibrary = new MyLibrary("Test Drive Library");
        Book b1 = new Book("War And Peace");
        Book b2 = new Book("Great Expectations");
        b1.setAuthor("Tolstoy");
        b2.setAuthor("Dickens");
        Person jim = new Person();
        Person sue = new Person();
        jim.setName("Jim");
        sue.setName("Sue");

        testLibrary.addBook(b1);
        testLibrary.addBook(b2);
        testLibrary.addPerson(jim);
        testLibrary.addPerson(sue);

        System.out.println("Just created new library");
        testLibrary.printStatus();

        System.out.println("Check out War And Peace to Sue");
        testLibrary.checkOut(b1, sue);
        testLibrary.printStatus();

        System.out.println("Do some more stuff");
        testLibrary.checkIn(b1);
        testLibrary.checkOut(b2, jim);
        testLibrary.printStatus();

    }

    private void printStatus()
    {
        System.out.printf("Status Report of MyLibrary \n%s\n", this.toString());
        for (Book book : this.getBooks())
        {
            System.out.println(book);
        }

        for (Person person : this.getPeople())
        {
            int count = this.getBooksForPerson(person).size();
            System.out.printf("%s (has %d of my books)\n", person, count);
        }

        System.out.printf("Books Available: %d\n", this.getAvailableBooks().size());
        System.out.println("--- End of Status Report ---");
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Book> getBooks()
    {
        return books;
    }

    public ArrayList<Person> getPeople()
    {
        return people;
    }

    public void addBook(Book book)
    {
        books.add(book);
    }

    public void addPerson(Person person)
    {
        people.add(person);
    }

    public void removeBook(Book book)
    {
        books.remove(book);
    }

    public void removePerson(Person person)
    {
        people.remove(person);
    }

    public boolean checkOut(Book book, Person person)
    {
        int booksOut = this.getBooksForPerson(person).size();
        if (book.getPerson() == null && booksOut < person.getMaximumBooks())
        {
            book.setPerson(person);
            return true;
        }
        return false;
    }

    public boolean checkIn(Book book)
    {
        if (book.getPerson() != null)
        {
            book.setPerson(null);
            return true;
        }
        return false;
    }

    public ArrayList<Book> getAvailableBooks()
    {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book: this.getBooks())
        {
            if (book.getPerson() == null)
            {
                result.add(book);
            }
        }
        return result;
    }

    public ArrayList<Book> getUnavailableBooks()
    {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book: this.getBooks())
        {
            if (book.getPerson() != null)
            {
                result.add(book);
            }
        }
        return result;
    }

    public ArrayList<Book> getBooksForPerson(Person person)
    {
        ArrayList<Book> result = new ArrayList<>();

        for (Book book: this.getBooks())
        {
            if (book.getPerson() != null &&
                book.getPerson().getName().equals(person.getName()))
            {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public String toString()
    {
        return String.format("%s: %d books; %d people.", this.getName(), this.getBooks().size(), this.getPeople().size());
    }
}
