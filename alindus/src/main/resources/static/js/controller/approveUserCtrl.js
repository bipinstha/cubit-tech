myApp.controller('ApproveUserCtrl', [ '$scope', '$http', 'UserService',
		'$routeParams', function($scope, $http, UserService, $routeParams) {

			console.log("LOADED******Approve User Controller");
			$scope.email = $routeParams.email;

			UserService.findUserByEmail($scope.email).then(function(response) {
				$scope.user = response;
				console.log("User to be approved::");
				console.log($scope.user);
			})

			UserService.getListOfRoles().then(function(response) {
				$scope.listOfRoles = response.data;
				console.log($scope.listOfRoles);
			})

			$scope.approveUser = function(user) {
				console.log(user);
				if (user.role != "") {
					user.enable = true;
					UserService.approveUser(user).then(function(response) {
						$scope.success = true;
					})
				} else {
					// show error message
				}

			}

		} ]);