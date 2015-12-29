'use strict';
myApp.controller('DashboardCtrl',['$scope','$routeParams','UserService','$rootScope','$cookies',function ($scope, $routeParams, UserService, $rootScope,$cookies) {
	console.log("LOADED******Dashboard  Controller");
	
	$scope.email = $routeParams.email;
	
	
	$scope.user = {};
	UserService.currentUser().then(function (response) {
		$rootScope.currentUser = response.data;
		$cookies.put('currentUser', response.data);
		$scope.roleAdmin = ($rootScope.currentUser.role=='ROLE_ADMIN')?true:false;
	})
	
	//List of Clients
}])
