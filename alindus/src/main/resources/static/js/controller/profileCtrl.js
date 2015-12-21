'use strict';
myApp.controller('ProfileCtrl',['$scope','UserService','$rootScope',function ($scope, UserService, $rootScope) {
	
	UserService.currentUser().then(function (response) {
		$scope.user = response.data;
	})
	$scope.success = false;
	$scope.updateUser = function (user) {
		console.log(user);
		UserService.updateUser(user).then(function (response){
			$rootScope.currentUser = response.data;
			$scope.success = true;
		})
	}
	$scope.closeAlert = function () {
		$scope.success = !$scope.success;
	}
}])