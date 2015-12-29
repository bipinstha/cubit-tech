'use strict';

myApp.factory('UserService', [ '$http', '$q', function($http, $q) {

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
				  url: '/secure/user/unapproved',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then (function (response) {
					console.log("UnApproved Users::");
					console.log(response);
				return response;
			},function (errResponse) {
				console.log('Error while fetching un approved users..');
		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
				return $q.reject(errResponse);
			})
		},
		getDisabledUsers: function () {
			return $http({
				  method: 'GET',
				  url: '/secure/user/enablefalse',
				  data: '',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then (function (response) {
					console.log("Disabled Users::");
					console.log(response);
				return response;
			},function (errResponse) {
				console.log('Error while fetching disabled users..');
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
		removeUserByEmail: function (email){
			return $http.post('/secure/user/'+email+'/remove')
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
		},
		getListOfRoles: function (){
			console.log("Getting list of Roles::");
			return $http({
				  method: 'GET',
				  url: '/secure/user/all/roles',
				  data:'',
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(
                  function(response){
                	  console.log("ROles:");
                	  console.log(response);
                      return response;
                  }, 
                  function(errResponse){
                  	console.log('Error while fetching list of Roles..');
      		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
      				return $q.reject(errResponse);
                  }
          )
		},
		approveUser: function (user) {
			console.log("Approving User");
			console.log(user);
			return $http({
				  method: 'POST',
				  url: '/secure/user/approve/',
				  data: user,
				  headers: {
					  'Accept':'application/json; charset=utf-8',
					  'Content-Type':'application/json; charset=utf-8'
				  }
				}).then(
                function(response){
              	  console.log(response);
                    return response;
                }, 
                function(errResponse){
                	console.log('Error while approving user..');
    		        console.log({ message: errResponse.data.message, status: errResponse.data.status}); 
    				return $q.reject(errResponse);
                }
        )
		}
	}

} ]);