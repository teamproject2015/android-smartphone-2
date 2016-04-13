package de.unimannheim.loggingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import de.unimannheim.loggingapp.R;
import de.unimannheim.loggingapp.pojo.NavigationList;

/**
 * Created by suryadevara on 17-06-2015.
 * NavigationAdapter class is used for Navigation menu for selecting different activities
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationViewHolder> {

    private LayoutInflater inflater;

    private List<NavigationList> data = Collections.emptyList();

    public NavigationAdapter(Context context, List<NavigationList> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    /**
     * selects the row and displayed using viewholder
     * @param parent viewgroup
     * @param viewType viewType
     * @return viewholder
     */
    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        return new NavigationViewHolder(view);
    }

    /**
     * returns the number of items in the menu
     * @return data size
     */
    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onBindViewHolder(NavigationViewHolder holder, int position) {
        NavigationList current = data.get(position);

        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getId());
    }

    /**
     * ViewHolder Innerclass to set the Names for each row in navigation menu
     */
    class NavigationViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public NavigationViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textView_rowName);
            icon = (ImageView) itemView.findViewById(R.id.imageView_row);
        }
    }


}
