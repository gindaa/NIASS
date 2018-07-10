package idev.gin.nias;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class KasusTbAdapter extends RecyclerView.Adapter<KasusTbAdapter.tbViewHolder> {

    Context context;
    List<KasusClass> listKasusClassTB;
    tbClickListener listener;

    public KasusTbAdapter(Context context, List<KasusClass> listKasusClassTB, tbClickListener listener) {
        this.context = context;
        this.listKasusClassTB = listKasusClassTB;
        this.listener = listener;
    }

    @Override
    public tbViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.card_kasustb,parent,false);
        return new tbViewHolder(root);
    }

    @Override
    public void onBindViewHolder(tbViewHolder holder, int position) {
            KasusClass mkasustb = listKasusClassTB.get(position);
            holder.namatb.setText(mkasustb.getmTextNama());
            holder.noregtb.setText(mkasustb.getmTextregis());
            holder.jktb.setText(mkasustb.getmTextjk());

    };

    @Override
    public int getItemCount() {
        //return listKasusClassTB;
        return 1;
    }

    public class tbViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView namatb, noregtb ,jktb;
        ;

        public tbViewHolder(View itemView) {
            super(itemView);

            namatb = itemView.findViewById(R.id.namapasien);
            noregtb = itemView.findViewById(R.id.noregis);
            jktb = itemView.findViewById(R.id.jk);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            listener.tbClicked(position);
        }
    }

    public interface tbClickListener{
        void tbClicked(int position);
    }
}
