package tw.scu.edu.graduationprojrct.Setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import tw.scu.edu.graduationprojrct.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    private ArrayList username, password;

    public CustomAdapter(Context context, ArrayList username, ArrayList password){
        this.context = context;
        this.username = username;
        this.password = password;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.manager_username.setText(String.valueOf(username.get(position)));
        holder.manager_password.setText(String.valueOf(password.get(position)));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView manager_username, manager_password;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            manager_username = itemView.findViewById(R.id.manager_username);
            manager_password = itemView.findViewById(R.id.manager_password);
        }
    }
}
