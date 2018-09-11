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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;

import idev.gin.nias.KasusDetailClass;
import idev.gin.nias.R;
import idev.gin.nias.RiwayatClassKader;
import idev.gin.nias.activity.Form16Activity;
import idev.gin.nias.dao.AddPoinDao;
import idev.gin.nias.utils.CONSTANT;

import static android.content.Context.MODE_PRIVATE;

public class NotifikasiKaderAdapter extends RecyclerView.Adapter<NotifikasiKaderAdapter.ViewHolder> {
    private Context context;
    SharedPreferences sharedPref;
    private ArrayList<RiwayatClassKader> listRiwayatTB;
    private String idkasustb;


    public NotifikasiKaderAdapter(Context context, ArrayList<RiwayatClassKader> listRiwayatTB) {
        this.context = context;
        this.listRiwayatTB = listRiwayatTB;
        sharedPref = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
    }

    @Override
    public NotifikasiKaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.card_notifikasi_kader, parent, false);
        return new NotifikasiKaderAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotifikasiKaderAdapter.ViewHolder holder, int position) {

        holder.idKasus.setText(listRiwayatTB.get(position).getIdriwayat());
        holder.namaKader.setText(listRiwayatTB.get(position).getNamaKaderriwayat());
        holder.desa.setText(listRiwayatTB.get(position).getDesariwayat());
        holder.tanggal.setText(listRiwayatTB.get(position).getTanggalriwayat());
        holder.namaOrangtua.setText(listRiwayatTB.get(position).getNamaOrangtuariwayat());
        holder.namaAnaknakes.setText(listRiwayatTB.get(position).getNamaAnakriwayat());
        holder.usiaAnak.setText(listRiwayatTB.get(position).getUsiaAnakriwayat().toString());
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
                AndroidNetworking.put(CONSTANT.BASE_URL + "penilaianriwayat")
                        .addHeaders("Authorization", "bearer " + pref.getString("token", "0"))
                        .addBodyParameter("fk_faskes", pref.getString("idFaskes", "0"))
                        .addBodyParameter("id",pref.getString("idKasus","0"))
                        .addBodyParameter("status","selesai")
                        .setTag("setstatus")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsObject(AddPoinDao.class, new ParsedRequestListener() {
                            @Override
                            public void onResponse(Object response) {
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(context, "Error Happen" + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                            }
                        });

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
        public TextView idfaskes;
        public Button btIdkasus;

        public ViewHolder(View itemView) {
            super(itemView);
            idKasus = itemView.findViewById(R.id.idkasusnotifkader);
            namaKader = itemView.findViewById(R.id.namakadernotifkader);
            desa = itemView.findViewById(R.id.namadesanotifkader);
            tanggal = itemView.findViewById(R.id.tanggalnotifkader);
            namaAnaknakes = itemView.findViewById(R.id.namaanaknotifkader);
            namaOrangtua = itemView.findViewById(R.id.namaorangtuanotifkader);
            usiaAnak = itemView.findViewById(R.id.usiaanaknotifkader);
            idfaskes = itemView.findViewById(R.id.idkasusfaskesnotifkader);
            btIdkasus = itemView.findViewById(R.id.btgetidkasusnotifkader);

        }


    }
}
