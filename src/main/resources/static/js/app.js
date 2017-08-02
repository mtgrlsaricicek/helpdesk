
var app = angular.module('app', ['ngRoute']);

app.factory('CsrfTokenInterceptorService', ['$q',
    function CsrfTokenInterceptorService($q) {

        // Private constants.
        var CSRF_TOKEN_HEADER = 'X-CSRF-TOKEN',
            HTTP_TYPES_TO_ADD_TOKEN = ['DELETE', 'POST', 'PUT'];

        // Private properties.
        var token;

        // Public interface.
        var service = {
            response: onSuccess,
            responseError: onFailure,
            request: onRequest,
        };

        return service;

        // Private functions.
        function onFailure(response) {
            if (response.status === 403) {
                console.log('Request forbidden. Ensure CSRF token is sent for non-idempotent requests.');
            }

            return $q.reject(response);
        }

        function onRequest(config) {
            if (HTTP_TYPES_TO_ADD_TOKEN.indexOf(config.method.toUpperCase()) !== -1) {
                config.headers[CSRF_TOKEN_HEADER] = token;
            }

            return config;
        }

        function onSuccess(response) {
            var newToken = response.headers(CSRF_TOKEN_HEADER);

            if (newToken) {
                token = newToken;
            }

            return response;
        }
    }]);

app.config(function($routeProvider,$httpProvider) {
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
                    controller  : 'mainController'
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


app.controller('mainController', function($rootScope, $scope, $http, $location) {


      var authenticate = function(credentials, callback) {

        var headers = credentials ? {authorization : "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};

        $http.get('user', {headers : headers}).success(function(data) {
          if (data.name) {
            $rootScope.authenticated = true;
          } else {
            $rootScope.authenticated = false;
          }
          callback && callback();
        }).error(function() {
          $rootScope.authenticated = false;
          callback && callback();
        });

      }

      authenticate();
      $scope.credentials = {};
      $scope.login = function() {
          authenticate($scope.credentials, function() {
            if ($rootScope.authenticated) {
              $location.path("/home");
              $scope.error = false;
            } else {
              $location.path("/login");
              $scope.error = true;
            }
          });
      };

      $scope.logout = function() {
        $http.post('logout').success(function() {
          $rootScope.authenticated = false;
          $location.path("/home");
        }).error(function(data) {
          $rootScope.authenticated = false;
        });
      }



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