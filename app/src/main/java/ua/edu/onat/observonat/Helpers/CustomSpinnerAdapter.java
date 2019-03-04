package ua.edu.onat.observonat.Helpers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import ua.edu.onat.observonat.R;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<CharSequence> implements SpinnerAdapter {

    private final Context activity;
    private ArrayList<String> asr;

    public CustomSpinnerAdapter(Context context, int res, ArrayList<String> asr) {
        super(context, res);
        this.asr=asr;
        activity = context;
    }



    public int getCount()
    {
        return asr.size();
    }

    @Nullable
    @Override
    public CharSequence getItem(int position) {
        return asr.get(position);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView txt = new TextView(activity);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(18);
        txt.setGravity(Gravity.CENTER_VERTICAL);
        txt.setText(asr.get(position));
        txt.setTextColor(Color.parseColor("#000000"));
        return  txt;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AppCompatSpinner.LayoutParams txtParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        Drawable img = ContextCompat.getDrawable(activity, R.drawable.ic_triangle);
        if(img!=null)
            img.setBounds(0,0,28,23);
        Typeface font = ResourcesCompat.getFont(activity, R.font.fradm);
        TextView txt = new TextView(activity);
        txt.setLayoutParams(txtParams);
        txt.setTypeface(font, 16);
        txt.setGravity(Gravity.CENTER);
        txt.setBackgroundResource(R.drawable.dropdown_background);
        txt.setCompoundDrawablePadding(24);
        txt.setPadding(16, 16, 16, 16);
        txt.setCompoundDrawables(null, null, img, null);
        txt.setText(asr.get(position));
        txt.setTextColor(Color.parseColor("#424242"));
        return  txt;
    }

}
