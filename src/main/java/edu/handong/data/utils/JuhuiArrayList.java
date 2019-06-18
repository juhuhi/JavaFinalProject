package edu.handong.data.utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

//public class JuhuiArrayList<T>  extends ArrayList implements Iterable{
//	JuhuiArrayList<T> list;
//	public JuhuiArrayList() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public JuhuiArrayList(Collection c) {
//		super(c);
//		// TODO Auto-generated constructor stub
//	}
//
//	public JuhuiArrayList(int initialCapacity) {
//		super(initialCapacity);
//		// TODO Auto-generated constructor stub
//	}
//	@Override
//	public Iterator<T> iterator() {
//		return list.iterator();
//	}
//
//	JuhuiArrayList<T>[] next;
//	
//}


public class JuhuiArrayList<T>{
 
	ArrayList<T> list = new ArrayList<T>();
    int size = 0;

    public JuhuiArrayList(){
    	
    }
    
    
    public void add(T item) {

    	list.add(item);
    	size++;
        
    }
    
   public ArrayList<T> get() {
	   return list;
   }


 
}



