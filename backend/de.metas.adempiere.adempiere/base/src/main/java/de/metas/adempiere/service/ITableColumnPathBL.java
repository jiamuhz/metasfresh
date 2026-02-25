package de.metas.adempiere.service;

/*
 * #%L
 * de.metas.swat.base
 * %%
 * Copyright (C) 2015 metas GmbH
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
 * ITableColumnPathBL
 * 解析点号分隔的字段路径表达式
 * 就像是字段路径的"导航系统"，告诉系统如何从一张表出发，通过外键关系找到另一张表的字段，并生成正确的SQL表达式。
 */

import java.util.Properties;

import de.metas.util.ISingletonService;

public interface ITableColumnPathBL extends ISingletonService
{
	Object getValueByPath(Properties ctx, String tableName, int id, String pathStr);
}
