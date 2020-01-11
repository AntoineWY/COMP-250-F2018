
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS

    	if(initialCapacity == 0){
    		this.numBuckets =1;
    	}else{
    		this.numBuckets = initialCapacity;
    	}
    	this.numEntries = 0;

        buckets = new ArrayList<LinkedList<HashPair<K,V>>>(numBuckets);
        for (int i = 0; i < numBuckets; i++) 
        {
            buckets.add(new LinkedList<>());
        }
    	//ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets vairable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE
    
    	if(((double)numEntries/numBuckets)>=MAX_LOAD_FACTOR ){
    		rehash();
    	}
      
    	if (key == null){
          	return null;
          }         
        HashPair<K,V> pairAdd = new HashPair<K,V>(key, value);
      
        int bucketIndex = hashFunction(key);
       
        if (buckets.get(bucketIndex).isEmpty() ||buckets.get(bucketIndex).size()==0){
        	LinkedList<HashPair<K,V>> bucketSlot = new LinkedList<HashPair<K,V>>();
        	bucketSlot.add(pairAdd);
        	buckets.set(bucketIndex, bucketSlot);
        	this.numEntries++;
        	
        
        	
        	return null;
        }else{
        	for(HashPair<K,V> a: buckets.get(bucketIndex)){
        		if(a.getKey().equals(key)){
        			V temp = a.getValue();
        			a.setValue(value);
        			return temp;
        		}
        	}
        	buckets.get(bucketIndex).add(pairAdd);
        	this.numEntries++;
 
        	
        	return null;
        }
       
      
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
    	int index = hashFunction(key);
    	LinkedList<HashPair<K,V>> slot = buckets.get(index);
    	if(slot.isEmpty()||slot.size()==0){
    		return null;
    	}else{
    		for(HashPair<K,V> a : slot){
    			if(a.getKey().equals(key)){
    				return a.getValue();
    			}   			
    		}
    	}
        return null;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
    	if(key.equals(null)){
    		return null;
    	}
    	else{
    		int hashIndex = hashFunction(key);
    		LinkedList<HashPair<K,V>> slot = buckets.get(hashIndex);
    		if(slot.isEmpty()||slot.size()==0){
    			return null;
    		}else{
    			for(HashPair<K,V> a : slot){
        			if(a.getKey().equals(key)){
        				V temp = a.getValue();
        				buckets.get(hashIndex).remove(a);        // adjust bucket if empty??
        				numEntries--;
        		
        				return temp;
        			}  
    			}
    		}
    	}

        return null;
        //ADD YOUR CODE ABOVE HERE
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
        //ADD YOUR CODE BELOW HERE

    	   ArrayList<LinkedList<HashPair<K,V>>> temp = new ArrayList<>(this.buckets);
           this.numBuckets = 2*this.numBuckets;
           this.numEntries  = 0;
           this.buckets = new ArrayList<>();
           for(int i = 0 ;i < numBuckets;i++)
           {
        	      this.buckets.add(new LinkedList<>());
           }
           for(LinkedList<HashPair<K,V>> a : temp)
              {
        	         for(HashPair<K,V> b : a)
        	         {
        	        	    this.put(b.getKey(),b.getValue());
        	         }
              }
        
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
    	ArrayList<K> keyList = new ArrayList<K>(numEntries);
    	MyHashIterator iter = this.iterator();
    	while(iter.hasNext()){
    		keyList.add(iter.next().getKey());
    	}
        return keyList;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
    	ArrayList<V> valueList = new ArrayList<V>(numEntries);
    	MyHashIterator iter = this.iterator();
    	while(iter.hasNext()){
    		valueList.add(iter.next().getValue());
    	}
        return valueList;
        //ADD CODE ABOVE HERE
    }
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> entries;
        
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
        	entries = new LinkedList<HashPair<K,V>>();
            for(LinkedList<HashPair<K,V>> bucketSlot : buckets){
            	if(!bucketSlot.isEmpty()&&bucketSlot.size()!=0){
            		for (HashPair<K,V> a:bucketSlot){
            			
            		entries.add(a);	
            			
            		}
            	}
            }
        	
        	
       // System.out.println(entries);
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
        	
            return (!(entries.size()==0));
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE

        return entries.removeFirst();
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
}
