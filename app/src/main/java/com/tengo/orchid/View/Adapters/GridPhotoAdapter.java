package com.tengo.orchid.View.Adapters;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tengo.orchid.R;

/**
 * Created by johnteng on 2018-03-10.
 */

public class GridPhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface GridPhotosDelegate {

        Bitmap getThumbnail(int position);

        int getListSize();

        int getRating(int position);

        void onAddPhoto();

        void onSelectPhoto(int position);
    }

    private GridPhotosDelegate mDelegate;
    private final int ITEM_ADD_PHOTO = 0;
    private final int ITEM_SINGLE_PHOTO = 1;

    public GridPhotoAdapter(GridPhotosDelegate fragmentDelegate) {
        mDelegate = fragmentDelegate;
    }

    public void setFragmentDelegate(@Nullable GridPhotosDelegate delegate) {
        mDelegate = delegate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ITEM_SINGLE_PHOTO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_photo, parent, false);
                return new GridPhotoViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_add_photo, parent, false);
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
            case ITEM_SINGLE_PHOTO: {
                GridPhotoViewHolder photoViewHolder = (GridPhotoViewHolder) holder;
                // position - 1 to account for the "add photo" item in the first position
                photoViewHolder.mThumbnail.setImageBitmap(mDelegate.getThumbnail(position - 1));
                // Set item position here so that the delegate can reference it
                photoViewHolder.setItemPosition(position - 1);
                break;
            }
            case ITEM_ADD_PHOTO: {
                AddPhotoViewHolder addPhotoViewHolder = (AddPhotoViewHolder) holder;
                addPhotoViewHolder.mThumbnail.setImageResource(R.drawable.ic_add);
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
            // Add 1 because of the "add photo" button
            return mDelegate.getListSize() + 1;
        }
    }

    class GridPhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView mThumbnail;
        private int mItemPosition;

        public GridPhotoViewHolder(View itemView) {
            super(itemView);
            mThumbnail = (ImageView) itemView.findViewById(R.id.single_photo_thumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDelegate != null) {
                        mDelegate.onSelectPhoto(mItemPosition);
                    }
                }
            });
        }

        public void setItemPosition(int position) {
            mItemPosition = position;
        }
    }

    class AddPhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView mThumbnail;

        public AddPhotoViewHolder(View itemView) {
            super(itemView);
            mThumbnail = (ImageView) itemView.findViewById(R.id.add_photo_thumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDelegate != null) {
                        mDelegate.onAddPhoto();
                    }
                }
            });
        }
    }

}
