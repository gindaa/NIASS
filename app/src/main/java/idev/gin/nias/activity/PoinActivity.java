package idev.gin.nias.activity;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.MissingResourceException;

import idev.gin.nias.R;
import idev.gin.nias.dao.AkunidDao;
import idev.gin.nias.dao.PoinDao;
import idev.gin.nias.utils.CONSTANT;

public class PoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poin);
        Bundle extras = getIntent().getExtras();
        String emailpass = extras.getString("email");
        String tokenpass = extras.getString("token");
        final TextView pointv = (TextView) findViewById(R.id.poinview);
        try {
            AndroidNetworking.get(CONSTANT.BASE_URL + "getpoint")
                    .addHeaders("Authorization", "bearer " + tokenpass)
                    .addHeaders("email", emailpass)
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsObject(PoinDao.class, new ParsedRequestListener<PoinDao>() {
                        @Override
                        public void onResponse(PoinDao response) {
                            int poin = response.getPoint();
                            pointv.setText(String.valueOf(poin));
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getApplicationContext(), "Failed get Poin : " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
        catch (Resources.NotFoundException err){
            err.printStackTrace();

        }
    }

}
