package com.example.baseproject.utilities;
/**
 * @author shivang
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.baseproject.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MapUtils {


	/**
	 * Private Instance Variable
	 */
	private static MapUtils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private MapUtils(){}

	/**
	 * Method return the class instance
	 * @return MapUtils
	 */
	public static MapUtils getInstance(){
		if(classInstance == null){
			classInstance = new MapUtils();
		}
		return classInstance;
	}

	/**
	 * Method call to get address from lat long
	 * @param ctx
	 * @param lati
	 * @param longi
	 * @return address string
	 */
	protected String findPresentAddress(Context ctx, double lati, double longi) {
		String addressString = "";
		try {
			Geocoder gc = new Geocoder(ctx, Locale.getDefault());
			List<Address> address = gc.getFromLocation(lati, longi, 1);
			for (int i = 0; i < address.get(0).getMaxAddressLineIndex(); i++) {
				addressString += address.get(0).getAddressLine(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addressString;
	}

	/**
	 * Method call to get random lat long near to current
	 * @param latitude
	 * @param longitude
	 * @return double[]
	 */
	public double[] createRandLocation(Double latitude, double longitude) {
		return new double[] { latitude + ((Math.random() - 0.5) / 500),
				longitude + ((Math.random() - 0.5) / 500),
				150 + ((Math.random() - 0.5) * 10) };
	}
	
	/**
	 * Method call to get complete address From lat long
	 * @param context
	 * @param LATITUDE
	 * @param LONGITUDE
	 * @return String
	 */
	public String getCompleteAddressString(Context context, double LATITUDE, double LONGITUDE) {
		String strAdd = "";
		Geocoder geocoder = new Geocoder(context, Locale.getDefault());
		try {
			List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
			if (addresses != null) {
				Address returnedAddress = addresses.get(0);
				StringBuilder strReturnedAddress = new StringBuilder("");

				for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
					strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
				}
				strAdd = strReturnedAddress.toString();
				Logger.getInstance().log_info("My Current location address", " " + strReturnedAddress.toString());
			} else {
				Logger.getInstance().log_info("My Current location address", "No Address returned!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getInstance().log_info("My Current location address", "Canont get Address!");
		}
		return strAdd;
	}
	
	 /**
     * Method call to Calculate distance
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return double
     */
	protected double calculateDistance(final double lat1, final double lon1, final double lat2, final double lon2) {
        // haversine great circle distance approximation, returns meters
        final double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60; // 60 nautical miles per degree of seperation
        dist = dist * 1852; // 1852 meters per nautical mile
        return dist;
    }
    
    private double deg2rad(final double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(final double rad) {
        return (rad * 180.0 / Math.PI);
    }
    
    /**
     * Method call to draw marker on map
     * @param googleMap
     * @param latitude
     * @param longitude
     * @param drawable
     */
    public void drawMarkerOnMap(GoogleMap googleMap, double latitude, double longitude, int drawable){
    	// Adding a marker
		MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude));
		marker.icon(BitmapDescriptorFactory.fromResource(drawable));
		googleMap.addMarker(marker);	
    }
    
    /**
     * Method call to draw marker on map
     * @param googleMap
     * @param latitude
     * @param longitude
     * @param drawable
     */
    public void drawMarkerOnMap(GoogleMap googleMap, String latitude, String longitude, String name, int drawable){
    	// Adding a marker
		MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)));
		marker.icon(BitmapDescriptorFactory.fromResource(drawable));
		marker.title(name);
		
		googleMap.addMarker(marker);	
    }
	
    /**
     * Method call to draw marker on map
     * @param googleMap
     * @param latitude
     * @param longitude
     * @param name
     * @param icon
     */
    public void drawMarkerOnMap(Context context, GoogleMap googleMap, String latitude, String longitude, String name, Bitmap icon, OnClickListener clickListener){
    	// Adding a marker
		MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)));
		
		View view; 
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		view = inflater.inflate(R.layout.view_map_marker, null);
		
		ImageView image_view_map_marker = (ImageView) view.findViewById(R.id.image_view_map_marker);
		image_view_map_marker.setImageBitmap(icon);
		
		RelativeLayout main_layout = (RelativeLayout) view.findViewById(R.id.main_layout);
		main_layout.setOnClickListener(clickListener);
		
		marker.icon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.getInstance().createDrawableFromView(context, view)));
//		marker.title(name);
		googleMap.addMarker(marker);	
    }
    
    /**
     * Method call to get sorted location list near to provided location
     * @param locations
     * @param myLatitude
     * @param myLongitude
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Location> sortLocations(List<Location> locations, final double myLatitude,final double myLongitude) {
        Comparator comp = new Comparator<Location>() {
            @Override
            public int compare(Location o, Location o2) {
                float[] result1 = new float[3];
                Location.distanceBetween(myLatitude, myLongitude, o.getLatitude(), o.getLongitude(), result1);
                Float distance1 = result1[0];

                float[] result2 = new float[3];
                Location.distanceBetween(myLatitude, myLongitude, o2.getLatitude(), o2.getLongitude(), result2);
                Float distance2 = result2[0];

                return distance1.compareTo(distance2);
            }
        };
        Collections.sort(locations, comp);
        return locations;
    }
	/**
	 * Method call to clean object from memory
	 */
	public void cleanObject(){
		classInstance = null;
	}
}
/**
 * @author shivang
 */