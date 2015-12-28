myApp.controller('"CandidateListCtrl"', [ '$scope', '$http', 'UserService',
		'CandidateService',
		function($scope, $http, UserService, CandidateService) {

			console.log("LOADED******Candidate Controller");
			
			$scope.sortType = 'firstName';
			$scope.sortReverse = false;
			$scope.searchFilter = '';

		} ]);