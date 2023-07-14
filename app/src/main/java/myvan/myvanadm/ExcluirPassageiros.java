package myvan.myvanadm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import myvan.myvanadm.Backgrounds.BackgroundExclude;
import myvan.myvanadm.Backgrounds.User;

public class ExcluirPassageiros extends AppCompatActivity {

    EditText txtExcluir;
    String strExcluir;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_excluir_passageiros );

        txtExcluir = (EditText)findViewById( R.id.txtExcluir );
    }

    public void ExcluirPassageiro(View view)
    {
        strExcluir = txtExcluir.getText().toString();
        String login =  user.getUser_name().toString();
        String password = user.getPassword().toString();

        if(strExcluir.equals( "" ))
        {
            Toast.makeText(this, "Entrada de texto em branco", Toast.LENGTH_LONG ).show();
        }
        else
        {

            BackgroundExclude backgroundExclude = new BackgroundExclude( this );
            backgroundExclude.execute(login,password,strExcluir );

        }



    }
}
