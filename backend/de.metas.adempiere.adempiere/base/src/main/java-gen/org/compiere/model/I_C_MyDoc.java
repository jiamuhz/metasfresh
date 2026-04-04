package org.compiere.model;

import java.math.BigDecimal;
import javax.annotation.Nullable;
import org.adempiere.model.ModelColumn;

/** Generated Interface for C_MyDoc
 *  @author metasfresh (generated) 
 */
@SuppressWarnings("unused")
public interface I_C_MyDoc 
{

	String Table_Name = "C_MyDoc";

//	/** AD_Table_ID=1000000 */
//	int Table_ID = org.compiere.model.MTable.getTable_ID(Table_Name);


	/**
	 * Get 客户.
	 * 客户/租户此安装。
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	int getAD_Client_ID();

	String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/**
	 * Set 组织.
	 * 客户内的组织实体
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setAD_Org_ID (int AD_Org_ID);

	/**
	 * Get 组织.
	 * 客户内的组织实体
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	int getAD_Org_ID();

	String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/**
	 * Set 文档类型.
	 * 文档类型或规则
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setC_DocType_ID (int C_DocType_ID);

	/**
	 * Get 文档类型.
	 * 文档类型或规则
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	int getC_DocType_ID();

	String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/**
	 * Set 文档类型.
	 * 对话文档的目标文档类型
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID);

	/**
	 * Get 文档类型.
	 * 对话文档的目标文档类型
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	int getC_DocTypeTarget_ID();

	String COLUMNNAME_C_DocTypeTarget_ID = "C_DocTypeTarget_ID";

	/**
	 * Set 文档表测试.
	 *
	 * <br>Type: ID
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setc_mydoc_ID (int c_mydoc_ID);

	/**
	 * Get 文档表测试.
	 *
	 * <br>Type: ID
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	int getc_mydoc_ID();

	ModelColumn<I_C_MyDoc, Object> COLUMN_c_mydoc_ID = new ModelColumn<>(I_C_MyDoc.class, "c_mydoc_ID", null);
	String COLUMNNAME_c_mydoc_ID = "c_mydoc_ID";

	/**
	 * Get 创建.
	 * 日期创建了此记录
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	java.sql.Timestamp getCreated();

	ModelColumn<I_C_MyDoc, Object> COLUMN_Created = new ModelColumn<>(I_C_MyDoc.class, "Created", null);
	String COLUMNNAME_Created = "Created";

	/**
	 * Get 由.
	 * 创建此记录的用户
	 *
	 * <br>Type: Table
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	int getCreatedBy();

	String COLUMNNAME_CreatedBy = "CreatedBy";

	/**
	 * Set 描述.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setDescription (@Nullable String Description);

	/**
	 * Get 描述.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	@Nullable String getDescription();

	ModelColumn<I_C_MyDoc, Object> COLUMN_Description = new ModelColumn<>(I_C_MyDoc.class, "Description", null);
	String COLUMNNAME_Description = "Description";

	/**
	 * Set 尾注.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setDescriptionBottom (@Nullable String DescriptionBottom);

	/**
	 * Get 尾注.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	@Nullable String getDescriptionBottom();

	ModelColumn<I_C_MyDoc, Object> COLUMN_DescriptionBottom = new ModelColumn<>(I_C_MyDoc.class, "DescriptionBottom", null);
	String COLUMNNAME_DescriptionBottom = "DescriptionBottom";

	/**
	 * Set 过程批次.
	 *
	 * <br>Type: List
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setDocAction (String DocAction);

	/**
	 * Get 文档操作.
	 *
	 * <br>Type: List
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	String getDocAction();

	ModelColumn<I_C_MyDoc, Object> COLUMN_DocAction = new ModelColumn<>(I_C_MyDoc.class, "DocAction", null);
	String COLUMNNAME_DocAction = "DocAction";

	/**
	 * Set 地位.
	 *
	 * <br>Type: List
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setDocStatus (String DocStatus);

	/**
	 * Get 地位.
	 *
	 * <br>Type: List
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	String getDocStatus();

	ModelColumn<I_C_MyDoc, Object> COLUMN_DocStatus = new ModelColumn<>(I_C_MyDoc.class, "DocStatus", null);
	String COLUMNNAME_DocStatus = "DocStatus";

	/**
	 * Set 文件号.
	 * 文档序列编号
	 *
	 * <br>Type: String
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setDocumentNo (String DocumentNo);

	/**
	 * Get 文件号.
	 * 文档序列编号
	 *
	 * <br>Type: String
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	String getDocumentNo();

	ModelColumn<I_C_MyDoc, Object> COLUMN_DocumentNo = new ModelColumn<>(I_C_MyDoc.class, "DocumentNo", null);
	String COLUMNNAME_DocumentNo = "DocumentNo";

	/**
	 * Set 电子邮件.
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setEMail (@Nullable String EMail);

	/**
	 * Get 电子邮件.
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	@Nullable String getEMail();

	ModelColumn<I_C_MyDoc, Object> COLUMN_EMail = new ModelColumn<>(I_C_MyDoc.class, "EMail", null);
	String COLUMNNAME_EMail = "EMail";

	/**
	 * Set 积极的.
	 * 记录在系统中活跃
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setIsActive (boolean IsActive);

	/**
	 * Get 积极的.
	 * 记录在系统中活跃
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	boolean isActive();

	ModelColumn<I_C_MyDoc, Object> COLUMN_IsActive = new ModelColumn<>(I_C_MyDoc.class, "IsActive", null);
	String COLUMNNAME_IsActive = "IsActive";

	/**
	 * Set 得到正式认可的.
	 * 指示该文档是否需要批准
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setIsApproved (boolean IsApproved);

	/**
	 * Get 得到正式认可的.
	 * 指示该文档是否需要批准
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	boolean isApproved();

	ModelColumn<I_C_MyDoc, Object> COLUMN_IsApproved = new ModelColumn<>(I_C_MyDoc.class, "IsApproved", null);
	String COLUMNNAME_IsApproved = "IsApproved";

	/**
	 * Set 选定.
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setIsSelected (boolean IsSelected);

	/**
	 * Get 选定.
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	boolean isSelected();

	ModelColumn<I_C_MyDoc, Object> COLUMN_IsSelected = new ModelColumn<>(I_C_MyDoc.class, "IsSelected", null);
	String COLUMNNAME_IsSelected = "IsSelected";

	/**
	 * Set 销售交易.
	 * 这是一项销售交易
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setIsSOTrx (boolean IsSOTrx);

	/**
	 * Get 销售交易.
	 * 这是一项销售交易
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	boolean isSOTrx();

	ModelColumn<I_C_MyDoc, Object> COLUMN_IsSOTrx = new ModelColumn<>(I_C_MyDoc.class, "IsSOTrx", null);
	String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/**
	 * Set 产品.
	 * 产品，服务，项目
	 *
	 * <br>Type: Search
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setM_Product_ID (int M_Product_ID);

	/**
	 * Get 产品.
	 * 产品，服务，项目
	 *
	 * <br>Type: Search
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	int getM_Product_ID();

	String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/**
	 * Set 仓库.
	 * 存储仓库和服务点
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setM_Warehouse_ID (int M_Warehouse_ID);

	/**
	 * Get 仓库.
	 * 存储仓库和服务点
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	int getM_Warehouse_ID();

	String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/**
	 * Set 电话.
	 * 标识电话号码
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setPhone (@Nullable String Phone);

	/**
	 * Get 电话.
	 * 标识电话号码
	 *
	 * <br>Type: String
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	@Nullable String getPhone();

	ModelColumn<I_C_MyDoc, Object> COLUMN_Phone = new ModelColumn<>(I_C_MyDoc.class, "Phone", null);
	String COLUMNNAME_Phone = "Phone";

	/**
	 * Set 处理.
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	void setProcessed (boolean Processed);

	/**
	 * Get 处理.
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	boolean isProcessed();

	ModelColumn<I_C_MyDoc, Object> COLUMN_Processed = new ModelColumn<>(I_C_MyDoc.class, "Processed", null);
	String COLUMNNAME_Processed = "Processed";

	/**
	 * Set 现在处理.
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setProcessing (boolean Processing);

	/**
	 * Get 现在处理.
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	boolean isProcessing();

	ModelColumn<I_C_MyDoc, Object> COLUMN_Processing = new ModelColumn<>(I_C_MyDoc.class, "Processing", null);
	String COLUMNNAME_Processing = "Processing";

	/**
	 * Get 更新.
	 * 约会此记录已更新
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	java.sql.Timestamp getUpdated();

	ModelColumn<I_C_MyDoc, Object> COLUMN_Updated = new ModelColumn<>(I_C_MyDoc.class, "Updated", null);
	String COLUMNNAME_Updated = "Updated";

	/**
	 * Get 更新.
	 * 更新此记录的用户
	 *
	 * <br>Type: Table
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	int getUpdatedBy();

	String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/**
	 * Set 用户元素字符串1.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setUserElementString1 (@Nullable String UserElementString1);

	/**
	 * Get 用户元素字符串1.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	@Nullable String getUserElementString1();

	ModelColumn<I_C_MyDoc, Object> COLUMN_UserElementString1 = new ModelColumn<>(I_C_MyDoc.class, "UserElementString1", null);
	String COLUMNNAME_UserElementString1 = "UserElementString1";

	/**
	 * Set 用户元素字符串2.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setUserElementString2 (@Nullable String UserElementString2);

	/**
	 * Get 用户元素字符串2.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	@Nullable String getUserElementString2();

	ModelColumn<I_C_MyDoc, Object> COLUMN_UserElementString2 = new ModelColumn<>(I_C_MyDoc.class, "UserElementString2", null);
	String COLUMNNAME_UserElementString2 = "UserElementString2";

	/**
	 * Set 用户元素字符串3.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setUserElementString3 (@Nullable String UserElementString3);

	/**
	 * Get 用户元素字符串3.
	 *
	 * <br>Type: Text
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	@Nullable String getUserElementString3();

	ModelColumn<I_C_MyDoc, Object> COLUMN_UserElementString3 = new ModelColumn<>(I_C_MyDoc.class, "UserElementString3", null);
	String COLUMNNAME_UserElementString3 = "UserElementString3";

	/**
	 * Set 体积.
	 * 产品量
	 *
	 * <br>Type: Number
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setVolume (@Nullable BigDecimal Volume);

	/**
	 * Get 体积.
	 * 产品量
	 *
	 * <br>Type: Number
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	BigDecimal getVolume();

	ModelColumn<I_C_MyDoc, Object> COLUMN_Volume = new ModelColumn<>(I_C_MyDoc.class, "Volume", null);
	String COLUMNNAME_Volume = "Volume";

	/**
	 * Set 重量.
	 * 产品的重量
	 *
	 * <br>Type: Number
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	void setWeight (@Nullable BigDecimal Weight);

	/**
	 * Get 重量.
	 * 产品的重量
	 *
	 * <br>Type: Number
	 * <br>Mandatory: false
	 * <br>Virtual Column: false
	 */
	BigDecimal getWeight();

	ModelColumn<I_C_MyDoc, Object> COLUMN_Weight = new ModelColumn<>(I_C_MyDoc.class, "Weight", null);
	String COLUMNNAME_Weight = "Weight";
}
