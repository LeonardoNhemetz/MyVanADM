package myvan.myvanadm.LocalizacaoVan;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import myvan.myvanadm.Backgrounds.User;


public class BackgroundGeoLoc extends AsyncTask <String,Void,String>
{
    Context ctx;
    protected String result = "";
    protected String line="";
    User user;
    String latVan,lngVan,login_user,pass_user;




    public BackgroundGeoLoc(Context ctx, String... strings)
    {
        this.ctx = ctx;
        this.latVan = strings[0];
        this.lngVan = strings[1];
        user = new User();

        login_user = user.getUser_name();
        pass_user = user.getPassword();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params)
    {
        String url_myvan = "http://35.231.239.84/myvan/ADM/GeoLocVan.php";


        try
        {
            URL url = new URL(url_myvan);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data = URLEncoder.encode("latVan","UTF-8")+"="+URLEncoder.encode(latVan,"UTF-8")+"&"+
                    URLEncoder.encode("lngVan","UTF-8")+"="+URLEncoder.encode(lngVan,"UTF-8")+"&"+
                    URLEncoder.encode("login_user","UTF-8")+"="+URLEncoder.encode(login_user,"UTF-8")+"&"+
                    URLEncoder.encode("pass_user","UTF-8")+"="+URLEncoder.encode(pass_user,"UTF-8");

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

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
    }
}
