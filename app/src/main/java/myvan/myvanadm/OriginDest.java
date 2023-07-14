package myvan.myvanadm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import myvan.myvanadm.MapsIR.GetSetOriginDestIR;
import myvan.myvanadm.MapsVOLTAR.GetSetOriginDestVOLTAR;

public class OriginDest extends AppCompatActivity {

    EditText txtEndOri,txtCidOri,txtNumOri,txtEndDest,txtCidDest,txtNumDest;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origin_dest);


        txtEndOri = (EditText) findViewById(R.id.txtOriEnd);
        txtCidOri = (EditText) findViewById(R.id.txtOriCid);
        txtNumOri = (EditText) findViewById(R.id.txtOriNum);
        txtEndDest = (EditText) findViewById(R.id.txtDestEnd);
        txtCidDest = (EditText) findViewById(R.id.txtDestCid);
        txtNumDest = (EditText) findViewById(R.id.txtDestNum);

        SharedPreferences sharedPreferences =  getSharedPreferences("OriDest",MODE_PRIVATE);
        String EndOriS = sharedPreferences.getString("EndOriS","");
        String CidOriS = sharedPreferences.getString("CidOriS","");
        String NumOriS = sharedPreferences.getString("NumOriS","");
        String EndDestS = sharedPreferences.getString("EndDestS","");
        String CidDestS = sharedPreferences.getString("CidDestS","");
        String NumDestS = sharedPreferences.getString("NumDestS","");

        txtEndOri.setText(EndOriS);
        txtCidOri.setText(CidOriS);
        txtNumOri.setText(NumOriS);
        txtEndDest.setText(EndDestS);
        txtCidDest.setText(CidDestS);
        txtNumDest.setText(NumDestS);



    }



    public void SaveShared(View view)
    {

        String EndOriS = txtEndOri.getText().toString();
        String CidOriS = txtCidOri.getText().toString();
        String NumOriS = txtNumOri.getText().toString();
        String EndDestS = txtEndDest.getText().toString();
        String CidDestS = txtCidDest.getText().toString();
        String NumDestS = txtNumDest.getText().toString();

        SharedPreferences sharedPreferences =  getSharedPreferences("OriDest",MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("EndOriS",EndOriS);
        editor.putString("CidOriS",CidOriS);
        editor.putString("NumOriS",NumOriS);
        editor.putString("EndDestS",EndDestS);
        editor.putString("CidDestS",CidDestS);
        editor.putString("NumDestS",NumDestS);

        editor.commit();

        GetSetOriginDestIR getset = new GetSetOriginDestIR();
        getset.setEndOri(EndOriS);
        getset.setCidOri(CidOriS);
        getset.setNumOri(NumOriS);
        getset.setEndDest(EndDestS);
        getset.setNumDest(NumDestS);
        getset.setCidDest(CidDestS);

        GetSetOriginDestVOLTAR getsetV = new GetSetOriginDestVOLTAR();
        getsetV.setEndOriV(EndOriS);
        getsetV.setCidOriV(CidOriS);
        getsetV.setNumOriV(NumOriS);
        getsetV.setEndDestV(EndDestS);
        getsetV.setNumDestV(NumDestS);
        getsetV.setCidDestV(CidDestS);

        this.startActivity(new Intent(this, Principal.class));
    }
}
