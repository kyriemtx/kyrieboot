var vm = new Vue({
    el: '#app',
    data: {
        tableData: [],
        total: 50,
        page_size: 10,
        current_page: 1
    },
    methods: {
        addNotice:function() {
            layer.open({
                type: 2,
                title: '新增',
                maxmin: true,
                shadeClose: false,
                area: ['600px', '520px'],
                content: context + 'notice/add',
                end: function () {
                    vm.getNotice();
                }
            });
        },
        handleEdit: function(row) {
            layer.open({
                type: 2,
                title: '编辑',
                maxmin: true,
                shadeClose: false,
                area: ['600px', '520px'],
                content: context + 'notice/update?id='+row.id,
                end: function () {
                    vm.getNotice();
                }
            });
        },
        noticeSearch:function() {
            var sysNotice = {
                'noticeType':$('#noticeType option:selected').val(),
                'status':$('#status option:selected').val()
            };
            $.ajax({
                cache : true,
                type : "GET",
                url : context + 'notice/selectForm',
                data : sysNotice,
                dataType : 'json',
                contentType:'application/json',
                success: function (res) {
                    vm.tableData = res.respData.sysNoticeList;
                }
            })
        },
        handleDelete:function(row,tableData) {
            layer.confirm("您确定要删除吗？", function (index) {
                $.ajax({
                    url: context + 'notice/deleteNotice?id=' + row.id,
                    type: 'GET',
                    success: function (res) {
                        if (res.respCode == 200){
                            if (res.respData.code == 200){
                                layer.msg("操作成功");
                                vm.getNotice();
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
            this.getNotice();
        },
        handleCurrentChange: function (val) {
            vm.current_page = val;
            this.getNotice();
        },

        getNotice: function () {
            $.ajax({
                url: context + 'notice/getNoticeInfo?page=' + this.current_page + '&page_size=' + this.page_size,
                type: 'GET',
                success: function (res) {
                    vm.tableData = res.respData.sysNoticeList;
                    vm.total = res.respData.total;
                    vm.page_size = res.respData.page_size;
                    vm.current_page = res.respData.page;
                }
            });
        }
    },
    mounted: function () {
        this.getNotice();
    }
});