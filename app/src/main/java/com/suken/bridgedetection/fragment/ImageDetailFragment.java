package com.suken.bridgedetection.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.suken.bridgedetection.R;
import com.yuntongxun.ecdemo.photoview.PhotoViewAttacher;
import com.suken.bridgedetection.util.Logger;

public class ImageDetailFragment extends Fragment {
	private String mImageUrl;
	private int imageSource;
	private ImageView mImageView;
	private ProgressBar progressBar;
	private RelativeLayout LoadingLayout;
	private TextView LoadingText;
	private PhotoViewAttacher mAttacher;

	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putString("url", imageUrl);
//		args.putInt("sourch", imageSource);
		f.setArguments(args);

		return f;
	}	
	private Activity mContext;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
		imageSource = getArguments().getInt("sourch");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContext = ImageDetailFragment.this.getActivity();
		final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
		mImageView = (ImageView) v.findViewById(R.id.image);
		mAttacher = new PhotoViewAttacher(mImageView);
		
		mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
			
			@Override
			public void onPhotoTap(View arg0, float arg1, float arg2) {
//				mContext.finish();
			}
		});
		
		progressBar = (ProgressBar) v.findViewById(R.id.loading);
		LoadingLayout = (RelativeLayout) v.findViewById(R.id.loadingLayout);
		LoadingText = (TextView) v.findViewById(R.id.loadingText);
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mImageView.setImageBitmap(BitmapFactory.decodeFile(mImageUrl));
//		ImageLoader.getInstance().displayImage(mImageUrl, mImageView,options, new ImageLoadingListener() {
//			//加载开始
//			@Override
//			public void onLoadingStarted(String imageUri, View view) {
//				// TODO Auto-generated method stub
//				progressBar.setVisibility(View.VISIBLE);
//			}
//			// 加载失败
//			@Override
//			public void onLoadingFailed(String imageUri, View view,
//					FailReason failReason) {
//				// TODO Auto-generated method stub
//				String message = null;
//				switch (failReason.getType()) {
//				case IO_ERROR:
//					message = "下载错误";
//					break;
//				case DECODING_ERROR:
//					message = "图片无法显示";
//					break;
//				case NETWORK_DENIED:
//					message = "网络有问题，无法下载";
//					break;
//				case OUT_OF_MEMORY:
//					message = "图片太大无法显示";
//					break;
//				case UNKNOWN:
//					message = "未知的错误";
//					break;
//				}
//				Logger.d("aaa", message);
////				Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
//				progressBar.setVisibility(View.VISIBLE);
//			}
//			// 加载完成
//			@Override
//			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//				// TODO Auto-generated method stub
//				progressBar.setVisibility(View.GONE);
//				mAttacher.update();
//			}
//			// 取消加载
//			@Override
//			public void onLoadingCancelled(String imageUri, View view) {
//				// TODO Auto-generated method stub
//				Logger.d( "aaa", "======清除图片缓存=====");
//				ImageLoader.getInstance().clearMemoryCache();
//			}
//		}, new ImageLoadingProgressListener() {
//
//			@Override
//			public void onProgressUpdate(String imageUri, View view, int current,
//					int total) {
//				// TODO Auto-generated method stub
//				LoadingText.setText(100.0f * current / total+"%");
//			}
//		});
		
		
	}

}
