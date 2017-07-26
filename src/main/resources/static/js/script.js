
var app = angular.module('app', ['ngRoute']);

    app.config(function($routeProvider) {
            $routeProvider

                // route for the home page
                .when('/', {
                    templateUrl : 'pages/home.html',
                    controller  : 'mainController'
                })
                .when('/home', {
                    templateUrl : 'pages/home.html',
                    controller  : 'mainController'
                                })
                .when('/about', {
                    templateUrl : 'pages/about.html',
                    controller  : 'aboutController'
                })

                // route for the login page
                .when('/login', {
                    templateUrl : 'pages/login.html',
                    controller  : 'loginController'
                })
                .when('/helpRequest', {
                    templateUrl : 'pages/helpRequest.html',
                    controller  : 'helpRequestController'
                })
                .when('/queryHelpRequest', {
                    templateUrl : 'pages/helpQueryPage.html',
                    controller  : 'helpQueryController'
                });
        });


app.controller('mainController', function($scope) {

        // create a message to display in our view
        $scope.message = 'This page is in the process of preparation !';
    });

app.controller('mainController', function($scope) {
            // create a message to display in our view
    $scope.message = 'This page is in the process of preparation!';
});

app.controller('aboutController', function($scope) {
    $scope.message = 'This page is in the process of preparation';
});

app.controller('loginController', function($scope) {
    $scope.message = 'This page is in the process of preparation';
});

app.controller('helpRequestController', function($scope) {
     $scope.message = 'This page is in the process of preparation';
 });

app.controller('helpQueryController', function($scope) {
     $scope.message = 'This page is in the process of preparation';
 });