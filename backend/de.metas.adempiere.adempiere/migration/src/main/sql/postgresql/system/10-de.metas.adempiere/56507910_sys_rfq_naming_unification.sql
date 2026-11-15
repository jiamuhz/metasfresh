-- 2026-11-08
-- RFQ(Request for Quotation) 中文命名统一为"询价"
-- 现有三套并存叫法: "提案请求"(窗口/菜单/页签), "询价请求响应"(菜单), "RFQ xxx"(字段)
-- 英文 "Request for Proposal"(RFP) 一并修正为 "Request for Quotation"(RFQ)

--- 1. 窗口 ---------------------------------------------------------------
UPDATE AD_Window SET Name='询价主题', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=314;
UPDATE AD_Window SET Name='询价单',   Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=315;
UPDATE AD_Window SET Name='询价响应', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=324;
UPDATE AD_Window SET Name='询价响应行', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=540295;

UPDATE AD_Window_Trl SET Name='询价主题',   IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=314 AND AD_Language='zh_CN';
UPDATE AD_Window_Trl SET Name='询价单',     IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=315 AND AD_Language='zh_CN';
UPDATE AD_Window_Trl SET Name='询价响应',   IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=324 AND AD_Language='zh_CN';
UPDATE AD_Window_Trl SET Name='询价响应行', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=540295 AND AD_Language='zh_CN';

UPDATE AD_Window_Trl SET Name='Request for Quotation Topic',        IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=314 AND AD_Language='en_US';
UPDATE AD_Window_Trl SET Name='Request for Quotation',              IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=315 AND AD_Language='en_US';
UPDATE AD_Window_Trl SET Name='Request for Quotation Response',     IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=324 AND AD_Language='en_US';
UPDATE AD_Window_Trl SET Name='Request for Quotation Response Line', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Window_ID=540295 AND AD_Language='en_US';

--- 2. 页签 ---------------------------------------------------------------
UPDATE AD_Tab SET Name='询价单',   Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Tab_ID=613;
UPDATE AD_Tab SET Name='询价响应行', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Tab_ID=540740;

UPDATE AD_Tab_Trl SET Name='询价单',   IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Tab_ID=613 AND AD_Language='zh_CN';
UPDATE AD_Tab_Trl SET Name='询价响应行', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Tab_ID=540740 AND AD_Language='zh_CN';

UPDATE AD_Tab_Trl SET Name='Request for Quotation',              IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Tab_ID=613 AND AD_Language='en_US';
UPDATE AD_Tab_Trl SET Name='Request for Quotation Response Line', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Tab_ID=540740 AND AD_Language='en_US';

--- 3. 菜单: 采购下新菜单组 ------------------------------------------------
UPDATE AD_Menu SET Name='询价单',   Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540877;
UPDATE AD_Menu SET Name='询价响应', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540879;
UPDATE AD_Menu SET Name='询价响应行', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540880;

UPDATE AD_Menu_Trl SET Name='询价单',   WEBUI_NameBrowse='询价单',   IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540877 AND AD_Language='zh_CN';
UPDATE AD_Menu_Trl SET Name='询价响应', WEBUI_NameBrowse='询价响应', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540879 AND AD_Language='zh_CN';
UPDATE AD_Menu_Trl SET Name='询价响应行', WEBUI_NameBrowse='询价响应行', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540880 AND AD_Language='zh_CN';

-- 540878 中文名已正确, 仅修正侧边栏英文残留
UPDATE AD_Menu_Trl SET WEBUI_NameBrowse='询价主题', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540878 AND AD_Language='zh_CN';

UPDATE AD_Menu_Trl SET Name='Request for Quotation',              WEBUI_NameBrowse='Request for Quotation',              IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540877 AND AD_Language='en_US';
UPDATE AD_Menu_Trl SET Name='Request for Quotation Topic',        WEBUI_NameBrowse='Request for Quotation Topic',        IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540878 AND AD_Language='en_US';
UPDATE AD_Menu_Trl SET Name='Request for Quotation Response',     WEBUI_NameBrowse='Request for Quotation Response',     IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540879 AND AD_Language='en_US';
UPDATE AD_Menu_Trl SET Name='Request for Quotation Response Line', WEBUI_NameBrowse='Request for Quotation Response Line', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=540880 AND AD_Language='en_US';

--- 4. 菜单: 请购到发票下旧菜单组 (与新菜单组指向同一批窗口) ------------------
UPDATE AD_Menu SET Name='询价单',   Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=454;
UPDATE AD_Menu SET Name='询价响应', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=466;
UPDATE AD_Menu SET Name='询价响应', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=467;

UPDATE AD_Menu_Trl SET Name='询价单',   IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=454 AND AD_Language='zh_CN';
UPDATE AD_Menu_Trl SET Name='询价响应', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=466 AND AD_Language='zh_CN';
UPDATE AD_Menu_Trl SET Name='询价响应', IsTranslated='Y', Updated=CURRENT_TIMESTAMP, UpdatedBy=100 WHERE AD_Menu_ID=467 AND AD_Language='zh_CN';

--- 5. 元素与字段名 --------------------------------------------------------
UPDATE AD_Element SET Name = CASE AD_Element_ID
		WHEN 2375 THEN '询价单'
		WHEN 2376 THEN '询价主题'
		WHEN 2377 THEN '询价订阅者'
		WHEN 2378 THEN '询价行'
		WHEN 2379 THEN '询价行数量'
		WHEN 2381 THEN '询价响应行'
		WHEN 2382 THEN '询价响应行数量'
		WHEN 2446 THEN '询价响应'
		WHEN 2424 THEN '发布询价'
		WHEN 2425 THEN '询价类型'
		WHEN 2476 THEN '将询价邀请发送给供应商'
		WHEN 2483 THEN '询价等级'
		WHEN 2575 THEN '询价数量'
		WHEN 2580 THEN '询价主题订阅者限制'
		WHEN 2739 THEN '询价菜单'
		WHEN 573406 THEN '询价单'
		WHEN 573805 THEN '询价响应行'
		WHEN 574188 THEN '询价单'
		WHEN 574295 THEN '询价响应'
		WHEN 574296 THEN '询价主题'
		WHEN 574302 THEN '询价响应行'
		WHEN 574679 THEN '询价单'
		WHEN 574680 THEN '询价响应'
		WHEN 574681 THEN '询价响应行'
	END,
	Updated=CURRENT_TIMESTAMP, UpdatedBy=100
WHERE AD_Element_ID IN (2375,2376,2377,2378,2379,2381,2382,2446,2424,2425,2476,2483,2575,2580,2739,
                        573406,573805,574188,574295,574296,574302,574679,574680,574681);

UPDATE AD_Element_Trl SET Name = CASE AD_Element_ID
		WHEN 2375 THEN '询价单'
		WHEN 2376 THEN '询价主题'
		WHEN 2377 THEN '询价订阅者'
		WHEN 2378 THEN '询价行'
		WHEN 2379 THEN '询价行数量'
		WHEN 2381 THEN '询价响应行'
		WHEN 2382 THEN '询价响应行数量'
		WHEN 2446 THEN '询价响应'
		WHEN 2424 THEN '发布询价'
		WHEN 2425 THEN '询价类型'
		WHEN 2476 THEN '将询价邀请发送给供应商'
		WHEN 2483 THEN '询价等级'
		WHEN 2575 THEN '询价数量'
		WHEN 2580 THEN '询价主题订阅者限制'
		WHEN 2739 THEN '询价菜单'
		WHEN 573406 THEN '询价单'
		WHEN 573805 THEN '询价响应行'
		WHEN 574188 THEN '询价单'
		WHEN 574295 THEN '询价响应'
		WHEN 574296 THEN '询价主题'
		WHEN 574302 THEN '询价响应行'
		WHEN 574679 THEN '询价单'
		WHEN 574680 THEN '询价响应'
		WHEN 574681 THEN '询价响应行'
	END,
	Updated=CURRENT_TIMESTAMP, UpdatedBy=100
WHERE AD_Language='zh_CN'
  AND AD_Element_ID IN (2375,2376,2377,2378,2379,2381,2382,2446,2424,2425,2476,2483,2575,2580,2739,
                        573406,573805,574188,574295,574296,574302,574679,574680,574681);

UPDATE AD_Element_Trl SET Name = CASE AD_Element_ID
		WHEN 2375 THEN 'Request for Quotation'
		WHEN 2376 THEN 'Request for Quotation Topic'
		WHEN 2377 THEN 'Request for Quotation Subscriber'
		WHEN 2378 THEN 'Request for Quotation Line'
		WHEN 2379 THEN 'Request for Quotation Line Qty'
		WHEN 2381 THEN 'Request for Quotation Response Line'
		WHEN 2382 THEN 'Request for Quotation Response Line Qty'
		WHEN 2446 THEN 'Request for Quotation Response'
		WHEN 2424 THEN 'Publish Request for Quotation'
		WHEN 2425 THEN 'Request for Quotation Type'
		WHEN 2476 THEN 'Send Request for Quotation Invitation to Vendor'
		WHEN 2483 THEN 'Request for Quotation Rank'
		WHEN 2575 THEN 'Request for Quotation Qty'
		WHEN 2580 THEN 'Request for Quotation Topic Subscriber Restriction'
		WHEN 2739 THEN 'Request for Quotation Menu'
		WHEN 573406 THEN 'Request for Quotation'
		WHEN 573805 THEN 'Request for Quotation Response Line'
		WHEN 574188 THEN 'Request for Quotation'
		WHEN 574295 THEN 'Request for Quotation Response'
		WHEN 574296 THEN 'Request for Quotation Topic'
		WHEN 574302 THEN 'Request for Quotation Response Line'
		WHEN 574679 THEN 'Request for Quotation'
		WHEN 574680 THEN 'Request for Quotation Response'
		WHEN 574681 THEN 'Request for Quotation Response Line'
	END,
	Updated=CURRENT_TIMESTAMP, UpdatedBy=100
WHERE AD_Language='en_US'
  AND AD_Element_ID IN (2375,2376,2377,2378,2379,2381,2382,2446,2424,2425,2476,2483,2575,2580,2739,
                        573406,573805,574188,574295,574296,574302,574679,574680,574681);

-- 字段标签跟随元素
UPDATE AD_Column c
SET Name=e.Name, Updated=CURRENT_TIMESTAMP, UpdatedBy=100
FROM AD_Element e
WHERE e.AD_Element_ID=c.AD_Element_ID
  AND e.AD_Element_ID IN (2375,2376,2377,2378,2379,2381,2382,2446,2424,2425,2476,2483,2575,2580,2739,
                          573406,573805,574188,574295,574296,574302,574679,574680,574681)
  AND c.Name <> e.Name;

UPDATE AD_Column_Trl ct
SET Name=e.Name, Updated=CURRENT_TIMESTAMP, UpdatedBy=100
FROM AD_Column c
JOIN AD_Element e ON e.AD_Element_ID=c.AD_Element_ID
WHERE ct.AD_Column_ID=c.AD_Column_ID
  AND e.AD_Element_ID IN (2375,2376,2377,2378,2379,2381,2382,2446,2424,2425,2476,2483,2575,2580,2739,
                          573406,573805,574188,574295,574296,574302,574679,574680,574681)
  AND ct.AD_Language='zh_CN';
