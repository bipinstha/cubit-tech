myApp.controller('AdminCtrl', [ '$scope','$http','UserService', function($scope,$http, UserService) {
	
	//fetch all users
	UserService.getAllUser().then(function(response){
		$scope.users = response.data;
    });
	console.log($scope.users);
} ])