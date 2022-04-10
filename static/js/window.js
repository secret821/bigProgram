define(['jquery','jqueryUI'],function($,$UI){

    var Window = function(){
        this.config = {
            title           : '系统消息',        // 弹框的标题文字
            content         : '内容',           // 弹框的内容问题
            btnText         : '确定',           // 弹框的按钮文字
            width           : 800,             // 弹框的宽度
            height          : 500,             // 弹框的高度
            handlerSureBtn  : null,            // 弹框的按钮触发的事件
            hasCloseBtn     : false,           // 弹框中是否显示关闭按钮
            handlerCloseBtn : null,            // 弹框关闭按钮触发的事件
            skinClassName   : null,            // 弹框换肤
            hasMask         : true,            // 弹框遮罩
            isDraggable     : true,            // 弹框是否可拖动
            dragHandel      : null             // 弹框中拖动的'把手'：'.window_title'
        };
        this.handlers = {};                    // 弹框中的自定义事件集合
    };

    Window.prototype = {

        // 自定义事件
        on : function(type, handler){
            if( typeof this.handlers[type] == "undefined" ){
                this.handlers[type] = [];
            }
            this.handlers[type].push(handler);
        },

        fire : function(type, data){
            if( this.handlers[type] instanceof Array ){
                var handlers = this.handlers[type];
                for( var i = 0; i < handlers.length; i++ ){
                    handlers[i](data);
                }
            }
        },
        // 弹框事件
        alert : function(cfg){

                var that = this;
                var config = $.extend(this.config, cfg);

                // 弹框盒子
                var boundingBox = $('<div class="window_boundingBox">' +
                                    '<div class="window_header">' + config.title + '</div>' +
                                    '<div class="window_body">' + config.content + '</div>' +
                                    '<div class="window_footer">' + 
                                    '<input type="button" id="btn_sure" value=' + config.btnText + '>' + '</div>' +
                                    '</div>');

                boundingBox.appendTo('body');

                // 定制皮肤
                if(config.skinClassName){
                    boundingBox.addClass(config.skinClassName);
                }

                //模态弹窗
                if(config.hasMask){
                    var mask = $('<div class="window_mask"></div>');
                    mask.appendTo('body');
                }

                //拖动属性
                if(config.isDraggable){
                    if(config.dragHandel){
                        boundingBox.draggable({handle:config.dragHandel});
                    }else{
                        boundingBox.draggable();
                    }
                }

                // 设置宽、高、坐标
                boundingBox.css({
                    width: config.width,
                    height: config.height,
                    left: (config.x || (window.innerWidth - config.width)/2) + 'px',
                    top: (config.y || (window.innerHeight - config.height)/2) + 'px'
                })

                // 关闭按钮
                if(config.hasCloseBtn){
                    var closeBtn = $('<div class="closeBtn">X</div>');
                    boundingBox.append(closeBtn);
                    $('.closeBtn').on('click',function(){
                        // config.handlerCloseBtn && handlerCloseBtn();
                        that.fire('close');
                        boundingBox.remove();
                        mask && mask.remove();
                    })
                }

                // 确定按钮点击事件
                $('#btn_sure').on('click',function(){
                    // config.handlerSureBtn && config.handlerSureBtn();
                    that.fire('alert');
                    boundingBox.remove();
                    mask && mask.remove();
                })

                // 为关闭按钮添加'close'事件
                if( config.handlerCloseBtn ){
                    this.on('close',config.handlerCloseBtn);
                }                

                // 为确定按钮添加'alert'事件
                if( config.handlerSureBtn ){
                    this.on('alert',config.handlerSureBtn);
                }
        }
    }

    return {
        Window : Window
    }

})