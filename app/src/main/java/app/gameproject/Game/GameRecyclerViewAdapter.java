package app.gameproject.Game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import app.gameproject.R;

public class GameRecyclerViewAdapter extends RecyclerView.Adapter<GameRecyclerViewAdapter.ViewHolder> {
    private ArrayList<GameRecyclerViewItem> mData;

    public GameRecyclerViewAdapter(ArrayList<GameRecyclerViewItem> list) {
        mData = list;
    }

    @NonNull
    @Override
    public GameRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.game_recycler_view_item, parent, false);
        GameRecyclerViewAdapter.ViewHolder vh = new GameRecyclerViewAdapter.ViewHolder(view);
        return vh;
    }

    private OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull GameRecyclerViewAdapter.ViewHolder holder, int position) {
        GameRecyclerViewItem item = mData.get(position);

        holder.id.setText(item.getId());
        holder.name.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       TextView id;
       TextView name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(view1 -> {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    if (mListener != null)
                        mListener.onItemClick(view1, position);
                }
            });

            id = itemView.findViewById(R.id.game_recycler_item_id);
            name = itemView.findViewById(R.id.game_recycler_item_name);
        }
    }
}
