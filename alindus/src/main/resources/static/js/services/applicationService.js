'use strict';

//Application Service use to fetch all users and clients list required for application

myApp.factory('ApplicationService',['$http', '$q', function ($http, $q,CandidateService,UserService) {
	return {
		getAllUser : function () {
			return  $http({
				  method: 'GET',
				  url: '/secure/user/enabletrue',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(function(response) {
					
				return response;
			}, function(errResponse) {
				console.log(errResponse);
				console.log(errResponse.data);
				console.error('Error while fetching users '+errResponse);
				return $q.reject(errResponse);
			})
		},
		getAllCandidate: function () {
			return  $http({
				  method: 'GET',
				  url: '/secure/candidate/findall',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(function(response) {
					
				return response;
			}, function(errResponse) {
				console.log(errResponse);
				console.log(errResponse.data);
				console.error('Error while fetching candidates '+errResponse);
				return $q.reject(errResponse);
			})
		},
		getAllClients: function () {
			return  $http({
				  method: 'GET',
				  url: '/secure/client/all',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(function(response) {
				return response;
			}, function(errResponse) {
				console.log(errResponse);
				console.log(errResponse.data);
				console.error('Error while fetching all clients '+errResponse);
				return $q.reject(errResponse);
			})
		},
		getAllVendors: function () {
			return  $http({
				  method: 'GET',
				  url: '/secure/vendor/all',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(function(response) {
				return response;
			}, function(errResponse) {
				console.log(errResponse);
				console.log(errResponse.data);
				console.error('Error while fetching all vendors '+errResponse);
				return $q.reject(errResponse);
			})
		},
		getAllTechnologies: function () {
			return  $http({
				  method: 'GET',
				  url: '/secure/technologie/all',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(function(response) {
				return response;
			}, function(errResponse) {
				console.log(errResponse);
				console.log(errResponse.data);
				console.error('Error while fetching all technologies '+errResponse);
				return $q.reject(errResponse);
			})
		},
		getAllRounds: function () {
			return  $http({
				  method: 'GET',
				  url: '/secure/round/all',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(function(response) {
				return response;
			}, function(errResponse) {
				console.log(errResponse);
				console.log(errResponse.data);
				console.error('Error while fetching all rounds '+errResponse);
				return $q.reject(errResponse);
			})
		},
		getAllInterviewType: function () {
			return  $http({
				  method: 'GET',
				  url: '/secure/interviewtype/all',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(function(response) {
				return response;
			}, function(errResponse) {
				console.log(errResponse);
				console.log(errResponse.data);
				console.error('Error while fetching interview types '+errResponse);
				return $q.reject(errResponse);
			})
		},
		getAllStatus: function () {
			return  $http({
				  method: 'GET',
				  url: '/secure/all/status',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(function(response) {
				return response;
			}, function(errResponse) {
				console.log(errResponse);
				console.log(errResponse.data);
				console.error('Error while fetching status '+errResponse);
				return $q.reject(errResponse);
			})
		}
	}
}])