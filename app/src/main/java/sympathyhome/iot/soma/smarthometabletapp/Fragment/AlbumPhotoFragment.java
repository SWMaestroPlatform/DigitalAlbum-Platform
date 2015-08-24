package sympathyhome.iot.soma.smarthometabletapp.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import sympathyhome.iot.soma.smarthometabletapp.AlbumPhotoActivity;
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

        return root;
    }

    private ImageView mImageView;

    private int mFrameNum;

    private void initFragment(View view){
        mImageView = (ImageView) view.findViewById(R.id.fragment_album_photo_imageview);



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

    }


}
