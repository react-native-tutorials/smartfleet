/**
 * 
 */
package com.smartlife.smartfleet.dto;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class Coordinates {
	
	double latitud;
	double longitud;

	public Coordinates(double lat, double lng) {
		this.latitud = lat;
		this.longitud = lng;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	@Override
	public String toString() {
		return "L:" + latitud + " | l:" + longitud;
	}
}
