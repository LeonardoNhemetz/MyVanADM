package myvan.myvanadm.MapsVOLTAR;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonardo on 03/01/2017.
 */

public class RouteVOLTAR extends AsyncTask<String, Void, String> {

    Context context;
    Polyline polyline;
    List<LatLng> list;



    public RouteVOLTAR(Context ctx) {
        context = ctx;
    }


    protected String doInBackground(String... params) {



        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();



            InputStream is = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader((new InputStreamReader(is)));

            String line;
            StringBuilder json = new StringBuilder();

            while ((line = br.readLine()) != null) {
                json.append(line + "\n");
            }
            br.close();
            is.close();

            return json.toString();




        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected void onPostExecute(String json) {
        try {

            list = buildJSONRoute(json);
            drawRoute();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private List<LatLng> decodePolyline(String encoded) {

        List<LatLng> listPoints = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)), (((double) lng / 1E5)));

            listPoints.add(p);
        }
        return listPoints;
    }


    public List<LatLng> buildJSONRoute(String json) throws JSONException {
        JSONObject result = new JSONObject(json);

        Log.i("String",json);


        JSONArray routes = result.getJSONArray("routes");
        JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");
        JSONArray steps = null;

        List<LatLng> lines = new ArrayList<LatLng>();

        for (int a = 0; a < legs.length(); a++) {
            steps = legs.getJSONObject(a).getJSONArray("steps");


            for (int i = 0; i < steps.length(); i++) {

                String polyline = steps.getJSONObject(i).getJSONObject("polyline").getString("points");

                for (LatLng p : decodePolyline(polyline)) {
                    lines.add(p);
                }


            }
        }

        return (lines);
    }
    public void drawRoute() {
        PolylineOptions po;


        if (polyline == null) {
            po = new PolylineOptions();

            for (int i = 0, tam = list.size(); i < tam; i++) {
                po.add(list.get(i));
            }

            po.color(Color.BLUE).width(7);
            polyline = MapsVoltar.map2.addPolyline(po);
        } else {
            polyline.setPoints(list);
        }
    }
}
