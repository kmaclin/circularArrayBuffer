//Kelsey Maclin
//Section A1


public class CircularArrayBuffer<T> implements CircularBuffer<T> {
    
	private int counter = 0, front = 0, rear = 0;
	private T[] buffer;
	private BufferGrowMode mode;
	
	public CircularArrayBuffer() {
		T[] array = (T[])new Object[10];
        buffer = array;
        mode = BufferGrowMode.REGROW;
	}
	
    public boolean isEmpty() {
    	return counter == 0;
    }
    
    public int capacity() {
    	return buffer.length;
    }
    
    public int size() {
    	return counter;
    }
    
    public void setMode(BufferGrowMode newMode) {
        mode = newMode;
    }
    
    private void regrow() {
    	int num = 2 * this.capacity();
		T[] array = (T[]) (new Object[num]);
		for (int i = 0; i < this.capacity(); i++) {
			array[i] =  buffer[i];
		}
        buffer = array;
    }
    
    public void add(T item) {
    	
    	if (mode.equals(BufferGrowMode.REGROW)) {
    		if (this.size() == this.capacity()) {
    			this.regrow();
    	    	rear = this.size();
    		}
			counter++;
    		buffer[rear] = item;
    		rear = (rear + 1) % this.capacity();
    	} else {
    		if (this.size() == this.capacity()) {
    			buffer[rear] = item;
    	    	rear = (rear + 1) % this.capacity();
    	    	front = (front + 1) % this.capacity();
    		} else {
    			counter++;
        		buffer[rear] = item;
        		rear = (rear + 1) % this.capacity();
    		}
    	}
    }
    
    public T remove() {
        if (this.isEmpty()) {
        	return null;
        } else {
        	counter--;
        	T item = buffer[front];
       		front = (front + 1) % this.capacity();	
       		return item;
        }
    }

    public boolean contains(T item) {
        for (int i = 0; i < this.size(); i++) {
            if (buffer[i].equals(item)) {
            	return true;
            }
        }
        return false;
    }
}
