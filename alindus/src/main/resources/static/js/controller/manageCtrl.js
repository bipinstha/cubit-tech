myApp.controller('ManageCtrl', [ '$scope', '$http', 'UserService',
		'CandidateService',
		function($scope, $http, UserService, CandidateService) {
			console.log("===================Manage Controller Loaded======================");
			// fetch all candidates
			CandidateService.getAllCandidate().then(function(response) {
				$scope.candidates = response.data;
				console.log("PRINTING ALL CANDIDATES LIST ")
				console.log($scope.candidates);
			})

			// sorting

			$scope.sortType = 'firstName';
			$scope.sortReverse = false;
			$scope.searchFilter = '';

			$scope.searchFilter = '';

		} ]);