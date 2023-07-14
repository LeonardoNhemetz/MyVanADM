package myvan.myvanadm.LocalizacaoVan;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GeoLoc
{

    Context ctx;
    Location m_location;
    LocationManager m_LocationManager;
    String m_provider = LocationManager.GPS_PROVIDER;

    public GeoLoc(Context context)
    {
        ctx = context;
        m_LocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        //permições
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != (PackageManager.PERMISSION_GRANTED) ||
                (ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION)
                        != (PackageManager.PERMISSION_GRANTED)))
        {
            //mostra o erro e/ou quit
            return;
        }

        m_LocationManager.requestLocationUpdates(m_provider, 10 * 1000, 25, new LocationListener()
        {
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}

            public void onLocationChanged(Location location)
            {
                m_location = location;
            }
        });
    }

    Location getLocation()
            // pegar localização do celular
    {
        if(m_location == null)
        {
            if(ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION)
                    != (PackageManager.PERMISSION_GRANTED) ||
                    (ActivityCompat.checkSelfPermission(ctx,Manifest.permission.ACCESS_COARSE_LOCATION)
                            != (PackageManager.PERMISSION_GRANTED)))
                m_location = m_LocationManager.getLastKnownLocation(m_provider);

            if(m_location == null)
            {
                m_location = new Location(m_provider);
                m_location.setLatitude(0);
                m_location.setLongitude(0);

            }

        }

        return m_location;


    }




    //****************************ATUALIZAR COORDENADAS*********************************************************

    public static final long TEMPO = (1000 /*milesimos*/ * 15 /*segundos*/); //15 seg

    public  void Atualizar()
    {

        Timer timer = null;
        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask()
            {
                public void run()
                {
                    try
                    {

                        //inicio do repetidor de localização
                        Location location = getLocation();
                        Double latVan = location.getLatitude();
                        Double lngVan = location.getLongitude();
                        lngVan.toString();
                        latVan.toString();
                        System.out.println(latVan+" , "+lngVan);

                        BackgroundGeoLoc backgroundGeoLoc = new BackgroundGeoLoc(ctx,latVan.toString(),lngVan.toString());
                        backgroundGeoLoc.execute();



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);

            //Laço terminado e será repetido no tempo determinado
        }
    }
    //************************************************************************************************************
}
