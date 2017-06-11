package net.radityalabs.debounceimage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by radityagumay on 6/11/17.
 */

public class DebounceAdapter extends RecyclerView.Adapter<DebounceViewHolder> {

    private List<Debounce> items;

    public DebounceAdapter(List<Debounce> items) {
        this.items = items;
    }

    @Override
    public DebounceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.debounce_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DebounceViewHolder holder, int position) {
        holder.image.setForegroundImage(items.get(position).foreground);
        holder.image.setBackgroundImage(items.get(position).background);
        holder.image.refreshView(items.size(), position);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
