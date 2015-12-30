myApp.controller('InterviewCtrl', [ '$scope', '$http','$routeParams', 'UserService',
		'CandidateService','InterviewService',
		function($scope, $http, $routeParams, UserService, CandidateService, InterviewService) {
			console.log("LOADED******Interview Controller");
			
			$scope.interview_id = $routeParams.id;
			console.log($scope.interview_id);
			//fetch interview by id
			
			InterviewService.findInterviewById($scope.interview_id).then(function(response){
				$scope.interview = response.data;
				//used to update interview
				$scope.interviewDTO = response.data;
			})

			// sorting

			$scope.sortType = 'firstName';
			$scope.sortReverse = false;
			$scope.searchFilter = '';

			$scope.searchFilter = '';
			
			$scope.updateFlag = false;
			
			$scope.updateSave = function () {
				if ($scope.updateFlag) {
					$scope.interview = $scope.interviewDTO;
					InterviewService.updateInterview($scope.interview).then(function(response){
						console.log("Update Response::");
						console.log(response);
					})
					$scope.updateFlag = !$scope.updateFlag;
				} else {
					$scope.updateFlag = !$scope.updateFlag;
				}
			}
			$scope.displayUser = function () {
				console.log($scope.interviewDTO.candidate);
			}

		} ]);