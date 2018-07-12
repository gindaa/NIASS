package idev.gin.nias.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import idev.gin.nias.KasusClass;
import idev.gin.nias.R;

public class KasusTbActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private List<KasusClass> tbList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasus_tb);

        recyclerView = (RecyclerView) findViewById(R.id.reckasustb);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getApi();
    }

    private void getApi() {
        String url = "https://damp-fjord-46089.herokuapp.com/faskes";

}}
