var vm = new Vue({
    el: '#app',
    data: {
        tableData: [],
        total: 50,
        page_size: 10,
        current_page: 1
    },
    methods: {
        addArea:function() {
            layer.open({
                type: 2,
                title: '新增',
                maxmin: true,
                shadeClose: false,
                area: ['800px', '520px'],
                content: context + 'area/add',
                end: function () {
                    vm.getArea();
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
                content: context + 'area/update?areaCode='+row.areaCode,
                end: function () {
                    vm.getArea();
                }
            });
        },
        handleDelete:function(row,tableData) {
            layer.confirm("您确定要删除吗？", function (index) {
                $.ajax({
                    url: context + 'area/deleteArea?areaCode=' + row.areaCode,
                    type: 'GET',
                    success: function (res) {
                        if (res.respCode == 200){
                            if (res.respData.code == 200){
                                layer.msg("操作成功");
                                vm.getArea();
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
            this.getArea();
        },
        handleCurrentChange: function (val) {
            vm.current_page = val;
            this.getArea();
        },

        getArea: function () {
            $.ajax({
                url: context + 'area/getAreaInfo?page=' + this.current_page + '&page_size=' + this.page_size,
                type: 'GET',
                success: function (res) {
                    vm.tableData = res.respData.sysAreaList;
                    vm.total = res.respData.total;
                    vm.page_size = res.respData.page_size;
                    vm.current_page = res.respData.page;
                }
            });
        }
    },
    mounted: function () {
        this.getArea();
    }
});