package ua.edu.onat.observonat.Helpers;


import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.Log;

import java.lang.reflect.Field;


public class NDSpinner extends AppCompatSpinner {
    public NDSpinner(Context context)
    { super(context); }

    public NDSpinner(Context context, AttributeSet attrs)
    { super(context, attrs); }

    public NDSpinner(Context context, AttributeSet attrs, int defStyle)
    { super(context, attrs, defStyle); }

    private void ignoreOldSelectionByReflection() {
        try {
            Class<?> c = this.getClass().getSuperclass().getSuperclass().getSuperclass();
            Field reqField = c.getDeclaredField("mOldSelectedPosition");
            reqField.setAccessible(true);
            reqField.setInt(this, -1);
        } catch (Exception e) {
            Log.d("Exception Private", "ex", e);

        }
    }

    @Override public void
    setSelection(int position, boolean animate)
    {
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position, animate);
        if (sameSelected) {
            // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            try {
                getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override public void
    setSelection(int position)
    {
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position);
        if (sameSelected) {
            // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
            try {
                getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}

