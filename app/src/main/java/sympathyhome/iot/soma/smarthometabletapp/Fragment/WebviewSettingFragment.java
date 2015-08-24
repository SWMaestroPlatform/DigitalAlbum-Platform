package sympathyhome.iot.soma.smarthometabletapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sympathyhome.iot.soma.smarthometabletapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebviewSettingFragment extends Fragment {


    public WebviewSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webview_setting, container, false);
    }


}
