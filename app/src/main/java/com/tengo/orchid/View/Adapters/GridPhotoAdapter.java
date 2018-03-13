package com.tengo.orchid.View.Adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tengo.orchid.Presenter.PhotosTabDelegate;
import com.tengo.orchid.R;

/**
 * Created by johnteng on 2018-03-10.
 */

public class GridPhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PhotosTabDelegate mDelegate;
    private final int ITEM_ADD_PHOTO = 0;
    private final int ITEM_SINGLE_PHOTO = 1;

    public GridPhotoAdapter(PhotosTabDelegate delegate) {
        mDelegate = delegate;
    }

    public void setDelegate(@Nullable PhotosTabDelegate delegate) {
        mDelegate = delegate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        // TODO add onclicklisteners
        switch (viewType) {
            case ITEM_SINGLE_PHOTO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_photo, parent);
                return new GridPhotoViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_add_photo, parent);
                return new AddPhotoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDelegate == null) {
            return;
        }
        int viewType = getItemViewType(position);

        switch (viewType) {
            case ITEM_ADD_PHOTO: {
                GridPhotoViewHolder photoViewHolder = (GridPhotoViewHolder)holder;
                photoViewHolder.mThumbnail.setImageBitmap(mDelegate.getThumbnail());
                break;
            }
            case ITEM_SINGLE_PHOTO: {
                AddPhotoViewHolder addPhotoViewHolder = (AddPhotoViewHolder)holder;
                addPhotoViewHolder.mThumbnail.setImageResource(R.drawable.selector);
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_ADD_PHOTO : ITEM_SINGLE_PHOTO;
    }

    @Override
    public int getItemCount() {
        if (mDelegate == null) {
            return 0;
        } else {
            return mDelegate.getListSize();
        }
    }

    class GridPhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView mThumbnail;

        public GridPhotoViewHolder(View itemView) {
            super(itemView);
        }
    }

    class AddPhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView mThumbnail;

        public AddPhotoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
