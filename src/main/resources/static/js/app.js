
var app = angular.module('app', ['ngRoute','ngMaterial','ngMessages']);

app.factory('sidenavCloseService',function($mdSidenav){
    return {
        closeIfOpen : function(){
            var sidenav = $mdSidenav('left');
            if(sidenav.isOpen()){
                sidenav.close();
            }
        }
    }
});

app.config(function($routeProvider,$httpProvider) {
       $routeProvider
           .when('/', {
               templateUrl : 'commonPages/home.html',
               controller  : 'navigation'
           })
           .when('/home', {
               templateUrl : 'commonPages/home.html',
               controller  : 'navigation'
                           })
           .when('/about', {
               templateUrl : 'commonPages/about.html',
               controller  : 'aboutController'
           })

           // route for the login page
           .when('/login', {
               templateUrl : 'commonPages/login.html',
               controller  : 'navigation'
           })
           .when('/helpRequest', {
               templateUrl : 'commonPages/helpRequest.html',
               controller  : 'helpRequestController'
           })
           .when('/queryHelpRequest', {
               templateUrl : 'commonPages/helpQueryPage.html',
               controller  : 'helpQueryController'
           }).when('/userRegister', {
               templateUrl : 'commonUserPages/userRegister.html',
               controller  : 'userRegisterController'
           });
           $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
           $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
           $httpProvider.interceptors.push('CsrfTokenInterceptorService');
});




app.controller('aboutController',['$scope','sidenavCloseService',
                        function($scope,sidenavCloseService) {
    sidenavCloseService.closeIfOpen();
    $scope.message = 'This page is in the process of preparation';
}]);

app.controller('helpRequestController',['$scope','sidenavCloseService',
                        function($scope,sidenavCloseService) {
     sidenavCloseService.closeIfOpen();
     $scope.message = 'This page is in the process of preparation';
 }]);

app.controller('helpQueryController',['$scope','sidenavCloseService',
                        function($scope,sidenavCloseService) {
     sidenavCloseService.closeIfOpen();
     $scope.message = 'This page is in the process of preparation';
 }]);