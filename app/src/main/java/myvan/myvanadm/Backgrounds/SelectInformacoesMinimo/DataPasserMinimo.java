package myvan.myvanadm.Backgrounds.SelectInformacoesMinimo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Leonardo on 12/07/2016.
 */
public class DataPasserMinimo extends AsyncTask<Void,Void,Integer> {

    Context c;
    ListView lv;
    String jsonData;



    ProgressDialog pd;
    ArrayList<VariaveisSelectMinimo> variaveisSelectMinimos = new ArrayList<>();

    public DataPasserMinimo(Context c, ListView lv, String jsonData) {
        this.c = c;
        this.lv = lv;
        this.jsonData = jsonData;

    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Análise");
        pd.setMessage("Analisando...");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();
        if(result == 0)
        {
            Toast.makeText(c,"Não retornou nenhum dado",Toast.LENGTH_SHORT).show();
        }
        else
        {
            //call adapter

            AdapterSelectMinimo adapter = new AdapterSelectMinimo(c, variaveisSelectMinimos);
            lv.setAdapter(adapter);
        }


    }

    private int parseData()
    {
        try {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo = null;

            variaveisSelectMinimos.clear();
            VariaveisSelectMinimo s = null;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                int id=jo.getInt("id_pass");
                String name=jo.getString("nome_pass");
                String Ir=jo.getString("status_ir_pass");
                String Voltar=jo.getString("status_voltar_pass");

                s=new VariaveisSelectMinimo();
                s.setId(id);
                s.setName(name);
                s.setIr(Ir);
                s.setVoltar(Voltar);

                variaveisSelectMinimos.add(s);

            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
