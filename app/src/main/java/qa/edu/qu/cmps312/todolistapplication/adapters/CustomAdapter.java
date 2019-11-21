package qa.edu.qu.cmps312.todolistapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import qa.edu.qu.cmps312.todolistapplication.R;
import qa.edu.qu.cmps312.todolistapplication.model.Tutorial;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Tutorial> Tutorial;
    private Context context;
    private AdapterInteraction interaction;

    public CustomAdapter(Context context, ArrayList<Tutorial> Tutorial) {
        this.Tutorial = Tutorial;
        this.context = context;
        try {
            if (!(context instanceof AdapterInteraction)) throw new AssertionError();
            interaction = (AdapterInteraction) context;
        } catch (AssertionError e) {

        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView subjectTv;
        private TextView nameTv;
        private TextView phoneTv;
        private TextView infoTv;



        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            subjectTv = itemView.findViewById(R.id.subjectTv);
            nameTv = itemView.findViewById(R.id.nameTv);
            phoneTv = itemView.findViewById(R.id.phoneTv);
            infoTv = itemView.findViewById(R.id.informationTv);



        }
    }

    public interface AdapterInteraction {
        void deleteTodo(int position);
        void editToDoList(int position, Tutorial todo);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_design, viewGroup, false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.subjectTv.setText(Tutorial.get(i).getSubject());
       viewHolder.nameTv.setText(Tutorial.get(i).getName());
        viewHolder.phoneTv.setText(Tutorial.get(i).getPhone());
        viewHolder.infoTv.setText(Tutorial.get(i).getInfo());
    }

    @Override
    public int getItemCount() {
        return Tutorial.size();
    }
}
