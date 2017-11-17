package com.example.matheus.ragnarokgps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;


import static android.location.LocationManager.GPS_PROVIDER;

public class TelaPrincipal extends AppCompatActivity implements View.OnClickListener, LocationListener, GpsStatus.Listener { //Não consegui colocar nada que fizesse o GpsStatus funcionar

    private LocationManager locManager; // Gerente de Localizaçao.
    private LocationProvider locProvider; //Provedor de Localização.
    private static final int REQUEST_LOCATION = 2; //???? Don't know what is this



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        locManager.getProvider(GPS_PROVIDER);


        //Instância dos Buttons
        Button map = (Button)findViewById(R.id.button_map);
        map.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_map:
                //Criar tela relativa ao mapa
                Intent map = new Intent(this, MapsActivity.class);
                startActivity(map);
                break;

            case R.id.button_gnss:
               /*Intent gnss = new Intent(this, GnssActivity.class);
                startActivity(gnss);
                */
                break;

            case R.id.button_config:
                /*Intent config = new Intent(this, ConfigActivity.class);
                startActivity(config);
                 */
                break;

            case R.id.button_credits:
                Intent credits = new Intent(this, CreditsActivity.class);
                startActivity(credits);
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            // A permissão foi dada
            ativaGPS();
        } else {
            // Solicite a permissão
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }
    }

    public void desativaGPS() {
        try {
            locManager.removeUpdates(this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void ativaGPS() {
        try {
            locProvider = locManager.getProvider(LocationManager.GPS_PROVIDER);
            locManager.requestLocationUpdates(locProvider.getName(), 30000, 1, this);
            locManager.addGpsStatusListener(this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onGpsStatusChanged(int i) {

    }
}
