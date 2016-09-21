package base;
import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;


public class Note implements Comparable<Note>{

	private Date date;
	private String title;
	
	public Note(){
		
	}
	
	public Note(String title){
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle(){
		return title;
	}
	
	public boolean equals(Note note2){
		return this.title == note2.getTitle();
		
	}
	
	@Override
	public int compareTo(Note o){
		return this.date.compareTo(o.date);
	}
	
	@Override
	public String toString(){
		
		Format dateformat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		String datestring = dateformat.format(date);
		
		
		
		return datestring + "\t" + this.getTitle();
	}
	
}
