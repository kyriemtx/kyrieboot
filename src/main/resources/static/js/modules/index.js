var vm = new Vue({
    el: '#LAY_app',
    data: {
        nickName: '',
        menuList: ''
    },
    methods: {
        logout: function () {
            var localObj = window.location;
            var contextPath = localObj.pathname.split("/")[1];
            location.href = 'http://' + location.host + "/" + contextPath +"/logout";
        }
    },
    mounted: function () {
        $.ajax({
            url: 'menu/getMenulist',
            type: 'GET',
            success: function (res) {
                vm.nickName = res.respData.nickName;
                vm.menuList = res.respData.menuList;
            }
        })
    }

});