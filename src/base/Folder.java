package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, java.io.Serializable {
	
	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;
	
	public Folder(String name){
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note1){
		notes.add(note1);
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public boolean equals(Folder folder2){
		return this.name == folder2.getName();
	}
	
	@Override
	public String toString(){
		int nText = 0;
		int nImage = 0;
		
		for (int i = 0; i < notes.size(); i++){
			if (notes.get(i) instanceof TextNote){
				nText += 1;
			} else if (notes.get(i) instanceof ImageNote){
				nImage += 1;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}
	
	@Override
	public int compareTo(Folder o){
		return this.name.compareTo(o.name);
	}
	
	public void sortNotes(){
		Collections.sort(notes);
	}
	
	private List<ImageNote> getImageNotes(){
		List<ImageNote> result = new ArrayList<ImageNote>();
		for (Note i: notes){
			if (i instanceof ImageNote){
				ImageNote iImage = (ImageNote) i;
				result.add(iImage);
			}
		}
		return result;
	}
	
	private List<TextNote> getTextNotes(){
		List<TextNote> result = new ArrayList<TextNote>();
		for (Note i: notes){
			if (i instanceof TextNote){
				TextNote iText = (TextNote) i;
				result.add(iText);
			}
		}
		return result;
	}
	
	public List<Note> searchNotes(String keys){
		// Parse Keywords
		
		List<String[]> keywords = new ArrayList<String[]>();
		
		keys = keys.toLowerCase();
		String[] orSplit = keys.split(" [o][r] ");
		
		for (String i : orSplit){
			keywords.add(i.split(" "));
		}
		
		
		List<Note> result = new ArrayList<Note>();
		for(TextNote i: this.getTextNotes()){
			noteloop:
			for(String[] keyList: keywords){
				keyloop:
				for (String searchString: keyList){
					if (!i.getTitle().toLowerCase().contains(searchString) && !i.getContent().toLowerCase().contains(searchString)){
						break keyloop;
					}
					result.add(i);
					break noteloop;
				}
				
			}
			
		}
		
		for(ImageNote i: this.getImageNotes()){
			noteloop:
			for(String[] keyList: keywords){
				keyloop:
				for (String searchString: keyList){
					if (!i.getTitle().toLowerCase().contains(searchString)){
						break keyloop;
					}
					result.add(i);
					break noteloop;
				}
				
			}
			
		}
		
		return result;
	}
	
	public boolean removeNotes(String title){
		for(Note N: notes){
			if (N.getTitle().equals(title)){
				notes.remove(N);
				return true;
			}
		}
		return false;
	}
}
