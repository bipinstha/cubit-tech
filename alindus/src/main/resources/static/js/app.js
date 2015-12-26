'use strict';


var myApp = angular.module('cubicApp', [ 'ngRoute', 'ui.bootstrap','ngCookies','ngReallyClickModule']);

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


angular.module('ngReallyClickModule', ['ui.bootstrap'])
  .directive('ngReallyClick', ['$modal',
    function($modal) {

      var ModalInstanceCtrl = function($scope, $modalInstance) {
        $scope.ok = function() {
          $modalInstance.close();
        };

        $scope.cancel = function() {
          $modalInstance.dismiss('cancel');
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

            var modalInstance = $modal.open({
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