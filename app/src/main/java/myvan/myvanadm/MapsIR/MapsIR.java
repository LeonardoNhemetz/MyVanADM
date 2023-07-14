package myvan.myvanadm.MapsIR;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import myvan.myvanadm.LocalizacaoVan.GeoLoc;
import myvan.myvanadm.R;

public class MapsIR extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap map;
    GeoLoc geoloc;
    AdView adView;


    LatLng latlng = new LatLng(-23.689853, -46.564800);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        adView = (AdView) findViewById(R.id.adBannerMapsIr);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


    }

    private void setUpMapIfNeeded() {
        if (map == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);


        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latlng).zoom(13).build();

        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        new WaypointsURL_IR(this).execute();


        geoloc = new GeoLoc(this);
        geoloc.Atualizar();

    }

    public void onPause()
    {
        if(adView != null)
        {
            adView.pause();
        }
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
        if(adView != null)
        {
            adView.resume();
        }
    }

    public void onDestroy()
    {
        if(adView != null)
        {
            adView.destroy();
        }
        super.onDestroy();
    }
}
