package org.totalbeginner.tutorial;


public class Book
{
    public String author;
    public String title;
    public Person person;

    public Book(String title)
    {
        this.title = title;
        this.author = "unknown author";
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    public Person getPerson()
    {
        return person;
    }

    @Override
    public String toString()
    {
        String checkOutStatus = this.getPerson() != null ?
            "Checked out to " + this.getPerson().getName() :
            "Available";

        return String.format("%s by %s; %s", this.getTitle(), this.getAuthor(), checkOutStatus);
    }
}
