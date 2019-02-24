package com.example.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layoutRef;
    private List<String> names;

    public MyAdapter(Context context, int layoutRef, List<String> names){
        this.context = context;
        this.layoutRef = layoutRef;
        this.names = names;
    }

    //says to adapt how many iteration will do over given collection
    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return names.get(i);
    }

    @Override
    public long getItemId(int i) { return i;
    }

    /**
     *
     * @param position
     * @param convertView transform view
     * @param viewGroup
     * @return inflated and modified view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        //viewHolder Pattern
        ViewHolder holder;
        //only if never be rendering
        if(convertView == null){
            //parsing layout to layoutinflater inflanting
            LayoutInflater lI = LayoutInflater.from(this.context);
            convertView = lI.inflate(this.layoutRef, null);

            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //get clicked position element
        String currentName = names.get(position);
        //currentName = (String) getItem(position);

        holder.nameTextView.setText(currentName);

        return convertView;
    }

    static class ViewHolder{

        private TextView nameTextView;

    }
}
