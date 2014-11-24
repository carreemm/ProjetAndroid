package gpx;

import java.util.ArrayList;
import java.util.List;

public class TrackSeg {
	List<TrackPoint> points;
	
	public TrackSeg(){
		points = new ArrayList<TrackPoint>();
	}
	
	public List<TrackPoint> getTracksPoint(){
		return points;
	}
	
	public void addTrackPoint(TrackPoint tckpt){
		points.add(tckpt);
	}
	
	public void removeTrackPoint(TrackPoint tckpt){
		points.remove(tckpt);
	}
}
