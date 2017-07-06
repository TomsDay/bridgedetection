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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (lastEditLocalId ^ (lastEditLocalId >>> 32));
		result = prime * result + ((lxbm == null) ? 0 : lxbm.hashCode());
		result = prime * result + ((lxmc == null) ? 0 : lxmc.hashCode());
		result = prime * result + ((mLastFormData == null) ? 0 : mLastFormData.hashCode());
		result = prime * result + mtimes;
		result = prime * result + ((qhbs == null) ? 0 : qhbs.hashCode());
		result = prime * result + ((qhmc == null) ? 0 : qhmc.hashCode());
		result = prime * result + ((qhzh == null) ? 0 : qhzh.hashCode());
		result = prime * result + ((realBean == null) ? 0 : realBean.hashCode());
		result = prime * result + ((sdfx == null) ? 0 : sdfx.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + type;
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
		ListBean other = (ListBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastEditLocalId != other.lastEditLocalId)
			return false;
		if (lxbm == null) {
			if (other.lxbm != null)
				return false;
		} else if (!lxbm.equals(other.lxbm))
			return false;
		if (lxmc == null) {
			if (other.lxmc != null)
				return false;
		} else if (!lxmc.equals(other.lxmc))
			return false;
		if (mLastFormData == null) {
			if (other.mLastFormData != null)
				return false;
		} else if (!mLastFormData.equals(other.mLastFormData))
			return false;
		if (mtimes != other.mtimes)
			return false;
		if (qhbs == null) {
			if (other.qhbs != null)
				return false;
		} else if (!qhbs.equals(other.qhbs))
			return false;
		if (qhmc == null) {
			if (other.qhmc != null)
				return false;
		} else if (!qhmc.equals(other.qhmc))
			return false;
		if (qhzh == null) {
			if (other.qhzh != null)
				return false;
		} else if (!qhzh.equals(other.qhzh))
			return false;
		if (realBean == null) {
			if (other.realBean != null)
				return false;
		} else if (!realBean.equals(other.realBean))
			return false;
		if (sdfx == null) {
			if (other.sdfx != null)
				return false;
		} else if (!sdfx.equals(other.sdfx))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
