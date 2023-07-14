package myvan.myvanadm;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import myvan.myvanadm.Backgrounds.SelectInformacoesMinimo.DownloaderMinimo;

public class ShowSelect extends Activity {

    String urlAddress= "http://35.231.239.84/myvan/ADM/select.php";

    Button btn_Atualizar_Select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_select);

        final ListView lv = (ListView)findViewById(R.id.lv);
        btn_Atualizar_Select = (Button)findViewById(R.id.btnAtualizarSelect);



        btn_Atualizar_Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DownloaderMinimo d = new DownloaderMinimo(ShowSelect.this,urlAddress,lv);
                d.execute();
            }
        });
    }
}
