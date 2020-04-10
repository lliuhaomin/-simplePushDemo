/*MiPushPlugin插件对象*/
var MiPushPlugin = function (){};

/*判断是否为Android设备*/
MiPushPlugin.prototype.isAndroidDevice = function(){
    return device.platform == 'Android';
};
/*失败的回调方法*/
MiPushPlugin.prototype.error_callback = function(msg){
    console.log('MiPushPlugin Callback Error: ' + msg)
};
/*调用原生方法*/
MiPushPlugin.prototype.call_native = function(name, args, callback){
    return cordova.exec(callback, this.error_callback, 'MiPushPlugin', name, args);
};

/*弹出土司*/
MiPushPlugin.prototype.showToast = function(msg){
    if(this.isAndroidDevice()){
        var data = [msg];
        this.call_native('showToast',data,null);
    }
};

/*初始化插件*/
MiPushPlugin.prototype.init = function(){
    var data = [];
    this.call_native('init',data,null);
};

/*设置别名*/
MiPushPlugin.prototype.setAlias = function(alias){
    var data = [alias];
    this.call_native('setAlias',data,null);
};
/*取消别名*/
MiPushPlugin.prototype.unSetAlias = function(alias){
    var data = [alias];
    this.call_native('unSetAlias',data,null);
};

/*设置userAccount*/
MiPushPlugin.prototype.setUserAccount = function(userAccount){
    var data = [userAccount];
    this.call_native('setUserAccount',data,null);
};
/*取消userAccount*/
MiPushPlugin.prototype.unSetUserAccount = function(userAccount){
    var data = [userAccount];
    this.call_native('unSetUserAccount',data,null);
};

/*设置Topic*/
MiPushPlugin.prototype.setTopic = function(topic){
    var data = [topic];
    console.log(data);
    this.call_native('setTopic',data,null);
};
/*取消userTopic*/
MiPushPlugin.prototype.unSetTopic = function(topic){
    var data = [topic];
    this.call_native('unSetTopic',data,null);
};
/*得到所有别名*/
MiPushPlugin.prototype.getAllAlias = function(cb){
    this.call_native('getAllAlias',[],cb);
};
/*得到所有订阅*/
MiPushPlugin.prototype.getAllTopic = function(cb){
    this.call_native('getAllTopic',[],cb);
};

if (!window.plugins) {
  window.plugins = {};
}

if (!window.plugins.MiPushPlugin) {
  window.plugins.MiPushPlugin = new MiPushPlugin();
}

module.exports = new MiPushPlugin();