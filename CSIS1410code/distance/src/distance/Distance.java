package distance;

import java.util.Objects;

public class Distance
{
    private int feet;
    private int inches;

    /**
     * Default constructor
     */
    public Distance()
    {
    }

    /**
     * Parameterized constructor initializes this object to provided distances
     *
     * @param feet
     * @param inches
     */
    public Distance(int feet, int inches)
    {
        setFeet(feet);
        setInches(inches);
    }

    /**
     * Getter method for feet property
     *
     * @return feet
     */
    public int getFeet()
    {
        return feet;
    }

    /**
     * Getter method for inches property
     *
     * @return inches
     */
    public int getInches()
    {
        return inches;
    }

    /**
     * Setter method for feet property.
     *
     * @param feet
     */
    public void setFeet(int feet)
    {
        this.feet = feet;
    }

    /**
     * Setter method for inches property.
     *
     * @param inches
     */
    public void setInches(int inches)
    {
        this.inches = inches;
        if (this.inches >= 12)
        {
            this.feet += this.inches / 12;
            this.inches = this.inches % 12;
        }
    }

    /**
     * Add one distance to this distance.
     *
     * @param d The distance to add to this distance.
     * @return This distance after d has been added to it.
     */
    public Distance add(Distance d)
    {
        Distance dToAdd = d;
        this.feet += dToAdd.getFeet();
        this.inches += dToAdd.getInches();

        if (this.inches >= 12)
        {
            this.feet += this.inches / 12;
            this.inches = this.inches % 12;
        }

        return this;
    }

    /**
     * Subtract one distance from this distance.
     *
     * @param d The distance to subtract from this distance.
     * @return This distance after d has been added to it.
     */
    public Distance sub(Distance d)
    {
        Distance dToSub = d;
        this.feet -= dToSub.getFeet();
        this.inches -= dToSub.getInches();

        if (this.inches < 0)
        {
            int x = ((this.inches * -1) / 12) + 1;
            this.feet -= x;
            this.inches = (this.inches + (12 * x));
        }
        return this;
    }

    /**
     * Overridden hashCode method. Call Objects.hash() to hash the object.
     *
     * @return an integer representing the hash index of this object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(feet, inches);
    }

    /**
     * Overridden equals method.
     *
     * @param other an object to compare to this distance.
     * @return whether or not this distance is the same as obj.
     */
    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null || getClass() != other.getClass())
        {
            return false;
        }
        Distance distance = (Distance) other;
        return feet == distance.feet && inches == distance.inches;
    }

    /**
     * Overridden toString method
     *
     * @return A string representation of this distance.
     */
    @Override
    public String toString()
    {
        return String.format("%d' %d\"", getFeet(), getInches());
    }

}
