
var app = angular.module('app', ['ngRoute']);

app.config(function($routeProvider,$httpProvider) {
            $routeProvider

                // route for the home page
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




app.controller('aboutController', function($scope) {
    $scope.message = 'This page is in the process of preparation';
});

app.controller('helpRequestController', function($scope) {
     $scope.message = 'This page is in the process of preparation';
 });

app.controller('helpQueryController', function($scope) {
     $scope.message = 'This page is in the process of preparation';
 });