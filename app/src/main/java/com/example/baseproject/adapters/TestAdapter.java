package com.example.baseproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;
import com.example.baseproject.utilities.StringUtils;

import java.util.ArrayList;

/**
 * Created by Shivang Goel on 28/7/16.
 */
public class TestAdapter extends BaseRecyclerViewAdapter {

    private ArrayList<String> mList;

    public TestAdapter(ArrayList<String> dataList) {
        super(dataList.size());
        mList = dataList;
    }

    @Override
    void onBindVH(RecyclerView.ViewHolder holder, int position) {
         /*  if (holder instanceof CloudFileItemListHolder) {
            CloudFile file = mList.get(position);
            CloudFileItemListHolder vh = (CloudFileItemListHolder) holder;
            vh.tvFileName.setText(file.filewithoutExtension());

            String fileSize = Formatter.formatShortFileSize(mContext, file.getFileSize());
            vh.tvFileCreated.setText(fileSize + " | " + file.getCommentCount() + " Comment");
            ImageUtils.getInstance().loadImage(mContext, vh.ivImage, AppConstant.FILE_ICON_BASE_URL + FilenameUtils.getExtension(file.getName()) + ".png" ,R.drawable.ic_image, ImageDimenConstants.getInstance(mContext).IMAGE_70);
        }*/
    }

    @Override
    RecyclerView.ViewHolder onCreateVH(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_empty_adapter, parent, false);
        return new CloudFileItemListHolder(itemView);
    }

    @Override
    public int getItemCount() {
//        return mList.size();
        return mList.size() > 0 ? mList.size() : 1;
    }


    public class CloudFileItemListHolder extends RecyclerView.ViewHolder {

        /*private LinearLayout llFileList;
        private TextView tvFileName, tvFileCreated;
        private ImageView ivImage;

        private View.OnClickListener cloudFileOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getLayoutPosition();
                CloudFile file = mList.get(position);
                Intent intent = new Intent(mContext, CloudFileDetailsActivity.class);
                intent.putExtra(AppConstant.SELECTED_FOLDER_NAME, mFolderName);
                EventBus.getDefault().postSticky(file);
                mContext.startActivity(intent);
            }
        };*/

        public CloudFileItemListHolder(View view) {
            super(view);
           /* llFileList = (LinearLayout) view.findViewById(R.id.ll_file_list);
            tvFileName = (TextView) view.findViewById(R.id.tvFileName);
            tvFileCreated = (TextView) view.findViewById(R.id.tvFileCreatedDate);
            ivImage = (ImageView) view.findViewById(R.id.ivRoundFileImage);
            llFileList.setOnClickListener(cloudFileOnClickListener);*/
        }

    }
}
