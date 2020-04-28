

function getProvinces() {
    var provinceCodesUrl = "http://localhost:9999/kyrie/province/citySelect";
    $.ajax({
        url: provinceCodesUrl,
        dataType: 'json',
        success : function(data) {
            for(var i = 0;i<data.respData.data.length; i++){
                var value = data.respData.data[i].provinceCode;
                var lable = data.respData.data[i].provinceName;
                $('#provinceCode').append("<option value="+value+">"+lable+"</option>");
            }
        },
        error : function(data) {
            console.log("下拉框数据加载失败")
        }
    })
}

function selectCityByProvinceCode() {
    var cityCodesUrl = "http://localhost:9999/kyrie/city/selectCitiesByProvinceCode";
    var provinceCode = document.getElementById("province");

    $.ajax({
        url: cityCodesUrl,
        dataType: 'json',
        data:provinceCode,
        success : function(data) {
            debugger;
            for(var i = 0;i<data.respData.data.length; i++){
                var value = data.respData.data[i].cityCode;
                var lable = data.respData.data[i].cityName;
                $('#provinceCode').append("<option value="+value+">"+lable+"</option>");
            }
        },
        error : function(data) {
            console.log("下拉框数据加载失败")
        }
    })
}