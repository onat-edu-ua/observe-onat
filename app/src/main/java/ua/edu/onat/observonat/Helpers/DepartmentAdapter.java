package ua.edu.onat.observonat.Helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ua.edu.onat.observonat.R;

public class DepartmentAdapter extends ArrayAdapter<DepartmentItem> {

    private ArrayList<DepartmentItem> dataset;
    private Context _context;

    public DepartmentAdapter(ArrayList<DepartmentItem> data, Context context){
        super(context, R.layout.cafedra_item, data);
        this.dataset = data;
        _context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DepartmentItem item = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cafedra_item, parent, false);
        }

        TextView nameView = convertView.findViewById(R.id.name_of_department);
        TextView idView = convertView.findViewById(R.id.id_of_department);

        nameView.setText(item.name);
        idView.setText(item.id);

        return convertView;
    }

}
