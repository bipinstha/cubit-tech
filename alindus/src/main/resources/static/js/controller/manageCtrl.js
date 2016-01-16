myApp.controller('ManageCtrl', [ '$scope', '$http', '$location', '$window', 'UserService',
		'CandidateService', 'AlertService',
		function($scope, $http, $location, $window, UserService, CandidateService, AlertService) {
			console.log("===================Manage Controller Loaded======================");
			// fetch all candidates
			CandidateService.getAllCandidate().then(function(response) {
				$scope.candidates = response.data;
				console.log("PRINTING ALL CANDIDATES LIST ")
				console.log($scope.candidates);
			})

			console.log("----hasAlert-----");
			console.log("=====Check Alert======");
			console.log(AlertService.isSuccess() + " " + AlertService.isError());
			if(AlertService.isSuccess() || AlertService.isError()){
				$scope.successMsg = AlertService.getSuccessMsg();
				$scope.success = AlertService.isSuccess();
				$scope.errorMsg = AlertService.getErrorMsg();
				$scope.error = AlertService.isError();
				AlertService.reset();
			}
			// sorting

			$scope.sortType = 'firstName';
			$scope.sortReverse = false;
			$scope.searchFilter = '';

			$scope.searchFilter = '';

			
			$scope.delete_candidate = function (email) {
				console.log("candidateToDelete: " + email);
				CandidateService.removeCandidateByEmail(email).then(function(response){
					console.log("++++++++++++++++++Delete Candidate Response++++++++++++++++++")
					console.log(response);
					if(response.status == 500){
						AlertService.setSuccessMsg(response.error);
					}else{
						AlertService.setSuccessMsg("Candidate deleted successfully.");
					}
					window.location.reload();
//					 $window.location.href = $location.path();
//					$location.path('/manage');
//					$location.replace();
					console.log(AlertService.isSuccess());
				});
			}
		} ]);