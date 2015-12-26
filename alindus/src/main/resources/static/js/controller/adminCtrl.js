myApp.controller('AdminCtrl', [ '$scope', '$http', 'UserService',
		function($scope, $http, UserService) {

			// fetch all users
			UserService.getAllUser().then(function(response) {
				console.log(response);
				$scope.users = response.data;
			});

			UserService.getUnApprovedUsers().then(function(response) {
				console.log(response);
				$scope.unApprovedUsers = response.data;
			})

			UserService.currentUser().then(function(response) {
				console.log(response);
				$scope.user = response.data;
			})
			
			//disable user
			$scope.disableUser = function(user) {
				user.enable = false;
				
				UserService.updateUser(user).then(function(response) {
					var index = $scope.users.indexOf(user);
					$scope.users.splice(index, 1);

					$scope.unApprovedUsers.push(user);
				})
			};
			//enable user
			$scope.enableUser = function(user) {
				user.enable = true;
				
				UserService.updateUser(user).then(function(response) {
					var index = $scope.unApprovedUsers.indexOf(user);
					$scope.unApprovedUsers.splice(index, 1);

					$scope.users.push(user);
				})
				
			};
			//delete user
			$scope.deleteUser = function(user) {
				UserService.updateUser(user).then(function(response) {
					var index = $scope.users.indexOf(user);
					$scope.users.splice(index, 1);

					$scope.unApprovedUsers.push(user);
				})
			};
			
			//sorting
			
			$scope.sortType = 'firstName';
			$scope.sortReverse = false;
			$scope.searchFilter = '';
			
			$scope.searchFilterDisabledUsers= '';

		} ]);