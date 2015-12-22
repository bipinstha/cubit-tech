'use strict';
myApp.controller('UserCtrl',['$scope','$routeParams','UserService',function ($scope, $routeParams, UserService) {
	$scope.email = $routeParams.email;
	$scope.user = {};
	UserService.findUserByEmail($scope.email).then(function (response) {
		$scope.user = response;
	})
	
}])