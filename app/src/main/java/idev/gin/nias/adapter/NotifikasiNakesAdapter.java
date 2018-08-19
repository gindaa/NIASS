package idev.gin.nias.adapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import idev.gin.nias.KasusClass;
import idev.gin.nias.KasusDetailClass;
import idev.gin.nias.R;
import idev.gin.nias.activity.KasusTbActivity;
import idev.gin.nias.activity.RiwayatTbActivity;

import static android.content.Context.MODE_PRIVATE;

public class NotifikasiNakesAdapter extends RecyclerView.Adapter<NotifikasiNakesAdapter.ViewHolder> {
    private Context context;
    SharedPreferences sharedPref;
    private ArrayList<KasusDetailClass> listKasusClassTB;
    private String idkasustb;


    public NotifikasiNakesAdapter(Context context, ArrayList<KasusDetailClass> listKasusClassTB) {
        this.context = context;
        this.listKasusClassTB = listKasusClassTB;
        sharedPref = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
    }

    @Override
    public NotifikasiNakesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.card_notifikasi, parent, false);
        return new NotifikasiNakesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotifikasiNakesAdapter.ViewHolder holder, int position) {

        holder.idKasus.setText(listKasusClassTB.get(position).getIdKasus());
        holder.namaFaskes.setText(listKasusClassTB.get(position).getmTextnamafaskes());
        holder.Alamat.setText(listKasusClassTB.get(position).getmTextAlamat());
        holder.rtrw.setText(listKasusClassTB.get(position).getmTextrtrw());
        holder.kelurahan.setText(listKasusClassTB.get(position).getmTextKelurahan());
        holder.kecamatan.setText(listKasusClassTB.get(position).getmTextKecematan());
        holder.kabupaten.setText(listKasusClassTB.get(position).getmTextKabupaten());
        holder.provinsi.setText(listKasusClassTB.get(position).getmTextProvinsi());
        holder.kota.setText(listKasusClassTB.get(position).getmTextKota());
        holder.noReg.setText(listKasusClassTB.get(position).getmTextregis());
        holder.noRegTb.setText(listKasusClassTB.get(position).getmTextregisTbKota());
        holder.namaPasien.setText(listKasusClassTB.get(position).getmTextNamaPasien());
        holder.Nik.setText(listKasusClassTB.get(position).getmTextNik());
        holder.Jk.setText(listKasusClassTB.get(position).getMtextjk());
        holder.Umur.setText(listKasusClassTB.get(position).getmTextUmur());

        holder.dirujuk.setText(listKasusClassTB.get(position).getmTextRujuk());
        holder.tipeDiagnosisTB.setText(listKasusClassTB.get(position).getmTextdiagnosistb());
        holder.btIdkasus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idkasustb = listKasusClassTB.get(position).getIdKasus();
                String idKasus = sharedPref.getString("idKasus", "kosong");
//                Toast.makeText(context, "ID KASUS:" + idkasustb, Toast.LENGTH_LONG).show();
                SharedPreferences pref = context.getSharedPreferences("MyPrefs",MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPref.edit();
                edit.putString("idKasus", listKasusClassTB.get(position).getIdKasus());
                edit.apply();
                Toast.makeText(context, "ID KASUS Adalah:" + pref.getString("idKasus", "Id tidak Ketemu"), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, RiwayatTbActivity.class);
                intent.putExtra("email",pref.getString("email", "email"));
                intent.putExtra("token",pref.getString("token", "email"));
                intent.putExtra("idKasus" , idKasus);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (listKasusClassTB != null) ? listKasusClassTB.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idKasus;
        public TextView namaFaskes;
        public TextView alamat;
        public TextView rtrw;
        public TextView kelurahan;
        public TextView kecamatan;
        public TextView kabupaten;
        public TextView provinsi;
        public TextView kota;
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

        public ViewHolder(View itemView) {
            super(itemView);
            idKasus = itemView.findViewById(R.id.idkasus);
            namaFaskes = itemView.findViewById(R.id.namafaskeskasus);
            Alamat = itemView.findViewById(R.id.alamatkasus);
            rtrw = itemView.findViewById(R.id.rtrwnotif);
            kelurahan = itemView.findViewById(R.id.kelurahannotif);
            kecamatan = itemView.findViewById(R.id.kecamatannotif);
            kabupaten = itemView.findViewById(R.id.kabupatennotif);
            provinsi = itemView.findViewById(R.id.provinsiotif);
            kota = itemView.findViewById(R.id.kotanotif);
            noReg = itemView.findViewById(R.id.noregfaskeskasus);
            noRegTb = itemView.findViewById(R.id.noregtbkasus);
            namaPasien = itemView.findViewById(R.id.namapasienkasus);
            Nik = itemView.findViewById(R.id.nikkasus);
            Jk = itemView.findViewById(R.id.jeniskelaminkasus);
            Umur = itemView.findViewById(R.id.umurkasus);
            dirujuk = itemView.findViewById(R.id.dirujukkasus);
            tipeDiagnosisTB = itemView.findViewById(R.id.tipediagnosiskasus);
            btIdkasus = itemView.findViewById(R.id.btgetidkasus);

        }


    }
}
