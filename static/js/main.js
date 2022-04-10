// 配置路径及别名
require.config({
    baseUrl:'js',
    paths:{
        jquery   : 'jquery-1.9.1',
        jqueryUI : 'https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min'
    }
})

// 初始化window弹框
require(['jquery','window'],function($,w){
    $('#btn').on('click',function(){
        var win = new w.Window();
        win.alert({
            title           : '提示',
            content         : '换肤功能实现喽',
            btnText         : '确定按钮',
            width           : 500,
            height          : 300,
            skinClassName   : 'window_skin_a',
            hasMask         : true,
            dragHandel      : '.window_header',
            handlerSureBtn  : function(){
                alert('我是点击确定按钮后的回调...');
            },
            hasCloseBtn     : true,
            handlerCloseBtn : function(){
                alert('我是点击关闭按钮后的回调...');
            }
        });
        win.on('alert',function(){ alert('点击确定按钮事件01') });
        win.on('alert',function(){ alert('点击确定按钮事件02') });
        win.on('close',function(){ alert('点击关闭按钮事件01') });
        win.on('close',function(){ alert('点击关闭按钮事件02') });
    })
})