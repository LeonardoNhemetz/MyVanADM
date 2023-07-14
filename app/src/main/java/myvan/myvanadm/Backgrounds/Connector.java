package myvan.myvanadm.Backgrounds;

import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Leonardo on 12/07/2016.
 */
public class Connector {

    public static HttpURLConnection connect(String urlAdress)
    {
        try
        {
            URL url = new URL(urlAdress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //set props
            con.setRequestMethod("POST");
            con.setConnectTimeout(20000);
            con.setReadTimeout(20000);
            con.setDoInput(true);
            con.setDoOutput(true);

            return con;

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;

    }
}
