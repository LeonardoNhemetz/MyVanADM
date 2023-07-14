package myvan.myvanadm;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import myvan.myvanadm.Backgrounds.BackgroundInsert;

public class CadastrarPassageiros extends Activity {

    EditText  txt_Name,txt_Password,txt_Email,txt_End1,txt_End2,txt_End3,txt_End4,txt_Num1,txt_Num2,txt_Num3,txt_Num4,txt_Cid1,txt_Cid2,txt_Cid3,txt_Cid4,txt_Tel,txt_login_user,txt_pass_user;
    String nome_pass,senha_pass,email_pass,end1_pass,end2_pass,end3_pass,end4_pass,num1_end_pass,num2_end_pass,num3_end_pass,num4_end_pass,cid1_pass,cid2_pass,cid3_pass,cid4_pass,tel_pass,login_user,pass_user;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_passageiros);

        txt_Name = (EditText)findViewById(R.id.txtName);
        txt_Password = (EditText)findViewById(R.id.txtPassword);
        txt_Email = (EditText)findViewById(R.id.txtEmail);
        txt_End1 = (EditText)findViewById(R.id.txtEnd1);
        txt_End2 = (EditText)findViewById(R.id.txtEnd2);
        txt_End3 = (EditText)findViewById(R.id.txtEnd3);
        txt_End4 = (EditText)findViewById(R.id.txtEnd4);
        txt_Num1 = (EditText)findViewById(R.id.txtNum1);
        txt_Num2 = (EditText)findViewById(R.id.txtNum2);
        txt_Num3 = (EditText)findViewById(R.id.txtNum3);
        txt_Num4 = (EditText)findViewById(R.id.txtNum4);
        txt_Cid1 = (EditText)findViewById(R.id.txtCid1);
        txt_Cid2 = (EditText)findViewById(R.id.txtCid2);
        txt_Cid3 = (EditText)findViewById(R.id.txtCid3);
        txt_Cid4 = (EditText)findViewById(R.id.txtCid4);
        txt_Tel = (EditText)findViewById(R.id.txtTel);
        txt_login_user = (EditText)findViewById(R.id.txtLoginUser);
        txt_pass_user = (EditText)findViewById(R.id.txtPassUser);

        SharedPreferences sharedPreferences =  getSharedPreferences("LoginADM",MODE_PRIVATE);
        String Login = sharedPreferences.getString("Login","");
        String Pass = sharedPreferences.getString("Pass","");

        txt_login_user.setText(Login);
        txt_pass_user.setText(Pass);

        txt_login_user.setFocusable(false);
        txt_pass_user.setFocusable(false);




    }

    public void UserReg(View view)
    {
        nome_pass = txt_Name.getText().toString();
        senha_pass = txt_Password.getText().toString();
        email_pass = txt_Email.getText().toString();
        end1_pass = txt_End1.getText().toString();
        end2_pass = txt_End2.getText().toString();
        end3_pass = txt_End3.getText().toString();
        end4_pass = txt_End4.getText().toString();
        num1_end_pass = txt_Num1.getText().toString();
        num2_end_pass = txt_Num2.getText().toString();
        num3_end_pass = txt_Num3.getText().toString();
        num4_end_pass = txt_Num4.getText().toString();
        cid1_pass = txt_Cid1.getText().toString();
        cid2_pass = txt_Cid2.getText().toString();
        cid3_pass = txt_Cid3.getText().toString();
        cid4_pass = txt_Cid4.getText().toString();
        tel_pass = txt_Tel.getText().toString();
        login_user = txt_login_user.getText().toString();
        pass_user = txt_pass_user.getText().toString();




        String type = "Register";
        BackgroundInsert backgroundInsert = new BackgroundInsert(this);
        backgroundInsert.execute(type,nome_pass,senha_pass,email_pass,
                end1_pass,end2_pass,end3_pass,end4_pass,
                num1_end_pass, num2_end_pass, num3_end_pass, num4_end_pass,
                cid1_pass,cid2_pass,cid3_pass,cid4_pass,
                tel_pass,login_user,pass_user);

    }
}
