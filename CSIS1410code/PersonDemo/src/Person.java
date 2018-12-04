import java.util.Objects;

public class Person
{
    private int age;
    private String name;

    /**
     * Getter method for Age property.
     *
     * @return an int representing the age of this Person
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Setter method for the Age property.
     *
     * @param age an int to set as the Age for this Person.
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Getter method for Name property.
     *
     * @return a String representing the name of this Person
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter method for the Name property.
     *
     * @param name a String to set as the Name for this Person.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Compares this Person with another object to see if they are equal
     *
     * @param other an object to compare this Person to
     * @return true if other equals this Person
     */
    @Override
    public boolean equals(Object other)
    {
        try
        {
            if (this == other)
            {
                return true;
            }
            if (other == null || getClass() != other.getClass())
            {
                return false;
            }
            Person person = (Person) other;
            return age == person.age && Objects.equals(name, person.name);
        }
        finally
        {
            return false;
        }

    }

    /**
     * Overridden hashCode method calls Objects.hash() with our values
     *
     * @return An int representing the hash of our object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(age, name);
    }

    public static void main(String[] args)
    {

    }
}
