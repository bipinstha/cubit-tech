'use strict';
myApp.controller('ProfileCtrl',['$scope','UserService','$rootScope',function ($scope, UserService, $rootScope) {
	$scope.user = $rootScope.currentUser;
	console.log($scope.user);
}])