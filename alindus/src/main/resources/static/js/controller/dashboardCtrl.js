'use strict';

myApp.controller('DashboardCtrl',['$scope','$routeParams','UserService','$rootScope','$cookies','ApplicationService',function ($scope, $routeParams, UserService, $rootScope,$cookies,ApplicationService) {
	console.log("LOADED******Dashboard  Controller");
	
	$scope.user = {};
	
	UserService.currentUser().then(function (response) {
		$rootScope.currentUser = response.data;
		$cookies.put('currentUser', response.data);
		$scope.roleAdmin = ($rootScope.currentUser.role=='ROLE_ADMIN')?true:false;
	})
	
	ApplicationService.getAllClients().then(function(response){
		$scope.listOfClients = response.data;
		console.log(response.data);
	})
	
	ApplicationService.getAllUser().then(function(response){
		$scope.listOfUsers = response.data;
	})
	
//	ApplicationService.getAllCandidate().then(function(response){
//		$scope.listOfCandidates = response.data;
//		console.log(response.data);
//	})
	
	ApplicationService.getAllVendors().then(function(response){
		$scope.listOfVendors = response.data;
	})
	ApplicationService.getAllTechnologies().then(function(response){
		$scope.listOfTechnologies = response.data;
	})
	ApplicationService.getAllRounds().then(function(response){
		$scope.listOfRounds = response.data;
	})
	ApplicationService.getAllInterviewType().then(function(response){
		$scope.listOfInterviewTypes = response.data;
	})
	ApplicationService.getAllStatus().then(function(response){
		$scope.listOfStatus = response.data;
	})
	ApplicationService.getAllUsersByRole('ROLE_VC').then(function(response){
		$scope.listOfVc = response.data;
	})
	ApplicationService.getAllUsersByRole('ROLE_MARKETING').then(function(response){
		$scope.listOfMarketingUsers = response.data;
	})
	ApplicationService.getAllUsersByRole('ROLE_CALLTAKER').then(function(response){
		$scope.listOfCallTakerUsers = response.data;
	})
	
}])
