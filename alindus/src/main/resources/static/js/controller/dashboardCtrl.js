'use strict';
myApp.controller('DashboardCtrl',['$scope','$routeParams','UserService','$rootScope',function ($scope, $routeParams, UserService, $rootScope) {
	$scope.email = $routeParams.email;
	$scope.user = {};
	UserService.currentUser($scope.email).then(function (response) {
		$rootScope.currentUser = response.data;
	})
}])