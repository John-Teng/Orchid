package com.tengo.orchid.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tengo.orchid.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by johnteng on 2018-03-10.
 */

public class SinglePhotoFragment extends android.support.v4.app.Fragment{

    private static final int REQUEST_IMAGE_GET = 1;
    private ImageView mImage;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // SHOULDNT BE NEEDED
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_photos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if (view == null) {
            return;
        }

        mImage = (ImageView) view.findViewById(R.id.main_image);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                if (gallery.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivityForResult(gallery, REQUEST_IMAGE_GET);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK: {
                if (requestCode == REQUEST_IMAGE_GET) {
                    Uri imageUri = data.getData();
                    InputStream inputStream;
                    try {
//                        mImage.setImageURI(data.getData());
                        inputStream = getContext().getContentResolver().openInputStream(imageUri);
                        Bitmap image = BitmapFactory.decodeStream(inputStream);
                        mImage.setImageBitmap(image);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case RESULT_CANCELED:
                // do something:
                break;
            default:
                //do something:
                break;
        }
    }
}
