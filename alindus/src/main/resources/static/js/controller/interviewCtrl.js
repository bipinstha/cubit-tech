myApp.controller('InterviewCtrl', [ '$scope', '$http','$routeParams', 'UserService',
		'CandidateService','InterviewService',
		function($scope, $http, $routeParams, UserService, CandidateService, InterviewService) {
			console.log("LOADED******Interview Controller");
			
			$scope.interview_id = $routeParams.id;
			console.log($scope.interview_id);
			//fetch interview by id
			
			InterviewService.findInterviewById($scope.interview_id).then(function(response){
				$scope.interview = response.data;
			})

			// sorting

			$scope.sortType = 'firstName';
			$scope.sortReverse = false;
			$scope.searchFilter = '';

			$scope.searchFilter = '';

		} ]);