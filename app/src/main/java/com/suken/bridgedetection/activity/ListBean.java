package com.suken.bridgedetection.activity;

import com.suken.bridgedetection.storage.CheckFormData;

public class ListBean  {
	
	public String qhzh;
	public String qhmc;
	public String qhbs;
	public String lxbm;
	public String lxmc;
	public String sdfx;
	/**
	 * 0 未检查
	 * 1 上传
	 * 2 再次检查
	 *
	 */
	public String status;
	public String id;
	public int type;

	public int mtimes;
	
	public Object realBean;
	public Object mLastFormData;
	public long lastEditLocalId = -1l;

	@Override
	public String toString() {
		return "ListBean{" +
				"qhzh='" + qhzh + '\'' +
				", qhmc='" + qhmc + '\'' +
				", qhbs='" + qhbs + '\'' +
				", lxbm='" + lxbm + '\'' +
				", lxmc='" + lxmc + '\'' +
				", sdfx='" + sdfx + '\'' +
				", status='" + status + '\'' +
				", id='" + id + '\'' +
				", type=" + type +
				", mtimes=" + mtimes +
				", realBean=" + realBean +
				", mLastFormData=" + mLastFormData +
				", lastEditLocalId=" + lastEditLocalId +
				'}';
	}
}
