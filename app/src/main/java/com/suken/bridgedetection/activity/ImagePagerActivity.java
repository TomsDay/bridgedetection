package com.suken.bridgedetection.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.IVDescDao;
import com.suken.bridgedetection.fragment.ImageDetailFragment;
import com.yuntongxun.ecdemo.ui.chatting.HackyViewPager;

/**
 * 看大图1
 */
public class ImagePagerActivity extends FragmentActivity {
	private static final String STATE_POSITION = "STATE_POSITION";
	public static final String EXTRA_IMAGE_INDEX = "image_index";
	public static final String EXTRA_IMAGE_URLS = "image_urls";
	public static final String EXTRA_IMAGE_ID = "image_id";
	public static final String EXTRA_IMAGE_SOURCE = "getAllSource";
	
	private HackyViewPager mPager;
	private int pagerPosition;
	private TextView indicator;
//	private TextView  contentTitle;
	private TextView tv_ImageDetail_back, tv_ImageDetail_delete;
	int nowPosition;
	private IVDescDao ivDescDao;
	private int ImageIDArray[];
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_detail_pager);
//		TextView title_item_content = (TextView) findViewById(R.id.title_item_content);
//		title_item_content.setText("查看图片");
		ivDescDao = new IVDescDao();
		pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
		String[] urls = getIntent().getStringArrayExtra(EXTRA_IMAGE_URLS);
		ImageIDArray = getIntent().getIntArrayExtra(EXTRA_IMAGE_ID);
		String[] sourch = getIntent().getStringArrayExtra(EXTRA_IMAGE_SOURCE);

		tv_ImageDetail_back = (TextView) findViewById(R.id.tv_ImageDetail_back);
		tv_ImageDetail_delete = (TextView) findViewById(R.id.tv_ImageDetail_delete);
//		contentTitle = (TextView) findViewById(R.id.title_item_content);
		
		mPager = (HackyViewPager) findViewById(R.id.pager);
		ImagePagerAdapter mAdapter = new ImagePagerAdapter(
				getSupportFragmentManager(), urls,sourch);
		mPager.setAdapter(mAdapter);
		indicator = (TextView) findViewById(R.id.indicator);

		tv_ImageDetail_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		tv_ImageDetail_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDeleteDialog();
			}
		});
		
		
		
		CharSequence text = getString(R.string.viewpager_indicator, 1, mPager
				.getAdapter().getCount());
		indicator.setText(text);
		// 更新下标
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int arg0) {
				nowPosition = arg0;
				CharSequence text = getString(R.string.viewpager_indicator,
						arg0 + 1, mPager.getAdapter().getCount());
				indicator.setText(text);
			}

		});
		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		mPager.setCurrentItem(pagerPosition);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, mPager.getCurrentItem());
	}

	private class ImagePagerAdapter extends FragmentStatePagerAdapter {

		public String[] fileList;
		public String[] sourch;

		public ImagePagerAdapter(FragmentManager fm, String[] fileList, String[] sourch) {
			super(fm);
			this.fileList = fileList;
			this.sourch = sourch;
		}

		@Override
		public int getCount() {
			return fileList == null ? 0 : fileList.length;
		}

		@Override
		public Fragment getItem(int position) {
			String url = fileList[position];
//			int sourchArr = Integer.parseInt(sourch[position]);
			return ImageDetailFragment.newInstance(url);
		}

	}
	public void showDeleteDialog(){
		new AlertDialog.Builder(ImagePagerActivity.this)
		.setTitle("删除")
		.setMessage("是否删除当前这张图片？")
		.setPositiveButton("删除", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				int thisImageID = ImageIDArray[nowPosition];
				if(thisImageID != 0){
					ivDescDao.delete(thisImageID);
				}
				ProjectAcceptanceActivity.ivDescs.remove(nowPosition);
//				PrescriptionActivity.imageList.remove(nowPosition);
				finish();
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub

			}
		}).show();
	}
}