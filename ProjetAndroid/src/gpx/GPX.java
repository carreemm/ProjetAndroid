package gpx;

import java.util.ArrayList;
import java.util.List;


public class GPX {
	
	private List<Track> parcours;
	
	public GPX(){
		parcours = new ArrayList<Track>();
	}
	
	public List<Track> getTracks(){
		return parcours;
	}
	
	public void addTrack(Track tck){
		parcours.add(tck);
	}
	
	public void removeTrack(Track tck){
		parcours.remove(tck);
	}

}
