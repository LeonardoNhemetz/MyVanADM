package myvan.myvanadm;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import myvan.myvanadm.Backgrounds.SelectInformacoesMaximo.DownloaderMaximo;
import myvan.myvanadm.Backgrounds.User;

public class InformacaoDetalhada extends Activity {

    String urlAddress= "http://35.231.239.84/myvan/ADM/selectMax.php";
    Button btnAtualizar;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao_detalhada);

        final ListView lvDet = (ListView)findViewById(R.id.lvDet);
        btnAtualizar = (Button) findViewById(R.id.btnAtualizarMaximo);




        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DownloaderMaximo d = new DownloaderMaximo(InformacaoDetalhada.this,urlAddress,lvDet);
                d.execute();
            }
        });


    }
}
