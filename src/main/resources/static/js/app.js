
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
                    templateUrl : 'pages/home.html',
                    controller  : 'navigation'
                })
                .when('/home', {
                    templateUrl : 'pages/home.html',
                    controller  : 'navigation'
                                })
                .when('/about', {
                    templateUrl : 'pages/about.html',
                    controller  : 'aboutController'
                })

                // route for the login page
                .when('/login', {
                    templateUrl : 'pages/login.html',
                    controller  : 'navigation'
                })
                .when('/helpRequest', {
                    templateUrl : 'pages/helpRequest.html',
                    controller  : 'helpRequestController'
                })
                .when('/queryHelpRequest', {
                    templateUrl : 'pages/helpQueryPage.html',
                    controller  : 'helpQueryController'
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