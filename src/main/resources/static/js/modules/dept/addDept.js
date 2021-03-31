$.validator.setDefaults({
    submitHandler : function() {
        addDept();
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
        getCheckedKeys:function () {
            return this.$refs.tree.getCheckedKeys();
        },
        getHalfCheckedKeys:function (value) {
            return this.$refs.tree.getHalfCheckedKeys().concat(value);
        },
        validateRule:function () {
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules : {
                    name : {
                        required : true
                    }, authority : {
                        required : true
                    }
                },
                messages : {
                    name : {
                        required : icon + "请输入角色名称"
                    }, authority : {
                        required : icon + "请输入角色权限"
                    }
                }
            })
        }
    },
    mounted: function () {
        this.validateRule();
    }
});

function getDeptInfo(){
    $.ajax({
        cache : true,
        type : "GET",
        url : context + 'dept/deptList',
        success : function(data) {
            if (data.respCode == 200) {
                for (var i = 0; i < data.respData.length; i++) {
                    $("#parentName").append((new Option(data.respData[i].deptName )));
                }
            }
        }
    });
}


function addDept(){
    var sysDept = {
        'deptName':$("#deptName").val(),
        "parentName":$("#parentName").val(),
        "leader":$("#leader").val(),
        "phone":$("#phone").val(),
        "email":$("#email").val(),
        "status":$('input:radio[name="status"]:checked').val(),
    };
    $.ajax({
        cache : true,
        type : "POST",
        url : context + 'dept/addDept',
        data :JSON.stringify(sysDept),
        dataType : 'json',
        contentType:'application/json',
        success : function(data) {
            if (data.respCode == 200) {
                if (data.respData.code == 200){
                    parent.layer.msg("操作成功");
                } else if (data.respData.code == 501){
                    parent.layer.msg("该部门已存在，操作失败");
                } else if (data.respData.code == 500){
                    parent.layer.msg("操作失败");
                }
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        }
    });
}