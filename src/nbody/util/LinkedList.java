package nbody.util;


public class LinkedList<E> implements List<E> {
    //Pointer to head node.
    Node<E> head;
    //The size of the LinkedList
    int size;

    //Node 
    private class Node<E> {
        //element
        E data;
        //Pointer to next, left, right node
        Node<E> next, left, right;
        
	//Constructor
        public Node(E data, Node<E> l, Node<E> r) {
            left = l;
            right = r;
            this.data = data;
        }

        //Constructor
	public Node(E data) {
            this(data, null, null);
        }
    }

    //Constructor
    public LinkedList() {
        //Initial default value
        head = null;
        size = 0;
    }

    //Returns the number of elements in this list.
    public int size() {
        return size;
    }

    //Appends the specified element to the end of this list
    public boolean add(E value) {
        //Check null pointer
        if(head == null) {
            //Set new node with that value
            head = new Node<>(value);
            //inc
            size++;
            return true;
        }

        //The previous value
        Node<E> prev = head;

        //Loop
        for(int i = 0; i < size - 1; i++) {
            //Set the previous to the next index
            prev = prev.next;
        }

        //New a tmp node
        Node<E> tmp = new Node<>(value);
        //Set the next value
        prev.next = tmp;
        //inc
        size++;
        return true;
    }

    //Returns the element at the specified position in this list.
    public E get(int index) {
        //Check invalid index
        if(index < 0 && index >= size) {
            System.out.println("Invalid index.");
	    return null;
        }

        //Set current node to the head
        Node<E> current = head;
        
	//Loop
        for(int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

}
