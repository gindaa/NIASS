package idev.gin.nias.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import idev.gin.nias.KasusClass;
import idev.gin.nias.R;
import idev.gin.nias.activity.KasusTbActivity;

public class KasusTbAdapter extends RecyclerView.Adapter<KasusTbAdapter.ViewHolder> {

    private Context context;
    private ArrayList<KasusClass> listKasusClassTB;




    public KasusTbAdapter(Context context, ArrayList<KasusClass> listKasusClassTB) {
        this.context = context;
        this.listKasusClassTB = listKasusClassTB;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView =  layoutInflater.inflate(R.layout.card_kasustb,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.idKasus.setText(listKasusClassTB.get(position).getIdKasus());
        holder.namaFaskes.setText(listKasusClassTB.get(position).getmTextnamafaskes());
        holder.kabKota.setText(listKasusClassTB.get(position).getmTextKota());
        holder.namaProvinsi.setText(listKasusClassTB.get(position).getmTextProvinsi());
        holder.noReg.setText(listKasusClassTB.get(position).getmTextregis());
        holder.noRegTb.setText(listKasusClassTB.get(position).getmTextregisTbKota());
        holder.namaPasien.setText(listKasusClassTB.get(position).getmTextNamaPasien());
        holder.Nik.setText(listKasusClassTB.get(position).getmTextNik());
        holder.Jk.setText(listKasusClassTB.get(position).getMtextjk());
        holder.Umur.setText(listKasusClassTB.get(position).getmTextUmur());
        holder.Alamat.setText(listKasusClassTB.get(position).getmTextAlamat());
        holder.dirujuk.setText(listKasusClassTB.get(position).getmTextRujuk());
        holder.tipeDiagnosisTB.setText(listKasusClassTB.get(position).getmTextdiagnosistb());
        holder.btIdkasus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Anda Ingin menginvestigasi id no:"+position, Toast.LENGTH_LONG).show();
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return (listKasusClassTB != null) ? listKasusClassTB.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView idKasus;
        public TextView namaFaskes;
        public TextView kabKota;
        public TextView namaProvinsi;
        public TextView noReg;
        public TextView noRegTb;
        public TextView namaPasien;
        public TextView Nik;
        public TextView Jk;
        public TextView Umur;
        public TextView Alamat;
        public TextView dirujuk;
        public TextView tipeDiagnosisTB;
        public Button btIdkasus;

        public ViewHolder(View itemView){
            super(itemView);
            idKasus = (TextView) itemView.findViewById(R.id.idkasus);
            namaFaskes = (TextView) itemView.findViewById(R.id.namafaskeskasus);
            kabKota = (TextView) itemView.findViewById(R.id.kabkotakasus);
            namaProvinsi = (TextView) itemView.findViewById(R.id.namaprovinsikasus);
            noReg = (TextView) itemView.findViewById(R.id.noregfaskeskasus);
            noRegTb = (TextView) itemView.findViewById(R.id.noregtbkasus);
            namaPasien = (TextView) itemView.findViewById(R.id.namapasienkasus);
            Nik = (TextView) itemView.findViewById(R.id.nikkasus);
            Jk = (TextView) itemView.findViewById(R.id.jeniskelaminkasus);
            Umur = (TextView) itemView.findViewById(R.id.umurkasus);
            Alamat = (TextView) itemView.findViewById(R.id.alamatkasus);
            dirujuk = (TextView) itemView.findViewById(R.id.dirujukkasus);
            tipeDiagnosisTB = (TextView) itemView.findViewById(R.id.tipediagnosiskasus);
            btIdkasus = (Button) itemView.findViewById(R.id.btgetidkasus);

        }



    }
}
