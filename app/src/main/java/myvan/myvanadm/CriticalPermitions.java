package myvan.myvanadm;

import android.*;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by Leonardo on 27/11/2017.
 */

public class CriticalPermitions extends AppCompatActivity {

    int REQUEST_PERMISSIONS_CODE = 1;
    MaterialDialog MT;



    public void PermitionLocale()
    {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION))
            {

                CallDialog("É preciso a permição para acesso ao mapa",new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION});

            }
            else
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSIONS_CODE);
            }
        }
    }

    public void PermitionExternalStorage()
    {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {

                CallDialog("É preciso a permição para acesso ao armazenamento para melhor funcionalidade",new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE});

            }
            else
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS_CODE);
            }
        }
    }

    private void CallDialog(String message,final String[] permissions)
    {

        MT = new MaterialDialog( this )
                .setTitle( "Permição" )
                .setMessage( message )
                .setPositiveButton("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ActivityCompat.requestPermissions(CriticalPermitions.this, permissions, REQUEST_PERMISSIONS_CODE);
                        MT.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MT.dismiss();
                    }
                });
        MT.show();
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(CriticalPermitions.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
