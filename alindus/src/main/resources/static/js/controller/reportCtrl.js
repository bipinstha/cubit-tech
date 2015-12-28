'use strict';
myApp.controller('ReportCtrl',['$scope','UserService','$rootScope','$cookies','InterviewService',function ($scope, UserService, $rootScope,$cookies,InterviewService) {
	
	console.log("LOADED******Report Controller");
	
	InterviewService.getAllInterview().then(function(response){
		$scope.interviews = response.data;
		console.log($scope.interviews);
	})
	
	$scope.searchFilter = '';
	
}])