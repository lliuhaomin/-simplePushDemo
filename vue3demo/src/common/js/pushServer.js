/*
 * @Description:
 * @Author: liuhm
 * @Date: 2020-04-10 14:05:04
 * @LastEditTime: 2020-04-10 14:05:41
 * @LastEditors: liuhm
 * @FilePath: \vue3demo\src\common\js\pushServer.js
 */
var app = {
  regId: "",
  that: "",
  initialize: function() {
    this.bindEvents();
  },
  setAlias(alias) {
    try {
      window.plugins.MiPushPlugin.setAlias(alias);
      console.log("设置别名", alias);
    } catch (e) {}
  },
  unSetAlias(alias) {
    try {
      window.plugins.MiPushPlugin.unSetAlias(alias);
      console.log("移除别名", alias);
    } catch (e) {}
  },
  bindEvents: function() {
    document.addEventListener("deviceready", this.onDeviceReady, false);
    document.addEventListener(
      "mipush.notificationMessageArrived",
      this.onNotificationMessageArrived,
      false
    );
    document.addEventListener(
      "mipush.notificationMessageClicked",
      this.onNotificationMessageClicked,
      false
    );
    document.addEventListener(
      "mipush.receiveRegisterResult",
      this.onReceiveRegisterResult,
      false
    );
  },
  onDeviceReady: function() {
    console.log("onDeviceReady:---------------");
    app.initiateUI();
  },
  initiateUI: function() {
    try {
      console.log("initiateUI");
      window.plugins.MiPushPlugin.init();
    } catch (exception) {
      console.log("initiateUI-----exception:" + exception);
    }
  },
  onNotificationMessageClicked: function(data) {
    console.log("onNotificationMessageClickedonNotificationMessage");
    try {
      var title = data.title;
      var description = data.description;
      console.log(
        "onNotificationMessageClicked---------" +
          "-title-" +
          title +
          "-description-" +
          description
      );
      console.log(
        "onNotificationMessageClickeddata" +
          data.extra.path +
          "         " +
          JSON.stringify(data)
      );
      app.that.$router.push(data.extra.path);
    } catch (exception) {
      console.log("onNotificationMessageClicked------exception:" + exception);
    }
  },
  showToast(message) {
    console.log("showToastshowToastshowToastshowToast", message);
    window.plugins.toast.showWithOptions(
      {
        message: message,
        duration: "2000", // which is 2000 ms. "long" is 4000. Or specify the nr of ms yourself.
        position: "top",
        addPixelsY: 0 // added a negative value to move it up a bit (default 0)
      },
      function(args) {
        console.log("toast args: ", args.event);
        //This will print 'hide'
      },
      function(error) {
        console.error("toast error: ", error);
      }
    );
  },
  onNotificationMessageArrived: function(data) {
    try {
      var title = data.title;
      var description = data.description;
      console.log("app.showToast", data);
      app.showToast(title);
      console.log(
        "onNotificationMessageArrived---------" +
          "-title-" +
          title +
          "-description-" +
          description
      );
      console.log(
        "onNotificationMessageArriveddata---------" + JSON.stringify(data)
      );
    } catch (exception) {
      console.log("onNotificationMessageArrived------exception:" + exception);
    }
  },
  onReceiveRegisterResult: function(data) {
    try {
      console.log("onReceiveRegisterResult---------" + data.regId);
      app.regId = data.regId;
    } catch (exception) {
      console.log("onReceiveRegisterResult------exception:" + exception);
    }
  }
};
export default app;
// app.initialize();
