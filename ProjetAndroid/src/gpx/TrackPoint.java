package gpx;

public class TrackPoint {
	private double latitude;
	private double longitude;
	//double elevation;
	
	public TrackPoint(double lat, double lon){
		latitude = lat;
		longitude = lon;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
}
