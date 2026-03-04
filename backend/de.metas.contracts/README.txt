

This addon add features related to long-running BPartner-contracts.

In particular this includes
*"Subscriptions" with regular outgoing shipments
*"FlatFees" where regular orders (and shipments) are invoiced based on a flatrate schema (and not based on their actual price and qty)
*"HoldingFees" where there is no delivery, but a recurring fee like in rents

run "mvn site" for further documentation


Modular
模块化合同允许将一个主合同拆分为多个独立的模块，每个模块可以有自己的条款、计费规则和有效期。
ModCntr_Contract (主合同)
    ├── ModCntr_Module (合同模块1)
    │       ├── 模块基本信息
    │       ├── 计费规则
    │       └── 关联的产品
    ├── ModCntr_Module (合同模块2)
    │       ├── 模块基本信息
    │       ├── 计费规则
    │       └── 关联的产品
    └── ...
