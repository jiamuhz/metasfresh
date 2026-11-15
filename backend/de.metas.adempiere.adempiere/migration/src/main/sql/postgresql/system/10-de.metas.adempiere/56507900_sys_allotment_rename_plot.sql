-- 2026-11-08
-- "Allotment" 中文误译为"分配"，实际业务为农产品种植地块(Plot/Parzelle)，字段层已译为"地块"。
-- 统一命名：菜单/窗口 -> 种植地块，页签 C_BPartner_Allotment -> 供应商地块

UPDATE AD_Menu SET Name='种植地块', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540888;

UPDATE AD_Menu_Trl SET Name='种植地块', WEBUI_NameBrowse='种植地块', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540888 AND AD_Language='zh_CN';

UPDATE AD_Window SET Name='种植地块', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=540210;

UPDATE AD_Window_Trl SET Name='种植地块', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=540210 AND AD_Language='zh_CN';

UPDATE AD_Tab SET Name='供应商地块', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Tab_ID=540574;

UPDATE AD_Tab_Trl SET Name='供应商地块', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Tab_ID=540574 AND AD_Language='zh_CN';

-- 第一个页签(C_Allotment 主表)原名"分配"，同样是误译
UPDATE AD_Tab SET Name='地块', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Tab_ID=540573;

UPDATE AD_Tab_Trl SET Name='地块', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Tab_ID=540573 AND AD_Language='zh_CN';
