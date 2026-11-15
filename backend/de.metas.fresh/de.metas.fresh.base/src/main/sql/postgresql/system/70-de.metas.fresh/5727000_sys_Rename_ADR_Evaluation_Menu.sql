-- 「ADR评估」菜单名与实际功能不符：
-- 报表 AD_Process_ID=540655 调用 report.fresh_ADR_umsatzliste_bpartner_report，
-- 实际逻辑是按业务伙伴汇总销售额清单（productname / sameperiodsum / productcategory），
-- 函数内不含任何 IsADRCustomer / IsADRVendor / Fresh_ADRRegion 筛选条件。
-- 故将菜单名更正为符合实际业务内容的名称。

UPDATE AD_Menu
SET Name = '客户销售额清单',
    Updated = now(),
    UpdatedBy = 100
WHERE AD_Menu_ID IN (540681, 540930);

UPDATE AD_Menu_Trl
SET Name = '客户销售额清单',
    IsTranslated = 'Y',
    Updated = now(),
    UpdatedBy = 100
WHERE AD_Menu_ID IN (540681, 540930)
  AND AD_Language = 'zh_CN';

UPDATE AD_Menu_Trl
SET Name = 'Sales List by Business Partner',
    IsTranslated = 'Y',
    Updated = now(),
    UpdatedBy = 100
WHERE AD_Menu_ID IN (540681, 540930)
  AND AD_Language = 'en_US';

UPDATE AD_Menu_Trl
SET Name = 'Umsatzliste nach Geschäftspartner',
    IsTranslated = 'Y',
    Updated = now(),
    UpdatedBy = 100
WHERE AD_Menu_ID IN (540681, 540930)
  AND AD_Language = 'de_DE';
