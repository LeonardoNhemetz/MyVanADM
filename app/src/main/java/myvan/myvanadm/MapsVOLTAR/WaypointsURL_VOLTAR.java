package myvan.myvanadm.MapsVOLTAR;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
 * Created by Leonardo on 03/01/2017.
 */

public class WaypointsURL_VOLTAR extends AsyncTask<Void, Void, String> {
    private static final String LOG_TAG = "ExampleApp";

    private static final String SERVICE_URL = "http://35.231.239.84/myvan/ADM/MapsVOLTAR.php";

    Context context;
    GetSetOriginDestVOLTAR getset = new GetSetOriginDestVOLTAR();
    User user;





    public WaypointsURL_VOLTAR(Context context) {
        this.context = context;

        user = new User();


    }

    // Invoked by execute() method of this object
    @Override
    protected String doInBackground(Void... args) {

        Object connection = Connector.connect(SERVICE_URL);
        if (connection == null) {
            return null;
        }


        try {
            HttpURLConnection con = (HttpURLConnection) connection;
            OutputStream os = new BufferedOutputStream(con.getOutputStream());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            String registrationData = new RegistrationData(user).packRegistrationData();

            bw.write(registrationData);
            bw.flush();
            bw.close();
            os.close();

            //get response
            int responseCode = con.getResponseCode();
            if (responseCode == con.HTTP_OK) {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader((new InputStreamReader(is)));

                String line;
                StringBuilder json = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    json.append(line + "\n");
                }
                br.close();
                is.close();

                return json.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Executed after the complete execution of doInBackground() method
    @Override
    protected void onPostExecute(String json) {
        StringBuilder waypoints = new StringBuilder();


        try
        {
            // De-serialize the JSON string into an array of city objects
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i <= jsonArray.length(); i++)
            {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                String status = (jsonObj.getString("status_voltar_pass"));

                if(status.equals("sim"))
                {

                    LatLng latLng = new LatLng(jsonObj.getJSONArray("latlng").getDouble(0),
                            jsonObj.getJSONArray("latlng").getDouble(1));

                    MapsVoltar.map2.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                            .title(jsonObj.getString("nome_pass"))
                            .position(latLng));

                    String[] ARlat = new String[jsonArray.length()];
                    ARlat[i] = jsonObj.getJSONArray("latlng").getString(0);
                    String[] ARlng = new String[jsonArray.length()];
                    ARlng[i] = jsonObj.getJSONArray("latlng").getString(1);

                    waypoints.append(ARlat[i] + "," + ARlng[i] + "|");
                }


            }



        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error processing JSON", e);
        }

        waypoints.toString();
        String dest = getset.getEndOriV()+","+getset.getNumOriV()+","+getset.getCidOriV();
        String origin = getset.getEndDestV()+","+getset.getNumDestV()+","+getset.getCidDestV();
        origin = origin.replaceAll(" ","");
        dest = dest.replaceAll(" ","");
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+ origin +
                "&destination=" + dest + "&waypoints=" + waypoints+"&key=AIzaSyAoaxrkg3YoqHVSNxp6FMunQOlj7XMY0rU";

        Log.i("URL:",url);

        RouteVOLTAR routeVOLTAR = new RouteVOLTAR(context);
        routeVOLTAR.execute(url);





    }
    //fim da sub-classe
}
