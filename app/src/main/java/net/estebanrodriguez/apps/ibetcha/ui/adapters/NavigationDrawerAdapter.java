package net.estebanrodriguez.apps.ibetcha.ui.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.estebanrodriguez.apps.ibetcha.R;


public class NavigationDrawerAdapter extends ArrayAdapter<String> {

    private final Context mContext;
    private final String[] mValues;

    public NavigationDrawerAdapter(Context context, String[] values){
        super(context, R.layout.navigation_drawer_item,values);
        mContext = context;
        mValues = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.navigation_drawer_item, parent, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.navigation_item_image_view);
        TextView textView = (TextView) rootView.findViewById(R.id.navigation_item_text_view);
        textView.setText(mValues[position]);
        switch (position){
            case 0:{
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_search_black_24px));
                break;
            }
            case 1:{
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_black_24px));
                break;
            }
            case 2:{
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_list_black_24px));
                break;
            }
            case 3:{
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_trophy));
                break;
            }
            case 4:{
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_thumb_down_black_24px));
                break;
            }
            case 5:{
                imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_tie));
                break;
            }

        }

        return rootView;
    }
}
