package de.metas.ui.web.view;

import lombok.NonNull;
import lombok.Value;

import de.metas.i18n.ITranslatableString;

/*
 * #%L
 * metasfresh-webui-api
 * %%
 * Copyright (C) 2017 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

/**
 * ViewProfile 封装了影响视图外观和行为的各种配置项：
 *
 * 列定义：哪些列显示，列的宽度、顺序、对齐方式
 * 排序规则：默认的排序字段和排序方向（升序/降序）
 * 过滤条件：预定义的过滤器组合（如"只显示未完成的订单"）
 * 分组设置：数据如何分组显示（如按客户分组、按日期分组）
 * 聚合方式：是否需要显示汇总行、小计等
 *
 * 多Profile管理
 * 一个视图可以关联多个 ViewProfile，例如：
 * "标准Profile"：显示所有列，按订单日期倒序
 * "简洁Profile"：只显示关键字段，按优先级排序
 * "财务Profile"：突出显示金额、支付状态相关字段
 * "客服Profile"：突出显示客户信息、问题标记字段
 */
@Value(staticConstructor = "of")
public class ViewProfile
{
	@NonNull
	private final ViewProfileId profileId;
	@NonNull
	private final ITranslatableString caption;
}
