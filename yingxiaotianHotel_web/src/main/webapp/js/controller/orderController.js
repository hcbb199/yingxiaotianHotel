 //控制层 
app.controller('orderController' ,function($scope,$controller,$location,orderService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		orderService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	};
	
	//分页
	$scope.findPage=function(page,rows){			
		orderService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	};
	
	//查询实体 
	$scope.findOne=function(id){				
		orderService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	};
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=orderService.update( $scope.entity ); //修改  
		}else{
			serviceObject=orderService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	};
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		orderService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	};
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		orderService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	};

	$scope.order = {roomId:0,customerName:"",customerIdCard:"",checkoutDate:new Date()};
	$scope.getRoomParam = function () {
		$scope.order.roomId = $location.search().roomId;
		$scope.roomNumber = $location.search().roomNumber;
		$scope.roomPicture = $location.search().roomPic;
		$scope.roomTypeId= $location.search().roomTypeId;
		$scope.roomPrice=$location.search().roomPrice;

	};

    //办理入住
	$scope.createOrder = function () {
		$scope.order.checkoutDate  = $scope.checkoutDate;

		orderService.checkin($scope.order ).success(

			function (response) {
				if (response.success) {
					alert("入住成功!");
					location.href = "home.html";
				} else {
					alert(response.message);
				}
			}
		)
	};

   //根据typeid查找房间类型
    $scope.getRoomType = function () {
		orderService.getRoomType($scope.roomTypeId).success(
			function (response) {
				$scope.roomType = response.roomTypeDesc;
			}
		)
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
/*
* adminId: 2,
checkinDate: "2019-09-19 22:19:54",
checkoutDate: "2019-09-21 22:19:58",
createTime: "2019-09-19 22:20:12",
customerIdcard: "610422422260117878",
customerName: "shadiao",
orderId: 3,
orderStatus: 1,
roomId: 2
*
* */
	$scope.order = {orderId:0,checkoutDate:new Date};


	$scope.getOrderParam = function () {
		$scope.order.orderId = $location.search().orderId;
		$scope.order.checkoutDate = $location.search().checkoutDate;

		$scope.initTime = JSON.parse(JSON.stringify($scope.order.checkoutDate));
	};
    //办理续住
	$scope.changeDate = function () {
		orderService.changeDate($scope.order.orderId,$scope.order.checkoutDate).success(
			function (response) {
				if (response.success) {
					alert(response.message);
					location.href="home.html";
				} else {
					alert(response.message);
				}
			}
		)
	};
    //根据typeid查找房间类型
	$scope.getRoomTypeInOrder = function () {
		orderService.getRoomType($scope.room.roomTypeId).success(
			function (response) {
				$scope.roomTypeInOrder = response.roomTypeDesc;
			}
		)
	};

});	
