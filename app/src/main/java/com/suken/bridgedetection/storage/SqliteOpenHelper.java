package com.suken.bridgedetection.storage;

import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.suken.bridgedetection.bean.CatalogueByUIDBean;
import com.suken.bridgedetection.bean.CatalogueByUIDDao;
import com.suken.bridgedetection.bean.CooperationBean;
import com.suken.bridgedetection.bean.CooperationDao;
import com.suken.bridgedetection.bean.GeteMaterialBean;
import com.suken.bridgedetection.bean.GeteMaterialDao;
import com.suken.bridgedetection.bean.IVDesc;
import com.suken.bridgedetection.bean.MaintenanceBean;
import com.suken.bridgedetection.bean.MaintenanceDiseaseBean;
import com.suken.bridgedetection.bean.MaintenanceDiseaseDao;
import com.suken.bridgedetection.bean.MaintenanceLogBean;
import com.suken.bridgedetection.bean.MaintenanceLogItemBean;
import com.suken.bridgedetection.bean.MaintenanceOfOrderBean;
import com.suken.bridgedetection.bean.MaintenanceOfOrderItemBean;
import com.suken.bridgedetection.bean.MaintenanceTableBean;
import com.suken.bridgedetection.bean.MaintenanceTableDao;
import com.suken.bridgedetection.bean.MaintenanceTableItemBean;
import com.suken.bridgedetection.bean.ProjacceptItemBean;
import com.suken.bridgedetection.bean.ProjectAcceptanceBean;
import com.suken.bridgedetection.bean.QualityDemandBean;
import com.suken.bridgedetection.bean.QualityDemandDao;
import com.suken.bridgedetection.bean.SDXCBean;
import com.suken.bridgedetection.bean.SynchMaintenlogBean;
import com.suken.bridgedetection.bean.UpkeepdiseaseListBean;
import com.suken.bridgedetection.bean.UploadUpkeepnoticeBean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SqliteOpenHelper extends OrmLiteSqliteOpenHelper {

	public final static int version = 13; // 数据库版本
	private static final String TABLE_NAME = "bridgedetection.db";
	private static SqliteOpenHelper instance;
	private SqliteOpenHelper(Context context) {
		super(context, TABLE_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, UserInfo.class);
			TableUtils.createTable(connectionSource, GXLuXianInfo.class);
			TableUtils.createTable(connectionSource, QLBaseData.class);
			TableUtils.createTable(connectionSource, HDBaseData.class);
			TableUtils.createTable(connectionSource, SDBaseData.class);
			TableUtils.createTable(connectionSource, YWDictionaryInfo.class);
			TableUtils.createTable(connectionSource, QHYangHuZeRenInfo.class);
			TableUtils.createTable(connectionSource, SDYangHuZeRenInfo.class);
			TableUtils.createTable(connectionSource, CheckFormData.class);
			TableUtils.createTable(connectionSource, CheckDetail.class);
			TableUtils.createTable(connectionSource, SdxcFormData.class);
			TableUtils.createTable(connectionSource, SdxcFormDetail.class);
			TableUtils.createTable(connectionSource, GpsData.class);
			TableUtils.createTable(connectionSource, GpsGjData.class);
			TableUtils.createTable(connectionSource, FileDesc.class);

			TableUtils.createTable(connectionSource, MaintenanceBean.class);
			TableUtils.createTable(connectionSource, MaintenanceTableBean.class);
			TableUtils.createTable(connectionSource, MaintenanceTableItemBean.class);
			TableUtils.createTable(connectionSource, MaintenanceDiseaseBean.class);
			TableUtils.createTable(connectionSource, MaintenanceLogBean.class);
			TableUtils.createTable(connectionSource, MaintenanceLogItemBean.class);
			TableUtils.createTable(connectionSource, MaintenanceOfOrderBean.class);
			TableUtils.createTable(connectionSource, MaintenanceOfOrderItemBean.class);
			TableUtils.createTable(connectionSource, ProjectAcceptanceBean.class);
			TableUtils.createTable(connectionSource, IVDesc.class);
			TableUtils.createTable(connectionSource, CatalogueByUIDBean.class);
			TableUtils.createTable(connectionSource, ProjacceptItemBean.class);
			TableUtils.createTable(connectionSource, GeteMaterialBean.class);
			TableUtils.createTable(connectionSource, SDXCBean.class);
			TableUtils.createTable(connectionSource, QualityDemandBean.class);
			TableUtils.createTable(connectionSource, CooperationBean.class);
			TableUtils.createTable(connectionSource, UploadUpkeepnoticeBean.class);
			TableUtils.createTable(connectionSource, UpkeepdiseaseListBean.class);


//			TableUtils.createTable(connectionSource, MaintenanceItemBean.class);


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//		try {
//			TableUtils.dropTable(connectionSource, UserInfo.class, true);
//			TableUtils.dropTable(connectionSource, GXLuXianInfo.class, true);
//			TableUtils.dropTable(connectionSource, QLBaseData.class, true);
//			TableUtils.dropTable(connectionSource, HDBaseData.class, true);
//			TableUtils.dropTable(connectionSource, SDBaseData.class, true);
//			TableUtils.dropTable(connectionSource, YWDictionaryInfo.class, true);
//			TableUtils.dropTable(connectionSource, QHYangHuZeRenInfo.class, true);
//			TableUtils.dropTable(connectionSource, SDYangHuZeRenInfo.class, true);
//			TableUtils.dropTable(connectionSource, CheckFormData.class, true);
//			TableUtils.dropTable(connectionSource, CheckDetail.class, true);
//			TableUtils.dropTable(connectionSource, SdxcFormData.class, true);
//			TableUtils.dropTable(connectionSource, SdxcFormDetail.class, true);
//			TableUtils.dropTable(connectionSource, GpsData.class, true);
//			TableUtils.dropTable(connectionSource, GpsGjData.class, true);
//			TableUtils.dropTable(connectionSource, FileDesc.class, true);
//
//			TableUtils.dropTable(connectionSource, MaintenanceBean.class, true);
//			TableUtils.dropTable(connectionSource, MaintenanceTableBean.class, true);
//			TableUtils.dropTable(connectionSource, MaintenanceTableItemBean.class, true);
//			TableUtils.dropTable(connectionSource, MaintenanceDiseaseBean.class, true);
//			TableUtils.dropTable(connectionSource, MaintenanceLogBean.class, true);
//			TableUtils.dropTable(connectionSource, MaintenanceLogItemBean.class, true);
//			TableUtils.dropTable(connectionSource, MaintenanceOfOrderBean.class, true);
//			TableUtils.dropTable(connectionSource, MaintenanceOfOrderItemBean.class, true);
//			TableUtils.dropTable(connectionSource, ProjectAcceptanceBean.class, true);
//			TableUtils.dropTable(connectionSource, IVDesc.class, true);
//			TableUtils.dropTable(connectionSource, CatalogueByUIDBean.class, true);
//			TableUtils.dropTable(connectionSource, ProjacceptItemBean.class, true);
//			TableUtils.dropTable(connectionSource, GeteMaterialBean.class, true);
////			TableUtils.dropTable(connectionSource, MaintenanceItemBean.class, true);
//
//
//			onCreate(database, connectionSource);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		if (oldVersion <10) {
			try {

				new MaintenanceTableDao().getMaintenanceTableItemBeanDao().executeRaw("ALTER TABLE `tb_maintenancetableitem` ADD COLUMN isxd TEXT DEFAULT '1';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(oldVersion <11){//添加新的数据库

			try {
				new SDBaseDataDao().getSDBaseDataDao().executeRaw("ALTER TABLE `tb_sdbasedata` ADD COLUMN sdfx TEXT;");
				new SDBaseDataDao().getSDBaseDataDao().executeRaw("ALTER TABLE `tb_sdbasedata` ADD COLUMN inspecttimes INT;");
				new SdxcFormAndDetailDao().getSdxcFormDataDao().executeRaw("ALTER TABLE `tb_sdcheckform` ADD COLUMN sdlx TEXT;");
				TableUtils.createTable(connectionSource, QualityDemandBean.class);
				TableUtils.createTable(connectionSource, CooperationBean.class);
				TableUtils.createTable(connectionSource, SDXCBean.class);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(oldVersion<12){
			try {
				new QualityDemandDao().getQualityDemandDao().executeRaw("alter table `tb_qualitydemand` drop column clno;");
				new QualityDemandDao().getQualityDemandDao().executeRaw("alter table `tb_qualitydemand` drop column gg;");
				new QualityDemandDao().getQualityDemandDao().executeRaw("alter table `tb_qualitydemand` drop column xh;");
				new QualityDemandDao().getQualityDemandDao().executeRaw("alter table `tb_qualitydemand` drop column dw;");
				new QualityDemandDao().getQualityDemandDao().executeRaw("ALTER TABLE `tb_qualitydemand` ADD COLUMN bhzl TEXT;");
				new QualityDemandDao().getQualityDemandDao().executeRaw("ALTER TABLE `tb_qualitydemand` ADD COLUMN yqnr TEXT;");
				new CheckFormAndDetailDao().getFormDao().executeRaw("ALTER TABLE `tb_checkform` ADD COLUMN qhbh TEXT;");
				new CheckFormAndDetailDao().getDetailDao().executeRaw("ALTER TABLE `tb_checkform` ADD COLUMN sdbh TEXT;");

//				new GeteMaterialDao().getGeteMaterialDao().executeRaw("ALTER TABLE `tb_getematerial` ADD COLUMN commitNum INT;");
//				new MaintenanceDiseaseDao().getMaintenanceDiseaseBeen().executeRaw("ALTER TABLE `tb_maintenancedisease` ADD COLUMN commitNum INT;");
//				new CatalogueByUIDDao().getCatalogueByUIDBeen().executeRaw("ALTER TABLE `tb_cataloguebyuid` ADD COLUMN commitNum INT;");


				TableUtils.createTable(connectionSource, UploadUpkeepnoticeBean.class);
				TableUtils.createTable(connectionSource, UpkeepdiseaseListBean.class);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}



	}

	/**
	 * 单例获取该Helper
	 *
	 * @param context
	 * @return
	 */
	public static synchronized SqliteOpenHelper getHelper(Context context) {
		if (instance == null) {
			synchronized (SqliteOpenHelper.class) {
				if (instance == null)
					instance = new SqliteOpenHelper(context);
			}
		}

		return instance;
	}

	/**
	 * 释放资源
	 */
	@Override
	public void close() {
		super.close();
	}

}
