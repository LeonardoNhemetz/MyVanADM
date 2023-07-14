package myvan.myvanadm.Backgrounds.SelectInformacoesMinimo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import myvan.myvanadm.R;

/**
 * Created by Leonardo on 12/07/2016.
 */
public class AdapterSelectMinimo extends BaseAdapter {

    Context c;
    ArrayList<VariaveisSelectMinimo> variaveisSelectMinimos;
    LayoutInflater inflater;

    public AdapterSelectMinimo(Context c, ArrayList<VariaveisSelectMinimo> variaveisSelectMinimos) {
        this.c = c;
        this.variaveisSelectMinimos = variaveisSelectMinimos;

        //initialize
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return variaveisSelectMinimos.size();
    }

    @Override
    public Object getItem(int position) {
        return variaveisSelectMinimos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return variaveisSelectMinimos.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.modelminimo,parent,false);
        }

        TextView nameTxt=(TextView) convertView.findViewById(R.id.nameTxt);
        TextView IrTxt=(TextView) convertView.findViewById(R.id.IrTxt);
        TextView VoltarTxt=(TextView) convertView.findViewById(R.id.VoltarTxt);

        nameTxt.setText(variaveisSelectMinimos.get(position).getName());
        IrTxt.setText(variaveisSelectMinimos.get(position).getIr());
        VoltarTxt.setText(variaveisSelectMinimos.get(position).getVoltar());



        return convertView;
    }
}
