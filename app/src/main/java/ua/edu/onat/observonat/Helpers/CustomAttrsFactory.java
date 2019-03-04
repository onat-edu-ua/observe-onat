package ua.edu.onat.observonat.Helpers;



import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

@SuppressWarnings( "deprecation" )
public class CustomAttrsFactory implements LayoutInflaterFactory {

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        int attributeValue = attrs
                .getAttributeResourceValue(
                        "http://schemas.android.com/apk/res-auto",
                        "fontFamily", 0);
        LayoutInflater inflater = LayoutInflater.from(context);
        if(attributeValue!=0)
        {
            try {
                TextView v = (TextView) inflater.createView(name, null, attrs);
                v.setText(attributeValue);
            } catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return null;
    }


}
