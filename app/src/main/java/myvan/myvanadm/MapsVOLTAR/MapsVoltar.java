package myvan.myvanadm.MapsVOLTAR;

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

import myvan.myvanadm.R;

public class MapsVoltar extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap map2;
    AdView adView;

    LatLng latlng = new LatLng(-23.7173934, -46.5683219);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_voltar);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        adView = (AdView) findViewById(R.id.adBannerMapsVoltar);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        map2 = googleMap;

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
        map2.setMyLocationEnabled(true);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latlng).zoom(14).build();

        map2.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        new WaypointsURL_VOLTAR(this).execute();

        map2.getUiSettings().setZoomControlsEnabled(true);
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
