package net.radityalabs.debounceimage;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by radityagumay on 6/11/17.
 */

public class DebounceViewHolder extends RecyclerView.ViewHolder {

    public DebounceImage image;

    public DebounceViewHolder(View itemView) {
        super(itemView);
        image = (DebounceImage) itemView.findViewById(R.id.debounce);
    }
}
