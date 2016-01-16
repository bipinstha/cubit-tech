'use strict';
/**
 * author: bipin
 */
myApp.factory('AlertService', function() {
	var successMsg = {}, success = false, errorMsg = {}, error = false;
	return {
		getSuccessMsg : function() {
			return successMsg;
		},
		setSuccessMsg : function(value) {
			successMsg = value;
			success = true;
		},
		isSuccess : function(){
			return success;
		},
		getErrorMsg : function() {
			return errorMsg;
		},
		setErrorMsg : function(value) {
			errorMsg = value;
			error = true;
		},
		isError : function(){
			return error;
		},
		reset : function() {
			successMsg = {};
			errorMsg = {};
			success = false;
			error = false;
		}
//		hasAlert : function(){
//			return (isSuccess() | isError()).then(function(alert){
//				return alert;
//			});
//		}
	}
});