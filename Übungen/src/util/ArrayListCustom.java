package util;

public class ArrayListCustom<E> {
	
	private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;
    
    
    public ArrayListCustom() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    
    public ArrayListCustom(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        
        elements = new Object[initialCapacity];
        size = 0;
    }
    
    public boolean add(E element) {
        ensureCapacity();
        elements[size++] = element;
        return true;
    }
    
    public void add(int index, E element) {
        checkIndexForAdd(index);
        ensureCapacity();
        
        // Shift elements to the right
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }
    
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }
    
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = get(index);
        elements[index] = element;
        return oldValue;
    }
    
    public E remove(int index) {
        checkIndex(index);
        E oldValue = get(index);
        
        // Shift elements to the left
        int numToMove = size - index - 1;
        if (numToMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, numToMove);
        }
        elements[--size] = null; // Clear reference for GC
        
        return oldValue;
    }
    
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null; // Help GC
        }
        size = 0;
    }
    
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }
    
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }
    
    private void ensureCapacity() {
        if (size != elements.length) { return; }
        
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        
        for (int index = 0; index < elements.length; index++) {
        	newElements[index] = elements[index];
        }
        
        elements = newElements;
    }
    
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    @Override
    public String toString() {
        if (size == 0) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
