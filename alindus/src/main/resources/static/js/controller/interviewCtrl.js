myApp.controller('InterviewCtrl', [
		'$scope',
		'$http',
		'$routeParams',
		'UserService',
		'CandidateService',
		'InterviewService','ApplicationService',
		function($scope, $http, $routeParams, UserService, CandidateService,
				InterviewService,ApplicationService) {
			console.log("LOADED******Interview Controller");

			$scope.interview_id = $routeParams.id;
			console.log($scope.interview_id);
			// fetch interview by id

			InterviewService.findInterviewById($scope.interview_id).then(
					function(response) {
						$scope.interview = response.data;
						// used to update interview
						$scope.interviewDTO = angular.copy($scope.interview);
					})

			ApplicationService.getAllCandidate().then(function(response) {
				$scope.listOfCandidates = response.data;
				console.log(response.data);
			})

			// sorting

			$scope.sortType = 'firstName';
			$scope.sortReverse = false;
			$scope.searchFilter = '';

			$scope.searchFilter = '';

			$scope.updateFlag = false;

			$scope.updateSave = function() {
				if ($scope.updateFlag) {
					console.log("updating interview....");
					console.log($scope.interviewDTO);
					$scope.interview = angular.copy($scope.interviewDTO);
					console.log($scope.interview);
					InterviewService.updateInterview($scope.interview).then(
							function(response) {
								console.log("Update Response::");
								console.log(response);
							})
					$scope.updateFlag = !$scope.updateFlag;
				} else {
					$scope.updateFlag = !$scope.updateFlag;
				}
			}
			$scope.displayUser = function() {
				console.log("Interview DTO Candidate");
				console.log($scope.interviewDTO.candidate);
				console.log($scope.interview.candidate);
			}
			$scope.resetUpdate = function () {
				$scope.interviewDTO = angular.copy($scope.interview);
				$scope.updateFlag = false;
			}
			
			$scope.newClient = {name: ""};
			
			$scope.addClient = function () {
				if ($scope.newClient.name != "") {
					$scope.listOfClients.push($scope.newClient);
					//$scope.interview.client = $scope.newClient;
				}
			}
			$scope.addVendor = function () {
				if ($scope.newVendor.name != "") {
					$scope.listOfVendors.push($scope.newVendor);
					//$scope.interview.client = $scope.newClient;
				}
			}
			
		} ]);