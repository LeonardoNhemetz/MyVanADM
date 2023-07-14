package myvan.myvanadm;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import myvan.myvanadm.MapsIR.MapsIR;
import myvan.myvanadm.MapsVOLTAR.MapsVoltar;

public class Principal extends AppCompatActivity {


    Button btnListarPassageiros,btn_Select,btn_Maps,btnMapsVoltar;
    int REQUEST_PERMISSIONS_CODE = 1;

    AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        adView = (AdView) findViewById(R.id.adBannerPrincipal);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        btnListarPassageiros = (Button) findViewById(R.id.btn_ListarPass);
        btn_Select = (Button) findViewById(R.id.btnAtualizarSelect);
        btn_Maps = (Button) findViewById(R.id.btnMap);
        btnMapsVoltar = (Button)findViewById(R.id.btnMapsVoltar);

        PermitionLocale();

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_principal:
                startActivity(new Intent(this, OriginDest.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void ListarPass(View view)
    {
        Intent listar = new Intent(this, CadastrarPassageiros.class);
        startActivity(listar);
    }

    public void SelectPass(View view)
    {
        Intent select = new Intent(this, ShowSelect.class);
        startActivity(select);
    }

    public void InfoDet(View view)
    {
        Intent det = new Intent(this,InformacaoDetalhada.class);
        startActivity(det);
    }

    public void maps(View view)
    {

        startActivity(new Intent(this, MapsIR.class));
    }

    public void MapsVoltar(View view)
    {

        Intent Voltar = new Intent(this, MapsVoltar.class);
        startActivity(Voltar);
    }
    public void btnExcluir(View view)
    {

        startActivity( new Intent( this,ExcluirPassageiros.class ) );

    }
    public void Politics(View view)
    {
        Uri uri = Uri.parse("https://drive.google.com/file/d/1_WBf8yh1Mq5kTXl2LX82gbAvc11MGHbz/view?usp=sharing");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    public void PermitionLocale()
    {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION))
            {

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSIONS_CODE);

            }
            else
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
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
                    Toast.makeText(Principal.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
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
