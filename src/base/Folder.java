package base;

import java.util.ArrayList;

public class Folder {
	
	private ArrayList<Note> notes;
	private String name;
	
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
}
