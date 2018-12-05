/********************************************************
 *
 *  Project :  M5A4
 *  File    :  Person.java
 *  Name    :  Steven Rollman
 *
 *  Description : A Serializable Person class with a name and cellPhone attribute.
 *
 ********************************************************/

package M5A4;

import java.io.*;

public class Person implements Serializable
{

    private String name;
    private String cellPhone;

    public Person(String name, String cellPhoneNumber)
    {
        setName(name);
        setCellPhone(cellPhoneNumber);
    }

    @Override
    public String toString()
    {
        return String.format("Name: %-30s\t%s",  getName(), getCellPhone());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCellPhone()
    {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone)
    {
        this.cellPhone = cellPhone;
    }
}
