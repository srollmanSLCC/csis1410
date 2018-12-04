package M6A2;

import M6A2.LinkedList.SLNode;
import M6A2.LinkedList.SinglyLinkedList;
import M6A2.Person.Person;
import M6A2.Stack.Stack;

/**
 * A generic LinkedStack class
 *
 * @param <T>
 */
public class LinkedStack<T> implements Stack<T>
{
    // Private variable.
    private SinglyLinkedList<T> list;

    /**
     * Default constructor.
     */
    public LinkedStack()
    {
        this.list = new SinglyLinkedList<>();
    }

    /**
     * Overridden isEmpty method.
     *
     * @return true if the stack is empty.
     */
    @Override
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Overridden peek method.
     *
     * @return the value of the top element in the stack.
     */
    @Override
    public T peek()
    {
        SLNode<T> node = list.getHead();
        return node.getSuccessor().getElement();
    }

    /**
     * Overridden pop method
     *
     * @return the top element in the stack and remove it.
     */
    @Override
    public T pop()
    {
        return list.remove(0);
    }

    /**
     * Overridden push method
     *
     * @param element the element to push onto the stack.
     */
    @Override
    public void push(T element)
    {
        list.add(element);
    }

    /**
     * Overridden size method
     *
     * @return the size of the stack.
     */
    @Override
    public int size()
    {
        return list.getLength();
    }

    /**
     * Main method to test functionality of the class.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        LinkedStack<Integer> intStack = new LinkedStack<>();
        intStack.push(1);
        intStack.push(5);
        System.out.println("Stack Size: " + intStack.size() + ". Should be 2. Correct? " + (intStack.size() == 2));
        System.out.println("Popping: " + intStack.pop());
        System.out.println("Peeking: " + intStack.peek());
        System.out.println("Popping: " + intStack.pop());
        System.out.println("Peeking: " + intStack.peek());
        System.out.println();
        Person p1 = new Person("Fred", 50);
        Person p2 = new Person("Bill", 26);
        LinkedStack<Person> pStack = new LinkedStack<>();
        pStack.push(p1);
        pStack.push(p2);
        System.out.println("Stack Size: " + pStack.size() + ". Should be 2. Correct? " + (pStack.size() == 2));
        System.out.println("Popping: " + pStack.pop());
        System.out.println("Peeking: " + pStack.peek());
        System.out.println("Popping: " + pStack.pop());
        System.out.println("Peeking: " + pStack.peek());
    }
}
