const translations = {
  appName: 'metasfresh 移动端',
  error: {
    PleaseTryAgain: '请重试',
    network: {
      noResponse: '连接错误',
    },
  },
  general: {
    Product: '产品',
    ProductValue: '产品值',
    Locator: '库位',
    QRCode: '二维码',
    QtyToPick: '待拣数量',
    QtyToPick_Total: '待拣数量(总计)',
    QtyPicked: '已拣数量',
    QtyMoved: '已移动数量',
    QtyToMove: '待移动数量',
    QtyRejected: '已拒绝数量',
    CatchWeight: '重量',
    DropToLocator: '投放到库位',
    cancelText: '取消',
    scanQRCode: '扫描二维码',
    Back: '返回',
    Home: '首页',
    filter: {
      showResults: '显示结果 (%(count)s)',
      clearFilters: '清除筛选',
    },
  },
  login: {
    submitButton: '登录',
  },
  logout: '退出登录',
  mobileui: {
    manufacturing: {
      appName: '生产',
    },
    picking: {
      appName: '拣配',
    },
    distribution: {
      appName: '配送',
    },
  },
  components: {
    BarcodeScannerComponent: {
      scanTextPlaceholder: '扫描...',
    },
  },
  activities: {
    scanBarcode: {
      defaultCaption: '扫描',
      invalidScannedBarcode: '扫描的条码无效',
    },
    picking: {
      PickingLine: '拣配行',
      PickHU: '拣选物料搬运单元',
      scanQRCode: '扫描二维码',
      notEligibleHUBarcode: '物料搬运单元条码不匹配',
      qtyAboveMax: '超出最大值 %(qtyDiff)s',
      notPositiveQtyNotAllowed: '不允许为零或负数',
      confirmDone: '完成',
      rejectedPrompt: '有 %(qtyRejected)s %(uom)s 未拣选。原因？',
      unPickBtn: '取消拣选',
      target: '待拣选',
      picked: '已拣选',
    },
    distribution: {
      DistributionLine: '配送行',
      target: '待移动',
      picked: '已移动',
      scanHU: '扫描拣选来源物料搬运单元',
      scanLocator: '扫描投放目标库位',
      invalidLocatorQRCode: '无效的库位二维码',
      invalidQtyToMove: '无效的移动数量',
    },
    confirmButton: {
      default: {
        caption: '确认',
        promptQuestion: '确定吗？',
        yes: '是',
        no: '否',
      },
      abort: '中止',
      notFound: '未找到',
    },
    mfg: {
      ProductName: '产品名称',
      target: '待发放',
      picked: '已发放',
      generateHUQRCodes: {
        packing: '包装',
        qtyTUs: '运输单元数量',
        print: '打印',
      },
      issues: {
        target: '待发放',
        picked: '已发放',
        qtyToIssueTarget: '目标发放数量',
        qtyToIssueRemaining: '待发放数量',
        qtyIssued: '已发放数量',
        qtyRejected: '已拒绝数量',
        step: {
          name: '发放物料搬运单元',
        },
      },
      receipts: {
        qtyToReceiveTarget: '目标接收数量',
        qtyReceived: '已接收数量',
        qtyToReceive: '待接收数量',
        btnReceiveTarget: '接收目标',
        btnReceiveProducts: '接收产品',
        existingLU: '现有物料搬运单元',
        newHU: '新建物料搬运单元',
        target: '待接收',
        picked: '已接收',
      },
    },
  },
};

export default translations;
