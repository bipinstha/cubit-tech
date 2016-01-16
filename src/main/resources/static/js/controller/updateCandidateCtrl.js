myApp.controller('updateCandidateCtrl', [ '$scope', '$http', 'UserService',
		'CandidateService', '$routeParams',
		function($scope, $http, UserService, CandidateService, $routeParams) {

			console.log("====================== Update Candidate Controller Loaded================");
			
			$scope.email = $routeParams.email;
			console.log($routeParams.email);
			$scope.success = false;
			
			UserService.getUnApprovedUsers().then(function(response) {
				$scope.technologyList = response.data;
			})
			
			CandidateService.findCandidateByEmail($scope.email).then(function(response) {
				$scope.candidate = response;
			})
			
			$scope.addcandidate = function (candidate) {
				
				console.log("========Candidate==========");
				console.log(candidate);
				
				CandidateService.addCandidate(candidate).then(function(response){
					console.log("++++++++++++++++++Response++++++++++++++++++")
					console.log(response);
					$scope.candidate = response;
					$scope.success = true;
				})
			}
			
			$scope.addTechnology = function() {
				if ($scope.newTechnology.name != "") {
					$scope.listOfTechnologies.push(angular
							.copy($scope.newTechnology));
					$scope.newTechnology.name = "";
				}
			}
			
			$scope.updateCandidate = function (candidate) {
				console.log("Candidate on update");
				console.log(candidate);
				CandidateService.updateCandidate(candidate).then(function (response){
					$scope.candidate = response.data;
					$scope.success = true;
				},function (errResponse){
					$scope.errMessage = errResponse.data.message;
					$scope.error = true;
				})
			}
			
		} ]);