'use strict';

myApp.factory('InterviewService', [ '$http', '$q', function($http, $q) {
	return {
		getAllInterview : function() {
			return  $http({
				  method: 'GET',
				  url: '/secure/interview/all',
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
				console.error('Error while fetching interviews '+errResponse);
				return $q.reject(errResponse);
			})
		},
		findInterviewById: function (id) {
			return $http({
				  method: 'GET',
				  url: '/secure/interview/'+id,
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then (function (response) {
				return response.data;
			},function (errResponse) {
				console.log('Error while fetching interview by id..'+email);
		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
				return $q.reject(errResponse);
			})
		},
		updateInterview: function (interview) {
			return $http({
				  method: 'POST',
				  url: '/secure/interview/update',
				  data: interview,
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
		addInterview: function (interview) {
			return $http.post('/secure/interview/add', interview)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while creating interview');
                        return $q.reject(errResponse);
                    }
            );
		},
		removeInterviewById: function (id){
			return $http.post('/secure/interview/remove'+id)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while removing interview');
                        return $q.reject(errResponse);
                    }
            );
		},
		updateInterviewRound: function(ir) {
			return $http({
				  method: 'POST',
				  url: '/secure/interview/update/interviewround',
				  data: ir,
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(
                  function(response){
                      return response;
                  }, 
                  function(errResponse){
                  	console.log('Error while updating interview round..');
      		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
      				return $q.reject(errResponse);
                  }
          )
		},
		getInterviewRoundsByInterviewId: function (id) {
			return  $http({
				  method: 'GET',
				  url: '/secure/interview/ir/all/'+id,
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
				console.error('Error while fetching interview rounds by interview id '+errResponse);
				return $q.reject(errResponse);
			})
		}
	}

} ]);