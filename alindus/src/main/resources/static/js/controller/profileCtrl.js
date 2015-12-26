'use strict';
myApp.controller('ProfileCtrl',['$scope','UserService','$rootScope','$cookies',function ($scope, UserService, $rootScope,$cookies) {
	
	console.log("LOADED******Profile Controller");
	UserService.currentUser().then(function (response) {
		$scope.user = response.data;
	})
	$scope.success = true;
	$scope.updateUser = function (user) {
		UserService.updateUser(user).then(function (response){
			$rootScope.currentUser = response.data;
			$cookies.put('currentUser', response.data);
			$scope.success = true;
		})
	}
	
	$scope.closeAlert = function () {
		$scope.success = false;
		$scope.error = false;
	}
	
	$scope.cp_data= {
			email: $cookies.get('currentUser').email,
			oldPassword: "",
			password: "",
			rePassword: "" 
	}
	$scope.errMessage = "";
	$scope.changePassword = function (data) {
		UserService.changePassword(data).then(function(response){
			$scope.success = true;
			$scope.cp_data.oldPassword = "";
			$scope.cp_data.password = "";
			$scope.cp_data.rePassword = "";
		},function (errResponse){
			$scope.errMessage = errResponse.data.message;
			$scope.error = true;
		})
	}
}])