package aimsmart.memead.aimad.assignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerdb extends RecyclerView.Adapter<recyclerdb.reholder> {
    private List<User> list;
    Context context;
    AppDatabase database;

    Activity activity;
    public recyclerdb(Context context, List<User> list, Activity activity){
        this.context = context;
        this.list = list;
        this.activity=activity;
    }
    @NonNull
    @Override
    public reholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclelist, parent,false);
        return new reholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final reholder holder, final int position) {
         final User currentitem = list.get(position);
         holder.title.setText(currentitem.getName());
         holder.imageView.setImageResource(R.mipmap.ic_launcher);
         holder.imageButton.setImageResource(R.drawable.ic_baseline_minimize_24);
         database = AppDatabase.getInstance(context);
         list = database.userDao().getAll();
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("name", currentitem.getName());
//                intent.putExtra("flag", currentitem.getImageurl());
                intent.putExtra("cap", currentitem.getCapital());
                intent.putExtra("reg", currentitem.getReg());
                intent.putExtra("sub", currentitem.getSubreg());
                intent.putExtra("pop", currentitem.getPopulation());
                intent.putExtra("lang",currentitem.getLanguage());
                intent.putExtra("bor",currentitem.getBorder());
                context.startActivity(intent);
            }
        });
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User U = list.get(position);
                database.userDao().delete(U);
                list.remove(position);
                notifyDataSetChanged();
                U.setStatus(false);
                if(list.isEmpty()){
                    holder.progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class reholder extends RecyclerView.ViewHolder{
        TextView title;
        CardView cardView;
        ImageView imageView;
        RelativeLayout relativeLayout;
        ImageButton imageButton;
        ProgressBar progressBar;
        public reholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.countryname);
            cardView=itemView.findViewById(R.id.cardshow);
            imageView = itemView.findViewById(R.id.flagshow);
            relativeLayout = itemView.findViewById(R.id.recyclelist1);
            imageButton = itemView.findViewById(R.id.addbtn);
            progressBar = activity.findViewById(R.id.progressBarpro);
        }
    }
}
