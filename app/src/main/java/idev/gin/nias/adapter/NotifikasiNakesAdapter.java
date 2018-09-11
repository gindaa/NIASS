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
import idev.gin.nias.RiwayatClass;
import idev.gin.nias.activity.Form16Activity;
import idev.gin.nias.activity.KasusTbActivity;
import idev.gin.nias.activity.RiwayatTbActivity;
import idev.gin.nias.activity.ScoringActivity;

import static android.content.Context.MODE_PRIVATE;

public class NotifikasiNakesAdapter extends RecyclerView.Adapter<NotifikasiNakesAdapter.ViewHolder> {
    private Context context;
    SharedPreferences sharedPref;
    private ArrayList<RiwayatClass> listRiwayatTB;
    private String idkasustb;


    public NotifikasiNakesAdapter(Context context, ArrayList<RiwayatClass> listRiwayatTB) {
        this.context = context;
        this.listRiwayatTB = listRiwayatTB;
        sharedPref = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
    }

    @Override
    public NotifikasiNakesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.card_notifikasi_nakes, parent, false);
        return new NotifikasiNakesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotifikasiNakesAdapter.ViewHolder holder, int position) {

        holder.idKasus.setText(listRiwayatTB.get(position).getIdriwayat());
        holder.namaKader.setText(listRiwayatTB.get(position).getNamaKaderriwayat());
        holder.desa.setText(listRiwayatTB.get(position).getDesariwayat());
        holder.tanggal.setText(listRiwayatTB.get(position).getTanggalriwayat());
        holder.namaOrangtua.setText(listRiwayatTB.get(position).getNamaOrangtuariwayat());
        holder.namaAnaknakes.setText(listRiwayatTB.get(position).getNamaAnakriwayat());
        holder.usiaAnak.setText(listRiwayatTB.get(position).getUsiaAnakriwayat().toString());
        holder.jumlahAnak.setText(listRiwayatTB.get(position).getJumlahAnakriwayat().toString());
        holder.alamatDesa.setText(listRiwayatTB.get(position).getAlamatDesariwayat());
        holder.kelurahan.setText(listRiwayatTB.get(position).getKelurahanriwayat());
        holder.kecamatan.setText(listRiwayatTB.get(position).getKecamatanriwayat());
        holder.kabupaten.setText(listRiwayatTB.get(position).getKabupatenriwayat());
        holder.provinsi.setText(listRiwayatTB.get(position).getProvinsiriwayat());
        holder.idfaskes.setText((listRiwayatTB.get(position).getIdfaskes().toString()));
        holder.btIdkasus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idkasustb = listRiwayatTB.get(position).getIdriwayat();
                String idKasus = sharedPref.getString("idKasus", "kosong");
//                Toast.makeText(context, "ID KASUS:" + idkasustb, Toast.LENGTH_LONG).show();
                SharedPreferences pref = context.getSharedPreferences("MyPrefs",MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPref.edit();
                edit.putString("idKasus", listRiwayatTB.get(position).getIdriwayat());
                edit.putString("idFaskes",listRiwayatTB.get(position).getIdfaskes().toString());
                edit.apply();
                Toast.makeText(context, "ID KASUS Adalah:" + pref.getString("idKasus", "Id tidak Ketemu"), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, Form16Activity.class);
                intent.putExtra("email",pref.getString("email", "email"));
                intent.putExtra("token",pref.getString("token", "email"));
                intent.putExtra("idKasus" , idKasus);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (listRiwayatTB != null) ? listRiwayatTB.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idKasus;
        public TextView namaKader;
        public TextView desa;
        public TextView tanggal;
        public TextView namaOrangtua;
        public TextView namaAnaknakes;
        public TextView usiaAnak;
        public TextView jumlahAnak;
        public TextView alamatDesa;
        public TextView kecamatan;
        public TextView kelurahan;
        public TextView kabupaten;
        public TextView provinsi;
        public TextView idfaskes;
        public Button btIdkasus;

        public ViewHolder(View itemView) {
            super(itemView);
            idKasus = itemView.findViewById(R.id.idkasusnotifnakes);
            namaKader = itemView.findViewById(R.id.namakadernotifnakes);
            desa = itemView.findViewById(R.id.namadesanotifnakes);
            tanggal = itemView.findViewById(R.id.tanggalnotifnakes);
            namaAnaknakes = itemView.findViewById(R.id.namaanaknotifnakes);
            usiaAnak = itemView.findViewById(R.id.usiaanaknotifnakes);
            jumlahAnak = itemView.findViewById(R.id.jumlahkasusnotifnakes);
            alamatDesa = itemView.findViewById(R.id.alamatdesa);
            namaOrangtua = itemView.findViewById(R.id.namaorangtuanotifnakes);
            kelurahan = itemView.findViewById(R.id.kelurahannotifnakes);
            kecamatan = itemView.findViewById(R.id.kecamatannotifnakes);
            kabupaten = itemView.findViewById(R.id.kabupatennotifnakes);
            provinsi = itemView.findViewById(R.id.provinsinotifnakes);
            idfaskes = itemView.findViewById(R.id.idkasusfaskesnotifnakes);
            btIdkasus = itemView.findViewById(R.id.btgetidkasusnotifnakes);

        }


    }
}
