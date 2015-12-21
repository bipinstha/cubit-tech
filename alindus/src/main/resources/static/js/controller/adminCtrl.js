myApp.controller('AdminCtrl', [ '$scope','$http','UserService', function($scope,$http, UserService) {
	
	//fetch all users
	UserService.getAllUser().then(function(response){
		console.log(response);
		$scope.users = response.data;
    });
	
	UserService.getUnApprovedUsers().then(function(response){
		console.log(response);
		$scope.unApprovedUsers = response.data;
	})
	
	UserService.currentUser().then(function (response){
		console.log(response);
		$scope.user = response.data;
	})
}]);