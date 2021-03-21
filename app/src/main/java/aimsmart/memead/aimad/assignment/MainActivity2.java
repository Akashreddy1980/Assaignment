package aimsmart.memead.aimad.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    TextView Name;
    TextView capital;
    TextView region;
    TextView subregion;
    TextView population;
    TextView languages;
    TextView borders;
    RequestOptions requestOptions;
    private String url = "https://restcountries.eu/data/afg.svg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = findViewById(R.id.flag2);
        Name = findViewById(R.id.countryname2);
        capital = findViewById(R.id.capital);
        region = findViewById(R.id.region);
        subregion = findViewById(R.id.subregion);
        population = findViewById(R.id.population);
        languages = findViewById(R.id.languages);
        borders = findViewById(R.id.borders);
        Name.setText(getIntent().getStringExtra("name"));
        capital.setText(getIntent().getStringExtra("cap"));
        region.setText(getIntent().getStringExtra("reg"));
        subregion.setText(getIntent().getStringExtra("sub"));
        population.setText(getIntent().getStringExtra("pop"));
        languages.setText(getIntent().getStringExtra("lang"));
        borders.setText(getIntent().getStringExtra("bor"));
//        try {
//            URL urls = new URL(url);
//            Glide.with(getApplicationContext()).load(urls).into(imageView);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
    }
}
