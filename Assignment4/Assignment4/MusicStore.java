/**
 * change sept4 2020
 */

import java.util.ArrayList;

public class MusicStore {
    //ADD YOUR CODE BELOW HERE
	private MyHashTable<String, Song> titleTable;
    private MyHashTable<String, ArrayList<Song>> artistTable;
    private MyHashTable<Integer, ArrayList<Song>> yearTable;
	
    //ADD YOUR CODE ABOVE HERE
    
    
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
    	int maxNumBuckets = songs.size();
    	titleTable = new MyHashTable<String, Song>(maxNumBuckets);
    	artistTable = new MyHashTable<String, ArrayList<Song>>(maxNumBuckets);
    	yearTable = new MyHashTable<Integer, ArrayList<Song>>(maxNumBuckets);
        for (Song song: songs) {
            addSong(song);
        }
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
    	 titleTable.put(s.getTitle(), s);
    	 
         if (artistTable.get(s.getArtist()) == null) {
             ArrayList<Song> repeatArtist = new ArrayList<>();
             repeatArtist.add(s);
             artistTable.put(s.getArtist(), repeatArtist);
         } else {
             artistTable.get(s.getArtist()).add(s);
         }

         if (yearTable.get(s.getYear()) == null) {
             ArrayList<Song> repeatYear = new ArrayList<>();
             repeatYear.add(s);
             yearTable.put(s.getYear(), repeatYear);
         } else {
             yearTable.get(s.getYear()).add(s);
         }

        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
    	 
        return titleTable.get(title); 
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
    	  if (artistTable.get(artist) != null ) {
              return artistTable.get(artist);
          } else {
              return new ArrayList<>();
          }
    	
    	

        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE
    	if (yearTable.get(year) != null ) {
            return yearTable.get(year);
        } else {
            return new ArrayList<>();
        }
  	
    	
      
        //ADD CODE ABOVE HERE
        
    }
}
