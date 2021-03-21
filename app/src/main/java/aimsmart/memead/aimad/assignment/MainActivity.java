package aimsmart.memead.aimad.assignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<senddata> arrayList = new ArrayList<>();
    getdata getdata;
    ProgressBar progressBarpro;
    AppDatabase database;
    recyclerdb recyclerdb;
    private List<User> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.list1);
        progressBarpro=(ProgressBar)findViewById(R.id.progressBarpro);
        if(isNetworkAvailable()){
            getdata = (getdata)new getdata(this, this).execute();
        }
        else{
            database = AppDatabase.getInstance(this);
            datalist = database.userDao().getAll();
            if(!datalist.isEmpty()){
               LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
               recyclerView.setLayoutManager(linearLayoutManager);
               recyclerdb = new recyclerdb(this,datalist,this);
               recyclerView.setHasFixedSize(true);
               recyclerView.setAdapter(recyclerdb);
            }
            else{
                progressBarpro.setVisibility(View.VISIBLE);
            }
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}