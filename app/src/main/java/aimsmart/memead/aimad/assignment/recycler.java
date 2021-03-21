package aimsmart.memead.aimad.assignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class recycler extends RecyclerView.Adapter<recycler.recyclerholder> {
    private List<senddata> list;
    private List<User> dblist;
    private Context context;
    private AppDatabase database;
    User user = new User();
    public recycler(List<senddata> list, Context context ) {
        this.list = list;
        this.context = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public recyclerholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclelist, parent,false);
        return new recyclerholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final recyclerholder holder, int position) {
        final senddata currentitem =  list.get(position);
        holder.title.setText(currentitem.getTitle());
        holder.imageView.setImageResource(R.mipmap.ic_launcher_round);
        database = AppDatabase.getInstance(context);
        dblist = database.userDao().getAll();
          try {
              holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(context, MainActivity2.class);
                      intent.putExtra("name", currentitem.getTitle());
                      intent.putExtra("flag", currentitem.getImageurl());
                      intent.putExtra("cap", currentitem.getCapital());
                      intent.putExtra("reg", currentitem.getRegion());
                      intent.putExtra("sub", currentitem.getSubregion());
                      intent.putExtra("pop", currentitem.getPopulation());
                      intent.putExtra("lang",currentitem.getLanguages());
                      intent.putExtra("bor",currentitem.getBorder());
                      context.startActivity(intent);
                  }
              });
              holder.imageButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if(user.getStatus()==null){
                          user.setStatus(false);
                      }
                      if(!user.getStatus()) {
                          user.setName(currentitem.getTitle());
                          user.setCapital(currentitem.getCapital());
                          user.setReg(currentitem.getRegion());
                          user.setSubreg(currentitem.getSubregion());
                          user.setPopulation(currentitem.getPopulation());
                          user.setLanguage(currentitem.getLanguages());
                          user.setBorder(currentitem.getBorder());
                          user.setStatus(true);
                          holder.imageButton.setImageResource(R.drawable.ic_baseline_minimize_24);
                          database.userDao().insert(user);
                          dblist.addAll(database.userDao().getAll());
                          Toast.makeText(context, "Added to database - " + currentitem.getTitle() + user.getStatus(), Toast.LENGTH_SHORT).show();
                      }
                      else{
                          Toast.makeText(context, "Already added" + currentitem.getTitle(), Toast.LENGTH_SHORT).show();
                      }
                  }
              });
          }
          catch (Exception e){
              Log.e("Error",e.getMessage());
          }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class recyclerholder extends RecyclerView.ViewHolder{
        TextView title;
        CardView cardView;
        ImageView imageView;
        RelativeLayout relativeLayout;
        ImageButton imageButton;
        public recyclerholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.countryname);
            cardView=itemView.findViewById(R.id.cardshow);
            imageView = itemView.findViewById(R.id.flagshow);
            relativeLayout = itemView.findViewById(R.id.recyclelist1);
            imageButton = itemView.findViewById(R.id.addbtn);
        }

    }

}
