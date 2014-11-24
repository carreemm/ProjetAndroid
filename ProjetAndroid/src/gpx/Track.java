package gpx;

import java.util.ArrayList;
import java.util.List;

public class Track {
	List<TrackSeg> sousParcours;
	
	public Track(){
		sousParcours = new ArrayList<TrackSeg>();
	}
	
	public List<TrackSeg> getTracksSeg(){
		return sousParcours;
	}
	
	public void addTrackSeg(TrackSeg tcksg){
		sousParcours.add(tcksg);
	}
	
	public void removeTrackSeg(TrackSeg tcksg){
		sousParcours.remove(tcksg);
	}
	
}
