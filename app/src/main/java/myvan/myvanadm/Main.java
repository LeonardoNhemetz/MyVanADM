package myvan.myvanadm;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import me.drakeet.materialdialog.MaterialDialog;
import myvan.myvanadm.Backgrounds.BackgroundLogin;


public class Main extends AppCompatActivity {


     EditText  txt_Login;
     EditText txt_Password;
     AdView adView;
     MaterialDialog MT;
     int REQUEST_PERMISSIONS_CODE = 1;






    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView = (AdView) findViewById(R.id.adBannerMain);
        MobileAds.initialize(this, "ca-app-pub-7204661155560964~8134362311");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



        txt_Login = (EditText) findViewById(R.id.txtLogin);
        txt_Password = (EditText) findViewById(R.id.txtPassword);


        SharedPreferences sharedPreferences =  getSharedPreferences("LoginADM",MODE_PRIVATE);
        String Login = sharedPreferences.getString("Login","");
        String Pass = sharedPreferences.getString("Pass","");

        txt_Login.setText(Login);
        txt_Password.setText(Pass);


        PermitionStorage();

    }


    public void PermitionStorage()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS_CODE);

            }
            else
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS_CODE);
            }
        }
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
                    Toast.makeText(Main.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    public void Onlogin(View view)
    {


        String Login = txt_Login.getText().toString();
        String Pass = txt_Password.getText().toString();

        SharedPreferences sharedPreferences =  getSharedPreferences("LoginADM",MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Login",Login);
        editor.putString("Pass",Pass);

        editor.commit();


        txt_Login.getText().toString();
        txt_Password.getText().toString();


        BackgroundLogin backgroundLogin = new BackgroundLogin(Main.this, txt_Login, txt_Password);
        backgroundLogin.execute();


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

