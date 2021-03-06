package com.suken.bridgedetection.activity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.suken.bridgedetection.Constants;
import com.suken.bridgedetection.R;
import com.suken.bridgedetection.bean.SDXCBean;
import com.suken.bridgedetection.storage.CheckFormAndDetailDao;
import com.suken.bridgedetection.storage.CheckFormData;
import com.suken.bridgedetection.storage.HDBaseData;
import com.suken.bridgedetection.storage.QLBaseData;
import com.suken.bridgedetection.storage.SDBaseData;
import com.suken.bridgedetection.storage.SdxcFormAndDetailDao;
import com.suken.bridgedetection.storage.SdxcFormData;
import com.suken.bridgedetection.util.NetWorkUtil;
import com.suken.bridgedetection.util.NetWorkUtil.ConnectType;
import com.suken.bridgedetection.util.TextUtil;
import com.suken.bridgedetection.util.UiUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class UpdateAllActivity extends BaseActivity {

	private int mType;
	private List<UpdateBean> mSourceData = new ArrayList<UpdateBean>();
	private ListView mListView = null;
	private String mTypeStr = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().getAttributes().height = -1;
		getWindow().getAttributes().width = -1;
		setContentView(R.layout.activity_page_updateall);
		mListView = (ListView) findViewById(R.id.update_all_list);
		Intent intent = getIntent();
		mType = intent.getIntExtra("type", R.drawable.qiaoliangjiancha);
		switch (mType) {
		case R.drawable.qiaoliangjiancha:
			mTypeStr = "桥涵经常检查记录表";
			break;
		case R.drawable.qiaoliangxuncha:
			mTypeStr = "桥涵巡查记录表";
			break;
		case R.drawable.suidaojiancha:
			mTypeStr = "隧道经常检查记录表";
			break;
		case R.drawable.suidaoxuncha:
			mTypeStr = "隧道巡查记录表";
			break;

		default:
			break;
		}
		ImageView img = (ImageView) findViewById(R.id.image);
		if (NetWorkUtil.getConnectType(this) == ConnectType.CONNECT_TYPE_WIFI) {
			img.setImageResource(R.drawable.wifi_red);
		} else {
			img.setImageResource(R.drawable.wifi_gray);
		}
		updateInit(false);
	}

	private class UpdateBean {
		String mc;
		String jlr;
		String sj;
		String id;
		int mType;
		ListBean listBean;
		boolean isChecked = true;

		private UpdateAllActivity getOuterType() {
			return UpdateAllActivity.this;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + (isChecked ? 1231 : 1237);
			result = prime * result + ((jlr == null) ? 0 : jlr.hashCode());
			result = prime * result + ((listBean == null) ? 0 : listBean.hashCode());
			result = prime * result + mType;
			result = prime * result + ((mc == null) ? 0 : mc.hashCode());
			result = prime * result + ((sj == null) ? 0 : sj.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UpdateBean other = (UpdateBean) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (isChecked != other.isChecked)
				return false;
			if (jlr == null) {
				if (other.jlr != null)
					return false;
			} else if (!jlr.equals(other.jlr))
				return false;
			if (listBean == null) {
				if (other.listBean != null)
					return false;
			} else if (!listBean.equals(other.listBean))
				return false;
			if (mType != other.mType)
				return false;
			if (mc == null) {
				if (other.mc != null)
					return false;
			} else if (!mc.equals(other.mc))
				return false;
			if (sj == null) {
				if (other.sj != null)
					return false;
			} else if (!sj.equals(other.sj))
				return false;
			return true;
		}


	}

	private void updateInit(boolean isReset) {

		List<UpdateBean> list = new ArrayList<UpdateBean>();
		if (mType == R.drawable.qiaoliangjiancha || mType == R.drawable.suidaojiancha) {
			CheckFormAndDetailDao formDao = new CheckFormAndDetailDao();
			for (ListBean listBean : BridgeDetectionListActivity.updateAllList) {
				CheckFormData data = formDao.queryByQHIdAndStatus(listBean.id, Constants.STATUS_UPDATE, mType);
				if (data != null) {
					UpdateBean bean = new UpdateBean();
					bean.listBean = listBean;
					bean.sj = data.getJcsj();
					if (mType == R.drawable.qiaoliangjiancha) {
						bean.id = data.getQhid();
						bean.mc = data.getQhmc();
					} else {
						bean.id = data.getSdid();
						bean.mc = data.getSdmc();
					}
					bean.jlr = data.getJlry();
					bean.mType = mType;
					list.add(bean);
				}
			}
		} else {
			SdxcFormAndDetailDao dao = new SdxcFormAndDetailDao();
			for (ListBean listBean : BridgeDetectionListActivity.updateAllList) {
				SdxcFormData data = dao.queryByQHIdAndStatus(listBean.id, Constants.STATUS_UPDATE, mType);
				if (data != null) {
					UpdateBean bean = new UpdateBean();
					bean.listBean = listBean;
					if (mType == R.drawable.suidaoxuncha) {
						bean.id = data.getSdid();
						bean.mc = data.getSdmc();
					} else {
						bean.id = data.getLocalId() + "";
						bean.mc = "";
					}
					bean.sj = data.getJcsj();
					bean.jlr = data.getJlry();
					bean.mType = mType;
					list.add(bean);
				}
			}
		}
		mSourceData = list;
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(mTypeStr + mSourceData.size() + "份未上传");
		if (isReset) {
			UpdateAdapter adapter = (UpdateAdapter) mListView.getAdapter();
			adapter.notifyDataSetChanged();
		} else {
			mListView.setAdapter(new UpdateAdapter());
		}

	}

	private class UpdateAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mSourceData.size();
		}

		@Override
		public UpdateBean getItem(int position) {
			return mSourceData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			if (convertView == null) {
				view = LayoutInflater.from(UpdateAllActivity.this).inflate(R.layout.updateall_item, null);
			} else {
				view = convertView;
			}
			CheckBox box = (CheckBox) view.findViewById(R.id.checkbox);
			UpdateBean bean = getItem(position);
			TextView tv = (TextView) view.findViewById(R.id.textview);
			String text = "记录人：" + bean.jlr + "," + "时间：" + bean.sj;
			if(bean.mType == R.drawable.qiaoliangxuncha){
				tv.setText(text);
			} else {
				tv.setText("名称：" + bean.mc + "," + text);
			}
			box.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
			box.setTag(bean);
			box.setChecked(bean.isChecked);
			view.setTag(bean);
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {

					UpdateBean bean = (UpdateBean) v.getTag();
					ListBean listBean = bean.listBean;
					Intent intent = new Intent(UpdateAllActivity.this, BridgeFormActivity.class);
					intent.putExtra("localId", listBean.lastEditLocalId);
					intent.putExtra("isEdit", true);
					intent.putExtra("type", mType);
					if (mType == R.drawable.qiaoliangjiancha || mType == R.drawable.qiaoliangxuncha) {
						if (listBean.realBean instanceof QLBaseData) {
							intent.putExtra("qhInfo", (QLBaseData) listBean.realBean);
						} else if (listBean.realBean instanceof HDBaseData) {
							intent.putExtra("qhInfo", (HDBaseData) listBean.realBean);
						}
					} else {
						if (listBean.realBean instanceof SDBaseData) {
							intent.putExtra("qhInfo", (SDBaseData) listBean.realBean);
						} else if (listBean.realBean instanceof SDXCBean) {
							intent.putExtra("qhInfo", (SDXCBean) listBean.realBean);
						}
//						intent.putExtra("qhInfo", (SDBaseData) listBean.realBean);
					}
					startActivityForResult(intent, mType == R.drawable.qiaoliangxuncha ? 2 : 1);
				
				}
			});
			box.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					UpdateBean tag = (UpdateBean) buttonView.getTag();
					tag.isChecked = isChecked;
				}
			});
			return view;
		}

	}


	/**
	 *	2017.07.06 添加去重复的逻辑（数据重复上传的问题）
	 */
	public List<UpdateBean> deleteRepetition(List<UpdateBean> list){
		Set<UpdateBean> set = new HashSet<UpdateBean>();
		List<UpdateBean> listTemp = new ArrayList<UpdateBean>();
		for (UpdateBean cd:list) {
			if(set.add(cd)){
				listTemp.add(cd);
			}
		}
//		for (UpdateBean i : listTemp) {
//			System.out.println(i);
//		}
//		System.out.println("111");
		return listTemp;
	}

	public void toUpdate(View view) {
		if (mSourceData.size() > 0) {
			BackgroundExecutor.execute(new Runnable() {
				
				@Override
				public void run() {

					List<UpdateBean> listBeans = new ArrayList<UpdateBean>();
					for (UpdateBean bean : mSourceData) {
						if (bean.isChecked) {
							listBeans.add(bean);
						}
					}

					if(TextUtil.isListEmpty(listBeans)){
						toast("您没有选中上传的数据！");
						return;
					}else{
						listBeans = deleteRepetition(listBeans);
//						showLoading("上传中...");
						for (int i = 0; i < listBeans.size(); i++) {
							UpdateBean bean = listBeans.get(i);
							boolean isEnd = false;
							if (i == listBeans.size() - 1) {
								isEnd = true;
							}
							UiUtil.updateSingleNotPost(bean.id + "", bean.mType, true, UpdateAllActivity.this,isEnd);
						}
//						toast("可以上传，上传数量"+listBeans.size());
					}

					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							updateInit(true);
//							if(mSourceData == null || mSourceData.size() == 0){
//								finish();
//							}
						}
					});
//					dismissLoading();
				}
			});
		}else{
			toast("您没有可以上传的数据！");
		}
	}
	
	public void close(View view){
		finish();
	}

}
