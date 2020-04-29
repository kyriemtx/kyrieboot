var vm = new Vue({
    el: '#app',
    data: {
        tableData: [],
        total: 50,
        page_size: 10,
        current_page: 1
    },
    methods: {
        addStreet:function() {
            layer.open({
                type: 2,
                title: '新增',
                maxmin: true,
                shadeClose: false,
                area: ['800px', '520px'],
                content: context + 'street/add',
                end: function () {
                    vm.getStreet();
                }
            });
        },
        handleEdit: function(row) {
            layer.open({
                type: 2,
                title: '编辑',
                maxmin: true,
                shadeClose: false,
                area: ['800px', '520px'],
                content: context + 'street/update?streetCode='+row.streetCode,
                end: function () {
                    vm.getStreet();
                }
            });
        },
        handleDelete:function(row,tableData) {
            layer.confirm("您确定要删除吗？", function (index) {
                $.ajax({
                    url: context + 'street/deleteStreet?streetCode=' + row.streetCode,
                    type: 'GET',
                    success: function (res) {
                        if (res.respCode == 200){
                            if (res.respData.code == 200){
                                layer.msg("操作成功");
                                vm.getStreet();
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
            this.getStreet();
        },
        handleCurrentChange: function (val) {
            vm.current_page = val;
            this.getStreet();
        },

        getStreet: function () {
            $.ajax({
                url: context + 'street/getStreetInfo?page=' + this.current_page + '&page_size=' + this.page_size,
                type: 'GET',
                success: function (res) {
                    vm.tableData = res.respData.sysStreetList;
                    vm.total = res.respData.total;
                    vm.page_size = res.respData.page_size;
                    vm.current_page = res.respData.page;
                }
            });
        }
    },
    mounted: function () {
        this.getStreet();
    }
});