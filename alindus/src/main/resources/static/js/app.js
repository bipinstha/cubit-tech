'use strict';


var myApp = angular.module('cubicApp', [ 'ngRoute', 'ui.bootstrap']);

myApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.
	when('/', {
		templateUrl : '/static/pages/dashboard.html',
		controller : 'dashboardCtrl'
	}).
	when('/reports', {
		templateUrl : '/static/pages/reports.html',
		controller : 'reportCtrl'
	}).
	when('/admin',{
		templateUrl : '/static/pages/admin.html',
		controller : 'AdminCtrl'
	}).
	when('/user/:email',{
		templateUrl: 'static/pages/user.html',
		controller : 'UserCtrl'
	}).
	when('/profile',{
		templateUrl: 'static/pages/profile.html',
		controller : 'ProfileCtrl'
	}).
	otherwise({
		redirectTo : '/'
	})
} ])

myApp.directive('test',function () {
	template:"Test data"
})