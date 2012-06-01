Ext.onReady(function() {
  Ext.QuickTips.init();
  Ext.form.Field.prototype.msgTarget = 'side';

  var viewport = new Ext.Viewport({
    layout: 'border',
    items: [{
      region: 'north',
      frame: true,
      layout: 'fit',
      height: 60,
      items: [schForm]
    }, {
      region: 'center',
      frame: true,
      layout: 'fit',
      items: [grid]
    }]
  });
  // Ext.apply(Ext.form.VTypes,{
  // barId:function(val,field){
  //        
  // if(val){
  // Ext.Ajax.request({
  // method:'post',
  // url:'blackBar_exist.do',
  // async:true,
  // params:{barId:val},
  // text:'查询中...',
  // success:function(response,opts){
  // var result = Ext.decode(response.responseText);
  // return Boolean(result.msg);
  // }
  // });
  // }
  // },
  // barIdText:'网吧id不存在!'
  // });
});

var userList = {
  controlFn: function(value, cellmeta, record, rowIndex, columnIndex, store) {
    return "<a href=\"#\" onclick=\"userList.viewFn(" + rowIndex
        + ");\"><img src=\"../../style/imgs/cog_edit.png\" title=\"修改\"/></a>"
        + "&nbsp;&nbsp;<a href=\"#\" onclick=\"userList.delFn(" + value
        + ");\"><img src=\"../../style/imgs/del.png\" title=\"删除\"/></a>";
  },

  addFn: function() {
    var addForm = Ext.getCmp("addForm").getForm();
    if (addForm.isValid()) {
      addForm.submit({
        success: function(form, action) {
          Ext.getCmp("addWin").hide();
          var flag = action.result.success;
          if (flag == true) {
            Ext.Msg.alert("提示", action.result.msg);
            userList.reload();
            // 刷新列表
          } else {
            Ext.Msg.alert("提示", "新增失败![是否重复添加?]");
          }
        }
      });
    }
  },

  viewFn: function(rowIndex) {
    var record = store.getAt(rowIndex);
    Ext.getCmp("modifyForm").getForm().loadRecord(record);
    modifyWin.show();
  },

  modifyFn: function(id) {
    var modifyForm = Ext.getCmp("modifyForm").getForm();
    if (modifyForm.isValid()) {
      modifyForm.submit({
        success: function(form, action) {
          Ext.getCmp("modifyWin").hide();
          var flag = action.result.success;
          if (flag == true) {
            Ext.Msg.alert("提示", action.result.msg);
            userList.reload();
            // 刷新列表
          } else {
            Ext.Msg.alert("提示", "修改失败![是否重复添加?]");
          }
        }
      });
    }
  },

  delFn: function(id) {
    Ext.MessageBox.confirm("警告", "确认删除所选记录吗?", function(btn) {
      if ("yes" == btn) {
        Ext.Ajax.request({
          method: 'post',
          url: 'removeUser.htm',
          params: {
            'userIds': [id]
          },
          text: '删除中...',
          success: function(response, opts) {
            var result = Ext.decode(response.responseText);
            var flag = result.success;
            if (flag == true) {
              Ext.Msg.alert("提醒", "删除成功!");
              userList.reload();
            } else {
              Ext.Msg.alert("提醒", "删除失败!");
            }
          }
        });
      }
    });
  },

  delsFn: function() {
    var sels = Ext.getCmp("grid").getSelectionModel().getSelections();
    if (sels.length < 1) {
      Ext.Msg.alert("警告", "请至少选择一条记录进行操作!");
      return;
    }

    Ext.MessageBox.confirm("警告", "确认删除所选记录吗?", function(btn) {
      if ("yes" == btn) {
        var idx = 0;
        var ids = [];
        Ext.each(sels, function(i, v) {
          var id = i.get("user.id");
          ids[idx++] = id;
        });

        Ext.Ajax.request({
          method: 'post',
          url: 'removeUser.htm',
          params: {
            "userIds": ids
          },
          text: '批量删除中...',
          success: function(response, opts) {
            var result = Ext.decode(response.responseText);
            var flag = result.success;
            if (flag == true) {
              Ext.Msg.alert("提醒", "删除成功!");
              userList.reload();
            } else {
              Ext.Msg.alert("提醒", "删除失败!");
            }
          }
        });
      }
    });
  },

  reload: function() {
    var params = Ext.getCmp("schForm").getForm().getValues();
    var size = Ext.getCmp("disPage").getValue();
    Ext.getCmp("pageToolbar")["pageSize"] = size;
    params["rp"] = size;
    params["limit"] = size;
    params["start"] = 0;
    params["page"] = 1;
    Ext.getCmp("grid").getStore().reload({
      params: params
    });
  },

  isExist: function() {
    var flag = false;
    var val = Ext.getCmp("addWin_barId").getValue();
    Ext.Ajax.request({
      method: 'post',
      url: 'blackBar_exist.do',
      async: false,
      params: {
        barId: val
      },
      text: '查询中...',
      success: function(response, opts) {
        var result = Ext.decode(response.responseText);
        var temp = result.msg;
        if ("false" != temp) {
          flag = true;
          Ext.getCmp("addWin_barName").focus();
          Ext.getCmp("addWin_barName").setValue(temp);
        }
      }
    });
    return flag;
  }
};

// 搜索面板
var schForm = new Ext.form.FormPanel({
  id: 'schForm',
  frame: true,
  labelWidth: 60,
  labelAlign: 'right',
  items: [{
    layout: 'column',
    items: [{
      columnWidth: '0.2',
      layout: 'form',
      items: [{
        xtype: 'textfield',
        name: 'user.username',
        fieldLabel: '用户名',
        anchor: '95%'
      }]
    }, {
      columnWidth: '0.2',
      layout: 'form',
      items: [{
        xtype: 'textfield',
        name: 'user.realName',
        fieldLabel: '真实姓名',
        anchor: '95%'
      }]
    }, {
      columnWidth: '0.1',
      layout: 'column',
      items: [{
        columnWidth: '0.5',
        items: [{
          xtype: 'button',
          text: '查询',
          anchor: '95%',
          iconCls: 'sch_search',
          handler: userList.reload
        }]
      }, {
        columnWidth: '0.5',
        items: [{
          xtype: 'button',
          text: '清空',
          anchor: '95%',
          iconCls: 'sch_reset',
          handler: function() {
            Ext.getCmp("schForm").getForm().reset();
          }
        }]
      }]
    }]
  }]
});

// 操作面板
var addWin = new Ext.Window({
  id: 'addWin',
  title: '新增用户信息',
  height: 160,
  width: 300,
  collapsible: true,
  resizable: false,
  frame: true,
  modal: true,
  hidden: true,
  closeAction: 'hide',
  layout: 'fit',
  items: [{
    frame: true,
    id: 'addForm',
    xtype: 'form',
    url: 'addUser.htm',
    labelWidth: 60,
    labelAlign: 'right',
    items: [{
      xtype: 'textfield',
      id: 'username',
      anchor: '90%',
      name: 'user.username',
      fieldLabel: '用户名',
      allowBlank: false,
    }, {
      xtype: 'textfield',
      inputType: 'password',
      id: 'password',
      anchor: '90%',
      name: 'user.password',
      fieldLabel: '密码',
      allowBlank: false
    }, {
      xtype: 'textfield',
      id: 'realName',
      anchor: '90%',
      name: 'user.realName',
      fieldLabel: '真实姓名',
      allowBlank: false,
    }],
    buttonAlign: 'center',
    buttons: [{
      text: '保存',
      handler: userList.addFn
    }, {
      text: '重置',
      handler: function() {
        Ext.getCmp("addForm").getForm().reset();
      }
    }, {
      text: '取消',
      handler: function() {
        Ext.getCmp("addWin").hide();
      }
    }]
  }]
});

var modifyWin = new Ext.Window({
  id: 'modifyWin',
  title: '修改用户信息',
  height: 160,
  width: 300,
  collapsible: true,
  resizable: false,
  frame: true,
  modal: true,
  hidden: true,
  closeAction: 'hide',
  layout: 'fit',
  items: [{
    frame: true,
    id: 'modifyForm',
    xtype: 'form',
    url: 'modifyUser.htm',
    labelWidth: 60,
    labelAlign: 'right',
    items: [{
      xtype: 'textfield',
      inputType: 'hidden',
      id: 'id',
      anchor: '90%',
      name: 'user.id',
      fieldLabel: '用户ID',
      allowBlank: false
    }, {
      xtype: 'textfield',
      id: 'username',
      anchor: '90%',
      name: 'user.username',
      fieldLabel: '用户名',
      allowBlank: false
    }, {
      xtype: 'textfield',
      inputType: 'password',
      id: 'password',
      anchor: '90%',
      name: 'user.password',
      fieldLabel: '密码',
      allowBlank: false
    }, {
      xtype: 'textfield',
      id: 'realName',
      anchor: '90%',
      name: 'user.realName',
      fieldLabel: '真实姓名',
      allowBlank: false
    }],
    buttonAlign: 'center',
    buttons: [{
      text: '保存',
      handler: userList.modifyFn
    }, {
      text: '重置',
      handler: function() {
        Ext.getCmp("modifyForm").getForm().reset();
      }
    }, {
      text: '取消',
      handler: function() {
        Ext.getCmp("modifyWin").hide();
      }
    }]
  }]
});

// 显示面板
var sm = new Ext.grid.CheckboxSelectionModel();
var cm = new Ext.grid.ColumnModel([sm, {
  header: '用户ID',
  dataIndex: 'user.id'
}, {
  header: '用户名',
  dataIndex: 'user.username'
}, {
  header: '真实姓名',
  dataIndex: 'user.realName',
}, {
  header: '修改时间',
  dataIndex: 'user.modifyTime',
}, {
  header: '创建时间',
  dataIndex: 'user.creationTime',
}, {
  header: '操作',
  dataIndex: 'user.id',
  renderer: userList.controlFn
}]);

var store = new Ext.data.Store({
  proxy: new Ext.data.HttpProxy({
    url: 'listUser.htm'
  }),
  reader: new Ext.data.JsonReader({
    totalProperty: 'total',
    root: 'rows'
  }, [{
    name: 'user.id',
    mapping: 'cell.id'
  }, {
    name: 'user.username',
    mapping: 'cell.username'
  }, {
    name: 'user.realName',
    mapping: 'cell.realName'
  }, {
    name: 'user.modifyTime',
    mapping: 'cell.modifyTime'
  }, {
    name: 'user.creationTime',
    mapping: 'cell.creationTime'
  }])
});

store.load({
  params: {
    'rp': 10,
    limit: 10,
    start: 0,
    'page': 1
  }
});

var disPage = new Ext.form.ComboBox({
  id: 'disPage',
  fieldLabel: '每页显示',
  triggerAction: 'all',
  mode: 'local',
  displayField: 'name',
  valueField: 'id',
  width: 70,
  editable: false,
  hiddenValue: 10,
  value: 10,
  store: new Ext.data.ArrayStore({
    fields: ['id', 'name'],
    data: [[10, '10'], [20, '20'], [30, '30']]
  }),
  listeners: {
    select: function() {
      userList.reload();
    }
  }
});

var grid = new Ext.grid.GridPanel({
  id: 'grid',
  sm: sm,
  cm: cm,
  store: store,
  autoWidth: true,
  loadMask: true,
  viewConfig: {
    forceFit: true
  },

  frame: true,
  stripeRows: true,
  stateful: true,
  tbar: new Ext.Toolbar({
    items: [{
      text: '新增',
      iconCls: 'add',
      handler: function() {
        Ext.getCmp("addWin").show();
        Ext.getCmp("addForm").getForm().reset();
      }
    }, '-', {
      text: '删除',
      iconCls: 'tbar_del',
      handler: userList.delsFn
    }]
  }),

  bbar: new Ext.PagingToolbar({
    id: 'pageToolbar',
    pageSize: Ext.getCmp("disPage").getValue(),
    store: store,
    displayInfo: true,
    displayMsg: '显示第{0}到第{1}条记录,总共{2}条记录',
    emptyMsg: '没有可显示的记录.',
    items: ['-', disPage],
    doLoad: function(start) {
      var o = Ext.getCmp("schForm").getForm().getValues(), pn = this.getParams();
      var size = parseInt(Ext.getCmp("disPage").getValue());
      o["rp"] = size;
      o[pn.limit] = size;
      o[pn.start] = start;
      o["page"] = start / size + 1;
      if (this.fireEvent('beforechange', this, o) !== false) {
        this.store.load({
          params: o
        });
      }
    }
  })
});
