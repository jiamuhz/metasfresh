-- 收货计划（窗口：收货计划，AD_Tab_ID=540526）
-- 之前为了“手动新建收货计划（盘盈/样品/赠品入库）”把一批原本只读的字段改成了可编辑。
-- 现在改为：只有手动新建（C_Order_ID 为空）的记录可编辑；
--           由采购订单生成的记录（C_Order_ID > 0）保持只读，行为与改造前一致。
-- 注：导出状态（AD_Field_ID=615399）与订单无关，直接恢复为始终只读。

UPDATE AD_Field
SET IsReadOnly='Y',
    Updated=TO_TIMESTAMP('2026-11-15 20:00:00','YYYY-MM-DD HH24:MI:SS'),
    UpdatedBy=100
WHERE AD_Field_ID=615399
;

UPDATE AD_Field
SET ReadOnlyLogic='@C_Order_ID/0@!=0',
    Updated=TO_TIMESTAMP('2026-11-15 20:00:00','YYYY-MM-DD HH24:MI:SS'),
    UpdatedBy=100
WHERE AD_Field_ID IN (
    552531, -- 用户/联系人            AD_User_ID
    552534, -- 采购订单日期          DateOrdered
    552536, -- 文档类型              C_DocType_ID
    552537, -- 订购数量              QtyOrdered
    552538, -- 承诺的日期            MovementDate
    552544, -- 业务伙伴              C_BPartner_ID
    552546, -- 仓库                  M_Warehouse_ID
    552548, -- 交货规则              DeliveryRule
    552550, -- 通过                  DeliveryViaRule
    552556, -- 产品                  M_Product_ID
    552561, -- 合作伙伴地址          C_BPartner_Location_ID
    552570, -- 计量单位(UOM)         C_UOM_ID
    553163  -- 属性                  M_AttributeSetInstance_ID
)
;

-- WebUI 只读判定：AD_Column.IsUpdateable='N' 的字段会直接按“可编辑”处理，忽略 AD_Field.ReadOnlyLogic。
-- 因此把上述字段所在的列改为可更新，由 ReadOnlyLogic 决定是否只读。
UPDATE AD_Column
SET IsUpdateable='Y',
    Updated=TO_TIMESTAMP('2026-11-15 20:00:00','YYYY-MM-DD HH24:MI:SS'),
    UpdatedBy=100
WHERE AD_Column_ID IN (
    SELECT AD_Column_ID
    FROM AD_Field
    WHERE AD_Field_ID IN (552534, 552536, 552537, 552546, 552548, 552550, 552556)
)
;
