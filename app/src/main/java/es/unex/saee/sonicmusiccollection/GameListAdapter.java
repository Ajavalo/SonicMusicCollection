package es.unex.saee.sonicmusiccollection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {

    private List<GameListItem> mItems = new ArrayList<GameListItem>();
    Context mContext;

    public interface OnItemClickListener {
        void onItemClick(GameListItem item);     //Type of the element to be returned
    }

    private final OnItemClickListener listener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public GameListAdapter(Context context, OnItemClickListener listener) {
        mContext = context;
        this.listener = listener;
        
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GameListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // - Inflate the View for every element
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gamelist_item, parent, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mItems.get(position),listener);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void add(GameListItem item) {

        mItems.add(item);
        notifyDataSetChanged();

    }

    public void clear(){

        mItems.clear();
        notifyDataSetChanged();

    }

    public void load(List<GameListItem> items){

        mItems.clear();
        mItems = items;
        notifyDataSetChanged();

    }

    public Object getItem(int pos) {

        return mItems.get(pos);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private Button button;

        public ViewHolder(View itemView) {
            super(itemView);

            // - Get the references to every widget of the Item View
            title = (TextView) itemView.findViewById(R.id.gameTitle);
            button = (Button) itemView.findViewById(R.id.gameButton);

        }

        public void bind(final GameListItem gameListItem, final OnItemClickListener listener) {

            // - Display Title in TextView
            title.setText(GameListItem.getTitle());

            // - Display Abbv in Button
            button.setText(GameListItem.getAbbv());

            itemView.setOnClickListener(new View.OnClickListener() {

                //TODO - Open selected game
                @Override
                public void onClick(View v) {
                    listener.onItemClick(gameListItem);
                }
            });
        }
    }

}
