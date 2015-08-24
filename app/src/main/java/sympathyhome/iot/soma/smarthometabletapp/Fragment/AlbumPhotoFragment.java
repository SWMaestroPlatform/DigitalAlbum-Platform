package sympathyhome.iot.soma.smarthometabletapp.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import sympathyhome.iot.soma.smarthometabletapp.Data.YSDataUtil;
import sympathyhome.iot.soma.smarthometabletapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumPhotoFragment extends Fragment {


    public AlbumPhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_album_photo, container, false);

        initFragment(root);

        mRootView = root;

        return root;
    }

    private ImageView mImageView;

    private int mFrameNum;

    private View mRootView;

    private final String address = "android.resource://sympathyhome.iot.soma.smarthometabletapp/drawable/";

    private String[] mImageResource = {
            "photo_autumn_09", "photo_autumn_05", "photo_autumn_02", "photo_autumn_04", "photo_autumn_03"
            , "photo_autumn_06", "photo_autumn_07", "photo_autumn_08", "photo_autumn_10", "photo_autumn_11"
    };

    private void initFragment(View view){
        mImageView = (ImageView) view.findViewById(R.id.fragment_album_photo_imageview);


        int pos = getArguments().getInt("image");
        Picasso.with(view.getContext()).load(address + mImageResource[pos]).resize(1280,800).centerCrop().into(mImageView);

        if(pos == 0){
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_album_photo_textlayout);
            linearLayout.setVisibility(view.VISIBLE);
        }
//        mImageView.setImageResource(pos);


//        switch (mFrameNum)
//        {
//            case 0:
//                mImageView.setImageResource(photos[1]);
//                break;
//            default:
//                mImageView.setImageResource(R.drawable.autumn_03);
//                break;
//        }
//        mImageView.setImageResource(R.drawable.autumn_03);

    }


    public void changePhoto(int frameNum){
        YSDataUtil a = new YSDataUtil();
        int []photos = a.getPhotoDrawables();

//        System.out.println(frameNum);

        Picasso.with(mRootView.getContext()).load("android.resource://soma.iot.sympathyhome/drawable/photo_autumn_04").centerCrop().into(mImageView);
    }


}
