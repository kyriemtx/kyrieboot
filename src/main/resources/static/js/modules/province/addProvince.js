$.validator.setDefaults({
    submitHandler : function() {
        addPost();
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
                    }, provinceName : {
                        required : true
                    }, shortName : {
                        required : true
                    }
                },
                messages : {
                    provinceCode : {
                        required : icon + "请输入省/直辖市代码"
                    }, provinceName : {
                        required : icon + "请输入省/直辖市名称"
                    }, shortName : {
                        required : icon + "请输入简称"
                    }
                }
            })
        }
    },
    mounted: function () {
        this.validateRule();
    }
});

function addPost(){
    var sysPost = {
        'provinceCode':$("#provinceCode").val(),
        "provinceName":$("#provinceName").val(),
        'shortName':$("#shortName").val(),
        "dataState":$('input:radio:checked').val(),
        "memo":$("#memo").val(),
    };
    $.ajax({
        cache : true,
        type : "POST",
        url : context + 'province/addProvince',
        data :JSON.stringify(sysPost),
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
                    parent.layer.msg("该岗位已存在，操作失败");
                } else if (data.respData.code == 500){
                    parent.layer.msg("操作失败");
                }
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        }
    });
}