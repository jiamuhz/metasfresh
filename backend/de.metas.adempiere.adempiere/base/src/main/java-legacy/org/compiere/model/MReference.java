/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2009 www.metas.de                                            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.compiere.model;

/** */


import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.service.ILookupDAO;
import org.compiere.util.DisplayType;

import de.metas.util.Services;

/**
 * validationtype   L (list) T (table)   D (data)
 *   为 D 时：  ad_reference_id  <--->  org.compiere.util.DisplayType  确定具体类型
 *   为 T 时：  ad_ref_table + ad_reference_id   确定 ad_table  ;  ad_val_rule_id (from ad_column 表) 确定 where 条件
 *   为 L 时：  ad_ref_list + ad_reference_id   确定 List 值的集合
 *   为 S 时：  (SQL): 使用SQL查询获取数据
 *
 * Note: maybe what you are looking for is here: {@link DisplayType}.
 *
 * @author Tobias Schoeneberg, www.metas.de - FR [ 2897194 ] Advanced Zoom and RelationTypes
 *
 */
public class MReference extends X_AD_Reference {

	/**
	 *
	 */
	private static final long serialVersionUID = 3298182955450711914L;

	public MReference(Properties ctx, int AD_Reference_ID, String trxName) {
		super(ctx, AD_Reference_ID, trxName);
	}

	public MReference(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * @param ctx
	 * @param referenceId
	 * @param trxName
	 * @return MRefTable
	 * @deprecated Please consider using {@link ILookupDAO#retrieveTableRefInfo(int)}
	 */
	@Deprecated
	public static I_AD_Ref_Table retrieveRefTable(final Properties ctx, final int referenceId, final String trxName)
	{
		return Services.get(IQueryBL.class)
				.createQueryBuilder(I_AD_Ref_Table.class, ctx, trxName)
				.addEqualsFilter(I_AD_Ref_Table.COLUMNNAME_AD_Reference_ID, referenceId)
				.create()
				.firstOnly(I_AD_Ref_Table.class);
	}

}
