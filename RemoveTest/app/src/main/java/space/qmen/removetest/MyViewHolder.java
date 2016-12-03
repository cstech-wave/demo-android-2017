package space.qmen.removetest;

// http://blog.csdn.net/u012911704/article/details/52786870

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView content;
    public TextView delete;
    public LinearLayout layout;

    public MyViewHolder(View itemView) {
        super(itemView);
        content = (TextView) itemView.findViewById(R.id.item_content);
        delete = (TextView) itemView.findViewById(R.id.item_delete);
        layout = (LinearLayout) itemView.findViewById(R.id.item_layout);
    }
}
