'use strict';
myApp.controller('DashboardCtrl',['$scope','$routeParams','UserService','$rootScope',function ($scope, $routeParams, UserService, $rootScope) {
	$scope.email = $routeParams.email;
	$scope.user = {};
	UserService.currentUser().then(function (response) {
		$rootScope.currentUser = response.data;
	})
}])