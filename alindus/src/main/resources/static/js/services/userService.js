'use strict';

myApp.factory('UserService', [ '$http', '$q', function($http, $q) {
	var config = {
			headers: {
				  'Accept':'application/json; charset=utf-8',
				  'Content-Type':'application/json; charset=utf-8'
			  }
	};
	var deferObject;
	return {
		getAllUser : function() {
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
		findUserByEmail: function (email) {
			return $http({
				  method: 'GET',
				  url: '/secure/user/'+email+'/find',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then (function (response) {
				return response.data;
			},function (errResponse) {
				console.log('Error while fetching users by email..'+email);
		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
				return $q.reject(errResponse);
			})
		},
		getUnApprovedUsers: function () {
			return $http({
				  method: 'GET',
				  url: '/secure/user/enablefalse',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then (function (response) {
				return response;
			},function (errResponse) {
				console.log('Error while fetching un approved users..');
		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
				return $q.reject(errResponse);
			})
		},
		currentUser: function () {
			return $http({
				  method: 'GET',
				  url: '/secure/user/mydetail',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then (function (response) {
					console.log(response);
					return response;
			},function (errResponse) {
				console.log('Error while fetching current user..');
		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
				return $q.reject(errResponse);
			})
		},
		updateUser: function (user) {
			return $http({
				  method: 'POST',
				  url: '/secure/user/update',
				  data: user,
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(
                    function(response){
                        return response;
                    }, 
                    function(errResponse){
                    	console.log('Error while updating current user..');
        		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
        				return $q.reject(errResponse);
                    }
            )
		},
		addUser: function (user) {
			return $http.post('/secure/user/add', user)
            .then(
                    function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while creating user');
                        return $q.reject(errResponse);
                    }
            );
		},
		changePassword: function (changePassword) {
			console.log(changePassword);
			return $http({
				  method: 'POST',
				  url: '/secure/user/password/change',
				  data: changePassword,
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(
                    function(response){
                        return response;
                    }, 
                    function(errResponse){
                    	console.log('Error while changing password..');
        		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
        				return $q.reject(errResponse);
                    }
            )
		}
	}

} ]);