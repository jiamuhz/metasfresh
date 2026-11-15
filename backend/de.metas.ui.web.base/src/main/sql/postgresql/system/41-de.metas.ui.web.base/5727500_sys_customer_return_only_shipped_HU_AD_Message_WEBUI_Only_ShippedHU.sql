-- 2026-11-15T10:00:00.000
-- Only HUs which were shipped to the customer can be returned by the customer
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,545374,0,TO_TIMESTAMP('2026-11-15 10:00:00','YYYY-MM-DD HH24:MI:SS'),100,'de.metas.ui.web','Y','Only shipped HUs can be returned from customer','E',TO_TIMESTAMP('2026-11-15 10:00:00','YYYY-MM-DD HH24:MI:SS'),100,'WEBUI_Only_ShippedHU')
;

-- 2026-11-15T10:00:00.000
-- system language translations
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=545374 AND NOT EXISTS (SELECT 1 FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- 2026-11-15T10:00:00.000
-- Chinese translation
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID,MsgText,MsgTip,IsTranslated,AD_Client_ID,AD_Org_ID,Created,CreatedBy,Updated,UpdatedBy)
SELECT 'zh_CN',545374,'仅可对已发货的HU执行客户退货',NULL,'Y',0,0,TO_TIMESTAMP('2026-11-15 10:00:00','YYYY-MM-DD HH24:MI:SS'),100,TO_TIMESTAMP('2026-11-15 10:00:00','YYYY-MM-DD HH24:MI:SS'),100
WHERE NOT EXISTS (SELECT 1 FROM AD_Message_Trl WHERE AD_Language='zh_CN' AND AD_Message_ID=545374)
;
