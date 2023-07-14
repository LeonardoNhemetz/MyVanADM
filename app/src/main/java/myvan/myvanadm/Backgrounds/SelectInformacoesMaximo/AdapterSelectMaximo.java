package myvan.myvanadm.Backgrounds.SelectInformacoesMaximo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import myvan.myvanadm.R;

/**
 * Created by Leonardo on 06/09/2016.
 */
public class AdapterSelectMaximo extends BaseAdapter{

    Context c;
    ArrayList<VariaveisSelectMaximo> variaveisSelectMaximos = new ArrayList<>();
    LayoutInflater inflater;

    public AdapterSelectMaximo(Context c, ArrayList<VariaveisSelectMaximo> variaveisSelectMaximos) {
        this.c = c;
        this.variaveisSelectMaximos = variaveisSelectMaximos;

        //initialize
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return variaveisSelectMaximos.size();
    }

    @Override
    public Object getItem(int position) {
        return variaveisSelectMaximos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return variaveisSelectMaximos.get(position).getId();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.modelmaximo, parent, false);
        }

        TextView nameMax = (TextView) convertView.findViewById(R.id.nameMaxTxt);
        TextView emailMax = (TextView) convertView.findViewById(R.id.emailMaxTxt);
        TextView SenhaMax = (TextView) convertView.findViewById(R.id.SenhaMaxTxt);
        TextView End1Max = (TextView) convertView.findViewById(R.id.End1MaxTxt);
        TextView End2Max = (TextView) convertView.findViewById(R.id.End2MaxTxt);
        TextView End3Max = (TextView) convertView.findViewById(R.id.End3MaxTxt);
        TextView End4Max = (TextView) convertView.findViewById(R.id.End4MaxTxt);
        TextView Num1Max = (TextView) convertView.findViewById(R.id.Num1MaxTxt);
        TextView Num2Max = (TextView) convertView.findViewById(R.id.Num2MaxTxt);
        TextView Num3Max = (TextView) convertView.findViewById(R.id.Num3MaxTxt);
        TextView Num4Max = (TextView) convertView.findViewById(R.id.Num4MaxTxt);
        TextView TelMax = (TextView) convertView.findViewById(R.id.TelMaxTxt);

        nameMax.setText(variaveisSelectMaximos.get(position).getNome());
        emailMax.setText(variaveisSelectMaximos.get(position).getEmail());
        SenhaMax.setText(variaveisSelectMaximos.get(position).getSenha());
        End1Max.setText(variaveisSelectMaximos.get(position).getEnd1());
        End2Max.setText(variaveisSelectMaximos.get(position).getEnd2());
        End3Max.setText(variaveisSelectMaximos.get(position).getEnd3());
        End4Max.setText(variaveisSelectMaximos.get(position).getEnd4());
        Num1Max.setText(variaveisSelectMaximos.get(position).getNum1());
        Num2Max.setText(variaveisSelectMaximos.get(position).getNum2());
        Num3Max.setText(variaveisSelectMaximos.get(position).getNum3());
        Num4Max.setText(variaveisSelectMaximos.get(position).getNum4());
        TelMax.setText(variaveisSelectMaximos.get(position).getTel());

        return convertView;
    }
}
