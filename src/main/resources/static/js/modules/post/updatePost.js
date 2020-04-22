$.validator.setDefaults({
    submitHandler : function() {
        updatePost();
    }
});

var postId=$("#postId").val();
debugger
var app = new Vue({
    el:"#app",
    data:{
        data: [],
        defaultProps: {
            children: 'children',
            label: 'menuName'
        },
        expandAll:true,
    },
    methods:{
       /* getData: function() {
            $.ajax({
                cache : true,
                type : "GET",
                url : context + 'post/getPost',
                data:{
                    "postId":postId
                },
                error : function(request) {
                    parent.layer.alert("Connection error");
                },
                success : function(data) {
                    if (data.respCode == 200) {
                        app.data = data.respData.data;
                    }
                }
            });
        },*/
        validateRule:function () {
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules : {
                    postId : {
                        required : true
                    }, postName : {
                        required : true
                    }, postCode : {
                        required : true
                    }, postSort : {
                        required : true
                    }, postStatus : {
                        required : true
                    }
                },
                messages : {
                    postName : {
                        required : icon + "请输入岗位名称"
                    }, postCode : {
                        required : icon + "请输入岗位编号"
                    }, postSort : {
                        required : icon + "请输入显示顺序"
                    }, postStatus : {
                        required : icon + "请选择岗位状态"
                    }
                }
            })
        }
    },
    mounted:function () {
       /* this.getData();*/
        this.validateRule();
    }
});

function updatePost(){
    var sysPost = {
        'postId':$("#postId").val(),
        'postName':$("#postName").val(),
        "postCode":$("#postCode").val(),
        "postSort":$("#postSort").val(),
        "postStatus":$('input:radio:checked').val(),
        "remark":$("#remark").val(),
    };
    $.ajax({
        cache : true,
        type : "POST",
        url : context + 'post/updatePost',
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
                } else if (data.respData.code == 500){
                    parent.layer.msg("操作失败");
                }
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            }
        }
    });
}