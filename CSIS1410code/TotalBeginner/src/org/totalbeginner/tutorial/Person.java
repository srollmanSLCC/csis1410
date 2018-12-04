package org.totalbeginner.tutorial;

public class Person
{
    // fields
    private String name; // name of the person
    private int maximumBooks; // most books the person can check out

    // constructors
    public Person()
    {
        maximumBooks = 3;
        name = "unknown name";
    }

    // methods
    public int getMaximumBooks()
    {
        return maximumBooks;
    }

    public String getName()
    {
        return name;
    }

    public void setMaximumBooks(int maximumBooks)
    {
        this.maximumBooks = maximumBooks;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return String.format("%s (%d books)", this.getName(),this.getMaximumBooks());
    }
}
