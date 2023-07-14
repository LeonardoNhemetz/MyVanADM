package myvan.myvanadm.Backgrounds;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;

import myvan.myvanadm.NavPrincipal;
import myvan.myvanadm.OriginDest;

public class BackgroundLogin extends AsyncTask <String,Void,String> {
    WeakReference<Activity> mActivityReference;
    Context context;
    android.support.v7.app.AlertDialog ad;
    ProgressDialog pd;
    User user;
    EditText user_name, password;

    public BackgroundLogin(Context ctx, EditText... editTexes) {
        context = ctx;
        this.user_name = editTexes[0];
        this.password = editTexes[1];

        user = new User();
        user.setUser_name(user_name.getText().toString());
        user.setPassword(password.getText().toString());
    }


    public String doInBackground(String... params) {
        String login_url = "http://35.231.239.84/myvan/ADM/login.php";


        Object connection = Connector.connect(login_url);
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

                String line = "";
                String result = "";

                while ((line = br.readLine()) != null) {
                    result += line;
                }
                br.close();
                is.close();

                return result.toString();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setTitle("Verificando Login");
        pd.setMessage("Espere...");

        pd.show();

    }

    @Override
    public void onPostExecute(String result) {

        if (result.equals("Logado com Sucesso!")) {
            pd.dismiss();
            context.startActivity( new Intent(context, OriginDest.class) );


        } else {
            pd.dismiss();
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
            builder.setTitle("Erro");
            builder.setMessage(result);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            ad = builder.create();
            ad.show();


        }

    }
}
