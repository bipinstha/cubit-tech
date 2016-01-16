myApp.controller('AdminCtrl', [
		'$scope',
		'$http',
		'UserService',
		function($scope, $http, UserService) {

			console.log("LOADED******Admin Controller");
			// fetch all users
			UserService.getAllUser().then(function(response) {
				$scope.users = response.data;
			})
			// fetch unapproved users
			UserService.getUnApprovedUsers().then(function(response) {
				$scope.unApprovedUsers = response.data;
			})
			// fetch disabled users
			UserService.getDisabledUsers().then(function(response) {
				$scope.disabledUsers = response.data;
			})
			// fetch current user detail
			UserService.currentUser().then(function(response) {
				$scope.user = response.data;
			})

			// disable user
			$scope.disableUser = function(user) {
				user.enable = false;

				UserService.updateUser(user).then(function(response) {
					var index = $scope.users.indexOf(user);
					$scope.users.splice(index, 1);

					$scope.disabledUsers.push(user);
				})
			};
			// enable user
			$scope.enableUser = function(user) {
				user.enable = true;

				UserService.updateUser(user).then(function(response) {
					var index = $scope.disabledUsers.indexOf(user);
					$scope.disabledUsers.splice(index, 1);

					$scope.users.push(user);
				})
			};

			// delete user
			$scope.deleteUser = function(user) {
				UserService.removeUserByEmail(user.email).then(
						function(response) {
							var index = $scope.unApprovedUsers.indexOf(user);
							$scope.unApprovedUsers.splice(index, 1);
						})
			};

			// sorting

			$scope.sortType = 'firstName';
			$scope.sortReverse = false;
			$scope.searchFilter = '';

			$scope.searchFilterDisabledUsers = '';

		} ]);