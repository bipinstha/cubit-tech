'use strict';

var myApp = angular.module('cubicApp', [ 'ngRoute', 'ui.bootstrap','ngCookies','ngReallyClickModule','ui.mask']);

myApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.
	when('/', {
		templateUrl : '/static/pages/dashboard.html',
		controller : 'DashboardCtrl'
	}).
	when('/reports', {
		templateUrl : '/static/pages/reports.html',
		controller : 'ReportCtrl'
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
	when('/approve/user/:email',{
		templateUrl: 'static/pages/approveUser.html',
		controller : 'ApproveUserCtrl'
	}).
	when('/manage',{
		templateUrl: 'static/pages/manage.html',
		controller : 'ManageCtrl'
	}).
	when('/interview/:id',{
		templateUrl: 'static/pages/interview.html',
		controller : 'InterviewCtrl'
	}).
	when('/add_candidate',{
		templateUrl: 'static/pages/addCandidate.html',
		controller : 'CandidateCtrl'
	}).
	otherwise({
		redirectTo : '/'
	})
} ])


angular.module('ngReallyClickModule', ['ui.bootstrap'])
  .directive('ngReallyClick', ['$uibModal',
    function($uibModal) {

      var ModalInstanceCtrl = function($scope, $uibModalInstance) {
        $scope.ok = function() {
        	$uibModalInstance.close();
        };

        $scope.cancel = function() {
        	$uibModalInstance.dismiss('cancel');
        };
      };

      return {
        restrict: 'A',
        scope:{
          ngReallyClick:"&",
          item:"="
        },
        link: function(scope, element, attrs) {
          element.bind('click', function() {
            var message = attrs.ngReallyMessage || "Are you sure ?";

            var modalHtml = '<div class="modal-body">' + message + '</div>';
            modalHtml += '<div class="modal-footer"><button class="btn btn-primary" ng-click="ok()">Yes</button><button class="btn btn-warning" ng-click="cancel()">Cancel</button></div>';

            var modalInstance = $uibModal.open({
              template: modalHtml,
              controller: ModalInstanceCtrl
            });

            modalInstance.result.then(function() {
              scope.ngReallyClick({item:scope.item}); //raise an error : $digest already in progress
            }, function() {
              //Modal dismissed
            });
            //*/
            
          });

        }
      }
    }
  ]);