app.controller('navigation',['$rootScope','$scope','$http','$location',
                             '$mdSidenav','$timeout','sidenavCloseService',
                function($rootScope,$scope,
                        $http,$location,$mdSidenav,$timeout,sidenavCloseService) {

        sidenavCloseService.closeIfOpen();

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
      }

       $scope.logout = function() {
              $http.post('logout').success(function() {
                $rootScope.authenticated = false;
                $location.path("/");
              }).error(function(data) {
                $rootScope.authenticated = false;
              });
            }

            $scope.toggleLeft = buildDelayedToggler('left');

                   /**
                    * Supplies a function that will continue to operate until the
                    * time is up.
                    */
                   function debounce(func, wait, context) {
                     var timer;

                     return function debounced() {
                       var context = $scope,
                           args = Array.prototype.slice.call(arguments);
                       $timeout.cancel(timer);
                       timer = $timeout(function() {
                         timer = undefined;
                         func.apply(context, args);
                       }, wait || 10);
                     };
                   }

                   /**
                    * Build handler to open/close a SideNav; when animation finishes
                    * report completion in console
                    */
                   function buildDelayedToggler(navID) {
                     return debounce(function() {
                       // Component lookup should always be available since we are not using `ng-if`
                       $mdSidenav(navID)
                         .toggle();
                     }, 200);
                   }

                   function buildToggler(navID) {
                     return function() {
                       // Component lookup should always be available since we are not using `ng-if`
                       $mdSidenav(navID)
                         .toggle();
                     };
                   }

       $scope.go = function(path){
            sidenavCloseService.closeIfOpen();
            $location.path(path);
       }


}]);