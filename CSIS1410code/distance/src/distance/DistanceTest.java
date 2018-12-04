package distance;

/**
 * This class will test Distance objects
 */
public class DistanceTest
{
    /**
     * Main method to setup some distance objects and perform operations to test them.
     *
     * @param args arguments sent from the command line to the method.
     */
    public static void main(String[] args)
    {
        Distance d0 = new Distance(3, 10);
        Distance d1 = new Distance(3, 10);
        System.out.printf("Is d0 equal to d1? %s\n", d0.equals(d1));
        System.out.printf("Is d0 the same object as d1? %s\n", d0 == d1);
        System.out.printf("Does d0 hash the same as d1? %s (d0 hash: %d) (d1 hash: %d)\n", d0.hashCode()==d1.hashCode(), d0.hashCode(), d1.hashCode());
        System.out.printf("Created new Distance object with Distance of: %s\n", d1);
        Distance d2 = new Distance(4, 18);
        System.out.printf("Created new Distance object with Distance of: %s\n", d2);
        d1.add(d2);
        System.out.printf("Add second distance to first distance. Result: %s\n", d1);
        d1.sub(d2);
        System.out.printf("Subtract our second distance from first distance to get original first distance. Result: %s\n", d1);
    }

}
