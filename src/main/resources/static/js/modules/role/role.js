var vm = new Vue({
    el: '#app',
    data: {
        tableData: [],
        total: 50,
        page_size: 10,
        current_page: 1
    },
    methods: {
        addRole:function() {
            layer.open({
                type: 2,
                title: '新增',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['600px', '520px'],
                content: context + 'role/add',
                end: function () {
                    vm.getRoleList();
                }
            });
        },
        handleEdit: function(row) {
            console.log(row);
            layer.open({
                type: 2,
                title: '修改',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['600px', '520px'],
                content: context + 'role/update?name='+row.name+"&authority="+row.authority+"&id="+row.id, // iframe的url
                end: function () {
                    vm.getRoleList();
                }
            });
        },
        roleSearch:function() {
            var sysRole = {
                'name':$("#name").val(),
            };
            $.ajax({
                cache : true,
                type : "GET",
                url : context + 'role/selectForm',
                data : sysRole,
                dataType : 'json',
                contentType:'application/json',
                success: function (res) {
                    vm.tableData = res.respData.sysRoleList;
                }
            })
        },
        handleDelete:function(row,tableData) {
            layer.confirm("您确定要删除吗？", function (index) {
                $.ajax({
                    url: context + 'role/deleteRole?id=' + row.id,
                    type: 'GET',
                    success: function (res) {
                        if (res.respCode == 200){
                            if (res.respData.code == 200){
                                layer.msg("操作成功");
                                vm.getRoleList();
                            } else {
                                layer.msg("操作失败");
                            }
                        }
                    }
                });
            });
        },
        handleSizeChange: function (val) {
            vm.page_size = val;
            this.getRoleList();
        },
        handleCurrentChange: function (val) {
            vm.current_page = val;
            this.getRoleList();
        },

        getRoleList: function () {
            $.ajax({
                url: context + 'role/getRoleInfo?page=' + this.current_page + '&page_size=' + this.page_size,
                type: 'GET',
                success: function (res) {
                    vm.tableData = res.respData.sysRoleList;
                    vm.total = res.respData.total;
                    vm.page_size = res.respData.page_size;
                    vm.current_page = res.respData.page;
                }
            });
        }
    },
    mounted: function () {
        this.getRoleList();
    }
});