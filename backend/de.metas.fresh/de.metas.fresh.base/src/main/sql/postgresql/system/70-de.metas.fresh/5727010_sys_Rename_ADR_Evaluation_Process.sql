-- 「ADR评估」报表（AD_Process_ID=540655）实际是按业务伙伴汇总的销售额清单，
-- 不含任何 ADR（危险品公路运输协议）筛选逻辑，故更正报表程序名称。

UPDATE AD_Process
SET Name = '客户销售额清单',
    Updated = now(),
    UpdatedBy = 100
WHERE AD_Process_ID = 540655;

UPDATE AD_Process_Trl
SET Name = '客户销售额清单',
    IsTranslated = 'Y',
    Updated = now(),
    UpdatedBy = 100
WHERE AD_Process_ID = 540655
  AND AD_Language = 'zh_CN';

UPDATE AD_Process_Trl
SET Name = 'Sales List by Business Partner',
    IsTranslated = 'Y',
    Updated = now(),
    UpdatedBy = 100
WHERE AD_Process_ID = 540655
  AND AD_Language = 'en_US';

UPDATE AD_Process_Trl
SET Name = 'Umsatzliste nach Geschäftspartner',
    IsTranslated = 'Y',
    Updated = now(),
    UpdatedBy = 100
WHERE AD_Process_ID = 540655
  AND AD_Language = 'de_DE';
