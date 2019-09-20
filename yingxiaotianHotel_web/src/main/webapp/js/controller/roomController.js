//控制层
app.controller('roomController', function ($scope, $controller, $interval, uploadService, roomService, orderService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        roomService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    };

    //分页
    $scope.findPage = function (page, rows) {
        roomService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    };

    //查询实体
    $scope.findOne = function (id) {
        roomService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    };

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = roomService.update($scope.entity); //修改
        } else {
            serviceObject = roomService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    $scope.reloadList();//重新加载
                } else {
                    alert(response.message);
                }
            }
        );
    };


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        roomService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    };

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        roomService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    };

    $scope.roomsList = {};
    //查询所有房间及其当前订单
    $scope.findAllRoomAndOrder = function () {
        roomService.findAllRoomAndOrder().success(
            function (response) {
                $scope.roomsList = response;
                if ($scope.roomsList != null && $scope.roomsList.length > 0) {
                    $scope.roomsListLength = $scope.roomsList.length;
                    $scope.empty = 0;
                    $scope.full = 0;
                    for (var i = 0; i < $scope.roomsList.length; i++) {
                        var room = $scope.roomsList[i];
                        if (room.tbRoom.roomAvailable == 1) {
                            $scope.empty++;
                        } else {
                            $scope.full++;
                        }

                    }
                }

            }
        );
    };
    $scope.copy = JSON.parse(JSON.stringify($scope.roomsList));
    $scope.selectEmpty = function () {
        $scope.roomsList = [];
        for (var i = 0; i < $scope.copy.length; i++) {
            if ( $scope.copy[i]["order"]==null) {
                $scope.roomsList = $scope.roomsList.append($scope.copy[i]);
            }

        }
        return $scope.roomsList;
    };

    time = $interval(function () {
        if ($scope.roomsList != null && $scope.roomsList.length > 0) {
            for (var i = 0; i < $scope.roomsList.length; i++) {
                if (($scope.roomsList[i].leftTime--) > 0) {
                    $scope.roomsList[i].leftTime = convertTimeString($scope.roomsList[i].leftTime);//转换时间字符串
                } else {
                    $interval.cancel(time);

                }
            }
        }
    }, 1000);


    //转换秒为   天小时分钟秒格式  XXX天 10:22:33
    $scope.convertTimeString = function (allsecond) {
        var days = Math.floor(allsecond / (60 * 60 * 24));//天数
        var hours = Math.floor((allsecond - days * 60 * 60 * 24) / (60 * 60));//小数数
        var minutes = Math.floor((allsecond - days * 60 * 60 * 24 - hours * 60 * 60) / 60);//分钟数
        var seconds = allsecond - days * 60 * 60 * 24 - hours * 60 * 60 - minutes * 60; //秒数
        var timeString = "";
        if (days > 0) {
            timeString = days + "天 ";
        }
        return timeString + hours + " 小时 " + minutes + " 分钟 " + seconds + " 秒";
    };

    //获取房间类型
    $scope.getRoomTypeList = function () {
        roomService.getRoomTypeList().success(
            function (response) {
                $scope.roomTypeList = response;
                //alert($scope.roomTypeList)
            }
        )
    };


    $scope.newRoom = {roomPic: ""};
    //上传图片
    $scope.uploadFile = function () {
        uploadService.uploadFile().success(
            function (response) {
                if (response.success) {
                    //alert(response.message);
                    //如果上传成功, 取出url, 设置文件地址
                    $scope.newRoom.roomPic = response.message;

                } else {
                    alert(response.message);
                }
            }).error(function () {
            alert("上传发生错误");
        });
    };

    $scope.saveNewRoom = function () {
        roomService.saveNewRoom($scope.newRoom).success(
            function (response) {
                if (response.success) {
                    //重新查询
                    location.href = "home.html";
                } else {
                    alert(response.message);
                }
            }
        );
    };
    //办理入住
    $scope.getCheckinParam = function (roomId, roomPic, roomNumber, roomTypeId, roomPrice) {
        /*alert(roomNumber );
        alert( roomId );
        alert( roomPic);*/

        location.href = "http://localhost:9201/admin/checkin.html#?roomId=" + roomId + "&roomPic=" + roomPic + "&roomNumber=" + roomNumber + "&roomTypeId=" + roomTypeId + "&roomPrice=" + roomPrice;
    };
    //办理续住
    $scope.changeDate = function (orderId, checkoutDate) {
        location.href = "continue.html#?orderId=" + orderId + "&checkoutDate=" + checkoutDate;
    };

    //办理退住
    $scope.checkout = function (roomId) {
        orderService.checkout(roomId).success(
            function (response) {
                if (response.success) {
                    alert(response.message);
                    location.reload();
                } else {
                    alert(response.message);
                }
            }
        )

    };


});
