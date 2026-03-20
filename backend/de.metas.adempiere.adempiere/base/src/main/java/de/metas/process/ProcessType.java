package de.metas.process;

import com.google.common.collect.ImmutableSet;
import de.metas.util.lang.ReferenceListAwareEnum;
import de.metas.util.lang.ReferenceListAwareEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.compiere.model.X_AD_Process;

/*
 * #%L
 * de.metas.adempiere.adempiere.base
 * %%
 * Copyright (C) 2019 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

/**
 *
 */
@AllArgsConstructor
public enum ProcessType implements ReferenceListAwareEnum
{
	/**
	 * 执行一个 Java 类。你需要在进程定义中指定一个实现了特定接口（如 ProcessCall）的 Java 类的全名。系统会调用这个类的 doIt() 方法来执行业务逻辑。
	 */
	Java(X_AD_Process.TYPE_Java),

	/**
	 * 执行一条 SQL 语句。你需要在进程定义中提供一个 SELECT、UPDATE 或 DELETE 语句。系统会直接使用数据库连接来运行这段 SQL。
	 */
	SQL(X_AD_Process.TYPE_SQL),

	/**
	 * 调用一个 PostgREST 端点。是一个会触发对 PostgREST API 调用的操作。
	 */
	POSTGREST(X_AD_Process.TYPE_PostgREST),

	/**
	 * 使用 JSON 数据源生成 Jasper 报告。进程将运行一个 Jasper 报告，但报告所需的数据不是通过 SQL 查询，而是由一个 JSON 字符串（通常由后端准备）提供的。
	 */
	JasperReportsJSON(X_AD_Process.TYPE_JasperReportsJSON),

	/**
	 * 使用 SQL 数据源生成 Jasper 报告。这是最经典的方式。进程将运行一个 Jasper 报告，报告将使用进程定义中关联的 SQL 语句作为其数据源。
	 */
	JasperReportsSQL(X_AD_Process.TYPE_JasperReportsSQL),

	/**
	 * 生成一个 Excel 文件。这个进程将基于定义好的格式和查询，动态生成一个 Excel 报表并供用户下载。
	 */
	Excel(X_AD_Process.TYPE_Excel),
	;

	private static final ReferenceListAwareEnums.ValuesIndex<ProcessType> index = ReferenceListAwareEnums.index(values());

	@Getter
	private final String code;

	public static ProcessType ofCode(@NonNull final String code)
	{
		return index.ofCode(code);
	}

	public boolean isJasper()
	{
		return this == JasperReportsJSON || this == JasperReportsSQL;
	}

	public boolean isJasperJSON()
	{
		return this == JasperReportsJSON;
	}

	public static ImmutableSet<ProcessType> getTypesRunnableFromAppRestController()
	{
		return ImmutableSet.of(SQL, Excel, POSTGREST);
	}

}
