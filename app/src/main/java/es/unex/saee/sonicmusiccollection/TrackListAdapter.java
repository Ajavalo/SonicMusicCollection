package es.unex.saee.sonicmusiccollection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.ViewHolder> {

    private List<TrackListItem> mItems = new ArrayList<TrackListItem>();
    Context mContext;

    public interface OnItemClickListener {
        void onItemClick(TrackListItem item);     //Type of the element to be returned
    }

    private final OnItemClickListener listener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public TrackListAdapter(Context context, OnItemClickListener listener) {
        mContext = context;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrackListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        //Inflate the View for every element
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tracklist_item, parent, false);

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

    public void add(TrackListItem item) {

        mItems.add(item);
        notifyDataSetChanged();

    }

    public void clear(){

        mItems.clear();
        notifyDataSetChanged();

    }

    public void load(List<TrackListItem> items){

        mItems.clear();
        mItems = items;
        notifyDataSetChanged();

    }

    public Object getItem(int pos) {

        return mItems.get(pos);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            //Get the references to every widget of the Item View
            name = (TextView) itemView.findViewById(R.id.trackName);

        }

        public void bind(final TrackListItem trackListItem, final OnItemClickListener listener) {

            //Display Name in TextView
            name.setText(trackListItem.getName());

            //TODO - Display Priority in a TextView


            // TODO - Display Time and Date.
            // Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and time String


            // TODO - Set up Status CheckBox


            itemView.setOnClickListener(new View.OnClickListener() {

                //Open selected Track
                @Override
                public void onClick(View v) {
                    listener.onItemClick(trackListItem);
                }
            });
        }
    }

}
