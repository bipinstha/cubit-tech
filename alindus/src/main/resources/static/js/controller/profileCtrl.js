'use strict';
myApp.controller('ProfileCtrl',['$scope','UserService','$rootScope',function ($scope, UserService, $rootScope) {
	
	UserService.currentUser().then(function (response) {
		$scope.user = response.data;
	})
	$scope.success = true;
	$scope.updateUser = function (user) {
		console.log(user);
		UserService.updateUser(user).then(function (response){
			$rootScope.currentUser = response.data;
			$scope.success = true;
		})
	}
	$scope.closeAlert = function () {
		$scope.success = false;
		$scope.error = false;
	}
	$scope.cp_data= {
			email: $scope.currentUser.email,
			oldPassword: "",
			password: "",
			rePassword: "" 
	}
	$scope.errMessage = "";
	$scope.changePassword = function (data) {
		UserService.changePassword(data).then(function(response){
			$scope.success = true;
			console.log(response);
		},function (errResponse){
			$scope.errMessage = errResponse.data.message;
			$scope.error = true;
		})
	}
}])