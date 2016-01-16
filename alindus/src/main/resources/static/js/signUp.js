'use strict';


var signUp = angular.module('cubicAppSignUp', [ 'ui.bootstrap']);

signUp.controller('SignUpController',['$scope','$http', '$q',function ($scope, $http, $q){
	$scope.user = {};
	$scope.success = false;
	$scope.error = false;
	$scope.closeSuccessAlert = function () {
		$scope.success = false;
	}
	$scope.closeErrorAlert = function () {
		$scope.error = false;
	}
	$scope.createUser = function (user) {
		console.log(user);
		return $http.post('/secure/user/add', user)
        .then(
                function(response){
                	console.log(response);
                	$scope.success = true;
                	$scope.user = {};
                    return response.data;
                }, 
                function(errresponse){
                    console.error('error while creating user');
                    $scope.error = true;
                    $scope.errorMessage = errresponse.data.message;
                    return $q.reject(errresponse);
                }
        );
	}
}]);

