package nbody.util;

public class ArrayList<E> implements List<E> {
    //The array buffer into which the elements of the ArrayList are stored.
    private E[] data;
    //The size of the ArrayList
    private int size;

    //Default initial space.
    private static final int DEFAULT_SPACE = 16;

    //Constructor
    public ArrayList() {
        //Create array of type E with default size 
        data = (E[]) new Object[DEFAULT_SPACE];
        //Initialize size
        size = 0;
    }

    //Returns the number of elements in this list.
    public int size() {
        return size;
    }

    //Appends the specified element to the end of this list.
    public boolean add(E value) {
        // if the size is same as the length of the array
        // just copy the array
        if(size == data.length) {
            arrayCopy();
        }

        // set value
        data[size] = value;
        // inc
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

        // returns the element at index
        return data[index];
    }

    // Array copy.
    private void arrayCopy() {
        //Twice the size of the original array
        E[] nowData = (E[]) new Object[data.length * 2];
        
	//Loop 
        for(int i = 0; i < data.length; i ++) {
            //Copy each value
            nowData[i] = data[i];
        }

        //setting array
        data = nowData;
    }
}
