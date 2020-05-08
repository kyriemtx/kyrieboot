$.validator.setDefaults({
    submitHandler : function() {
        getArea();
    }
});

var app = new Vue({
    el:"#app",
    data:{
        data: [],
        defaultProps: {
            children: 'children',
            label: 'menuName'
        },
        expandAll:true
    },
    methods:{
        validateRule:function () {
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules : {
                    provinceCode : {
                        required : true
                    }, cityName : {
                        required : true
                    }, shortName : {
                        required : true
                    }, cityCode : {
                        required : true
                    }
                },
                messages : {
                    provinceCode : {
                        required : icon + "请选择上级代码"
                    }, cityName : {
                        required : icon + "请输入城市名称"
                    }, shortName : {
                        required : icon + "请输入简称"
                    }, cityCode : {
                        required : icon + "请输入城市代码"
                    }
                }
            })
        }
    },
    mounted: function () {
        this.validateRule();
    }
});

function getArea(){
    var sysArea = {
        'cityCode':$("#cityCode").val(),
        "areaName":$("#areaName").val(),
        "areaCode":$("#areaCode").val(),
        'shortName':$("#shortName").val(),
        "dataState":$('input:radio:checked').val(),
        "memo":$("#memo").val(),
    };
    $.ajax({
        cache : true,
        type : "POST",
        url : context + 'area/addArea',
        data :JSON.stringify(sysArea),
        dataType : 'json',
        contentType:'application/json',
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.respCode == 200) {
                if (data.respData.code == 200){
                    parent.layer.msg("操作成功");
                } else if (data.respData.code == 501){
                    parent.layer.msg("该实体已存在，操作失败");
                } else if (data.respData.code == 500){
                    parent.layer.msg("操作失败");
                }
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        }
    });
}



function getCityCodes() {
    var cityCodesUrl = "http://localhost:8800/kyrie/city/areaSelect";
    $.ajax({
        url: cityCodesUrl,
        dataType: 'json',
        success : function(data) {
            for(var i = 0;i<data.respData.data.length; i++){
                var value = data.respData.data[i].cityCode;
                var lable = data.respData.data[i].cityName;
                $('#cityCode').append("<option value="+value+">"+lable+"</option>");
            }
        },
        error : function(data) {
            console.log("下拉框数据加载失败")
        }
    })
}