package myvan.myvanadm.Backgrounds;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Leonardo on 15/12/2017.
 */

public class BackgroundExclude extends AsyncTask<String,Void,String>
{

    Context ctx;
    ProgressDialog pd;


    protected String result = "";
    protected String line="";

    public BackgroundExclude(Context ctx)
    {
        this.ctx = ctx;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(ctx);
        pd.setTitle("Analisando");
        pd.setMessage("Espere...");
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String login_url = "http://35.231.239.84/myvan/ADM/excluir.php";
        String login = params[0];
        String password = params[1];
        String strExcluir = params[2];


        try
        {

            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data = URLEncoder.encode("login","UTF-8")+"="+URLEncoder.encode(login,"UTF-8")+"&"+
                    URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                    URLEncoder.encode("ExcluirPassageiro","UTF-8")+"="+URLEncoder.encode(strExcluir,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream IS = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));

            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            bufferedReader.close();
            IS.close();
            httpURLConnection.disconnect();
            return result;


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        pd.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(result)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
