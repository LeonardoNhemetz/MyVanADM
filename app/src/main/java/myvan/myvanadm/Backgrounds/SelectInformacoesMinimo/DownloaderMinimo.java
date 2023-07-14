package myvan.myvanadm.Backgrounds.SelectInformacoesMinimo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import myvan.myvanadm.Backgrounds.Connector;
import myvan.myvanadm.Backgrounds.RegistrationData;
import myvan.myvanadm.Backgrounds.User;

/**
 * Created by Leonardo on 12/07/2016.
 */
public class DownloaderMinimo extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    ListView lv;


    User user;

    ProgressDialog pd;

    public DownloaderMinimo(Context c, String urlAddress, ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;


        user = new User();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Fetch");
        pd.setMessage("Please Wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();



        if(s==null)
        {
            Toast.makeText(c,"Sem exito",Toast.LENGTH_SHORT).show();
        }
        else
        {
            //call data parser
            DataPasserMinimo parser = new DataPasserMinimo(c,lv,s);
            parser.execute();

        }
    }

    private String downloadData() {
        Object connection= Connector.connect(urlAddress);
        if(connection==null)
        {
            return null;
        }


        try
        {
            HttpURLConnection con = (HttpURLConnection) connection;
            OutputStream os= new BufferedOutputStream(con.getOutputStream());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            String registrationData = new RegistrationData(user).packRegistrationData();

            bw.write(registrationData);
            bw.flush();
            bw.close();
            os.close();

            //get response
            int responseCode = con.getResponseCode();
            if(responseCode==con.HTTP_OK)
            {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader((new InputStreamReader(is)));

                String line;
                StringBuffer response = new StringBuffer();

                while ((line=br.readLine()) != null)
                {
                    response.append(line+"\n");
                }
                br.close();
                is.close();

                return response.toString();
            }



        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "erro no IO";
        }

        return null;
    }
}
