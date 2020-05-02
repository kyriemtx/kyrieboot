var vm = new Vue({
    el: '#app',
    data: {
        tableData: [],
        total: 50,
        page_size: 10,
        current_page: 1
    },
    methods: {
        addCity:function() {
            layer.open({
                type: 2,
                title: '新增',
                maxmin: true,
                shadeClose: false,
                area: ['800px', '520px'],
                content: context + 'city/add',
                end: function () {
                    vm.getCity();
                }
            });
        },
        handleEdit: function(row) {
            console.log(row);
            layer.open({
                type: 2,
                title: '编辑',
                maxmin: true,
                shadeClose: false,
                area: ['800px', '520px'],
                content: context + 'city/update?cityCode='+row.cityCode,
                end: function () {
                    vm.getCity();
                }
            });
        },

        citySearch:function() {
            var sysCity = {
                'provinceName' : $('#provinceName').val(),
                'cityName' : $('#cityName').val(),
                'dataState':$('#dataState option:selected').val()
            };
            $.ajax({
                cache : true,
                type : "GET",
                url : context + 'city/selectForm',
                data : sysCity,
                dataType : 'json',
                contentType:'application/json',
                success: function (res) {
                    vm.tableData = res.respData.sysCityList;
                }
            })
        },

        handleDelete:function(row,tableData) {
            layer.confirm("您确定要删除吗？", function (index) {
                $.ajax({
                    url: context + 'city/deleteCity?cityCode=' + row.cityCode,
                    type: 'GET',
                    success: function (res) {
                        if (res.respCode == 200){
                            if (res.respData.code == 200){
                                layer.msg("操作成功");
                                vm.getCity();
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
            this.getCity();
        },
        handleCurrentChange: function (val) {
            vm.current_page = val;
            this.getCity();
        },

        getCity: function () {
            $.ajax({
                url: context + 'city/getCityInfo?page=' + this.current_page + '&page_size=' + this.page_size,
                type: 'GET',
                success: function (res) {
                    vm.tableData = res.respData.sysCityList;
                    vm.total = res.respData.total;
                    vm.page_size = res.respData.page_size;
                    vm.current_page = res.respData.page;
                }
            });
        }
    },
    mounted: function () {
        this.getCity();
    }
});