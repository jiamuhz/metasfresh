package de.metas.dataentry;

/**
 * AD_TAB 表自身没有父子连接字段。父子关系的建立是通过以下两个层面的配置实现的：
 * 数据模型层：ad_tab 对应的数据库表 通过外键字段指向主表。
 * 应用字典层：在 AD_Column 表中，将该外键字段的 IsParent 属性设置为 Y。
 * 系统在运行时，会结合 Level 字段和 IsParent 标记，共同推导出 Level=1 和 Level=2 的 Tab 之间正确的父子连接关系。
 */
