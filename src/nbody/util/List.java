package nbody.util;

public interface List<E> {
    //Returns the number of elements in this list.
    int size();

    //Appends the specified element to the end of this list 
    boolean add(E value);
    
    //Returns the element at the specified position in this list.
    E get(int index);
}

