package com.tengo.orchid.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tengo.orchid.Presenter.GridPhotosPresenter;
import com.tengo.orchid.R;
import com.tengo.orchid.View.Adapters.GridPhotoAdapter;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by johnteng on 2018-03-05.
 */

public class PhotosTabFragment extends android.support.v4.app.Fragment
        implements GridPhotoAdapter.GridPhotosDelegate {

    public interface GridPhotosPresenterDelegate {
        Bitmap getThumbnail(int position);

        int getListSize();

        int getRating(int position);
    }

    private RecyclerView mRecyclerView;
    private GridPhotoAdapter mAdapter;
    private GridPhotosPresenterDelegate mPresenterDelegate;
    private final int REQUEST_IMAGE_GET = 0;
    private final int NUM_COLUMNS = 4;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
        mPresenterDelegate = new GridPhotosPresenter(getContext());
        mAdapter = new GridPhotoAdapter(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.photos_recyclerview);
        GridLayoutManager gm = new GridLayoutManager(getContext(), NUM_COLUMNS);
        mRecyclerView.setLayoutManager(gm);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void openSinglePhoto(int position) {
        Intent intent = new Intent(getContext(), SinglePhotoActivity.class);
        Bundle params = new Bundle();
        params.putInt(getString(R.string.param_photo_id), position);
        intent.putExtras(params);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK: {
                if (requestCode == REQUEST_IMAGE_GET) {
                    Uri imageUri = data.getData();
                    InputStream inputStream;
                    try {
                        inputStream = getContext().getContentResolver().openInputStream(imageUri);
                        Bitmap image = BitmapFactory.decodeStream(inputStream);
                        // TODO get the presenter to save this image into fs and log in db
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

    @Override
    public Bitmap getThumbnail(int position) {
        return mPresenterDelegate.getThumbnail(position);
    }

    @Override
    public int getListSize() {
        return mPresenterDelegate.getListSize();
    }

    @Override
    public int getRating(int position) {
        return mPresenterDelegate.getRating(position);
    }

    @Override
    public void onAddPhoto() {
        // TODO fill this in to add new new photo
    }

    @Override
    public void onSelectPhoto(int position) {
        openSinglePhoto(position);
    }
}
