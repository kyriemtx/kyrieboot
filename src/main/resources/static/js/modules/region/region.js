var vm = new Vue({
    el: '#app',
    data: {
        tableData: [],
        total: 50,
        page_size: 10,
        current_page: 1
    },
    methods: {
        addRegion: function(){
            layer.open({
                type: 2,
                title: '新增',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: context + 'region/add',
                end: function () {
                    vm.getRegionList();
                }
            });
        },
        handleEdit: function(row) {
            layer.open({
                type: 2,
                title: '编辑',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: context + 'region/update?id='+row.id,
                end: function () {
                    vm.getRegionList();
                }
            });
        },
        handleDelete:function(row) {
            layer.confirm("您确定要删除吗？", function (index) {
                $.ajax({
                    url: context + 'region/deleteRegion?id=' + row.id,
                    type: 'GET',
                    success: function (res) {
                        if (res.respCode == 200){
                            if (res.respData.code == 200){
                                layer.msg("操作成功");
                                vm.getRegionList();
                            } else {
                                layer.msg("操作失败");
                            }

                        }
                    }
                });
            });
        },
        formatShow: function (row, column) {
            return row.isShow == true ? '启用' : '未启用';
        },
        handleSizeChange: function (val) {
            vm.page_size = val;
            this.getRegionList();
        },
        handleCurrentChange: function (val) {
            vm.current_page = val;
            this.getRegionList();
        },

        getRegionList: function () {
            $.ajax({
                url: context + 'region/getRegionInfo?page=' + this.current_page + '&page_size=' + this.page_size,
                type: 'GET',
                success: function (res) {
                    debugger;
                    vm.tableData = res.respData.regionList;
                    vm.total = res.respData.total;
                    vm.page_size = res.respData.page_size;
                    vm.current_page = res.respData.page;
                }
            });
        }

    },
    mounted: function () {
        this.getRegionList();
    }
});
