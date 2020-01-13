package com.example.selectutilpro.city_recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.selectutilpro.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyHolder> {

    private Context mContext;
    private List<PinYinComparator.CityBean> mData;
    public static final int HAS_STICKY_VIEW = 1;
    public static final int NONE_STICKY_VIEW = 2;

    public CityAdapter(List<PinYinComparator.CityBean> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_select_city, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
//        //这个区域才有点击事件
//        holder.recycle_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnItemClickListener.onItemClick(position);
//            }
//        });


        PinYinComparator.CityBean info = mData.get(position);

        holder.name.setText(info.getName());
        char selection = info.getSortLetter().charAt(0);
        int letterPosition = getPositionForSelection(selection);
        if (letterPosition == position) {
            holder.letter_tv.setText(info.getSortLetter());
            holder.letter_tv.setVisibility(View.VISIBLE);
            holder.itemView.setTag(HAS_STICKY_VIEW);
        } else {
            holder.letter_tv.setVisibility(View.GONE);
            holder.itemView.setTag(NONE_STICKY_VIEW);
        }

        holder.itemView.setContentDescription(info.getSortLetter());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView name;//歌名
        TextView letter_tv;//字母导航
//        RelativeLayout recycle_item;


        public MyHolder(View itemView) {
            super(itemView);
            letter_tv = (TextView) itemView.findViewById(R.id.item_letter_text);
            name = (TextView) itemView.findViewById(R.id.item_select_city_text);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }


    /**
     * 判断是否为首字母
     *
     * @param selection
     * @return 返回字母第一次出现的位置
     */
    public int getPositionForSelection(int selection) {
        for (int i = 0; i < mData.size(); i++) {
            char firstLetter = mData.get(i).getSortLetter().toUpperCase().charAt(0);
            if (selection == firstLetter) {
                return i;
            }
        }
        return -1;
    }
}
