package com.ins.kuaidi.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ins.kuaidi.R;
import com.ins.middle.entity.TestEntity;
import com.sobey.common.interfaces.OnRecycleItemClickListener;

import java.util.List;


public class RecycleAdapterCoupon extends RecyclerView.Adapter<RecycleAdapterCoupon.Holder> {

    private Context context;
    private List<TestEntity> results;

    public List<TestEntity> getResults() {
        return results;
    }

    public RecycleAdapterCoupon(Context context, List<TestEntity> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecycleAdapterCoupon.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_coupon, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecycleAdapterCoupon.Holder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onItemClick(holder);
            }
        });
        TestEntity show = results.get(holder.getLayoutPosition());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView img_item_show_pic;

        public Holder(View itemView) {
            super(itemView);
//            img_item_show_pic = (ImageView) itemView.findViewById(R.id.img_item_show_pic);
        }
    }

    private OnRecycleItemClickListener listener;

    public void setOnItemClickListener(OnRecycleItemClickListener listener) {
        this.listener = listener;
    }
}