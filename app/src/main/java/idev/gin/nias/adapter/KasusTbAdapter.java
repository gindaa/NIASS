package idev.gin.nias.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import idev.gin.nias.KasusClass;
import idev.gin.nias.R;
import idev.gin.nias.activity.KasusTbActivity;
import idev.gin.nias.activity.LoginActivity;
import idev.gin.nias.activity.RiwayatTbActivity;
import idev.gin.nias.activity.SignUpActivity;
import idev.gin.nias.dao.SignupDao;
import idev.gin.nias.utils.CONSTANT;

import static android.content.Context.MODE_PRIVATE;

public class KasusTbAdapter extends RecyclerView.Adapter<KasusTbAdapter.ViewHolder> {

    private Context context;
    SharedPreferences sharedPref;
    private ArrayList<KasusClass> listKasusClassTB;
    private ArrayList<KasusClass> listKasusClassTbFull;
    private String idkasustb;


    public KasusTbAdapter(Context context, ArrayList<KasusClass> listKasusClassTB) {
        this.context = context;
        this.listKasusClassTB = listKasusClassTB;
        listKasusClassTbFull = new ArrayList<>(listKasusClassTB);
        sharedPref = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.card_kasustb, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // We need an editor object to make changes
        holder.idKasus.setText(listKasusClassTB.get(position).getIdKasus());
        holder.namaFaskes.setText(listKasusClassTB.get(position).getmTextnamafaskes());
        holder.kabKota.setText(listKasusClassTB.get(position).getmTextKota());
        holder.namaProvinsi.setText(listKasusClassTB.get(position).getmTextProvinsi());
        holder.noReg.setText(listKasusClassTB.get(position).getmTextregis());
        holder.noRegTb.setText(listKasusClassTB.get(position).getmTextregisTbKota());
        holder.kecamatan.setText(listKasusClassTB.get(position).getmTextKecamatan());
        holder.kelurahan.setText(listKasusClassTB.get(position).getmTextKelurahan());
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
                ((Activity)context).finish();
            }
        });
        holder.btSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = context.getSharedPreferences("MyPrefs",MODE_PRIVATE);
                String tokenpass = pref.getString("token", "token");
                String emailpass = pref.getString("email", "mail");
                idkasustb = listKasusClassTB.get(position).getIdKasus();
                AndroidNetworking.put(CONSTANT.BASE_URL + "faskes")
                        .addHeaders("Authorization", "bearer " + tokenpass)
                        .addBodyParameter("id",idkasustb)
                        .addBodyParameter("status","selesai")
                        .setTag("setStatusSelesai")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsObject(SignupDao.class, new ParsedRequestListener<SignupDao>() {
                            @Override
                            public void onResponse(SignupDao response) {
                                Toast.makeText(context, "Kasus TB Selesai", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent (context, KasusTbActivity.class);
                                intent.putExtra("email",emailpass);
                                intent.putExtra("token",tokenpass);
                                ((Activity)context).finish();
                                context.startActivity(intent);
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(context, "Error :" + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                            }
                        });

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
        public TextView kabKota;
        public TextView namaProvinsi;
        public TextView noReg;
        public TextView noRegTb;
        public TextView kecamatan;
        public TextView kelurahan;
        public TextView namaPasien;
        public TextView Nik;
        public TextView Jk;
        public TextView Umur;
        public TextView Alamat;
        public TextView dirujuk;
        public TextView tipeDiagnosisTB;
        public Button btIdkasus;
        public Button btSelesai;

        public ViewHolder(View itemView) {
            super(itemView);
            idKasus = itemView.findViewById(R.id.idkasus);
            namaFaskes = itemView.findViewById(R.id.namafaskeskasus);
            kabKota = itemView.findViewById(R.id.kabkotakasus);
            namaProvinsi = itemView.findViewById(R.id.namaprovinsikasus);
            noReg = itemView.findViewById(R.id.noregfaskeskasus);
            noRegTb = itemView.findViewById(R.id.noregtbkasus);
            kecamatan = itemView.findViewById(R.id.kecamatankasustb);
            kelurahan = itemView.findViewById(R.id.kelurahankasustb);
            namaPasien = itemView.findViewById(R.id.namapasienkasus);
            Nik = itemView.findViewById(R.id.nikkasus);
            Jk = itemView.findViewById(R.id.jeniskelaminkasus);
            Umur = itemView.findViewById(R.id.umurkasus);
            Alamat = itemView.findViewById(R.id.alamatkasus);
            dirujuk = itemView.findViewById(R.id.dirujukkasus);
            tipeDiagnosisTB = itemView.findViewById(R.id.tipediagnosiskasus);
            btIdkasus = itemView.findViewById(R.id.btgetidkasus);
            btSelesai = itemView.findViewById(R.id.btselesai);

        }


    }
//    private Filter kasusfilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<KasusClass> filteredlist = new ArrayList<>();
//            if (constraint == null || constraint.length() == 0){
//                filteredlist.addAll(listKasusClassTbFull);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (KasusClass item : listKasusClassTbFull){
//                    if(item.getmTextKecamatan().toLowerCase().contains(filterPattern) || item.getmTextKelurahan().toLowerCase().contains(filterPattern)){
//                        filteredlist.add(item);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredlist;
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            listKasusClassTB.clear();
//            listKasusClassTB.addAll((List)results.values);
//            notifyDataSetChanged();
//
//        }
//    };
}
