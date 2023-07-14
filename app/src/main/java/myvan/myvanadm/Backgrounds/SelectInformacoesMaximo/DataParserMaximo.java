package myvan.myvanadm.Backgrounds.SelectInformacoesMaximo;

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
 * Created by Leonardo on 06/09/2016.
 */
public class DataParserMaximo extends AsyncTask<Void,Void,Integer> {

    Context c;
    ListView lvDet;
    String jsonData;

    ProgressDialog pd;
    ArrayList<VariaveisSelectMaximo> variaveisSelectMaximos = new ArrayList<>();

    public DataParserMaximo(Context c, ListView lvDet, String jsonData) {
        this.c = c;
        this.lvDet = lvDet;
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
        return parseData();
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
            AdapterSelectMaximo adapter = new AdapterSelectMaximo(c,variaveisSelectMaximos);
            lvDet.setAdapter(adapter);
        }


    }

    private int parseData()
    {
        try {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo = null;

            variaveisSelectMaximos.clear();
            VariaveisSelectMaximo s = null;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                int id=jo.getInt("id_pass");
                String name=jo.getString("nome_pass");
                String email=jo.getString("email_pass");
                String senha=jo.getString("senha_pass");
                String end1=jo.getString("end_pass1");
                String end2=jo.getString("end_pass2");
                String end3=jo.getString("end_pass3");
                String end4=jo.getString("end_pass4");
                String num1=jo.getString("num_end_pass1");
                String num2=jo.getString("num_end_pass2");
                String num3=jo.getString("num_end_pass3");
                String num4=jo.getString("num_end_pass4");
                String tel=jo.getString("tel_pass");

                s=new VariaveisSelectMaximo();
                s.setId(id);
                s.setNome(name);
                s.setEmail(email);
                s.setSenha(senha);
                s.setEnd1(end1);
                s.setEnd2(end2);
                s.setEnd3(end3);
                s.setEnd4(end4);
                s.setNum1(num1);
                s.setNum2(num2);
                s.setNum3(num3);
                s.setNum4(num4);
                s.setTel(tel);


                variaveisSelectMaximos.add(s);

            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
