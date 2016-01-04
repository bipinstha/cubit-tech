myApp.controller('CandidateCtrl', [ '$scope', '$http', 'UserService',
		'CandidateService',
		function($scope, $http, UserService, CandidateService) {

			console.log("======================Candidate Controller Loaded================");
			$scope.success = false;
			$scope.candidate = {};

			UserService.getUnApprovedUsers().then(function(response) {
				$scope.technologyList = response.data;
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
			
		} ]);