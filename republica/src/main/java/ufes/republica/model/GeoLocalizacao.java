/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufes.republica.model;

/**
 *
 * @author Lucas
 */
public class GeoLocalizacao {

    private int id;

    private String latitude;
    
    private String longitude;

    public GeoLocalizacao(int id, String latitude, String longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoLocalizacao(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoLocalizacao() {
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getId() {
        return id;
    }
}
