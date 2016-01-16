myApp.controller('CandidateCtrl', [ '$scope', '$http', '$location', 'UserService',
		'CandidateService', 'AlertService',
		function($scope, $http, $location, UserService, CandidateService, AlertService) {

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
					if(response.status == 500){
						$scope.error = true;
						$scope.errorMessage = JSON.parse(response.message);
						console.log(response.message);
					} else {
						$scope.candidate = response;
						AlertService.setSuccessMsg("Candidate added successfully.");
						console.log(AlertService.isSuccess());
						$location.path('/manage');
					}
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