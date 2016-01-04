'use strict';

myApp.factory('CandidateService', [ '$http', '$q', function($http, $q) {

	return {
		getAllCandidate : function() {
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
		findCandidateByEmail: function (email) {
			return $http({
				  method: 'GET',
				  url: '/secure/candidate/'+email+'/find',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then (function (response) {
				return response.data;
			},function (errResponse) {
				console.log('Error while fetching candidate by email..'+email);
		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
				return $q.reject(errResponse);
			})
		},
		findCandidateById: function (id) {
			return $http({
				  method: 'GET',
				  url: '/secure/candidate/findone/'+id,
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then (function (response) {
				return response.data;
			},function (errResponse) {
				console.log('Error while fetching candidate by email..'+email);
		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
				return $q.reject(errResponse);
			})
		},
		updateCandidate: function (candidate) {
			return $http({
				  method: 'POST',
				  url: '/secure/candidate/update',
				  data: candidate,
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(
                    function(response){
                        return response;
                    }, 
                    function(errResponse){
                    	console.log('Error while updating candidate..');
        		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
        				return $q.reject(errResponse);
                    }
            )
		},
		addCandidate: function (candidate) {
			return $http.post('/secure/candidate/add', candidate)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while creating candidate');
                        return $q.reject(errResponse);
                    }
            );
		},
		removeCandidateByEmail: function (email){
			return $http.post('/secure/candidate/'+email+'/remove')
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while creating user');
                        return $q.reject(errResponse);
                    }
            );
		}
	}

} ]);