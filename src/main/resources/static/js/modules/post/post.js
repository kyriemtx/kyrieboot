var vm = new Vue({
    el: '#app',
    data: {
        tableData: [],
        total: 50,
        page_size: 10,
        current_page: 1
    },
    methods: {
        addPost:function() {
            layer.open({
                type: 2,
                title: '新增',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['800px', '520px'],
                content: context + 'post/add',
                end: function () {
                    vm.getPostList();
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
                content: context + 'post/update?postId='+row.postId,
                end: function () {
                    vm.getPostList();
                }
            });
        },
        postSearch:function() {
            var sysPost = {
                'postName':$("#postName").val(),
                'postStatus':$('#postStatus option:selected').val()
            };
            $.ajax({
                cache : true,
                type : "GET",
                url : context + 'post/selectForm',
                data : sysPost,
                dataType : 'json',
                contentType:'application/json',
                success: function (res) {
                    vm.tableData = res.respData.sysPostList;
                }
            })
        },
        handleDelete:function(row,tableData) {
            layer.confirm("您确定要删除吗？", function (index) {
                $.ajax({
                    url: context + 'post/deletePost?postId=' + row.postId,
                    type: 'GET',
                    success: function (res) {
                        if (res.respCode == 200){
                            if (res.respData.code == 200){
                                layer.msg("操作成功");
                                vm.getPostList();
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
            this.getPostList();
        },
        handleCurrentChange: function (val) {
            vm.current_page = val;
            this.getPostList();
        },

        getPostList: function () {
            $.ajax({
                url: context + 'post/getPostInfo?page=' + this.current_page + '&page_size=' + this.page_size,
                type: 'GET',
                success: function (res) {
                    vm.tableData = res.respData.sysPostList;
                    vm.total = res.respData.total;
                    vm.page_size = res.respData.page_size;
                    vm.current_page = res.respData.page;
                }
            });
        }
    },
    mounted: function () {
        this.getPostList();
    }
});