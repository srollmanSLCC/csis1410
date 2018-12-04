package M6A2;

import junit.framework.TestCase;

public class LinkedStackTest extends TestCase
{
    /**
     * Test method for LinkedStack<T>
     */
    public void testClass()
    {
        // Test empty Stack
        LinkedStack<String> s = new LinkedStack<>();
        assertEquals(0, s.size());

        // Test adding some elements.
        s.push("A");
        assertEquals(1, s.size());
        assertEquals("A", s.peek());
        s.push("B");
        assertEquals(2, s.size());
        assertEquals("B", s.peek());

        // Test peeking.
        String str = s.peek();
        assertEquals("B", str);

        // Test popping.
        str = s.pop();
        assertEquals("B", str);

        // Test if we have an empty stack.
        assertEquals(false, s.isEmpty());

        // Pop
        str = s.pop();
        assertEquals("A", str);

        // Empty stack
        assertEquals(true, s.isEmpty());

        str = s.peek();
    }
}
