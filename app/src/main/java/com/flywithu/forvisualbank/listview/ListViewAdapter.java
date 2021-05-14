package com.flywithu.forvisualbank.listview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.flywithu.forvisualbank.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter{
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    private View.OnClickListener onClickListener = null;
    private ListViewAdapter() {

    }

    public ListViewAdapter(View.OnClickListener onClick)
    {
        onClickListener = onClick;
    }
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        if(convertView.getTag()==null) {
            ListViewItem listViewItem = listViewItemList.get(position);

            TextView pg_nameView = (TextView) convertView.findViewById(R.id.pg_name) ;
            EditText account_editView = (EditText) convertView.findViewById(R.id.account_number) ;
            ImageButton img_buttonView = convertView.findViewById(R.id.imageButton);
            img_buttonView.setTag(position);
            img_buttonView.setOnClickListener(onClickListener);

            pg_nameView.setText(listViewItem.getPg_name());
            account_editView.setText(listViewItem.getAccount_name());
            account_editView.setTag(position);


            if(position == 0)
            {
                ((ImageButton)convertView.findViewById(R.id.imageButton)).setImageResource(android.R.drawable.ic_menu_save);
                account_editView.setEnabled(false);
            }





            account_editView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int position = (Integer) account_editView.getTag();
                    ListViewItem lv = listViewItemList.get(position);
                    lv.setAccount_name(s.toString());
                    listViewItemList.set(position, lv);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        convertView.setTag("DONE");
        return convertView;
    }


    @Override
    public long getItemId(int position) {
        return position ;
    }


    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }


    public void addItem(String pg_name,String account) {
        ListViewItem item = new ListViewItem();

        item.setPg_name(pg_name);
        item.setAccount_name(account);


        listViewItemList.add(item);
    }

    public void delItem(int position)
    {
        listViewItemList.remove(position);
    }
}
