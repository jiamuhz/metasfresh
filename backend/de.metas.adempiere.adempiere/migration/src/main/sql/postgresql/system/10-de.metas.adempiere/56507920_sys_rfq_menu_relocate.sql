-- 2026-11-08
-- 询价菜单去重: 旧菜单组(请购到发票下)与新菜单组(采购下)指向同一批窗口
-- 1) 将旧组独有的两个流程入口 467(询价回复/打印)、468(未回复询价) 迁移到 采购 下
-- 2) 隐藏旧组里与新组重复的 452/454/466/540716

--- 1. 采购下腾位: seqno 10/11/12 后移为 12/13/14, 给 467/468 让出 10/11 --------
UPDATE AD_TreeNodeMM SET SeqNo=12, Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE Node_ID=1000047 AND AD_Tree_ID=10;
UPDATE AD_TreeNodeMM SET SeqNo=13, Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE Node_ID=1000049 AND AD_Tree_ID=10;
UPDATE AD_TreeNodeMM SET SeqNo=14, Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE Node_ID=1000051 AND AD_Tree_ID=10;

--- 2. 迁移 467 / 468 到 采购(1000011) 下, 紧跟询价菜单组 ----------------------
UPDATE AD_TreeNodeMM SET Parent_ID=1000011, SeqNo=10, Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE Node_ID=467 AND AD_Tree_ID=10;
UPDATE AD_TreeNodeMM SET Parent_ID=1000011, SeqNo=11, Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE Node_ID=468 AND AD_Tree_ID=10;

--- 3. 467 改名: 与新组窗口菜单 540879「询价响应」重名, 明确其为打印流程 ----------
UPDATE AD_Menu SET Name='询价响应打印', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=467;

UPDATE AD_Menu_Trl SET Name='询价响应打印', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=467 AND AD_Language='zh_CN';
UPDATE AD_Menu_Trl SET Name='Print Request for Quotation Response', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=467 AND AD_Language='en_US';
UPDATE AD_Menu_Trl SET Name='Ausschreibungs-Antwort drucken', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=467 AND AD_Language='de_DE';

--- 4. 隐藏旧组(与新菜单组重复的入口) ----------------------------------------
-- 452 询价主题     -> 已有 540878
-- 454 询价单       -> 已有 540877
-- 466 询价响应     -> 已有 540879
-- 540716 招标回复位置 -> 已有 540880(同指窗口 540295)
UPDATE AD_Menu SET IsActive='N', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID IN (452,454,466,540716);
