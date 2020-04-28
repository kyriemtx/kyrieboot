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
                area: ['800px', '520px'],
                content: context + 'province/add',
                end: function () {
                    vm.getProvince();
                }
            });
        },
        handleEdit: function(row) {
            console.log(row);
            layer.open({
                type: 2,
                title: '编辑',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: context + 'province/update?provinceCode='+row.provinceCode,
                end: function () {
                    vm.getProvince();
                }
            });
        },
        handleDelete:function(row,tableData) {
            layer.confirm("您确定要删除吗？", function (index) {
                $.ajax({
                    url: context + 'province/deleteProvince?Id=' + row.id,
                    type: 'GET',
                    success: function (res) {
                        if (res.respCode == 200){
                            if (res.respData.code == 200){
                                layer.msg("操作成功");
                                vm.getProvince();
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
            this.getProvince();
        },
        handleCurrentChange: function (val) {
            vm.current_page = val;
            this.getProvince();
        },

        getProvince: function () {
            $.ajax({
                url: context + 'province/getProvinceInfo?page=' + this.current_page + '&page_size=' + this.page_size,
                type: 'GET',
                success: function (res) {
                    vm.tableData = res.respData.sysProvinceList;
                    vm.total = res.respData.total;
                    vm.page_size = res.respData.page_size;
                    vm.current_page = res.respData.page;
                }
            });
        }
    },
    mounted: function () {
        this.getProvince();
    }
});